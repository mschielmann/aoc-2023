package pl.mschielmann.aoc.year2023.day6;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
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

    private Long getPossibleWinningWays(RaceInfo race)
    {
        BigDecimal raceTime = BigDecimal.valueOf(race.time());
        BigDecimal raceDistance = BigDecimal.valueOf(race.recordDistance());

        return LongStream.range(1, race.time())
                .mapToObj(BigDecimal::valueOf)
                .filter(second -> second.multiply(raceTime.subtract(second)).compareTo(raceDistance) > 0)
                .count();
    }

    long solvePartTwo()
    {
        return getPossibleWinningWays(puzzleInput.get(0));
    }

    record RaceInfo(long time, long recordDistance)
    {
    }
}
