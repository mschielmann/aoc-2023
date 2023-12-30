package pl.mschielmann.aoc.year2023.day23;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

        List<String> lines = puzzleInput.lines().toList();
        Map<Coordinates, PathTile> pathTiles = createPathTiles(lines);
        Coordinates startCoordinates = new Coordinates(0, 1);
        Coordinates endCoordinates = new Coordinates(lines.size() - 1, lines.get(0).length() - 2);
        List<List<Coordinates>> reachedEnd = findAllPaths(startCoordinates, endCoordinates, pathTiles);
        return getLongestPathDistanceOutOf(reachedEnd) - 1;
    }

    long solvePartTwo()
    {
        List<String> lines = puzzleInput.replaceAll(">", ".")
                .replaceAll("<", ".")
                .replaceAll("\\^", ".")
                .replaceAll("v", ".")
                .lines()
                .toList();
        Map<Coordinates, PathTile> pathTiles = createPathTiles(lines);
        Coordinates startCoordinates = new Coordinates(0, 1);
        Coordinates endCoordinates = new Coordinates(lines.size() - 1, lines.get(0).length() - 2);
        List<List<Coordinates>> reachedEnd = findAllPaths(startCoordinates, endCoordinates, pathTiles);
        return getLongestPathDistanceOutOf(reachedEnd) - 1;
    }

    private Integer getLongestPathDistanceOutOf(List<List<Coordinates>> reachedEnd)
    {
        return reachedEnd.stream()
                .map(Collection::size)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("No paths to destination found!"));
    }

    private List<List<Coordinates>> findAllPaths(Coordinates startCoordinates,
                                                 Coordinates endCoordinates,
                                                 Map<Coordinates, PathTile> pathTiles)
    {
        List<List<Coordinates>> possiblePaths = new ArrayList<>();
        possiblePaths.add(List.of(startCoordinates));

        List<List<Coordinates>> pathsThatReachedDestination = new ArrayList<>();
        while (!possiblePaths.isEmpty())
        {
            List<Coordinates> currentPath = possiblePaths.removeLast();
            Coordinates currentTileCoordinates = currentPath.getLast();
            /*Map<Boolean, List<List<Coordinates>>> thoseWhoMadeItBeforeUs = pathsThatReachedDestination.stream()
                    .filter(pathToDestination -> pathToDestination.contains(currentTileCoordinates))
                    .collect(Collectors.partitioningBy(oneWhoMadeItFromHere -> oneWhoMadeItFromHere.indexOf(currentTileCoordinates) >= currentPath.indexOf(currentTileCoordinates)));
            List<List<Coordinates>> thoseWhoMadeItSlowerToThisPoint = thoseWhoMadeItBeforeUs.get(true);
            if (!thoseWhoMadeItSlowerToThisPoint.isEmpty()) {
                log.info("Slower: {}", thoseWhoMadeItSlowerToThisPoint.stream().map(a -> a.indexOf(currentTileCoordinates)).toList());
                log.info("Current: {}", currentPath.indexOf(currentTileCoordinates));
                continue;
            }
            List<List<Coordinates>> thoseWhoMadeItFasterToThisPoint = thoseWhoMadeItBeforeUs.get(false);
            if (!thoseWhoMadeItFasterToThisPoint.isEmpty()) {
                thoseWhoMadeItFasterToThisPoint.forEach(pathsThatReachedDestination::remove);
                log.info("{}", thoseWhoMadeItFasterToThisPoint.stream().map(Collection::size).toList());
                List<Coordinates> longestOfFasterToThisPoint = thoseWhoMadeItFasterToThisPoint.stream().min((path1, path2) -> path2.size() - path1.size()).orElseThrow();
                log.info("{}", longestOfFasterToThisPoint.size());
                currentPath.addAll(longestOfFasterToThisPoint.subList(longestOfFasterToThisPoint.indexOf(currentTileCoordinates) + 1, longestOfFasterToThisPoint.size()));
                pathsThatReachedDestination.add(currentPath);
                continue;
            }*/

            if (currentTileCoordinates.equals(endCoordinates))
            {
                pathsThatReachedDestination.add(currentPath);
                continue;
            }

            findCandidatePath(pathTiles, currentTileCoordinates::getToRight, SlopeType.TO_RIGHT)
                    .filter(candidate -> !currentPath.contains(candidate))
                    .ifPresent(candidate -> createCandidatePathBasedOn(candidate, currentPath, possiblePaths));
            findCandidatePath(pathTiles, currentTileCoordinates::getToLeft, SlopeType.TO_LEFT)
                    .filter(candidate -> !currentPath.contains(candidate))
                    .ifPresent(candidate -> createCandidatePathBasedOn(candidate, currentPath, possiblePaths));
            findCandidatePath(pathTiles, currentTileCoordinates::getUp, SlopeType.TO_UP)
                    .filter(candidate -> !currentPath.contains(candidate))
                    .ifPresent(candidate -> createCandidatePathBasedOn(candidate, currentPath, possiblePaths));
            findCandidatePath(pathTiles, currentTileCoordinates::getDown, SlopeType.TO_DOWN)
                    .filter(candidate -> !currentPath.contains(candidate))
                    .ifPresent(candidate -> createCandidatePathBasedOn(candidate, currentPath, possiblePaths));
        }
        return pathsThatReachedDestination;
    }

    private Map<Coordinates, PathTile> createPathTiles(List<String> lines)
    {
        Map<Coordinates, PathTile> pathTiles = new HashMap<>();
        List<Character> slopeLabels = Arrays.stream(SlopeType.values())
                .map(slopeType -> slopeType.label)
                .toList();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++)
        {
            String line = lines.get(lineIndex);
            for (int charIndex = 0; charIndex < line.length(); charIndex++)
            {
                char currentChar = line.charAt(charIndex);
                if (currentChar == '.')
                {
                    pathTiles.put(new Coordinates(lineIndex, charIndex), new PathTile(false, null));
                } else if (slopeLabels.contains(currentChar))
                {
                    pathTiles.put(new Coordinates(lineIndex, charIndex), new PathTile(true, SlopeType.fromLabel(currentChar)));
                }
            }
        }
        return pathTiles;
    }

    private Optional<Coordinates> findCandidatePath(Map<Coordinates, PathTile> pathTiles,
                                                    Supplier<Coordinates> neighbourSupplier,
                                                    SlopeType allowedSlopeType)
    {
        Coordinates neighbourCoordinates = neighbourSupplier.get();
        if (pathTiles.containsKey(neighbourCoordinates))
        {
            PathTile neighbour = pathTiles.get(neighbourCoordinates);
            if (neighbour.isSlope() && allowedSlopeType.equals(neighbour.slopeType()))
            {
                return Optional.of(neighbourCoordinates);
            } else if (!neighbour.isSlope())
            {
                return Optional.of(neighbourCoordinates);
            }
        }
        return Optional.empty();
    }

    private void createCandidatePathBasedOn(Coordinates candidate, List<Coordinates> currentPath, List<List<Coordinates>> possiblePaths)
    {
        List<Coordinates> candidatePath = new ArrayList<>(currentPath);
        candidatePath.add(candidate);
        possiblePaths.add(candidatePath);
    }

    private record PathTile(boolean isSlope, SlopeType slopeType)
    {
    }

    private enum SlopeType
    {
        TO_RIGHT('>'),
        TO_LEFT('<'),
        TO_UP('^'),
        TO_DOWN('v');

        private final char label;

        SlopeType(char label)
        {
            this.label = label;
        }

        private static SlopeType fromLabel(char label)
        {
            return Arrays.stream(values()).filter(value -> value.label == label).findFirst().orElseThrow(() -> new IllegalArgumentException("No such slope!"));
        }
    }

    private record Coordinates(int row, int column)
    {
        private Coordinates getToRight()
        {
            return new Coordinates(row, column + 1);
        }

        private Coordinates getToLeft()
        {
            return new Coordinates(row, column - 1);
        }

        private Coordinates getUp()
        {
            return new Coordinates(row - 1, column);
        }

        private Coordinates getDown()
        {
            return new Coordinates(row + 1, column);
        }
    }
}
