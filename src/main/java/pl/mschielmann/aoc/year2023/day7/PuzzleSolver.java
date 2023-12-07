package pl.mschielmann.aoc.year2023.day7;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Long> orderedBids = puzzleInput.lines()
                .map(line -> new HandWithBid(new CardHand(line.split(" ")[0]), Long.parseLong(line.split(" ")[1])))
                .sorted()
                .map(HandWithBid::bid)
                .toList();

        long counter = 1;
        long result = 0;
        for (Long bid : orderedBids)
        {
            result = result + bid * counter;
            counter++;
        }
        return result;
    }

    long solvePartTwo()
    {
        List<Long> orderedBids = puzzleInput.lines()
                .map(line -> new AlternativeHandWithBid(new AlternativeCardHand(line.split(" ")[0]), Long.parseLong(line.split(" ")[1])))
                .sorted()
                .peek(a -> log.info("{}", a.cardHand.hand))
                .map(AlternativeHandWithBid::bid)
                .toList();

        long counter = 1;
        long result = 0;
        for (Long bid : orderedBids)
        {
            result = result + bid * counter;
            counter++;
        }
        return result;
    }

    private record HandWithBid(CardHand cardHand, Long bid) implements Comparable<HandWithBid>
    {

        @Override
        public int compareTo(HandWithBid other)
        {
            return cardHand.compareTo(other.cardHand);
        }
    }

    private record AlternativeHandWithBid(AlternativeCardHand cardHand, Long bid) implements Comparable<AlternativeHandWithBid>
    {
        @Override
        public int compareTo(AlternativeHandWithBid other)
        {
            return cardHand.compareTo(other.cardHand);
        }
    }

    private static class CardHand implements Comparable<CardHand>
    {
        private final String hand;
        private final HandType handType;

        private CardHand(String hand)
        {
            this.hand = hand;
            this.handType = calculateHandType(hand);
        }

        private HandType calculateHandType(String hand)
        {
            Map<CardType, Integer> charactersCounts = new HashMap<>();
            for (byte character : hand.getBytes(StandardCharsets.UTF_8))
            {
                CardType cardType = CardType.of((char) character);
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

        @Override
        public int compareTo(CardHand other)
        {
            int result = handType.ordinal() - other.handType.ordinal();
            if (result == 0)
            {
                for (int index = 0; index < hand.length() && result == 0; index++)
                {
                    result = CardType.of(hand.charAt(index)).compareTo(CardType.of(other.hand.charAt(index)));
                }
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

        private final String character;

        CardType(String character)
        {
            this.character = character;
        }

        private static CardType of(char character)
        {
            return Arrays.stream(values())
                    .filter(type -> type.character.equals(character + ""))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No such card type"));
        }
    }

    private static class AlternativeCardHand implements Comparable<AlternativeCardHand>
    {
        private final String hand;
        private final HandType handType;

        private AlternativeCardHand(String hand)
        {
            this.hand = hand;
            this.handType = calculateHandType(hand);
            log.info("Hand: {}, HandType: {}", hand, handType);
        }

        private HandType calculateHandType(String hand)
        {
            Map<AlternativeCardType, Integer> charactersCounts = new HashMap<>();
            for (byte character : hand.getBytes(StandardCharsets.UTF_8))
            {
                AlternativeCardType cardType = AlternativeCardType.of((char) character);
                charactersCounts.put(cardType, charactersCounts.getOrDefault(cardType, 0) + 1);
            }
            return switch (charactersCounts.keySet().size())
            {
                case 1 -> HandType.FIVE_OF_A_KIND;
                case 2 ->
                        charactersCounts.getOrDefault(AlternativeCardType.JOKER, 0) > 0 ? HandType.FIVE_OF_A_KIND : charactersCounts.values().stream().anyMatch(count -> count == 4) ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE;

                case 3 -> charactersCounts.getOrDefault(AlternativeCardType.JOKER, 0) == 1 ?
                        (charactersCounts.values().stream().anyMatch(count -> count == 3) ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE) :
                        (charactersCounts.getOrDefault(AlternativeCardType.JOKER, 0) > 0 ? HandType.FOUR_OF_A_KIND :
                                charactersCounts.values().stream().anyMatch(count -> count == 3) ? HandType.THREE_OF_A_KIND : HandType.TWO_PAIR);
                case 4 ->
                        charactersCounts.getOrDefault(AlternativeCardType.JOKER, 0) > 0 ? HandType.THREE_OF_A_KIND : HandType.PAIR;
                default -> charactersCounts.getOrDefault(AlternativeCardType.JOKER, 0) > 0 ? HandType.PAIR : HandType.HIGH_CARD;
            };
        }

        @Override
        public int compareTo(AlternativeCardHand other)
        {
            int result = handType.ordinal() - other.handType.ordinal();
            if (result == 0)
            {
                for (int index = 0; index < hand.length() && result == 0; index++)
                {
                    result = AlternativeCardType.of(hand.charAt(index)).compareTo(AlternativeCardType.of(other.hand.charAt(index)));
                }
            }

            return result;
        }
    }

    private enum AlternativeCardType
    {
        JOKER("J"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("T"),
        QUEEN("Q"),
        KIND("K"),
        ACE("A");

        private final String character;

        AlternativeCardType(String character)
        {
            this.character = character;
        }

        private static AlternativeCardType of(char character)
        {
            return Arrays.stream(values())
                    .filter(type -> type.character.equals(character + ""))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No such card type"));
        }
    }
}
