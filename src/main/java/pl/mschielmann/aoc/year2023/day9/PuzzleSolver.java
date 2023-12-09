package pl.mschielmann.aoc.year2023.day9;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return puzzleInput.lines()
                .map(this::calculateNewHistoryValue)
                .reduce(0L, Long::sum);
    }

    long solvePartTwo()
    {
        return puzzleInput.lines()
                .map(this::calculatePreviousHistoryValue)
                .reduce(0L, Long::sum);
    }

    private long calculateNewHistoryValue(String line)
    {
        List<Long> historyValues = Arrays.stream(line.split(" ")).map(Long::valueOf).toList();
        List<List<Long>> allDifferences = new ArrayList<>();
        List<Long> valuesToProcess = new ArrayList<>(historyValues);
        while (!valuesToProcess.stream().allMatch(difference -> difference == 0L))
        {
            List<Long> differences = new ArrayList<>();
            for (int index = 1; index < valuesToProcess.size(); index++)
            {
                differences.add(valuesToProcess.get(index) - valuesToProcess.get(index - 1));
            }
            allDifferences.add(differences);
            valuesToProcess = differences;
        }

        for (int index = allDifferences.size() - 2; index >= 0; index--)
        {
            allDifferences.get(index).add(allDifferences.get(index).getLast() + allDifferences.get(index + 1).getLast());
        }

        return historyValues.getLast() + allDifferences.get(0).getLast();
    }

    private long calculatePreviousHistoryValue(String line)
    {
        List<Long> historyValues = Arrays.stream(line.split(" ")).map(Long::valueOf).toList();
        List<List<Long>> allDifferences = new ArrayList<>();
        List<Long> valuesToProcess = new ArrayList<>(historyValues);
        while (!valuesToProcess.stream().allMatch(difference -> difference == 0L))
        {
            List<Long> differences = new ArrayList<>();
            for (int index = 1; index < valuesToProcess.size(); index++)
            {
                differences.add(valuesToProcess.get(index) - valuesToProcess.get(index - 1));
            }
            allDifferences.add(differences);
            valuesToProcess = differences;
        }

        for (int index = allDifferences.size() - 2; index >= 0; index--)
        {
            allDifferences.get(index).add(0, allDifferences.get(index).getFirst() - allDifferences.get(index + 1).getFirst());
        }

        return historyValues.getFirst() - allDifferences.get(0).getFirst();
    }
}
