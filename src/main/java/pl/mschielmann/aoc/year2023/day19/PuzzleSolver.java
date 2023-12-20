package pl.mschielmann.aoc.year2023.day19;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Slf4j
class PuzzleSolver
{
    private static final String ACCEPTED_WORKFLOW_NAME = "A";
    private static final String FIRST_WORKFLOW_NAME = "in";
    private static final String REJECTED_WORKFLOW_NAME = "R";
    private static final List<String> NAMES_OF_TERMINATING_WORKFLOW = List.of(ACCEPTED_WORKFLOW_NAME, REJECTED_WORKFLOW_NAME);

    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    BigDecimal solvePartOne()
    {
        Map<String, Workflow> workflowsByName = parseRules(puzzleInput);
        List<MachinePart> machineParts = parseMachineParts(puzzleInput);

        BigDecimal result = BigDecimal.ZERO;
        for (MachinePart machinePart : machineParts)
        {
            String currentWorkflowName = FIRST_WORKFLOW_NAME;
            while (!NAMES_OF_TERMINATING_WORKFLOW.contains(currentWorkflowName))
            {
                currentWorkflowName = workflowsByName.get(currentWorkflowName).processReturningNextWorkflowName(machinePart);
            }
            if (ACCEPTED_WORKFLOW_NAME.equals(currentWorkflowName))
            {
                result = result.add(BigDecimal.valueOf(machinePart.machinePartFeatures().values().stream().mapToLong(Long::valueOf).sum()));
            }
        }
        return result;
    }

    BigDecimal solvePartTwo()
    {
        Map<String, Workflow> workflowsByName = parseRules(puzzleInput);
        workflowsByName.put("A", new Workflow("A", List.of()));
        workflowsByName.put("R", new Workflow("R", List.of()));
        Map<MachinePartCategory, Integer> minValuesOfMachinePartFeatures = new HashMap<>();
        Map<MachinePartCategory, Integer> maxValuesOfMachinePartFeatures = new HashMap<>();

        Arrays.stream(MachinePartCategory.values()).forEach(category ->
        {
            minValuesOfMachinePartFeatures.put(category, 1);
            maxValuesOfMachinePartFeatures.put(category, 4000);
        });

        MachinePartGroup machinePartGroup = new MachinePartGroup(minValuesOfMachinePartFeatures, maxValuesOfMachinePartFeatures);
        List<MachinePartGroupWithStartingWorkflow> machinePartGroupsToProcess = new ArrayList<>();
        machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(machinePartGroup, workflowsByName.get("in")));

