package pl.mschielmann.aoc.year2023.day7;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class TestPuzzleSolverShould
{

    @Test
    void solvePartOneWithTestData1()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_2);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    private static final String TEST_INPUT_1 = """
                        
            """;

    private static final String TEST_INPUT_2 = """
                        
            """;

    static final String INPUT = """
                        
            """;
}
