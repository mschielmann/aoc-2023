package pl.mschielmann.aoc.year2023.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPuzzle2SolverShould {

    @Test
    void solvePartOneWithTestData1() {
        // GIVEN
        Puzzle2Solver puzzleSolver = new Puzzle2Solver(TEST_INPUT_1);

        // WHEN
        String result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(TEST_INPUT_1);
    }

    @Test
    void solvePartTwoWithTestData2() {
        // GIVEN
        Puzzle2Solver puzzleSolver = new Puzzle2Solver(TEST_INPUT_2);

        // WHEN
        String result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(TEST_INPUT_2);
    }

    @Test
    void solvePartOneWithActualData() {
        // GIVEN
        Puzzle2Solver puzzleSolver = new Puzzle2Solver(INPUT);

        // WHEN
        String result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(INPUT);
    }

    @Test
    void solvePartTwoWithActualData() {
        // GIVEN
        Puzzle2Solver puzzleSolver = new Puzzle2Solver(INPUT);

        // WHEN
        String result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(INPUT);
    }

    private static final String TEST_INPUT_1 = """

            """;

    private static final String TEST_INPUT_2 = """

            """;

    static final String INPUT = """
                        
            """;
}