        BigDecimal result = BigDecimal.ZERO;
        while (!machinePartGroupsToProcess.isEmpty())
        {
            MachinePartGroupWithStartingWorkflow nextInLine = machinePartGroupsToProcess.remove(0);
            Workflow workflow = nextInLine.startingWorkflow();
            MachinePartGroup currentMachinePartGroup = nextInLine.machinePartGroup();
            log.info("new group picked up: {}", currentMachinePartGroup);
            log.info("new workflow picked up: {}", workflow);
            if (ACCEPTED_WORKFLOW_NAME.equals(workflow.name()))
            {
                MachinePartGroup rejectedMachinePartGroup = currentMachinePartGroup;
                BigDecimal partialResult = Arrays.stream(MachinePartCategory.values()).map(category ->
                                Math.min(rejectedMachinePartGroup.maxValuesOfMachinePartFeatures().get(category), 4000) - Math.max(rejectedMachinePartGroup.minValuesOfMachinePartFeatures().get(category), 0) + 1
                        )
                        .map(BigDecimal::valueOf)
                        .reduce(BigDecimal.ONE, BigDecimal::multiply);
                result = result.add(partialResult);
                log.info("PARTIAL: {}, RESULT: {}", partialResult, result);
                log.info("Accepted: {}", currentMachinePartGroup);
            }
            if (REJECTED_WORKFLOW_NAME.equals(workflow.name()))
            {
                log.info("Rejected: {}", currentMachinePartGroup);
            }
            for (Rule rule : workflow.rules())
            {
                if (rule.conditionValue == -1)
                {
                    log.info("Default: {}, rule: {}", currentMachinePartGroup, rule);
                    machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(currentMachinePartGroup, workflowsByName.get(rule.nextWorkflowName())));
                    break;
                }
                MachinePartCategory category = rule.category();
                if (rule.conditionLabel == '>')
                {
                    if (currentMachinePartGroup.minValuesOfMachinePartFeatures().get(category) > rule.conditionValue) {
                        log.info("No split, going to next workflow(>): {}, rule: {}", currentMachinePartGroup, rule);
                        machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(currentMachinePartGroup, workflowsByName.get(rule.nextWorkflowName())));
                        break;
                    } else if (currentMachinePartGroup.maxValuesOfMachinePartFeatures().get(category) <= rule.conditionValue) {
                        log.info("No split, going to next rule(>): {}, rule: {}", currentMachinePartGroup, rule);
                        continue;
                    }
                    log.info("split(>) {}, rule {}", currentMachinePartGroup, rule);
                    Map<MachinePartCategory, Integer> newMinValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.minValuesOfMachinePartFeatures());
                    Map<MachinePartCategory, Integer> newMaxValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.maxValuesOfMachinePartFeatures());
                    newMinValuesOfMachinePartFeatures.put(category, rule.conditionValue() + 1);
                    MachinePartGroup newMachinePartGroup = new MachinePartGroup(newMinValuesOfMachinePartFeatures, newMaxValuesOfMachinePartFeatures);;
                    log.info("new machine part group created(>): {}", newMachinePartGroup);
                    machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(newMachinePartGroup, workflowsByName.get(rule.nextWorkflowName())));

                    newMinValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.minValuesOfMachinePartFeatures());
                    newMaxValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.maxValuesOfMachinePartFeatures());
                    newMaxValuesOfMachinePartFeatures.put(category, rule.conditionValue());
                    currentMachinePartGroup = new MachinePartGroup(newMinValuesOfMachinePartFeatures, newMaxValuesOfMachinePartFeatures);
                    log.info("continuing with: {}", currentMachinePartGroup);
                } else {
                    if (currentMachinePartGroup.maxValuesOfMachinePartFeatures().get(category) < rule.conditionValue) {
                        log.info("No split, going to next workflow(<): {}, rule {}", currentMachinePartGroup, rule);
                        machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(currentMachinePartGroup, workflowsByName.get(rule.nextWorkflowName())));
                        break;
                    } else if (currentMachinePartGroup.minValuesOfMachinePartFeatures().get(category) >= rule.conditionValue) {
                        log.info("No split, going to next rule(<): {}, rule: {}", currentMachinePartGroup, rule);
                        continue;
                    }
                    Map<MachinePartCategory, Integer> newMinValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.minValuesOfMachinePartFeatures());
                    Map<MachinePartCategory, Integer> newMaxValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.maxValuesOfMachinePartFeatures());
                    newMaxValuesOfMachinePartFeatures.put(category, rule.conditionValue() - 1);
                    MachinePartGroup newMachinePartGroup = new MachinePartGroup(newMinValuesOfMachinePartFeatures, newMaxValuesOfMachinePartFeatures);
                    log.info("new machine part group created(<): {}", newMachinePartGroup);
                    machinePartGroupsToProcess.add(new MachinePartGroupWithStartingWorkflow(newMachinePartGroup, workflowsByName.get(rule.nextWorkflowName())));

                    newMinValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.minValuesOfMachinePartFeatures());
                    newMaxValuesOfMachinePartFeatures = new HashMap<>(currentMachinePartGroup.maxValuesOfMachinePartFeatures());
                    newMinValuesOfMachinePartFeatures.put(category, rule.conditionValue());
                    currentMachinePartGroup = new MachinePartGroup(newMinValuesOfMachinePartFeatures, newMaxValuesOfMachinePartFeatures);
                    log.info("continuing with: {}", currentMachinePartGroup);
                }
            }
        }

        return result;
    }

    private Map<String, Workflow> parseRules(String puzzleInput)
    {
        Map<String, Workflow> workflowsByName = new HashMap<>();
        for (String workflowDefinition : puzzleInput.lines().toList())
        {
            if (workflowDefinition.isBlank())
            {
                return workflowsByName;
            }
            Workflow workflow = Workflow.parseWorkflow(workflowDefinition);
            workflowsByName.put(workflow.name(), workflow);
        }
        throw new IllegalArgumentException("No blank line found to finish rules definition section.");
    }

    private record Workflow(String name, List<Rule> rules)
    {
        private String processReturningNextWorkflowName(MachinePart machinePart)
        {
            return rules.stream()
                    .filter(rule -> rule.predicate().test(machinePart))
                    .findFirst()
                    .orElseThrow(
                            () -> new IllegalStateException("No rule (including default) matches the machine part: " + machinePart)
                    ).nextWorkflowName();
        }

        private static Workflow parseWorkflow(String workflowDefinition)
        {
            String name = workflowDefinition.replaceAll("\\{.*", "");
            List<Rule> rules = Arrays.stream(workflowDefinition
                            .replaceAll(".*\\{", "")
                            .replaceAll("\\}", "")
                            .split(","))
                    .map(Rule::parseRule)
                    .toList();
            return new Workflow(name, rules);
        }
    }

    private record Rule(Predicate<MachinePart> predicate, MachinePartCategory category, int conditionValue, char conditionLabel,
                        String nextWorkflowName)
    {

        public static Rule parseRule(String ruleDefinition)
        {
            if (!ruleDefinition.contains(":"))
            {
                return new Rule(machinePart -> true, MachinePartCategory.SHINY, -1, ' ', ruleDefinition);
            }
            String[] ruleDefinitionParts = ruleDefinition.split(":");
            String nextWorkflowName = ruleDefinitionParts[1];
            Integer conditionValue = Integer.parseInt(ruleDefinitionParts[0].substring(2));
            char conditionTypeLabel = ruleDefinitionParts[0].charAt(1);
            MachinePartCategory machinePartCategory = MachinePartCategory.findBy(ruleDefinitionParts[0].charAt(0));
            Predicate<MachinePart> predicate = machinePart -> (conditionTypeLabel == '>') ? machinePart.machinePartFeatures
                    .get(machinePartCategory).compareTo(conditionValue) > 0 : machinePart.machinePartFeatures
                    .get(machinePartCategory).compareTo(conditionValue) < 0;
            return new Rule(predicate, machinePartCategory, conditionValue, conditionTypeLabel, nextWorkflowName);
        }
    }

    private record MachinePart(Map<MachinePartCategory, Integer> machinePartFeatures)
    {

    }

    private record MachinePartGroup(Map<MachinePartCategory, Integer> minValuesOfMachinePartFeatures,
                                    Map<MachinePartCategory, Integer> maxValuesOfMachinePartFeatures)
    {

    }

    private record MachinePartGroupWithStartingWorkflow(MachinePartGroup machinePartGroup, Workflow startingWorkflow)
    {
    }

    private List<MachinePart> parseMachineParts(String puzzleInput)
    {
        boolean machinePartDefinitionSectionStarted = false;
        List<MachinePart> machineParts = new ArrayList<>();
        for (String line : puzzleInput.lines().toList())
        {
            if (line.isBlank())
            {
                machinePartDefinitionSectionStarted = true;
                continue;
            }
            if (!machinePartDefinitionSectionStarted)
            {
                continue;
            }

            Map<MachinePartCategory, Integer> machinePartFeatures = new HashMap<>();
            Arrays.stream(line.replaceAll("\\{", "")
                            .replaceAll("\\}", "")
                            .split(","))
                    .forEach(machinePartDefinition ->
                            machinePartFeatures.put(MachinePartCategory.findBy(machinePartDefinition.split("=")[0].charAt(0)),
                                    Integer.parseInt(machinePartDefinition.split("=")[1]))
                    );
            machineParts.add(new MachinePart(machinePartFeatures));
        }
        return machineParts;
    }

    private enum MachinePartCategory
    {
        EXTREMELY_COOL_LOOKING('x'),
        MUSICAL('m'),
        AERODYNAMIC('a'),
        SHINY('s');

        private final char label;

        MachinePartCategory(char label)
        {
            this.label = label;
        }

        private static MachinePartCategory findBy(char label)
        {
            return Arrays.stream(values())
                    .filter(value -> value.label == label)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid part category " + label));
        }
    }
}
