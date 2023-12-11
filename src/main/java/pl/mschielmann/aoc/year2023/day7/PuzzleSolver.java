package pl.mschielmann.aoc.year2023.day7;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
        return solve(false);
    }

    long solvePartTwo()
    {
        return solve(true);
    }

    private long solve(boolean usingJokers)
    {
        List<Long> orderedBids = puzzleInput.lines()
                .map(line -> new HandWithBid(new CardHand(line.split(" ")[0], usingJokers), Long.parseLong(line.split(" ")[1])))
                .sorted()
                .map(HandWithBid::bid)
                .toList();


        return IntStream.range(1, orderedBids.size() + 1)
                .mapToObj(rank -> rank * orderedBids.get(rank - 1))
                .reduce(0L, Long::sum);
    }

    private record HandWithBid(CardHand cardHand, Long bid) implements Comparable<HandWithBid>
    {
        @Override
        public int compareTo(HandWithBid other)
        {
            return cardHand.compareTo(other.cardHand);
        }
    }

    private static class CardHand implements Comparable<CardHand>
    {
        private final String hand;
        private final HandType handType;
        private final boolean jokersUsed;

        private CardHand(String hand, boolean jokersUsed)
        {
            this.hand = hand;
            this.handType = jokersUsed ? calculateHandTypeBasedOnAlternatives(hand) : calculateHandType(hand);
            this.jokersUsed = jokersUsed;
        }

        private HandType calculateHandType(String hand)
        {
            Map<CardType, Integer> charactersCounts = new HashMap<>();
            for (int index = 0; index < hand.length(); index++)
            {
                CardType cardType = CardType.of(hand.substring(index, index + 1));
                charactersCounts.put(cardType, charactersCounts.getOrDefault(cardType, 0) + 1);
            }
            return switch (charactersCounts.keySet().size())
            {
                case 1 -> HandType.FIVE_OF_A_KIND;
                case 2 ->
                        charactersCounts.values().stream().anyMatch(count -> count == 4) ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE;
                case 3 ->
                        charactersCounts.values().stream().anyMatch(count -> count == 3) ? HandType.THREE_OF_A_KIND : HandType.TWO_PAIR;
                case 4 -> HandType.PAIR;
                default -> HandType.HIGH_CARD;
            };
        }

        private HandType calculateHandTypeBasedOnAlternatives(String hand)
        {
            return Arrays.stream(CardType.values())
                    .filter(type -> !type.equals(CardType.JOKER))
                    .map(type -> hand.replaceAll(CardType.JACK.symbol, type.symbol))
                    .map(alternativeHand -> new CardHand(alternativeHand, false))
                    .max(Comparator.naturalOrder())
                    .orElseThrow(() -> new IllegalArgumentException("Well, that should not have happened."))
                    .handType;
        }

        @Override
        public int compareTo(CardHand other)
        {
            int result = handType.ordinal() - other.handType.ordinal();
            for (int index = 0; result == 0 && index < hand.length(); index++)
            {
                String thisCardCharacter = hand.substring(index, index + 1);
                String otherCardCharacter = other.hand.substring(index, index + 1);
                if (jokersUsed)
                {
                    thisCardCharacter = thisCardCharacter.equals(CardType.JACK.symbol) ? CardType.JOKER.symbol : thisCardCharacter;
                    otherCardCharacter = otherCardCharacter.equals(CardType.JACK.symbol) ? CardType.JOKER.symbol : otherCardCharacter;
                }
                result = CardType.of(thisCardCharacter).compareTo(CardType.of(otherCardCharacter));
            }

            return result;
        }
    }

    private enum HandType
    {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

    private enum CardType
    {
        JOKER("JOKER"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("T"),
        JACK("J"),
        QUEEN("Q"),
        KIND("K"),
        ACE("A");

        private final String symbol;

        CardType(String symbol)
        {
            this.symbol = symbol;
        }

        private static CardType of(String cardSymbol)
        {
            return Arrays.stream(values())
                    .filter(type -> type.symbol.equals(cardSymbol))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No such card type: " + cardSymbol));
        }
    }
}
