package pl.mschielmann.aoc.year2023.day13;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return createLavaMapsOf(puzzleInput.lines().toList())
                .stream()
                .map(map -> getReflection2(map, false, 0))
                .mapToLong(Long::valueOf)
                .sum();
    }

    long solvePartTwo()
    {
        return createLavaMapsOf(puzzleInput.lines().toList())
                .stream()
                .map(map -> getReflection2(map, false, 1))
                .mapToLong(Long::valueOf)
                .sum();
    }

    private Long getReflection(LavaMap lavaMap, boolean transposed)
    {
        List<String> rows = lavaMap.rows();
        Long result = null;
        for (int reflectionCandidateIndex = 1; reflectionCandidateIndex < rows.size(); reflectionCandidateIndex++)
        {
            boolean candidateIsReflection = true;
            for (int reflectionCounter = 0; reflectionCounter < 10000; reflectionCounter++)
            {
                try
                {
                    String row1 = rows.get(reflectionCandidateIndex + reflectionCounter);
                    String row2 = rows.get(reflectionCandidateIndex - reflectionCounter - 1);
                    if (!row1.equals(row2))
                    {
                        candidateIsReflection = false;
                        break;
                    }
                } catch (IndexOutOfBoundsException e)
                {
                    //log.info("I think we have our reflection!" + reflectionCandidateIndex);
                    break;
                }
            }
            if (candidateIsReflection)
            {
                result = (long) reflectionCandidateIndex;
                break;
            }
        }
        if (result == null) {
            return getReflection(lavaMap.transpose(), true);
        }
        if (transposed) {
            return result;
        }
        return result * 100;
    }

    private Long getReflection2(LavaMap lavaMap, boolean transposed, int expectedDifferenceInRow)
    {
        List<String> rows = lavaMap.rows();
        Long result = null;
        for (int reflectionCandidateIndex = 1; reflectionCandidateIndex < rows.size(); reflectionCandidateIndex++)
        {
            boolean candidateIsReflection = expectedDifferenceInRow == 0;
            boolean oneCharDifferenceUsed = false;
            for (int reflectionCounter = 0; reflectionCounter < 10000; reflectionCounter++)
            {
                try
                {
                    String row1 = rows.get(reflectionCandidateIndex + reflectionCounter);
                    String row2 = rows.get(reflectionCandidateIndex - reflectionCounter - 1);
                    if (!row1.equals(row2))
                    {
                        if (isDifferentByNumberOfChars(row1, row2, expectedDifferenceInRow) && !oneCharDifferenceUsed) {
                            oneCharDifferenceUsed = true;
                            candidateIsReflection = true;
                            continue;
                        }
                        candidateIsReflection = false;
                        break;
                    }
                } catch (IndexOutOfBoundsException e)
                {
                    break;
                }
            }
            if (candidateIsReflection)
            {
                result = (long) reflectionCandidateIndex;
                break;
            }
        }
        if (result == null) {
            return getReflection2(lavaMap.transpose(), true, expectedDifferenceInRow);
        }
        if (transposed) {
            return result;
        }
        return result * 100;
    }

    private boolean isDifferentByNumberOfChars(String input1, String input2, int expectedDifference) {
        int difference = 0;
        for (int index = 0; index < input1.length(); index++) {
            if (input1.charAt(index) != input2.charAt(index)) {
                difference++;
            }
        }
        return difference == expectedDifference;
    }

    private List<LavaMap> createLavaMapsOf(List<String> lines)
    {
        List<LavaMap> lavaMaps = new ArrayList<>();
        List<String> rowsOfMap = new ArrayList<>();
        for (String line : lines)
        {
            if (line.isEmpty())
            {
                lavaMaps.add(new LavaMap(rowsOfMap));
                rowsOfMap = new ArrayList<>();
                continue;
            }

            rowsOfMap.add(line.trim());
        }
        lavaMaps.add(new LavaMap(rowsOfMap));
        return lavaMaps;
    }

    private record LavaMap(List<String> rows)
    {
        private LavaMap transpose()
        {
            List<String> newRows = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < rows.get(0).length(); columnIndex++)
            {
                StringBuilder builder = new StringBuilder();
                for (String row : rows)
                {
                    builder.append(row.charAt(columnIndex));
                }
                newRows.add(builder.toString());
            }
            return new LavaMap(newRows);
        }
    }
}
