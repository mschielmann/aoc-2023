package pl.mschielmann.aoc.year2023.day14;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
class PuzzleSolver
{
    private static final char ROUND_ROCK_SYMBOL = 'O';
    private static final Pattern NORTH_WEST_REPLACEMENT_FINDING_PATTERN = Pattern.compile(".*\\." + ROUND_ROCK_SYMBOL + ".*");
    private static final Pattern SOUTH_EAST_REPLACEMENT_FINDING_PATTERN = Pattern.compile(".*" + ROUND_ROCK_SYMBOL + "\\..*");
    private static final Pattern NORTH_WEST_REPLACEMENT_PATTERN = Pattern.compile("\\." + ROUND_ROCK_SYMBOL);
    private static final Pattern SOUT_EAST_REPLACEMENT_PATTERN = Pattern.compile(ROUND_ROCK_SYMBOL + "\\.");
    private static final String NORTH_WEST_REPLACEMENT = "O.";
    private static final String SOUTH_EAST_REPLACEMENT = ".O";
    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        List<String> lines = puzzleInput.lines().toList();

        List<String> transposedMap = transpose(lines);
        transposedMap.forEach(row -> log.info("{}", row));

        tilt(transposedMap, NORTH_WEST_REPLACEMENT_FINDING_PATTERN, NORTH_WEST_REPLACEMENT_PATTERN, NORTH_WEST_REPLACEMENT);
        return calculateWeight(transposedMap);
    }

    long solvePartTwo()
    {
        List<String> transposedMap = puzzleInput.lines().toList();
        Map<String, Long> cache = new HashMap<>();
        long cycleStart = 0;
        long cycleLength = 0;
        for (long i = 0; i < 1000000000; i++)
        {
            if (cache.containsKey(String.join("", transposedMap)))
            {
                cycleStart = cache.get(String.join("", transposedMap));
                cycleLength = i - cycleStart;
                break;
            }
            cache.put(String.join("", transposedMap), i);
            transposedMap = performTiltingCycle(transposedMap);
        }

        long remainingCycles = (1000000000 - cycleStart) % cycleLength;
        for (int i = 0; i < remainingCycles; i++)
        {
            transposedMap = performTiltingCycle(transposedMap);
        }
        return calculateWeight(transpose(transposedMap));
    }

    private List<String> transpose(List<String> rows)
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
        return newRows;
    }

    private List<String> performTiltingCycle(List<String> transposedMap)
    {
        transposedMap = transpose(transposedMap);
        tilt(transposedMap, NORTH_WEST_REPLACEMENT_FINDING_PATTERN, NORTH_WEST_REPLACEMENT_PATTERN, NORTH_WEST_REPLACEMENT);
        transposedMap = transpose(transposedMap);
        tilt(transposedMap, NORTH_WEST_REPLACEMENT_FINDING_PATTERN, NORTH_WEST_REPLACEMENT_PATTERN, NORTH_WEST_REPLACEMENT);
        transposedMap = transpose(transposedMap);
        tilt(transposedMap, SOUTH_EAST_REPLACEMENT_FINDING_PATTERN, SOUT_EAST_REPLACEMENT_PATTERN, SOUTH_EAST_REPLACEMENT);
        transposedMap = transpose(transposedMap);
        tilt(transposedMap, SOUTH_EAST_REPLACEMENT_FINDING_PATTERN, SOUT_EAST_REPLACEMENT_PATTERN, SOUTH_EAST_REPLACEMENT);
        return transposedMap;
    }

    private void tilt(List<String> transposedMap, Pattern findingPattern, Pattern replacementPattern, String replacement)
    {
        for (int lineIndex = 0; lineIndex < transposedMap.size(); lineIndex++)
        {
            while (findingPattern.matcher(transposedMap.get(lineIndex)).matches())
            {
                transposedMap.set(lineIndex, replacementPattern.matcher(transposedMap.get(lineIndex))
                        .replaceAll(replacement)
                );
            }
        }
    }

    private long calculateWeight(List<String> transposedMap)
    {
        int maxWeightPerStone = transposedMap.get(0).length();
        long result = 0;
        for (String line : transposedMap)
        {
            int weight = maxWeightPerStone;
            for (int charIndex = 0; charIndex < line.length(); charIndex++)
            {
                if (line.charAt(charIndex) == ROUND_ROCK_SYMBOL)
                {
                    result += weight;
                }
                weight--;
            }
        }
        return result;
    }
}
