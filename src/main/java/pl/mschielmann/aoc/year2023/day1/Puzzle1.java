package pl.mschielmann.aoc.year2023.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
class Puzzle1 {
    private final String puzzleInput;

    Puzzle1(String puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne() {
        return solve(puzzleInput, ElfDigit::matchesOnlyDigit);
    }

    long solvePartTwo() {
        return solve(puzzleInput, ElfDigit::matches);
    }

    private long solve(final String input, BiFunction<ElfDigit, String, Boolean> matchingFunction) {
        long sum = 0;
        for (String line : input.lines().toList()) {
            int firstDigit = findFirstDigitIn(line, matchingFunction);
            int lastDigit = findLastDigitIn(line, matchingFunction);
            int numberFromDigits = Integer.parseInt("" + firstDigit + lastDigit);
            sum += numberFromDigits;
        }
        return sum;
    }

    private int findFirstDigitIn(String line, BiFunction<ElfDigit, String, Boolean> matchingFunction) {
        Stream<Integer> lastToFirstIndexNumbers = IntStream
                .range(0, line.length())
                .boxed();
        return findFirstOccurrence(line, lastToFirstIndexNumbers, matchingFunction);
    }

    private int findLastDigitIn(String line, BiFunction<ElfDigit, String, Boolean> matchingFunction) {
        Stream<Integer> lastToFirstIndexNumbers = IntStream
                .range(0, line.length())
                .boxed()
                .sorted(Comparator.reverseOrder());
        return findFirstOccurrence(line, lastToFirstIndexNumbers, matchingFunction);
    }

    private int findFirstOccurrence(String line, Stream<Integer> indexStream, BiFunction<ElfDigit, String, Boolean> matchingFunction) {
        var result = indexStream
                .map(
                        index -> {
                            String substring = line.substring(index);
                            return Arrays.stream(ElfDigit.values())
                                    .filter(digit -> matchingFunction.apply(digit, substring))
                                    .findFirst()
                                    .orElse(null);
                        })
                .filter(Objects::nonNull)
                .findFirst();
        if (result.isPresent()) {
            return result.get().value();
        }
        throw new IllegalStateException("No digit found.");
    }

    private enum ElfDigit {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE;

        boolean matches(String string) {
            return string.startsWith("" + (ordinal() + 1)) || string.startsWith(name().toLowerCase());
        }

        boolean matchesOnlyDigit(String string) {
            return string.startsWith("" + (ordinal() + 1));
        }

        int value() {
            return ordinal() + 1;
        }
    }
}
