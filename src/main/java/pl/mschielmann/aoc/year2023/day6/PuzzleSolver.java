package pl.mschielmann.aoc.year2023.day6;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.LongStream;

@Slf4j
class PuzzleSolver
{
    private final List<RaceInfo> puzzleInput;

    PuzzleSolver(List<RaceInfo> puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        return puzzleInput.stream()
                .map(this::getPossibleWinningWays)
                .reduce(1L, (actual, partial) -> actual * partial);
    }

    long solvePartTwo()
    {
        return getPossibleWinningWaysUsingQuadraticFunction(puzzleInput.get(0));
    }

    private Long getPossibleWinningWays(RaceInfo race)
    {
        BigDecimal raceTime = BigDecimal.valueOf(race.time());
        BigDecimal raceDistance = BigDecimal.valueOf(race.recordDistance());

        return LongStream.range(1, race.time())
                .mapToObj(BigDecimal::valueOf)
                .filter(second -> second.multiply(raceTime.subtract(second)).compareTo(raceDistance) > 0)
                .count();
    }

    private long getPossibleWinningWaysUsingQuadraticFunction(RaceInfo race)
    {
        BigDecimal raceTime = BigDecimal.valueOf(race.time());
        BigDecimal raceDistance = BigDecimal.valueOf(race.recordDistance());

        var delta = raceTime.multiply(raceTime).subtract(raceDistance.multiply(BigDecimal.valueOf(4)));
        var solution1 = raceTime.negate().add(delta.sqrt(MathContext.DECIMAL128)).divide(BigDecimal.valueOf(-2), RoundingMode.DOWN).setScale(0, RoundingMode.DOWN).longValue();
        var solution2 = raceTime.negate().subtract(delta.sqrt(MathContext.DECIMAL128)).divide(BigDecimal.valueOf(-2), RoundingMode.UP).setScale(0, RoundingMode.UP).longValue();
        return LongStream.range(1, race.time())
                .filter(second -> second > solution1 && second < solution2)
                .count();
    }

    record RaceInfo(long time, long recordDistance)
    {
    }
}
