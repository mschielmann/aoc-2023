package pl.mschielmann.aoc.year2023.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TestPuzzleSolverShould
{

    @Test
    void solvePartOneWithTestData1()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1, 2L);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(374L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT, 2L);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(9957702L);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1, 10L);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(1030L);
    }

    @Test
    void solvePartTwoWithTestData3()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1, 100L);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(8410L);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT, 1000000L);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(512240933238L);
    }

    private static final String TEST_INPUT_1 = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """;

    private static final String TEST_INPUT_2 = """
                        
            """;

    static final String INPUT = """
            ......................................#.....................................#......#.......#.....................#.................#........
            ............................................................................................................................................
            .............................................#............................................................................................#.
            ..........................#..................................#........#............................#........................................
            ...#...........................#.................#............................................#...............................#.............
            ....................#.................................#..................................................#.........#........................
            ......................................#...............................................................................................#.....
            ...................................................................................#.............#..........................................
            .......................#.........................................#......................#..................................#.....#..........
            ...............#................#...................#.........................#.................................#...........................
            .......#..............................................................#.....................................................................
            ........................................#...................#.............................................#.................................
            ...........................#.....................#..................................................#..................#....................
            .................#........................................................................#.......................#.........................
            ............#..................................................#...............................#........................................#...
            .#................................#.........................................................................#...............................
            .....................................................#...............#....................................................#.................
            ..........................#...................#....................................#.................#.........................#............
            ........#..............................#............................................................................#.......................
            ........................................................#................#..................................................................
            ...................#.............#...............................#.........................................#................................
            ........................#...........................#.........................#......#.....................................................#
            ..#........#.................#....................................................................................................#.........
            .............................................#.............#.........................................#............#.........................
            ...............#......................................................#......................#..........................................#...
            ............................................................................................................................................
            ..................................................................................#........................................#................
            ..........................#........#......#..............#.....#................................................................#...........
            .........................................................................................#.....#..............#.............................
            ....................#..........#.....................#................................................................................#.....
            .#....................................................................#........#...........................................................#
            ..........#..............................................................................................................#..................
            ............................................................#......................................#........................................
            ...............#..........#................................................................................#................................
            ........................................................#..................................#.....................................#..........
            ......................#................#...............................................................................................#....
            ........#.......................................#.........................................................................#.................
            .........................................................................#........#..................................#......................
            #..................................#......#............................................................#...........................#........
            ............................................................................................................................................
            ......#.................................................#..............................#....................................................
            .........................#....................................................#..............................................#..........#...
            .................................#.............................................................................#............................
            .................#..................................#................#...........................#..........................................
            ..............................................................#......................#......#...............................................
            .............#..........................#.................................................................................................#.
            ...............................#........................#...................................................................................
            ....................................#.................................................................#..............#......................
            .......#....................................................................#.................#................................#............
            ..........................#.................#.....#.................#....................................................#.............#....
            ............................................................................................................................................
            ............#.............................................................................................#......#................#.........
            ...#..........................#..........................#......................................#...........................................
            ............................................................................................................................................
            ........#.............#...................................................#.................................................................
            ...........................................#....................#.................#...........................#......#.........#............
            ............................................................................................................................................
            ............#....................#.............................................................#......#.....................................
            .....................................................................#..................#...................................................
            .....................#..............................#........#...............#........................................................#.....
            ....................................#...................................................................................#...................
            ..................................................................................................................#.........................
            .....#.......#...............#..................................................................#..............................#............
            .............................................#.........#....................................................................................
            ..................................................................#.......#................................#................................
            .........................#.................................................................#.......#.................#......................
            #...............................................#.........................................................................#.......#.........
            ...........................................#.................#................#...........................................................#.
            ......................#...............#............................................#........................................................
            ...............#..............#....................#....................................#.........................#.........................
            ..#..........................................................................................................#..............................
            ..........#...................................................................................#........#....................................
            ...........................#.......#.........#.........#..................#.................................................................
            ................................................................#......................................................#....................
            ............................................................................................................................................
            ...............................................................................................................................#............
            ........#...............................#............#...................................................#........#....................#....
            ....................#.......................................................................................................................
            ............#...................................#....................................#................................#.....#...............
            .............................#....................................#...............................#.........................................
            ...#....................................................#..................#...........................#....................................
            .......................#..............#..................................................#...............................#........#.........
            ..............#.................#............................................................................#..............................
            ........#..........................................#......................................................................................#.
            ..........................#............................................#.....#.......................................#......................
            ...................................#.....................#........#...............#.........#...............................................
            ...........................................................................................................................#................
            .........................................#.............................................................#...........................#........
            ......#.........................#...........................#............#.....#............................................................
            ...............#.....#.................................#.......................................................................#............
            ..................................................................................................#..............#........................#.
            ...................................#.......#................................................................................................
            ..............................................................#......................#..................#.........................#.........
            ..............................#.................#.........................................#.................................................
            ..#.........................................................................................................................................
            ..................#..................................................#......#......................#........................#...............
            ...........#...........................................................................................................#..............#.....
            .....................................#......................................................................................................
            ........................................................#.........................................................#.........................
            ..........................#....................................#............................................................................
            ............................................................................................#...............#............................#..
            #..............................................#...................................#..................#.....................................
            .........#.............#..................#.....................................................................#...........................
            .............................................................................................................................#..............
            .....................................#...........................#............#..................#.....................................#....
            .................#..........#............................#..............................................................#........#..........
            .........................................................................#...................#..............................................
            .................................#...........................................................................#.....#........................
            ........................................#..........................#........................................................................
            ...#.....#.................................................#......................................#......#..........................#.....#.
            ....................................#.............................................#.........................................................
            ....................#.......#................#..............................#...............................................................
            .....................................................................#....................#....................#............................
            .................................................#................................................................................#.........
            ...........#...........#...................................................................................................................#
            ................#.......................#.........................#...................................................#.....................
            ...#.......................#...............................#.........................................#.........................#............
            ....................................................................................#.......................................................
            .............................................................................................#...........................................#..
            ..............#...............................#................................................................#..........#.................
            .......................................................#.............#.....#................................................................
            ............................#...................................................................#...........................................
            .....#..............................#...........................................................................................#..........#
            ..................................................#...............#................#........................................................
            ..........#..........#.......................................................................................#.........#.............#......
            ........................................................#....................................................................#..............
            .............................#...................................................................................#.......................#..
            .#.........................................#...................#...........#..........................#.....................................
            ....................................................................#......................#................................................
            ...........#......................................................................#.................................#.......................
            .....#.................................................................................#.......................................#............
            ...............................#.....#..................................................................#...................................
            ....................#.........................#......................................................................................#......
            ..................................................................................................#...........#........#....................
            ...............#.........................................#............#.....................................................................
            ..................................#......#....................................#.............................................................
            ...........................#........................#.....................................#..............#...............................#..
            ....#..............#..............................................................................................#.........................
            .............#...................................................................................#..........................................
            ...................................................................................#..................#..................#............#.....
            """;
}
