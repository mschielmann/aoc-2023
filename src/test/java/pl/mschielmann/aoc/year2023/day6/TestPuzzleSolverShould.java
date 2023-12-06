package pl.mschielmann.aoc.year2023.day6;

import org.junit.jupiter.api.Test;
import pl.mschielmann.aoc.year2023.day6.PuzzleSolver.RaceInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(result).isEqualTo(288L);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_2);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(71503L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(PART_1_INPUT);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(2065338L);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(PART_2_INPUT);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(34934171L);
    }

    private static final List<RaceInfo> TEST_INPUT_1 = List.of(
            new RaceInfo(7, 9),
            new RaceInfo(15, 40),
            new RaceInfo(30, 200)
    );

    private static final List<RaceInfo> TEST_INPUT_2 = List.of(
            new RaceInfo(71530, 940200)
    );

    static final List<RaceInfo> PART_1_INPUT = List.of(
            new RaceInfo(54, 446),
            new RaceInfo(81, 1292),
            new RaceInfo(70, 1035),
            new RaceInfo(88, 1007)
    );

    static final List<RaceInfo> PART_2_INPUT = List.of(
            new RaceInfo(54817088L, 446129210351007L)
    );
}
