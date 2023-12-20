package pl.mschielmann.aoc.year2023.day18;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Instructions> instructions = puzzleInput.lines()
                .map(Instructions::parse)
                .toList();

        Trench trench = Trench.buildUsing(instructions);
        return -1L;//trench.edge().size() + trench.determineInsideArea();
    }

    long solvePartTwo()
    {
        List<Instructions> instructions = puzzleInput.lines()
                .map(Instructions::parseAlternatively)
                .toList();

        Trench trench = Trench.buildUsing(instructions);
        return trench.edgeParts().size() + trench.determineInsideArea();
    }

    private record Trench(Set<EdgePart> edgeParts)
    {
        private static Trench buildUsing(List<Instructions> instructions)
        {
            Set<Coordinates> edge = new HashSet<>();
            Coordinates coordinates = new Coordinates(0, 0);
            for (int instructionIndex = 0; instructionIndex < instructions.size(); instructionIndex++)
            {
                Instructions currentInstruction = instructions.get(instructionIndex);
                for (int stepCounter = 0; stepCounter < currentInstruction.stepCount(); stepCounter++)
                {
                    coordinates = coordinates.nextIn(currentInstruction.direction);
                    edge.add(coordinates);
                }
            }

            return null;//new Trench(edge);
        }

        private long determineInsideArea()
        {
            long result = 0;
            Coordinates currentCoordinates = new Coordinates(0, 0);
            long maxRow = edgeParts.stream().map(EdgePart::coordinates).max(Comparator.comparingInt(Coordinates::row)).orElseThrow().row();
            long maxColumn = edgeParts.stream().map(EdgePart::coordinates).max(Comparator.comparingInt(Coordinates::column)).orElseThrow().column();
            boolean isInside = false;
            Direction insideDirection = Direction.DOWN;
            for (int rowIndex = 0; rowIndex < maxRow; rowIndex++)
            {
                for (int columnIndex = 0; columnIndex < maxColumn; columnIndex++)
                {
                    if (isInside)
                    {
                        result++;
                        break;
                    }
                }
            }
            return result;
        }
    }

    private record Instructions(Direction direction, int stepCount)
    {
        private static Instructions parse(String input)
        {
            String[] instructionParts = input.split(" ");
            try
            {
                return new Instructions(Direction.withLabel(instructionParts[0].charAt(0)),
                        Integer.parseInt(instructionParts[1]));
            }
            catch (NumberFormatException e)
            {
                throw new RuntimeException("Invalid input!", e);
            }
        }

        private static Instructions parseAlternatively(String input)
        {
            String[] instructionParts = input.split(" ");
            try
            {
                String lastPart = instructionParts[2].replaceAll("\\(", "").replaceAll("\\)", "");
                return new Instructions(Direction.withOrdinal(lastPart.charAt(6)),
                        Integer.decode(lastPart.substring(0, 6)));
            }
            catch (NumberFormatException e)
            {
                throw new RuntimeException("Invalid input!", e);
            }
        }
    }

    @Getter
    private enum Direction
    {
        RIGHT('R'),
        DOWN('D'),
        LEFT('L'),
        UP('U');


        private final char label;

        Direction(char label)
        {
            this.label = label;
        }

        private static Direction withLabel(char label)
        {
            return Arrays.stream(values()).filter(direction -> direction.label == label)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No such direction: " + label));
        }

        private static Direction withOrdinal(char ordinal)
        {
            return Arrays.stream(values()).filter(direction -> direction.ordinal() == Integer.parseInt("" + ordinal))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No such direction: " + ordinal));
        }
    }

    private record EdgePart(Coordinates coordinates, Direction inDirection, Direction outDirection)
    {
    }

    private record Coordinates(int row, int column)
    {
        Coordinates nextIn(Direction direction)
        {
            return switch (direction)
            {
                case RIGHT -> new Coordinates(row, column + 1);
                case LEFT -> new Coordinates(row, column - 1);
                case DOWN -> new Coordinates(row + 1, column);
                case UP -> new Coordinates(row - 1, column);
            };
        }
    }
}
