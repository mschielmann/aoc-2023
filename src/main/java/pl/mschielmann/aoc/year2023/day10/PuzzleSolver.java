package pl.mschielmann.aoc.year2023.day10;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

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
        Pipeline pipeline = Pipeline.buildUsing(puzzleInput);
        List<Pipe> pipesSurroundingStart = pipeline.getPipesSurroundingStart();

        log.info("Surrounding start {}", pipeline.getPipesSurroundingStart());
        List<Pipe> pipesInLoop = findAllPipesInTheLoopBasedOn(pipesSurroundingStart, pipeline);

        return Double.valueOf(Math.ceil((double) pipesInLoop.size() / 2)).longValue();
    }

    long solvePartTwo()
    {
        Pipeline pipeline = Pipeline.buildUsing(puzzleInput);
        List<Pipe> pipesSurroundingStart = pipeline.getPipesSurroundingStart();

        log.info("Surrounding start {}", pipeline.getPipesSurroundingStart());
        List<Pipe> pipesInLoop = findAllPipesInTheLoopBasedOn(pipesSurroundingStart, pipeline)
                .stream().sorted((pipe1, pipe2) -> {
                    if (pipe1.rowCoordinate.equals(pipe1.rowCoordinate())) {
                        return pipe1.columnCoordinate.compareTo(pipe2.columnCoordinate);
                    }
                    return pipe1.rowCoordinate.compareTo(pipe2.rowCoordinate);
                }).toList();


        return -1L;
    }

    private List<Pipe> findAllPipesInTheLoopBasedOn(List<Pipe> pipesSurroundingStart, Pipeline pipeline)
    {
        List<Pipe> pipesInLoop = new ArrayList<>();
        for (Pipe pipe : pipesSurroundingStart)
        {
            Pipe previousPipe = pipeline.getStartingPipe();
            Optional<Pipe> currentPipe = pipeline.findNextFor(pipe, previousPipe);
            previousPipe = pipe;
            pipesInLoop = new ArrayList<>();
            pipesInLoop.add(previousPipe);
            while (currentPipe.isPresent() && !currentPipe.get().isStart)
            {
                pipesInLoop.add(currentPipe.get());
                Pipe tempPipe = currentPipe.get();
                currentPipe = pipeline.findNextFor(currentPipe.get(), previousPipe);
                previousPipe = tempPipe;
            }
            if (currentPipe.isPresent())
            {
                log.info("start found: {}", currentPipe.get());
                break;
            }
        }
        return pipesInLoop;
    }

    private record Pipeline(Map<Integer, Map<Integer, Pipe>> grid, Integer startCoordinateRow,
                            Integer startCoordinateColumn)
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
            return grid.get(startCoordinateRow).get(startCoordinateColumn);
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
                        log.info("Start {}, {}", rowIndex, columnIndex);
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

            return new Pipeline(grid, startCoordinateRow, startCoordinateColumn);
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
}
