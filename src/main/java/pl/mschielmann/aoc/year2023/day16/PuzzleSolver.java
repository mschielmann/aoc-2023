package pl.mschielmann.aoc.year2023.day16;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

@Slf4j
class PuzzleSolver
{
    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        Contraption contraption = createContraption(puzzleInput);
        Tile startingTile = new Tile(0, -1, TileType.EMPTY);
        List<LightBeam> beams = new ArrayList<>();
        beams.add(new LightBeam(startingTile, Direction.RIGHT, new HashMap<>()));
        while (!beams.stream().allMatch(beam -> beam.isLoopedOrOut))
        {
            LightBeam beam = beams.remove(0);
            beams.addAll(beam.moveAndReturnResult(contraption));
        }

        return beams.stream()
                .map(beam -> beam.visitedTiles.values())
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .distinct()
                // fake starting tile
                .count() - 1;
    }

    long solvePartTwo()
    {
        Contraption contraption = createContraption(puzzleInput);
        long result = 0;
        List<Tile> startingTiles = new ArrayList<>();
        int maxRow = contraption.layout.size();
        int maxColumn = contraption.layout.get(0).size();
        for (int column = 0; column < maxColumn; column++) {
            startingTiles.add(new Tile(-1, column, TileType.EMPTY));
            startingTiles.add(new Tile(maxRow, column, TileType.EMPTY));
        }
        for (int row = 0; row < maxRow; row++) {
            startingTiles.add(new Tile(row, -1, TileType.EMPTY));
            startingTiles.add(new Tile(row, maxColumn, TileType.EMPTY));
        }
        for (Tile tile : startingTiles)
        {
            List<LightBeam> beams = new ArrayList<>();
            beams.add(new LightBeam(tile, Direction.RIGHT, new HashMap<>()));
            beams.add(new LightBeam(tile, Direction.DOWN, new HashMap<>()));
            beams.add(new LightBeam(tile, Direction.LEFT, new HashMap<>()));
            beams.add(new LightBeam(tile, Direction.UP, new HashMap<>()));
            while (!beams.stream().allMatch(beam -> beam.isLoopedOrOut))
            {
                LightBeam beam = beams.remove(0);
                beams.addAll(beam.moveAndReturnResult(contraption));
            }

            long possibleResult = beams.stream()
                    .map(beam -> beam.visitedTiles.values())
                    .flatMap(Collection::stream)
                    .flatMap(Collection::stream)
                    .distinct()
                    // fake starting tile
                    .count() - 1;
            result = Math.max(result, possibleResult);
        }

        return result;
    }

    private Contraption createContraption(String puzzleInput)
    {
        Map<Integer, Map<Integer, Tile>> map = new HashMap<>();
        int rowCounter = 0;
        for (String line : puzzleInput.lines().toList())
        {
            map.put(rowCounter, new HashMap<>());
            for (int columnIndex = 0; columnIndex < line.length(); columnIndex++)
            {
                map.get(rowCounter).put(columnIndex, new Tile(rowCounter, columnIndex, TileType.findByLabel(line.charAt(columnIndex))));
            }
            rowCounter++;
        }
        return new Contraption(map);
    }

    private record Tile(int row, int column, TileType type)
    {
    }

    private record Contraption(Map<Integer, Map<Integer, Tile>> layout)
    {
        private static final Map<Integer, Tile> EMPTY_COLUMN = new HashMap<>();

        private Optional<Tile> getTileAt(int row, int column)
        {
            return Optional.ofNullable(layout.getOrDefault(row, EMPTY_COLUMN).get(column));
        }
    }

    private static class LightBeam
    {
        private Tile currentTile;
        private Direction direction;
        private boolean isLoopedOrOut;
        private final Map<Direction, Set<Tile>> visitedTiles;

        private LightBeam(Tile currentTile, Direction direction, Map<Direction, Set<Tile>> visitedTiles)
        {
            this.currentTile = currentTile;
            this.direction = direction;
            this.visitedTiles = visitedTiles;
            Arrays.stream(Direction.values()).forEach(d -> visitedTiles.putIfAbsent(d, new HashSet<>()));
        }

        private List<LightBeam> moveAndReturnResult(Contraption contraption)
        {
            if (isLoopedOrOut)
            {
                return List.of(this);
            }
            Integer nextTileRow = direction.rowMovement.apply(currentTile.row());
            Integer nextTileColumn = direction.columnMovement.apply(currentTile.column());
            Optional<Tile> nextTileOptional = contraption.getTileAt(nextTileRow, nextTileColumn);
            if (nextTileOptional.isEmpty() || visitedTiles.get(direction).contains(currentTile))
            {
                visitedTiles.get(direction).add(currentTile);
                isLoopedOrOut = true;
                currentTile = null;
                return List.of(this);
            }
            visitedTiles.get(direction).add(currentTile);
            Tile nextTile = nextTileOptional.get();
            currentTile = nextTile;
            if (direction.isVertical && nextTile.type().equals(TileType.HORIZONTAL_SPLIT))
            {
                direction = Direction.LEFT;
                return List.of(this, new LightBeam(nextTile, Direction.RIGHT, visitedTiles));
            } else if (!direction.isVertical && nextTile.type().equals(TileType.VERTICAL_SPLIT))
            {
                direction = Direction.UP;
                return List.of(this, new LightBeam(nextTile, Direction.DOWN, visitedTiles));
            }

            switch (nextTile.type)
            {
                case NORTH_EAST_MIRROR -> direction = switch (direction)
                {
                    case UP -> Direction.LEFT;
                    case DOWN -> Direction.RIGHT;
                    case LEFT -> Direction.UP;
                    case RIGHT -> Direction.DOWN;
                };
                case NORTH_WEST_MIRROR -> direction = switch (direction)
                {
                    case UP -> Direction.RIGHT;
                    case DOWN -> Direction.LEFT;
                    case LEFT -> Direction.DOWN;
                    case RIGHT -> Direction.UP;
                };
                default ->
                {
                }
            }

            return List.of(this);
        }
    }

    private enum TileType
    {
        VERTICAL_SPLIT('|'),
        HORIZONTAL_SPLIT('-'),
        NORTH_WEST_MIRROR('/'),
        NORTH_EAST_MIRROR('\\'),
        EMPTY('.');

        private final char label;

        TileType(char label)
        {
            this.label = label;
        }

        private static TileType findByLabel(char label)
        {
            return Arrays.stream(values())
                    .filter(type -> type.label == label)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid tile type provided: " + label));
        }
    }

    private enum Direction
    {
        UP(true, row -> row - 1, column -> column),
        DOWN(true, row -> row + 1, column -> column),
        RIGHT(false, row -> row, column -> column + 1),
        LEFT(false, row -> row, column -> column - 1);

        private final boolean isVertical;
        private final UnaryOperator<Integer> rowMovement;
        private final UnaryOperator<Integer> columnMovement;

        Direction(boolean isVertical, UnaryOperator<Integer> rowMovement, UnaryOperator<Integer> columnMovement)
        {
            this.isVertical = isVertical;
            this.rowMovement = rowMovement;
            this.columnMovement = columnMovement;
        }
    }
}
