package pl.mschielmann.aoc.year2023.day2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.naturalOrder;

@Slf4j
class Puzzle2Solver
{
    private static final Map<Colour, Integer> CUBES_COUNT = Map.of(
            Colour.RED, 12,
            Colour.GREEN, 13,
            Colour.BLUE, 14
    );
    private static final Pattern GAME_ID_PATTERN = Pattern.compile("\\d+:");
    private static final Pattern TURN_CHOICE_PATTERN = Pattern.compile("\\s\\d+\\s\\w+");

    private final String puzzleInput;

    Puzzle2Solver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    int solvePartOne()
    {
        return puzzleInput.lines()
                .map(this::createGameFrom)
                .filter(this::isGamePossible)
                .reduce(0, (partial, game) -> partial + game.id(), Integer::sum);
    }

    int solvePartTwo()
    {
        return puzzleInput.lines()
                .map(this::createGameFrom)
                .map(this::calculatePowerOfGame)
                .reduce(0, Integer::sum);
    }

    private int calculatePowerOfGame(Game game)
    {
        int minRed = game.turnChoices.stream().flatMap(Collection::stream)
                .filter(choice -> choice.colour.equals(Colour.RED))
                .map(choice -> choice.numberOfCubes)
                .max(naturalOrder()).orElse(0);
        int minGreen = game.turnChoices.stream().flatMap(Collection::stream)
                .filter(choice -> choice.colour.equals(Colour.GREEN))
                .map(choice -> choice.numberOfCubes)
                .max(naturalOrder()).orElse(0);
        int minBlue = game.turnChoices.stream().flatMap(Collection::stream)
                .filter(choice -> choice.colour.equals(Colour.BLUE))
                .map(choice -> choice.numberOfCubes)
                .max(naturalOrder()).orElse(0);

        log.info("mins: {}, {}, {}, {}", game, minRed, minGreen, minBlue);
        return minRed * minGreen * minBlue;
    }

    private Game createGameFrom(String line)
    {
        Matcher matcher = GAME_ID_PATTERN.matcher(line);
        if (!matcher.find())
        {
            throw new IllegalStateException("Game without id found!");
        }
        int id = Integer.parseInt(matcher.group(0).replace(":", ""));


        List<List<Choice>> allChoices = new ArrayList<>();

        String[] turns = line.split(";");
        Arrays.asList(turns).forEach(turn ->
        {
            Matcher turnMatcher = TURN_CHOICE_PATTERN.matcher(turn);
            List<Choice> turnChoices = new ArrayList<>();
            while (turnMatcher.find())
            {
                String turnDefinition = turnMatcher.group().trim();
                turnDefinition = turnDefinition.replace(",", "");
                String[] turnDefinitionParts = turnDefinition.split(" ");
                int numberOfCubes = Integer.parseInt(turnDefinitionParts[0]);
                Colour colour = Colour.valueOf(turnDefinitionParts[1].toUpperCase());
                Choice turnChoice = new Choice(colour, numberOfCubes);
                turnChoices.add(turnChoice);
            }
            allChoices.add(turnChoices);
        });


        Game game = new Game(id, allChoices);
        log.info("Game created: {}", game);
        return game;
    }

    private boolean isGamePossible(Game game)
    {
        return game.turnChoices.stream()
                .flatMap(Collection::stream)
                .noneMatch(turnChoice -> turnChoice.numberOfCubes > CUBES_COUNT.get(turnChoice.colour));
    }

    private record Game(int id, List<List<Choice>> turnChoices)
    {
    }

    private record Choice(Colour colour, int numberOfCubes)
    {
    }

    private enum Colour
    {
        RED, GREEN, BLUE
    }
}
