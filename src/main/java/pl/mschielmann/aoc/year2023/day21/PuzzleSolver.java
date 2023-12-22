package pl.mschielmann.aoc.year2023.day21;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
class PuzzleSolver
{
    private final String puzzleInput;
    private final int numberOfSteps;

    PuzzleSolver(String puzzleInput, int numberOfSteps)
    {
        this.puzzleInput = puzzleInput;
        this.numberOfSteps = numberOfSteps;
    }

    long solvePartOne()
    {
        MapOfGardens mapOfGardens = MapOfGardens.createFrom(puzzleInput.lines().toList());
        Map<Integer, Set<Integer>> currentPositions = new HashMap<>();
        for (int rowCounter = 0; rowCounter < mapOfGardens.layout.size(); rowCounter++)
        {
            currentPositions.put(rowCounter, new HashSet<>());
        }
        currentPositions.get(mapOfGardens.startingRow()).add(mapOfGardens.startingColumn());
        for (int stepCounter = 0; stepCounter < numberOfSteps; stepCounter++)
        {
            currentPositions = move(currentPositions, mapOfGardens);
        }
        return currentPositions.values().stream().mapToLong(Collection::size).sum();
    }

    BigDecimal solvePartTwo()
    {
        MapOfGardens mapOfGardens = MapOfGardens.createFrom(puzzleInput.lines().toList());
        Map<Integer, Map<Integer, BigDecimal>> currentPositions = new HashMap<>();
        for (int rowCounter = 0; rowCounter < mapOfGardens.layout.size(); rowCounter++)
        {
            currentPositions.put(rowCounter, new HashMap<>());
            for (int columnNumber : mapOfGardens.layout.get(rowCounter))
            {
                currentPositions.get(rowCounter).put(columnNumber, BigDecimal.ZERO);
            }
        }
        currentPositions.get(mapOfGardens.startingRow()).put(mapOfGardens.startingColumn(), BigDecimal.ONE);
        for (int stepCounter = 0; stepCounter < numberOfSteps; stepCounter++)
        {
            try
            {
                currentPositions = move2(currentPositions, mapOfGardens);
            }
            catch (NullPointerException e)
            {
                log.info("{}", stepCounter);
                log.info("{}", currentPositions);
                throw e;
            }
        }
        return currentPositions.values().stream().map(columns -> columns.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<Integer, Set<Integer>> move(Map<Integer, Set<Integer>> currentPositions, MapOfGardens mapOfGardens)
    {
        Map<Integer, Set<Integer>> newPositions = new HashMap<>();
        for (int rowCounter = 0; rowCounter < mapOfGardens.layout.size(); rowCounter++)
        {
            newPositions.put(rowCounter, new HashSet<>());
        }

        for (int rowCounter : currentPositions.keySet())
        {
            //log.info("{}-{}", rowCounter, currentPositions.get(rowCounter));
            for (int columnIndex : currentPositions.get(rowCounter))
            {
                if (mapOfGardens.hasGardenAt(rowCounter - 1, columnIndex))
                {
                    newPositions.putIfAbsent(rowCounter - 1, new HashSet<>());
                    newPositions.get(rowCounter - 1).add(columnIndex);
                }
                if (mapOfGardens.hasGardenAt(rowCounter + 1, columnIndex))
                {
                    newPositions.putIfAbsent(rowCounter + 1, new HashSet<>());
                    newPositions.get(rowCounter + 1).add(columnIndex);
                }
                if (mapOfGardens.hasGardenAt(rowCounter, columnIndex - 1))
                {
                    newPositions.get(rowCounter).add(columnIndex - 1);
                }
                if (mapOfGardens.hasGardenAt(rowCounter, columnIndex + 1))
                {
                    newPositions.get(rowCounter).add(columnIndex + 1);
                }
            }
        }

        return newPositions;
    }

    private Map<Integer, Map<Integer, BigDecimal>> move2(Map<Integer, Map<Integer, BigDecimal>> currentPositionsWithCount, MapOfGardens mapOfGardens)
    {
        Map<Integer, Map<Integer, BigDecimal>> newPositions = new HashMap<>();
        for (int rowCounter = 0; rowCounter < currentPositionsWithCount.size(); rowCounter++)
        {
            newPositions.put(rowCounter, new HashMap<>());
            for (int columnIndex : currentPositionsWithCount.get(rowCounter).keySet())
            {
                newPositions.get(rowCounter).put(columnIndex, currentPositionsWithCount.get(rowCounter).get(columnIndex));
            }
        }
        //log.info("new move");
        for (int rowCounter = 0; rowCounter < currentPositionsWithCount.size(); rowCounter++)
        {
            for (int columnNumber : currentPositionsWithCount.get(rowCounter).keySet())
            {
                BigDecimal count = currentPositionsWithCount.get(rowCounter).get(columnNumber);
                if (count.equals(BigDecimal.ZERO))
                {
                    continue;
                }
                //log.info("{}, {}", rowCounter, columnNumber);
                newPositions.get(rowCounter).put(columnNumber, newPositions.get(rowCounter).get(columnNumber).subtract(count));
                var newRow = mapOfGardens.getRowIndexFor(rowCounter - 1);
                var newColumn = columnNumber;
                if (mapOfGardens.hasGardenAt(newRow, newColumn))
                {
                    newPositions.get(newRow).put(newColumn, newPositions.get(newRow).get(newColumn).add(count));
                }
                newRow = mapOfGardens.getRowIndexFor(rowCounter + 1);
                newColumn = columnNumber;
                if (mapOfGardens.hasGardenAt(newRow, newColumn))
                {
                    newPositions.get(newRow).put(newColumn, newPositions.get(newRow).get(newColumn).add(count));
                }
                newRow = rowCounter;
                newColumn = mapOfGardens.getColumnIndexFor(columnNumber + 1);
                if (mapOfGardens.hasGardenAt(newRow, newColumn))
                {
                    newPositions.get(newRow).put(newColumn, newPositions.get(newRow).get(newColumn).add(count));
                }
                newRow = rowCounter;
                newColumn = mapOfGardens.getColumnIndexFor(columnNumber - 1);
                if (mapOfGardens.hasGardenAt(newRow, newColumn))
                {
                    newPositions.get(newRow).put(newColumn, newPositions.get(newRow).get(newColumn).add(count));
                }
            }
        }
        return newPositions;
    }

    private record MapOfGardens(Map<Integer, Set<Integer>> layout, int startingRow, int startingColumn,
                                int numberOfColumns)
    {
        private static MapOfGardens createFrom(List<String> layoutRows)
        {
            Map<Integer, Set<Integer>> layout = new HashMap<>();
            int rowCounter = 0;
            int startingRow = -1;
            int startingColumn = -1;
            for (String row : layoutRows)
            {
                layout.put(rowCounter, new HashSet<>());
                for (int columnIndex = 0; columnIndex < row.length(); columnIndex++)
                {
                    if (row.charAt(columnIndex) == '.')
                    {
                        layout.get(rowCounter).add(columnIndex);
                    } else if (row.charAt(columnIndex) == 'S')
                    {
                        layout.get(rowCounter).add(columnIndex);
                        startingRow = rowCounter;
                        startingColumn = columnIndex;
                    }
                }
                rowCounter++;
            }

            return new MapOfGardens(layout, startingRow, startingColumn, layoutRows.get(0).length());
        }

        public boolean hasGardenAt(int rowIndex, int columnIndex)
        {
            return layout.containsKey(rowIndex) && layout.get(rowIndex).contains(columnIndex);
        }

        public Integer getRowIndexFor(int newRowIndex)
        {
            if (newRowIndex < 0)
            {
                return layout.size() - 1;
            }
            if (newRowIndex >= layout.size())
            {
                return 0;
            }
            return newRowIndex;
        }

        public Integer getColumnIndexFor(int newColumnIndex)
        {
            if (newColumnIndex < 0)
            {
                return numberOfColumns - 1;
            }
            if (newColumnIndex >= numberOfColumns)
            {
                return 0;
            }

            return newColumnIndex;
        }
    }
}
