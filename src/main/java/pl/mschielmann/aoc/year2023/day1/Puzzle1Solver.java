package pl.mschielmann.aoc.year2023.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

@Slf4j
class Puzzle1Solver
{
    private final String puzzleInput;

    Puzzle1Solver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        return solve(puzzleInput, ElvesDigit::matchesOnlyDigit);
    }

    long solvePartTwo()
    {
        return solve(puzzleInput, ElvesDigit::matches);
    }

    private long solve(final String input, BiFunction<ElvesDigit, String, Boolean> matchingFunction)
    {
        return input.lines()
                .map(line -> findFirstDigitIn(line, matchingFunction) * 10 + findLastDigitIn(line, matchingFunction))
                .reduce(0, Integer::sum);
    }

    private int findFirstDigitIn(String line, BiFunction<ElvesDigit, String, Boolean> matchingFunction)
    {
        return IntStream
                .range(0, line.length())
                .boxed()
                .map(inputAtIndexToElvesDigit(line, matchingFunction))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No digit found."))
                .value();
    }


    private int findLastDigitIn(String line, BiFunction<ElvesDigit, String, Boolean> matchingFunction)
    {
        return IntStream
                .range(0, line.length())
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(inputAtIndexToElvesDigit(line, matchingFunction))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No digit found."))
                .value();
    }

    private Function<Integer, ElvesDigit> inputAtIndexToElvesDigit(String line,
                                                                   BiFunction<ElvesDigit, String, Boolean> matchingFunction)
    {
        return index ->
        {
            String substring = line.substring(index);
            return Arrays.stream(ElvesDigit.values())
                    .filter(digit -> matchingFunction.apply(digit, substring))
                    .findFirst()
                    .orElse(null);
        };
    }

    private enum ElvesDigit
    {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE;

        boolean matches(String string)
        {
            return string.startsWith("" + (ordinal() + 1)) || string.startsWith(name().toLowerCase());
        }

        boolean matchesOnlyDigit(String string)
        {
            return string.startsWith("" + (ordinal() + 1));
        }

        int value()
        {
            return ordinal() + 1;
        }
    }
}
