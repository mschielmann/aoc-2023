package pl.mschielmann.aoc.year2023.dayX;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPuzzleXSolverShould
{

    @Test
    void solvePartOneWithTestData1()
    {
        // GIVEN
        PuzzleXSolver puzzleSolver = new PuzzleXSolver(TEST_INPUT_1);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleXSolver puzzleSolver = new PuzzleXSolver(TEST_INPUT_2);

        // WHEN
        int result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleXSolver puzzleSolver = new PuzzleXSolver(INPUT);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleXSolver puzzleSolver = new PuzzleXSolver(INPUT);

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
