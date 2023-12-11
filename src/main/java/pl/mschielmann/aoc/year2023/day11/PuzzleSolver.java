package pl.mschielmann.aoc.year2023.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class PuzzleSolver
{
    private final String puzzleInput;
    private final long scaleIndex;

    PuzzleSolver(String puzzleInput, long scaleIndex)
    {
        this.puzzleInput = puzzleInput;
        this.scaleIndex = scaleIndex;
    }

    long solvePartOne()
    {
        GalaxyMap galaxyMap = GalaxyMap.buildGalaxy(puzzleInput);

        log.info("{}", galaxyMap.galaxiesWithCoordinates);
        long distance = 0;
        for (int galaxyIndex = 0; galaxyIndex < galaxyMap.galaxiesWithCoordinates.size(); galaxyIndex++) {
            Galaxy galaxy = galaxyMap.galaxiesWithCoordinates.get(galaxyIndex);
            for (int neighbourGalaxyIndex = galaxyIndex + 1; neighbourGalaxyIndex < galaxyMap.galaxiesWithCoordinates.size(); neighbourGalaxyIndex++) {
                Galaxy neighbour = galaxyMap.galaxiesWithCoordinates.get(neighbourGalaxyIndex);
                int startingGalaxyRowIndex = Math.min(galaxy.row, neighbour.row);
                for (int rowIndex = startingGalaxyRowIndex + 1; rowIndex <= startingGalaxyRowIndex + Math.abs(galaxy.row - neighbour.row); rowIndex++) {
                    if (galaxyMap.scaledRows.contains(rowIndex)) {
                        distance += scaleIndex;
                    } else {
                        distance += 1;
                    }
                }
                int startingGalaxyColumnIndex = Math.min(galaxy.column, neighbour.column);
                for (int columnIndex = startingGalaxyColumnIndex + 1; columnIndex <= startingGalaxyColumnIndex + Math.abs(galaxy.column - neighbour.column); columnIndex++) {
                    if (galaxyMap.scaledColumns.contains(columnIndex)) {
                        distance += scaleIndex;
                    } else {
                        distance += 1;
                    }
                }
            }
        }
        return distance;
    }

    long solvePartTwo()
    {
        return -1;
    }

    private record GalaxyMap(List<List<Integer>> map, List<Galaxy> galaxiesWithCoordinates, List<Integer> scaledRows,
                             List<Integer> scaledColumns) {

        private static GalaxyMap buildGalaxy(String input) {
            List<List<Integer>> map = new ArrayList<>();
            List<Integer> scaledRows = new ArrayList<>();
            int rowIndex = 0;
            for(String line : input.lines().toList()) {
                boolean galaxiesInRow = false;
                List<Integer> row = new ArrayList<>();
                for(int index = 0; index < line.length(); index++) {
                    boolean isGalaxy = line.charAt(index) == '#';
                    if (isGalaxy) {
                        galaxiesInRow = true;
                    }
                    row.add(isGalaxy ? 1 : 0);
                }
                map.add(row);
                if (!galaxiesInRow) {
                    scaledRows.add(rowIndex);
                }
                rowIndex++;
            }

            List<Integer> scaledColumns = new ArrayList<>();
            int numberOfColumns = map.get(0).size();
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                boolean galaxiesInColumn = false;
                for (List<Integer> row : map)
                {
                    if (row.get(columnIndex) == 1)
                    {
                        galaxiesInColumn = true;
                        break;
                    }
                }
                if (!galaxiesInColumn) {
                    scaledColumns.add(columnIndex);
                }
            }

            List<Galaxy> galaxiesWithCoordinates = new ArrayList<>();
            for (int row = 0; row < map.size(); row++) {
                for (int column = 0; column < map.get(row).size(); column++)
                {
                    if (map.get(row).get(column) == 1) {
                        galaxiesWithCoordinates.add(new Galaxy(row, column));
                    }
                }
            }
            return new GalaxyMap(map, galaxiesWithCoordinates, scaledRows, scaledColumns);
        }
    }

    record Galaxy(int row, int column) {

    }
}
