package pl.mschielmann.aoc.year2023.day5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.lang.Long.parseLong;

@Slf4j
class PuzzleSolver
{
    private static final Pattern MAPPER_START_PATTERN = Pattern.compile("(\\w+)-to-(\\w+).*");

    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        List<String> gardeningInstructions = puzzleInput.lines().collect(Collectors.toList());
        String seedsDefinition = gardeningInstructions.remove(0);
        List<Long> seeds = parseSeedNumbersForPartOne(seedsDefinition);

        Map<String, MappingRanges> sourceMappings = parseMappingDefinitions(gardeningInstructions).stream()
                .collect(Collectors.toMap(range -> range.source, Function.identity()));

        return getMinimumLocationFor(seeds, sourceMappings);
    }

    private long getMinimumLocationFor(List<Long> seeds, Map<String, MappingRanges> sourceMappings)
    {
        return seeds.stream()
                .map(seed -> sourceMappings.get("seed").getDestinationFor(seed, sourceMappings))
                .mapToLong(Long::valueOf)
                .min()
                .orElse(-1L);
    }

    long solvePartTwo()
    {
        List<String> gardeningInstructions = puzzleInput.lines().collect(Collectors.toList());
        String seedsDefinition = gardeningInstructions.remove(0);
        List<SeedRange> seedRanges = parseSeedNumbersForPartTwo(seedsDefinition);

        Map<String, MappingRanges> sourceMappings = parseMappingDefinitions(gardeningInstructions).stream()
                .collect(Collectors.toMap(range -> range.source, Function.identity()));

        Map<String, MappingRanges> destinationMappings = parseMappingDefinitions(gardeningInstructions).stream()
                .collect(Collectors.toMap(range -> range.destination, Function.identity()));

        long seedWithingStartingRangeForTheMinLocationNumber = LongStream.range(0, Long.MAX_VALUE)
                .map(location -> destinationMappings.get("location").getSourceFor(location, destinationMappings))
                .filter(seed -> seedRanges.stream().anyMatch(range -> range.contains(seed)))
                .findFirst()
                .orElse(-1);

        return getMinimumLocationFor(List.of(seedWithingStartingRangeForTheMinLocationNumber), sourceMappings);
    }

    private List<Long> parseSeedNumbersForPartOne(String definition)
    {
        return Arrays.stream(definition.replaceAll("seeds: ", "").split(" "))
                .map(Long::parseLong)
                .toList();
    }

    private List<SeedRange> parseSeedNumbersForPartTwo(String definition)
    {
        List<SeedRange> seedRanges = new ArrayList<>();
        List<Long> ranges = parseSeedNumbersForPartOne(definition);
        for (int index = 0; index < ranges.size(); index++)
        {
            if (index % 2 == 1)
            {
                seedRanges.add(new SeedRange(ranges.get(index - 1), ranges.get(index)));
            }
        }
        return seedRanges;
    }

    private List<MappingRanges> parseMappingDefinitions(List<String> gardeningInstructions)
    {
        List<MappingRanges> mappingRanges = new ArrayList<>();
        MappingRanges currentMappingRanges = null;
        for (String instruction : gardeningInstructions)
        {
            if (instruction.isBlank())
            {
                if (currentMappingRanges != null)
                {
                    mappingRanges.add(currentMappingRanges);
                }
                continue;
            }
            Matcher matcher = MAPPER_START_PATTERN.matcher(instruction);
            if (matcher.matches())
            {
                currentMappingRanges = new MappingRanges(matcher.group(1), matcher.group(2), new ArrayList<>());
                continue;
            }
            String[] rangeDefinition = instruction.split(" ");
            Objects.requireNonNull(currentMappingRanges, "No range definition has been found!");
            currentMappingRanges.mappingRanges.add(
                    new MappingRange(parseLong(rangeDefinition[1]), parseLong(rangeDefinition[0]), parseLong(rangeDefinition[2]))
            );
        }
        if (!mappingRanges.contains(currentMappingRanges))
        {
            mappingRanges.add(currentMappingRanges);
        }
        return mappingRanges;
    }

    private record MappingRange(long sourceRangeStart, long destinationRangeStart, long rangeLength)
    {
        private boolean isSourceWithin(long sourceNumber)
        {
            return sourceNumber >= sourceRangeStart && sourceNumber < sourceRangeStart + rangeLength;
        }

        private long getDestinationFor(long sourceNumber)
        {
            return destinationRangeStart + (sourceNumber - sourceRangeStart);
        }

        private boolean isDestinationWithin(long destinationNumber)
        {
            return destinationNumber >= destinationRangeStart && destinationNumber < destinationRangeStart + rangeLength;
        }

        private long getSourceFor(long destinationNumber)
        {
            return sourceRangeStart + (destinationNumber - destinationRangeStart);
        }
    }

    private record MappingRanges(String source, String destination, List<MappingRange> mappingRanges)
    {
        private long getDestinationFor(long sourceNumber, Map<String, MappingRanges> allRanges)
        {
            long mappedValue = mappingRanges.stream()
                    .filter(mappingRange -> mappingRange.isSourceWithin(sourceNumber))
                    .map(mappingRange -> mappingRange.getDestinationFor(sourceNumber))
                    .findFirst()
                    .orElse(sourceNumber);
            if (!allRanges.containsKey(destination))
            {
                return mappedValue;
            }
            return allRanges.get(destination).getDestinationFor(mappedValue, allRanges);
        }

        private long getSourceFor(long destinationNumber, Map<String, MappingRanges> allRanges)
        {
            long mappedValue = mappingRanges.stream()
                    .filter(mappingRange -> mappingRange.isDestinationWithin(destinationNumber))
                    .map(mappingRange -> mappingRange.getSourceFor(destinationNumber))
                    .findFirst()
                    .orElse(destinationNumber);
            if (!allRanges.containsKey(source))
            {
                return mappedValue;
            }
            return allRanges.get(source).getSourceFor(mappedValue, allRanges);
        }
    }

    private static final class SeedRange
    {
        private final Long start;
        private final Long end;

        private SeedRange(Long start, Long length)
        {
            this.start = start;
            this.end = start + length;
        }

        private boolean contains(Long number)
        {
            return number >= start && number < end;
        }
    }
}
