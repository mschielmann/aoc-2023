package pl.mschielmann.aoc.year2023.day8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
        List<String> input = puzzleInput.lines().collect(Collectors.toList());
        Instructions instructions = new Instructions(input.remove(0));
        input.remove(0);
        Map<String, Node> nodes = input.stream()
                .map(Node::new)
                .collect(Collectors.toMap(node -> node.name, Function.identity()));

        Node currentNode = nodes.values().stream()
                .filter(Node::isStart)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No starting node found!"));
        long numberOfStepsFromStartToFinish = 0;
        while (!currentNode.isEnd())
        {
            numberOfStepsFromStartToFinish++;
            if (instructions.nextIsLeft())
            {
                currentNode = nodes.get(currentNode.nextLeft);
            } else
            {
                currentNode = nodes.get(currentNode.nextRight);
            }
        }

        return numberOfStepsFromStartToFinish;
    }

    long solvePartTwo()
    {
        List<String> input = puzzleInput.lines().collect(Collectors.toList());
        Instructions instructions = new Instructions(input.remove(0));
        input.remove(0);
        Map<String, Node> nodes = input.stream()
                .map(Node::new)
                .collect(Collectors.toMap(node -> node.name, Function.identity()));

        Set<Node> startNodes = nodes.values().stream().filter(Node::isStartForGhosts).collect(Collectors.toSet());
        log.info("Start nodes: {}", startNodes.stream().map(node -> node.name).toList());
        Set<Node> endNodes = nodes.values().stream().filter(Node::isEndForGhosts).collect(Collectors.toSet());
        log.info("End nodes: {}", endNodes.stream().map(node -> node.name).toList());

        Map<Node, Map<Long, Long>> stepsToNodeByInstructionsCounter = endNodes.stream()
                .collect(Collectors.toMap(Function.identity(), (ignored) -> new HashMap<>()));
        for (Node startingNode : startNodes)
        {
            long steps = 0L;
            instructions.reset();
            Node currentNode = startingNode;
            while (true)
            {
                steps++;
                if (instructions.nextIsLeft())
                {
                    currentNode = nodes.get(currentNode.nextLeft);
                } else
                {
                    currentNode = nodes.get(currentNode.nextRight);
                }
                if (currentNode.isEndForGhosts())
                {
                    Long numberOfStepsBeforeCycle = stepsToNodeByInstructionsCounter.get(currentNode).getOrDefault(instructions.counter, -1L);
                    if (numberOfStepsBeforeCycle > 0)
                    {
                        log.info("Cycle for node {}, starts after: {}", currentNode.name, steps - stepsToNodeByInstructionsCounter.get(currentNode).get(instructions.counter));
                        break;
                    }
                    stepsToNodeByInstructionsCounter.get(currentNode).put(instructions.counter, steps);
                }
            }
        }
        log.info("{}", stepsToNodeByInstructionsCounter);
        var matchingStepNumber = stepsToNodeByInstructionsCounter.values().stream()
                .map(Map::values)
                .peek(value -> log.info("Values: {}", value))
                .reduce(new ArrayList<>(stepsToNodeByInstructionsCounter.values()).get(0).values(), (partial, current) ->
                {
                    partial.retainAll(current);
                    return partial;
                });

        return matchingStepNumber.stream().findFirst().orElse(-1L);
    }

    private static class Instructions
    {
        private final String instructions;
        private long counter = 0;

        private Instructions(String instructions)
        {
            this.instructions = instructions;
        }

        private boolean nextIsLeft()
        {
            int intCounter = Integer.valueOf("" + counter);
            var result = instructions.charAt(intCounter) == 'L';
            counter++;
            if (counter >= instructions.length())
            {
                counter = 0;
            }
            return result;
        }

        private void reset()
        {
            counter = 0;
        }
    }

    private static class Node
    {
        private final String name;
        private final String nextLeft;
        private final String nextRight;
        private final boolean isStart;
        private final boolean isEnd;
        private final boolean isStartForGhosts;
        private final boolean isEndForGhosts;

        private Node(String definition)
        {
            this.name = definition.substring(0, 3);
            this.nextLeft = definition.substring(7, 10);
            this.nextRight = definition.substring(12, 15);
            isStart = name.equals("AAA");
            isEnd = name.equals("ZZZ");
            isStartForGhosts = name.substring(2).equals("A");
            isEndForGhosts = name.substring(2).equals("Z");
        }

        private boolean isStart()
        {
            return isStart;
        }

        private boolean isEnd()
        {
            return isEnd;
        }

        private boolean isStartForGhosts()
        {
            return isStartForGhosts;
        }

        private boolean isEndForGhosts()
        {
            return isEndForGhosts;
        }
    }
}
