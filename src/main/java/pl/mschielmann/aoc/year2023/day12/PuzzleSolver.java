package pl.mschielmann.aoc.year2023.day12;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class PuzzleSolver
{
    private static final char EMPTY_SPACE = '.';
    private final String puzzleInput;
    private int counter = 0;
    private static final Map<String, BigDecimal> CACHE = new HashMap<>();

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    String solvePartOne()
    {

        return puzzleInput.lines()
                .map(this::calculatePossibilities)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .toPlainString();
    }

    String solvePartTwo()
    {
        return puzzleInput.lines()
                .map(line -> calculatePossibilities(line, 5))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .toPlainString();
    }

    private BigDecimal calculatePossibilities(String string)
    {
        return calculatePossibilities(string, 1);
    }

    private BigDecimal calculatePossibilities(String string, int repetition)
    {
        List<Integer> groupDefinitions = Arrays.stream(string.split(" ")[1].split(","))
                .map(Integer::parseInt)
                .toList();
        List<Integer> unfoldedDefinitions = IntStream.range(0, repetition).boxed().flatMap(ignored -> groupDefinitions.stream()).toList();
        String record = string.split(" ")[0].replaceAll("\\.+", ".");
        String unfoldedRecord = IntStream.range(0, repetition).boxed().map(ignored -> record).collect(Collectors.joining("?"));
        return countMatches(unfoldedDefinitions, unfoldedRecord);
    }

    private BigDecimal countMatches(List<Integer> groupDefinitions, String record)
    {
        if (notEnoughPositionsToFitAllGroups(groupDefinitions, record))
        {
            return BigDecimal.ZERO;
        }

        Integer groupSize = groupDefinitions.get(0);
        String cacheKey = record + groupDefinitions.stream().map(Object::toString).collect(Collectors.joining(","));
        if (CACHE.containsKey(cacheKey))
        {
            return CACHE.get(cacheKey);
        }

        BigDecimal result = BigDecimal.ZERO;
        for (int index = 0; index + groupSize <= record.length(); index++)
        {
            if (indexIsPastRequiredPosition(record, index))
            {
                break;
            }

            if (record.charAt(index) == EMPTY_SPACE)
            {
                continue;
            }

            boolean isCandidate = IntStream.range(index, index + groupSize)
                    .noneMatch(groupCandidateIndex -> record.charAt(groupCandidateIndex) == EMPTY_SPACE);

            if (isCandidate && noRequiredPositionJustAfterGroup(record, index, groupSize))
            {
                if (isLastGroup(groupDefinitions) && noRequiredPositionsAhead(record, index, groupSize))
                {
                    result = result.add(BigDecimal.ONE);
                } else if (!isLastGroup(groupDefinitions) && existsPositionsForAnotherGroup(record, index, groupSize))
                {
                    String newRecord = record.substring(index + groupSize + 1);
                    result = result.add(countMatches(groupDefinitions.subList(1, groupDefinitions.size()), newRecord));
                }
            }
        }
        CACHE.put(cacheKey, result);
        return result;
    }

    private boolean notEnoughPositionsToFitAllGroups(List<Integer> groupDefinitions, String record)
    {
        return groupDefinitions.stream().mapToInt(Integer::intValue).sum() + groupDefinitions.size() - 1 > record.length();
    }

    private boolean existsPositionsForAnotherGroup(String record, int index, Integer groupSize)
    {
        return index + groupSize < record.length() - 1;
    }

    private boolean isLastGroup(List<Integer> groupDefinitions)
    {
        return groupDefinitions.size() == 1;
    }

    private boolean noRequiredPositionsAhead(String record, int index, Integer groupSize)
    {
        return IntStream.range(index + groupSize, record.length()).noneMatch(i -> record.charAt(i) == '#');
    }

    private boolean noRequiredPositionJustAfterGroup(String record, int index, Integer groupSize)
    {
        return record.length() == index + groupSize || record.charAt(index + groupSize) != '#';
    }

    private boolean indexIsPastRequiredPosition(String record, int index)
    {
        return IntStream.range(0, index).anyMatch(i -> record.charAt(i) == '#');
    }
}
