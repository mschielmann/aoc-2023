package pl.mschielmann.aoc.year2023.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPuzzle4SolverShould
{

    @Test
    void solvePartOneWithTestData1()
    {
        // GIVEN
        Puzzle4Solver puzzleSolver = new Puzzle4Solver(TEST_INPUT_1);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        Puzzle4Solver puzzleSolver = new Puzzle4Solver(TEST_INPUT_2);

        // WHEN
        int result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        Puzzle4Solver puzzleSolver = new Puzzle4Solver(INPUT);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        Puzzle4Solver puzzleSolver = new Puzzle4Solver(INPUT);

        // WHEN
        int result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    private static final String TEST_INPUT_1 = """
                        
            """;

    private static final String TEST_INPUT_2 = """
                        
            """;

    static final String INPUT = """
                        
            """;
}
