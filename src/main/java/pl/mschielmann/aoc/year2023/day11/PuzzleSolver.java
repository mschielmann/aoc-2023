package pl.mschielmann.aoc.year2023.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
class PuzzleSolver
{
    private final String puzzleInput;
    private final long scaleIndex;

    PuzzleSolver(String spaceMap, long emptySpaceScaleIndex)
    {
        this.puzzleInput = spaceMap;
        this.scaleIndex = emptySpaceScaleIndex;
    }

    long solve()
    {
        GalaxyMap galaxyMap = GalaxyMap.buildGalaxy(puzzleInput);

        long distance = 0;
        for (int galaxyIndex = 0; galaxyIndex < galaxyMap.galaxiesWithCoordinates.size(); galaxyIndex++)
        {
            Galaxy galaxy = galaxyMap.galaxiesWithCoordinates.get(galaxyIndex);
            for (int neighbourGalaxyIndex = galaxyIndex + 1; neighbourGalaxyIndex < galaxyMap.galaxiesWithCoordinates.size(); neighbourGalaxyIndex++)
            {
                Galaxy neighbour = galaxyMap.galaxiesWithCoordinates.get(neighbourGalaxyIndex);
                distance += calculateDistance(galaxy.row, neighbour.row, galaxyMap.rowsWithoutGalaxies);
                distance += calculateDistance(galaxy.column, neighbour.column, galaxyMap.columnsWithoutGalaxies);
            }
        }
        return distance;
    }

    private long calculateDistance(int galaxyCoordinate, int neighbourCoordinate, List<Integer> scaledCoordinates)
    {
        int startingGalaxyRowIndex = Math.min(galaxyCoordinate, neighbourCoordinate);
        return IntStream.range(startingGalaxyRowIndex + 1, startingGalaxyRowIndex + 1 + Math.abs(galaxyCoordinate - neighbourCoordinate))
                .boxed()
                .reduce(0L, (partial, index) -> partial + (scaledCoordinates.contains(index) ? scaleIndex : 1L), Long::sum);
    }

    private record GalaxyMap(List<Galaxy> galaxiesWithCoordinates,
                             List<Integer> rowsWithoutGalaxies,
                             List<Integer> columnsWithoutGalaxies)
    {

        private static final char GALAXY_SYMBOL = '#';

        private static GalaxyMap buildGalaxy(String input)
        {
            List<String> lines = input.lines().toList();
            int rowCount = lines.size();
            int columnCount = lines.get(0).length();

            List<Galaxy> galaxiesWithCoordinates = new ArrayList<>();
            Set<Integer> rowsWithGalaxies = new HashSet<>();
            Set<Integer> columnWithGalaxies = new HashSet<>();
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++)
            {
                String line = lines.get(rowIndex);
                for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
                {
                    if (line.charAt(columnIndex) == GALAXY_SYMBOL)
                    {
                        galaxiesWithCoordinates.add(new Galaxy(rowIndex, columnIndex));
                        rowsWithGalaxies.add(rowIndex);
                        columnWithGalaxies.add(columnIndex);
                    }
                }
            }

            List<Integer> rowsWithoutGalaxies = IntStream.range(0, rowCount)
                    .filter(index -> !rowsWithGalaxies.contains(index))
                    .boxed().toList();
            List<Integer> columnWithoutGalaxies = IntStream.range(0, columnCount)
                    .filter(index -> !columnWithGalaxies.contains(index))
                    .boxed().toList();

            return new GalaxyMap(galaxiesWithCoordinates, rowsWithoutGalaxies, columnWithoutGalaxies);
        }
    }

    record Galaxy(int row, int column)
    {

    }
}
