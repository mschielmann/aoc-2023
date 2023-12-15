package pl.mschielmann.aoc.year2023.day15;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
        return getInstructionParts().stream()
                .map(this::calculateHash)
                .mapToLong(Long::valueOf)
                .sum();
    }

    long solvePartTwo()
    {
        List<List<Lens>> boxes = buildEmptyBoxes();
        List<String> instructions = getInstructionParts();

        setUpLensesInBoxesBasedOn(instructions, boxes);
        return calculateTotalLensPower(boxes);
    }

    private List<String> getInstructionParts()
    {
        return Arrays.stream(puzzleInput.split(","))
                .filter(input -> !input.isEmpty())
                .map(String::trim)
                .toList();
    }

    private int calculateHash(String toBeHashed)
    {
        int result = 0;
        for (int index = 0; index < toBeHashed.length(); index++)
        {
            result += (int) toBeHashed.charAt(index);
            result *= 17;
            result %= 256;
        }
        return result;
    }

    private List<List<Lens>> buildEmptyBoxes()
    {
        return IntStream.range(0, 256)
                .mapToObj(ignored -> (List<Lens>) new LinkedList<Lens>())
                .toList();
    }

    private void setUpLensesInBoxesBasedOn(List<String> instructions, List<List<Lens>> boxes)
    {
        for (String instruction : instructions)
        {
            int hash = calculateHash(instruction.replaceAll("-", "").split("=")[0]);
            List<Lens> box = boxes.get(hash);
            if (isRemovalInstruction(instruction))
            {
                box.remove(new Lens(instruction.replaceAll("-", ""), 0));
            } else
            {
                Lens lens = new Lens(instruction.split("=")[0], Integer.parseInt(instruction.split("=")[1]));
                if (box.contains(lens))
                {
                    box.set(box.indexOf(lens), lens);
                } else
                {
                    box.add(lens);
                }
            }
        }
    }

    private boolean isRemovalInstruction(String instruction)
    {
        return instruction.endsWith("-");
    }

    private long calculateTotalLensPower(List<List<Lens>> boxes)
    {
        long result = 0;

        for (int boxIndex = 0; boxIndex < boxes.size(); boxIndex++)
        {
            List<Lens> box = boxes.get(boxIndex);
            for (int lensIndex = 0; lensIndex < box.size(); lensIndex++)
            {
                result += (boxIndex + 1) * (lensIndex + 1) * box.get(lensIndex).strength;
            }
        }

        return result;
    }

    private record Lens(String label, long strength)
    {

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lens lens = (Lens) o;
            return Objects.equals(label, lens.label);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(label);
        }
    }
}
