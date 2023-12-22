package pl.mschielmann.aoc.year2023.day17;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

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
        TrafficMap trafficMap = TrafficMap.buildUsing(puzzleInput);
        return -1L;
    }

    long solvePartTwo()
    {
        return -1;
    }

    private record TrafficMap(Map<Integer, Map<Integer, City>> trafficMap)
    {

        private City firstCity()
        {
            return trafficMap.get(0).get(0);
        }

        private City lastCity()
        {
            Map<Integer, City> lastRow = trafficMap.get(trafficMap.size() - 1);
            return lastRow.get(lastRow.size() - 1);
        }

        private Optional<City> getNextCityFor(City lastCity, Direction direction)
        {
            Integer nextRow = direction.getRowConversion().apply(lastCity.rowIndex());
            Integer nextColumn = direction.getColumnConversion().apply(lastCity.columnIndex());
            return Optional.of(trafficMap.getOrDefault(nextRow, new HashMap<>()).get(nextColumn));
        }

        private static TrafficMap buildUsing(String input)
        {
            Map<Integer, Map<Integer, City>> trafficMap = new HashMap<>();
            List<String> rows = input.lines().toList();
            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++)
            {
                String row = rows.get(rowIndex);
                for (int columnIndex = 0; columnIndex < row.length(); columnIndex++)
                {
                    trafficMap.putIfAbsent(rowIndex, new HashMap<>());
                    int energyLossValue = Integer.parseInt(row.substring(columnIndex, columnIndex + 1));
                    trafficMap.get(rowIndex).put(columnIndex, new City(rowIndex, columnIndex, energyLossValue));
                }
            }
            trafficMap.values().forEach(row -> log.info("{}", row));
            return new TrafficMap(trafficMap);
        }
    }

    private record City(int rowIndex, int columnIndex, int energyLossValue)
    {

    }

    private record Path(List<City> visitedCities, Direction lastDirection, int lastDirectionCount)
    {
        List<Path> possiblePaths(TrafficMap trafficMap)
        {
            City lastCity = visitedCities.getLast();
            return Arrays.stream(Direction.values())
                    .filter(lastDirection::isNotOpposite)
                    .filter(direction -> lastDirectionCount < 3 || !direction.equals(lastDirection))
                    .map(direction ->
                    {
                        City nextCity = trafficMap.getNextCityFor(lastCity, direction).orElse(null);
                        if (nextCity == null)
                        {
                            return null;
                        }
                        visitedCities.add(nextCity);
                        return new Path(visitedCities, direction, lastDirection.equals(direction) ? lastDirectionCount + 1 : 0);
                    })
                    .toList();

        }

        long currentCost()
        {
            return visitedCities.stream()
                    .mapToLong(city -> (long) city.energyLossValue())
                    .sum();
        }
    }

    @Getter
    private enum Direction
    {
        UP(x -> x - 1, y -> y),
        DOWN(x -> x + 1, y -> y),
        LEFT(x -> x, y -> y - 1),
        RIGHT(x -> x, y -> y + 1);

        private final UnaryOperator<Integer> rowConversion;
        private final UnaryOperator<Integer> columnConversion;

        Direction(UnaryOperator<Integer> rowConversion, UnaryOperator<Integer> columnConversion)
        {
            this.rowConversion = rowConversion;
            this.columnConversion = columnConversion;
        }

        public boolean isNotOpposite(Direction direction)
        {
            return !List.of(UP, DOWN).containsAll(List.of(this, direction))
                    && List.of(LEFT, RIGHT).containsAll(List.of(this, direction));
        }

        public static Direction between(City last, City city)
        {
            if (last.rowIndex() != city.rowIndex())
            {
                if (last.rowIndex < city.rowIndex)
                {
                    return UP;
                } else
                {
                    return DOWN;
                }
            }

            if (last.columnIndex() != city.columnIndex())
            {
                if (last.columnIndex < city.columnIndex)
                {
                    return LEFT;
                } else
                {
                    return RIGHT;
                }
            }
            return null;
        }

    }

}
