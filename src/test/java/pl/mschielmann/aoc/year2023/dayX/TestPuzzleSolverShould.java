package pl.mschielmann.aoc.year2023.dayX;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPuzzleSolverShould
{

    @Test
    void solvePartOneWithTestData1()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_2);

        // WHEN
        int result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        int result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

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
