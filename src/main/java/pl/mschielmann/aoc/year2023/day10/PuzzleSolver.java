package pl.mschielmann.aoc.year2023.day10;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Slf4j
class PuzzleSolver
{
    private static final Map<Direction, Direction> NORTH_EAST_MAPPINGS = Map.of(
            Direction.NORTH, Direction.EAST,
            Direction.EAST, Direction.NORTH,
            Direction.SOUTH, Direction.WEST,
            Direction.WEST, Direction.SOUTH
    );
    private static final Map<Direction, Direction> NORTH_WEST_MAPPINGS = Map.of(
            Direction.NORTH, Direction.WEST,
            Direction.WEST, Direction.NORTH,
            Direction.SOUTH, Direction.EAST,
            Direction.EAST, Direction.SOUTH
    );
    private static final Map<Direction, Direction> SOUTH_WEST_MAPPINGS = Map.of(
            Direction.NORTH, Direction.EAST,
            Direction.EAST, Direction.NORTH,
            Direction.SOUTH, Direction.WEST,
            Direction.WEST, Direction.SOUTH
    );
    private static final Map<Direction, Direction> SOUTH_EAST_MAPPINGS = Map.of(
            Direction.NORTH, Direction.WEST,
            Direction.WEST, Direction.NORTH,
            Direction.SOUTH, Direction.EAST,
            Direction.EAST, Direction.SOUTH
    );

    private static final Map<PipeShape, Map<Direction, Direction>> SHAPE_TO_DIRECTION_MAPPINGS = Map.of(
            PipeShape.NORTH_EAST, NORTH_EAST_MAPPINGS,
            PipeShape.NORTH_WEST, NORTH_WEST_MAPPINGS,
            PipeShape.SOUTH_WEST, SOUTH_WEST_MAPPINGS,
            PipeShape.SOUTH_EAST, SOUTH_EAST_MAPPINGS
    );

    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        List<Pipe> pipesInLoop = findPipesInTheLoop();

