package pl.mschielmann.aoc.year2023.day20;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class PuzzleSolver
{
    private static final String BROADCASTER_MODULE_NAME = "broadcast";
    private static final String BUTTON = "button";
    private static final String OUTPUT_MODULE_NAME = "rx";
    private final Map<String, Long> buttonClicksUntilReachedHighPulsePerModule = new HashMap<>();
    private final List<Runnable> queue = new ArrayList<>();
    private final String puzzleInput;

    private long lowPulseCounter = 0;
    private long highPulseCounter = 0;
    private long buttonClicks = 0;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        Module broadcaster = createModules(puzzleInput).get(BROADCASTER_MODULE_NAME);

        IntStream.range(0, 1000)
                .forEach(ignored ->
                {
                    queue.add(() -> broadcaster.receiveSignal(BUTTON, SignalType.LOW));
                    while (!queue.isEmpty())
                    {
                        queue.remove(0).run();
                    }
                });
        return lowPulseCounter * highPulseCounter;
    }

    BigDecimal solvePartTwo()
    {
        Map<String, Module> moduleMap = createModules(puzzleInput);
        Module broadcaster = moduleMap.get(BROADCASTER_MODULE_NAME);
        List<String> allConnectingToOutput = moduleMap.values().stream()
                .filter(module -> module.getConnectedModuleNames().contains(OUTPUT_MODULE_NAME))
                .map(Module::getName)
                .toList();
        List<String> allConnectingToThoseConnectingToModule = moduleMap.values().stream()
                .filter(module -> module.getConnectedModuleNames().stream()
                        .anyMatch(allConnectingToOutput::contains))
                .map(Module::getName)
                .toList();

        while (!buttonClicksUntilReachedHighPulsePerModule.keySet().containsAll(allConnectingToThoseConnectingToModule))
        {
            buttonClicks++;
            queue.add(() -> broadcaster.receiveSignal(BUTTON, SignalType.LOW));
            while (!queue.isEmpty())
            {
                queue.remove(0).run();
            }
        }

        return buttonClicksUntilReachedHighPulsePerModule.entrySet().stream()
                .filter(entry -> allConnectingToThoseConnectingToModule.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    private Map<String, Module> createModules(String puzzleInput)
    {
        Map<String, Module> moduleMap = new HashMap<>();
        Set<String> allModuleNames = new HashSet<>();
        Map<String, Module> modulesByName = puzzleInput.lines()
                .map(line ->
                {
                    List<String> connectedModuleNames = Arrays.asList(line.split(" -> ")[1].split(", "));
                    allModuleNames.addAll(connectedModuleNames);
                    String moduleName = line.split(" -> ")[0]
                            .replaceAll("%", "")
                            .replaceAll("&", "");
                    if (line.startsWith("broadcast"))
                    {
                        return new BroadcastModule(connectedModuleNames, moduleMap);
                    } else if (line.startsWith("%"))
                    {
                        return new FlipFlopModule(moduleName, connectedModuleNames, moduleMap);
                    } else if (line.startsWith("&"))
                    {
                        return new ConjunctionModule(moduleName, connectedModuleNames, moduleMap);
                    } else
                    {
                        throw new IllegalArgumentException("Unknown module type for: " + line);
                    }
                })
                .collect(Collectors.toMap(Module::getName, Function.identity()));
        moduleMap.putAll(modulesByName);
        allModuleNames.stream()
                .filter(name -> !moduleMap.containsKey(name))
                .findFirst()
                .ifPresent(outputModuleName -> moduleMap.put(outputModuleName, new OutputModule(outputModuleName)));

        moduleMap.values().forEach(Module::introduceSelf);
        return moduleMap;
    }

    private interface Module
    {
        String getName();
        List<String> getConnectedModuleNames();

        void receiveSignal(String from, SignalType signalType);

        default void introduceSelf()
        {

        }

        default void registerSender(String name)
        {
        }
    }

    private class BroadcastModule implements Module
    {
        private final String name = "broadcast";
        private final List<String> connectedModuleNames;
        private final Map<String, Module> moduleMap;

        private BroadcastModule(List<String> connectedModuleNames, Map<String, Module> moduleMap)
        {
            this.connectedModuleNames = connectedModuleNames;
            this.moduleMap = moduleMap;
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public List<String> getConnectedModuleNames() {
            return connectedModuleNames;
        }

        @Override
        public void receiveSignal(String from, SignalType signalType)
        {
            //log.info("Received signal from: {}, to: {}, type: {}", from, name, signalType);
            if (SignalType.LOW.equals(signalType))
            {
                lowPulseCounter++;
            } else
            {
                highPulseCounter++;
            }
            connectedModuleNames.forEach(moduleName -> queue.add(() -> moduleMap.get(moduleName).receiveSignal(name, signalType)));
        }

        @Override
        public void introduceSelf()
        {
            connectedModuleNames.forEach(moduleName -> moduleMap.get(moduleName).registerSender(name));
        }
    }

    private class OutputModule implements Module
    {
        private final String name;

        private OutputModule(String name)
        {
            this.name = name;
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public List<String> getConnectedModuleNames() {
            return new ArrayList<>();
        }

        @Override
        public void receiveSignal(String from, SignalType signalType)
        {
            //log.info("Received signal from: {}, to: {}, type: {}", from, name, signalType);
            if (SignalType.LOW.equals(signalType))
            {
                lowPulseCounter++;
            } else
            {
                highPulseCounter++;
            }
        }
    }

    private class FlipFlopModule implements Module
    {
        private final String name;
        private final List<String> connectedModuleNames;
        private final Map<String, Module> moduleMap;
        private boolean on = false;

        private FlipFlopModule(String name, List<String> connectedModuleNames, Map<String, Module> moduleMap)
        {
            this.name = name;
            this.connectedModuleNames = connectedModuleNames;
            this.moduleMap = moduleMap;
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public List<String> getConnectedModuleNames() {
            return connectedModuleNames;
        }

        @Override
        public void receiveSignal(String from, SignalType signalType)
        {
            //log.info("Received signal from: {}, to: {}, type: {}", from, name, signalType);
            if (SignalType.LOW.equals(signalType))
            {
                lowPulseCounter++;
            } else
            {
                highPulseCounter++;
            }
            if (SignalType.HIGH.equals(signalType))
            {
                return;
            }
            on = !on;
            SignalType signalToSend = on ? SignalType.HIGH : SignalType.LOW;
            connectedModuleNames.forEach(moduleName -> queue.add(() -> moduleMap.get(moduleName).receiveSignal(name, signalToSend)));
        }

        @Override
        public void introduceSelf()
        {
            connectedModuleNames.forEach(moduleName -> moduleMap.get(moduleName).registerSender(name));
        }
    }

    private class ConjunctionModule implements Module
    {
        private final String name;
        private final List<String> connectedModuleNames;
        private final Map<String, Module> moduleMap;
        private final Map<String, SignalType> latestIncomingSignals = new HashMap<>();

        private ConjunctionModule(String name, List<String> connectedModuleNames, Map<String, Module> moduleMap)
        {
            this.name = name;
            this.connectedModuleNames = connectedModuleNames;
            this.moduleMap = moduleMap;
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public List<String> getConnectedModuleNames() {
            return connectedModuleNames;
        }

        @Override
        public void receiveSignal(String from, SignalType signalType)
        {
            //log.info("Received signal from: {}, to: {}, type: {}", from, name, signalType);
            if (SignalType.LOW.equals(signalType))
            {
                lowPulseCounter++;
            } else
            {
                highPulseCounter++;
            }
            latestIncomingSignals.put(from, signalType);
            SignalType signalToSend = latestIncomingSignals.values().stream()
                    .allMatch(SignalType.HIGH::equals) ? SignalType.LOW : SignalType.HIGH;

            if (signalToSend.equals(SignalType.HIGH)) {
                buttonClicksUntilReachedHighPulsePerModule.putIfAbsent(name, buttonClicks);
            }
            connectedModuleNames.forEach(moduleName -> queue.add(() -> moduleMap.get(moduleName).receiveSignal(name, signalToSend)));
        }

        @Override
        public void introduceSelf()
        {
            connectedModuleNames.forEach(moduleName -> moduleMap.get(moduleName).registerSender(name));
        }

        @Override
        public void registerSender(String from)
        {
            latestIncomingSignals.put(from, SignalType.LOW);
        }
    }

    private enum SignalType
    {
        LOW,
        HIGH
    }
}
