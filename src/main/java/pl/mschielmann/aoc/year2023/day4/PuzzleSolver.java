package pl.mschielmann.aoc.year2023.day4;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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

        Map<Long, AtomicLong> scratchCardsToProcess = scratchCardsById.values().stream()
                .collect(Collectors.toMap(ScratchCard::id, card -> new AtomicLong(1L)));

        Collection<ScratchCard> scratchCardsAtBeginning = scratchCardsById.values();
        scratchCardsAtBeginning.forEach(card ->
            LongStream.range(card.id + 1, card.id + card.numberOfWinningCandidateCards() + 1)
                    .forEach(index -> scratchCardsToProcess.get(index).addAndGet(scratchCardsToProcess.get(card.id).longValue()))
        );

        return scratchCardsToProcess.values().stream()
                .map(AtomicLong::get)
                .mapToLong(Long::valueOf)
                .sum();
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
