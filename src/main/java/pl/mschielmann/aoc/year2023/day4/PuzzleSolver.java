package pl.mschielmann.aoc.year2023.day4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .map(ScratchCard::createScratchCardOf)
                .map(ScratchCard::numberOfWinningCandidateCards)
                .map(number -> Math.pow(2, number - 1))
                .mapToLong(Double::longValue)
                .sum();
    }

    long solvePartTwo()
    {
        Map<Long, ScratchCard> scratchCardsById = puzzleInput.lines()
                .map(ScratchCard::createScratchCardOf)
                .collect(Collectors.toMap(ScratchCard::id, Function.identity()));

        List<ScratchCard> scratchCardsToProcess = new LinkedList<>(scratchCardsById.values());
        long numberOfCardsCollected = scratchCardsToProcess.size();
        while (!scratchCardsToProcess.isEmpty())
        {
            ScratchCard currentCard = scratchCardsToProcess.remove(0);
            int numberOfCardsAdded = scratchCardsToProcess.size();
            long newToProcess = currentCard.numberOfWinningCandidateCards();
            for (int counter = 1; counter <= newToProcess; counter++)
            {
                scratchCardsToProcess.add(scratchCardsById.get(currentCard.id + counter));
            }
            numberOfCardsAdded = scratchCardsToProcess.size() - numberOfCardsAdded;
            numberOfCardsCollected += numberOfCardsAdded;
        }

        return numberOfCardsCollected;
    }


    record ScratchCard(Long id, List<Integer> winningNumbers, List<Integer> candidateNumbers)
    {
        private static ScratchCard createScratchCardOf(String line)
        {
            String[] idAndNumbers = line.split(":");
            long id = Long.parseLong(idAndNumbers[0].replaceAll("\\s+", " ").split(" ")[1].trim());
            String[] kindsOfNumbers = idAndNumbers[1].split("\\|");
            List<Integer> winningNumbers = Stream.of(kindsOfNumbers[0].trim().split(" "))
                    .filter(number -> !number.trim().isEmpty())
                    .map(Integer::parseInt)
                    .toList();
            List<Integer> candidateNumbers = Stream.of(kindsOfNumbers[1].trim().split(" "))
                    .filter(number -> !number.trim().isEmpty())
                    .map(Integer::parseInt)
                    .toList();
            return new ScratchCard(id, winningNumbers, candidateNumbers);
        }

        long numberOfWinningCandidateCards()
        {
            return candidateNumbers().stream()
                    .filter(winningNumbers::contains)
                    .count();
        }
    }
}
