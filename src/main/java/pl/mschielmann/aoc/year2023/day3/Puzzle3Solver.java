package pl.mschielmann.aoc.year2023.day3;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
class Puzzle3Solver
{
    private static final Pattern NON_DIGIT_PATTERN = Pattern.compile("[^\\d.\\r\\n]");
    private static final Pattern GEAR_PATTERN = Pattern.compile("\\*");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private final String puzzleInput;
    private final int lineLength;

    Puzzle3Solver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
        lineLength = puzzleInput.lines().toList().get(0).length() + 1;
    }

    long solvePartOne()
    {
        return findAllNumbersInAllowedIndices(findAllAllowedEnginePartsIndices()).stream()
                .mapToLong(Long::valueOf)
                .sum();
    }

    long solvePartTwo()
    {
        return findAllGearRatios(getGearIndices()).stream()
                .mapToLong(Long::valueOf).sum();
    }

    private Set<Integer> findAllAllowedEnginePartsIndices()
    {
        Set<Integer> allowedIndices = new HashSet<>();

        Matcher matcher = NON_DIGIT_PATTERN.matcher(puzzleInput);
        while (matcher.find())
        {
            int indexOfNonDigit = matcher.start();
            allowedIndices.add(indexOfNonDigit);
            allowedIndices.add(indexOfNonDigit - 1);
            allowedIndices.add(indexOfNonDigit + 1);
            allowedIndices.add(indexOfNonDigit - lineLength - 1);
            allowedIndices.add(indexOfNonDigit - lineLength + 1);
            allowedIndices.add(indexOfNonDigit - lineLength);
            allowedIndices.add(indexOfNonDigit + lineLength);
            allowedIndices.add(indexOfNonDigit + lineLength - 1);
            allowedIndices.add(indexOfNonDigit + lineLength + 1);
        }

        return allowedIndices;
    }

    private List<Integer> findAllNumbersInAllowedIndices(Set<Integer> allowedIndices)
    {
        List<Integer> result = new ArrayList<>();

        Matcher matcher = NUMBER_PATTERN.matcher(puzzleInput);
        while (matcher.find())
        {
            int numberStartIndex = matcher.start();
            int numberEndIndex = matcher.end();
            int number = Integer.parseInt(matcher.group());

            Set<Integer> numberIndices = new HashSet<>();
            for (int index = numberStartIndex; index < numberEndIndex; index++)
            {
                numberIndices.add(index);
            }

            if (numberIndices.stream().anyMatch(allowedIndices::contains))
            {
                result.add(number);
            }
        }
        return result;
    }

    private Set<Integer> getGearIndices()
    {
        Set<Integer> gearIndices = new HashSet<>();

        Matcher matcher = GEAR_PATTERN.matcher(puzzleInput);
        while (matcher.find())
        {
            gearIndices.add(matcher.start());
        }
        return gearIndices;
    }

    private List<Long> findAllGearRatios(Set<Integer> gearIndices)
    {
        Map<Integer, List<Integer>> adjacentNumbers = new HashMap<>();
        gearIndices.forEach(index -> adjacentNumbers.put(index, new ArrayList<>()));

        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher matcher = numberPattern.matcher(puzzleInput);
        linkNumberToSurroundingGears(gearIndices, matcher, adjacentNumbers);

        return adjacentNumbers.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> (long) list.get(0) * list.get(1))
                .toList();
    }

    private void linkNumberToSurroundingGears(Set<Integer> gearIndices, Matcher matcher, Map<Integer, List<Integer>> adjacentNumbers)
    {
        while (matcher.find())
        {
            int numberStartIndex = matcher.start();
            int numberEndIndex = matcher.end();
            int number = Integer.parseInt(matcher.group());
            for (int index = numberStartIndex - 1; index <= numberEndIndex; index++)
            {
                if (gearIndices.contains(index - lineLength))
                {
                    adjacentNumbers.get(index - lineLength).add(number);
                }
                if (gearIndices.contains(index))
                {
                    adjacentNumbers.get(index).add(number);
                }
                if (gearIndices.contains(index + lineLength))
                {
                    adjacentNumbers.get(index + lineLength).add(number);
                }
            }
        }
    }
}