        return Double.valueOf(Math.ceil((double) pipesInLoop.size() / 2)).longValue();
    }

    long solvePartTwo()
    {
        List<Pipe> pipesInLoop = findPipesInTheLoop();

        Pipe firstNonHorizontalPipeInLoop = pipesInLoop.stream()
                .filter(pipe -> !PipeShape.HORIZONTAL.equals(pipe.shape))
                .min(pipeSortingComparator())
                .orElseThrow(() -> new IllegalStateException("No not-horizontal pipes found."));

        Pipe currentPipe = findNextInLoop(firstNonHorizontalPipeInLoop, pipesInLoop);
        Direction insideLoopDirection = firstNonHorizontalPipeInLoop.columnCoordinate < currentPipe.columnCoordinate ? Direction.SOUTH : Direction.EAST;

        Set<Coordinates> coordinatesWithinLoop = new HashSet<>();
        do
        {
            insideLoopDirection = SHAPE_TO_DIRECTION_MAPPINGS.getOrDefault(currentPipe.shape, new HashMap<>())
                    .getOrDefault(insideLoopDirection, insideLoopDirection);
            markCoordinatesWithinLoop(insideLoopDirection, currentPipe, coordinatesWithinLoop, pipesInLoop);
            currentPipe = findNextInLoop(currentPipe, pipesInLoop);
            markCoordinatesWithinLoop(insideLoopDirection, currentPipe, coordinatesWithinLoop, pipesInLoop);
        } while (!currentPipe.equals(firstNonHorizontalPipeInLoop));

        return coordinatesWithinLoop.size();
    }

    private List<Pipe> findPipesInTheLoop()
    {
        Pipeline pipeline = Pipeline.buildUsing(puzzleInput);
        List<Pipe> pipesSurroundingStart = pipeline.getPipesSurroundingStart();

        return findAllPipesInTheLoopBasedOn(pipesSurroundingStart, pipeline);
    }

    private Pipe findNextInLoop(Pipe currentPipe, List<Pipe> pipesInLoop)
    {
        int currentIndex = pipesInLoop.indexOf(currentPipe);
        if (currentIndex == pipesInLoop.size() - 1)
        {
            return pipesInLoop.get(0);
        }
        return pipesInLoop.get(currentIndex + 1);
    }

    private void markCoordinatesWithinLoop(Direction inDirection, Pipe currentPipe, Set<Coordinates> inCoordinates, List<Pipe> pipesInLoop)
    {
        switch (inDirection)
        {
            case EAST -> addAllEastTo(currentPipe, inCoordinates, pipesInLoop);
            case NORTH -> addAllNorthTo(currentPipe, inCoordinates, pipesInLoop);
            case SOUTH -> addAllSouthTo(currentPipe, inCoordinates, pipesInLoop);
            case WEST -> addAllWestTo(currentPipe, inCoordinates, pipesInLoop);
        }
    }

    private PipeShape startPipeShape(Pipe previousPipe, Pipe nextPipe)
    {
        if (Math.abs(previousPipe.columnCoordinate - nextPipe.columnCoordinate) > 1)
        {
            return PipeShape.HORIZONTAL;
        } else if (Math.abs(previousPipe.rowCoordinate - nextPipe.rowCoordinate) > 1)
        {
            return PipeShape.VERTICAL;
        } else if (nextPipe.rowCoordinate > previousPipe.rowCoordinate && nextPipe.columnCoordinate > previousPipe.columnCoordinate)
        {
            return PipeShape.NORTH_EAST;
        } else if (nextPipe.rowCoordinate > previousPipe.rowCoordinate && nextPipe.columnCoordinate < previousPipe.columnCoordinate)
        {
            return PipeShape.NORTH_WEST;
        } else if (nextPipe.rowCoordinate < previousPipe.rowCoordinate && nextPipe.columnCoordinate > previousPipe.columnCoordinate)
        {
            return PipeShape.SOUTH_EAST;
        } else if (nextPipe.rowCoordinate < previousPipe.rowCoordinate && nextPipe.columnCoordinate < previousPipe.columnCoordinate)
        {
            return PipeShape.SOUTH_WEST;
        }
        return null;
    }

    private void addAllEastTo(Pipe currentPipe, Set<Coordinates> inCoordinates, List<Pipe> pipesInLoop)
    {
        int currentColumn = currentPipe.columnCoordinate + 1;
        while (notOtherLoopPipe(currentPipe.rowCoordinate, currentColumn, pipesInLoop))
        {
            inCoordinates.add(new Coordinates(currentPipe.rowCoordinate(), currentColumn++));
        }
    }

    private void addAllWestTo(Pipe currentPipe, Set<Coordinates> inCoordinates, List<Pipe> pipesInLoop)
    {
        int currentColumn = currentPipe.columnCoordinate - 1;
        while (notOtherLoopPipe(currentPipe.rowCoordinate, currentColumn, pipesInLoop))
        {
            inCoordinates.add(new Coordinates(currentPipe.rowCoordinate(), currentColumn--));
        }
    }

    private void addAllNorthTo(Pipe currentPipe, Set<Coordinates> inCoordinates, List<Pipe> pipesInLoop)
    {
        int currentRow = currentPipe.rowCoordinate - 1;
        while (notOtherLoopPipe(currentRow, currentPipe.columnCoordinate(), pipesInLoop))
        {
            inCoordinates.add(new Coordinates(currentRow--, currentPipe.columnCoordinate()));
        }
    }

    private void addAllSouthTo(Pipe currentPipe, Set<Coordinates> inCoordinates, List<Pipe> pipesInLoop)
    {
        int currentRow = currentPipe.rowCoordinate + 1;
        while (notOtherLoopPipe(currentRow, currentPipe.columnCoordinate(), pipesInLoop))
        {
            inCoordinates.add(new Coordinates(currentRow++, currentPipe.columnCoordinate()));
        }
    }

    private boolean notOtherLoopPipe(int rowCoordinate, int columnCoordinate, List<Pipe> pipesInLoop)
    {
        return pipesInLoop.stream().noneMatch(pipe -> rowCoordinate == pipe.rowCoordinate() && columnCoordinate == pipe.columnCoordinate());
    }

    private List<Pipe> findAllPipesInTheLoopBasedOn(List<Pipe> pipesSurroundingStart, Pipeline pipeline)
    {
        List<Pipe> pipesInLoop = new LinkedList<>();
        for (Pipe startNeighbourPipe : pipesSurroundingStart)
        {
            Pipe previousPipe = pipeline.getStartingPipe();
            Optional<Pipe> currentPipeCandidate = pipeline.findNextFor(startNeighbourPipe, previousPipe);
            previousPipe = startNeighbourPipe;
            pipesInLoop = new LinkedList<>();
            pipesInLoop.add(previousPipe);
            while (currentPipeCandidate.isPresent() && !currentPipeCandidate.get().isStart)
            {
                Pipe currentPipe = currentPipeCandidate.get();
                pipesInLoop.add(currentPipe);
                currentPipeCandidate = pipeline.findNextFor(currentPipe, previousPipe);
                previousPipe = currentPipe;
            }
            if (currentPipeCandidate.isPresent())
            {
                Pipe startPipe = currentPipeCandidate.get();
                PipeShape pipeShape = startPipeShape(startNeighbourPipe, previousPipe);
                Pipe startPipeWithShape = new Pipe(pipeShape, startPipe.rowCoordinate, startPipe.columnCoordinate, true);
                pipesInLoop.add(startPipeWithShape);
                break;
            }
        }
        return pipesInLoop;
    }

    private record Pipeline(Map<Integer, Map<Integer, Pipe>> grid, Pipe startingPipe)
    {

        private Optional<Pipe> northOf(Pipe pipe)
        {
            return Optional.ofNullable(grid.getOrDefault(pipe.rowCoordinate - 1, new HashMap<>()).get(pipe.columnCoordinate))
                    .filter(candidate -> candidate.isStart() || List.of(PipeShape.SOUTH_WEST, PipeShape.SOUTH_EAST, PipeShape.VERTICAL).contains(candidate.shape));
        }

        private Optional<Pipe> southOf(Pipe pipe)
        {
            return Optional.ofNullable(grid.getOrDefault(pipe.rowCoordinate + 1, new HashMap<>()).get(pipe.columnCoordinate))
                    .filter(candidate -> candidate.isStart() || List.of(PipeShape.NORTH_WEST, PipeShape.NORTH_EAST, PipeShape.VERTICAL).contains(candidate.shape));
        }

        private Optional<Pipe> eastOf(Pipe pipe)
        {
            return Optional.ofNullable(grid.getOrDefault(pipe.rowCoordinate, new HashMap<>()).get(pipe.columnCoordinate + 1))
                    .filter(candidate -> candidate.isStart() || List.of(PipeShape.NORTH_WEST, PipeShape.SOUTH_WEST, PipeShape.HORIZONTAL).contains(candidate.shape));
        }

        private Optional<Pipe> westOf(Pipe pipe)
        {
            return Optional.ofNullable(grid.getOrDefault(pipe.rowCoordinate, new HashMap<>()).get(pipe.columnCoordinate - 1))
                    .filter(candidate -> candidate.isStart() || List.of(PipeShape.NORTH_EAST, PipeShape.SOUTH_EAST, PipeShape.HORIZONTAL).contains(candidate.shape));
        }

        private Optional<Pipe> findNextFor(Pipe pipe, Pipe previousPipe)
        {
            List<Optional<Pipe>> candidates = switch (pipe.shape)
            {
                case VERTICAL -> List.of(northOf(pipe), southOf(pipe));
                case HORIZONTAL -> List.of(eastOf(pipe), westOf(pipe));
                case NORTH_EAST -> List.of(northOf(pipe), eastOf(pipe));
                case NORTH_WEST -> List.of(northOf(pipe), westOf(pipe));
                case SOUTH_WEST -> List.of(southOf(pipe), westOf(pipe));
                case SOUTH_EAST -> List.of(southOf(pipe), eastOf(pipe));
            };

            return candidates.stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(candidate -> !candidate.equals(previousPipe))
                    .findFirst();
        }

        private List<Pipe> getPipesSurroundingStart()
        {
            Pipe pipe = getStartingPipe();
            return Stream.of(northOf(pipe), southOf(pipe), eastOf(pipe), westOf(pipe))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }

        private Pipe getStartingPipe()
        {
            return startingPipe;
        }

        public static Pipeline buildUsing(String puzzleInput)
        {
            Map<Integer, Map<Integer, Pipe>> grid = new HashMap<>();
            List<String> lines = puzzleInput.lines().toList();
            Integer startCoordinateRow = null;
            Integer startCoordinateColumn = null;
            for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++)
            {
                grid.put(rowIndex, new HashMap<>());
                String row = lines.get(rowIndex);
                for (int columnIndex = 0; columnIndex < row.length(); columnIndex++)
                {
                    String label = row.substring(columnIndex, columnIndex + 1);
                    if ("S".equals(label))
                    {
                        startCoordinateRow = rowIndex;
                        startCoordinateColumn = columnIndex;
                        grid.get(rowIndex).put(columnIndex, new Pipe(null, rowIndex, columnIndex, true));
                        continue;
                    }
                    Optional<PipeShape> shape = PipeShape.getByLabel(label);
                    if (shape.isEmpty())
                    {
                        continue;
                    }
                    grid.get(rowIndex).put(columnIndex, new Pipe(shape.get(), rowIndex, columnIndex, false));
                }
            }

            requireNonNull(startCoordinateRow, "No starting point found!");
            requireNonNull(startCoordinateColumn, "No starting point found!");

            return new Pipeline(grid, new Pipe(null, startCoordinateRow, startCoordinateColumn, true));
        }

    }

    private record Pipe(PipeShape shape, Integer rowCoordinate, Integer columnCoordinate, boolean isStart)
    {

    }

    private enum PipeShape
    {
        VERTICAL("|"),
        HORIZONTAL("-"),
        NORTH_EAST("L"),
        NORTH_WEST("J"),
        SOUTH_WEST("7"),
        SOUTH_EAST("F");

        private final String label;

        PipeShape(String label)
        {
            this.label = label;
        }

        private static Optional<PipeShape> getByLabel(String label)
        {
            return Arrays.stream(PipeShape.values())
                    .filter(pipeTypeLabel -> pipeTypeLabel.label.equals(label))
                    .findFirst();
        }
    }

    private enum Direction
    {
        EAST,
        WEST,
        SOUTH,
        NORTH
    }

    private Comparator<Pipe> pipeSortingComparator()
    {
        return (pipe1, pipe2) ->
        {
            if (pipe1.rowCoordinate.equals(pipe2.rowCoordinate()))
            {
                return pipe1.columnCoordinate.compareTo(pipe2.columnCoordinate);
            }
            return pipe1.rowCoordinate.compareTo(pipe2.rowCoordinate);
        };
    }

    private record Coordinates(int row, int column)
    {
    }
}
