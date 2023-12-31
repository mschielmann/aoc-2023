package pl.mschielmann.aoc.year2023.day13;

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
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(405L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(31877L);
    }

    @Test
    void solvePartTwoWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_1);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(400L);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(42996L);
    }

    private static final String TEST_INPUT_1 = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
            """;

    private static final String TEST_INPUT_2 = """
            .#..#....#..#.###
            .####.##.####....
            .#.#.####.#.#.#..
            ...........#..#..
            ####.#..#.####...
            ##..#.##.#..##.##
            .############.#..
            """;

    static final String INPUT = """
            #..#.....
            .##.##..#
            ####..###
            #..###.##
            #..#.###.
            ####.....
            ....#..#.
            #####....
            #####....
            ....#..#.
            ####....#
            #..#.###.
            #..###.##
                        
            ##..#....#..####.
            #.###.##.###.##.#
            ##.########.####.
            ..##.####.##....#
            ..##.####.##....#
            #..#.#..#.#..##..
            .#...####...#..#.
            #............##..
            ###...##...######
            ....#....#......#
            #.#.#....#.#.##.#
            ###.######.######
            ...#......#......
                        
            .#.#..########.
            #.#.#.#.####.#.
            .####.#..##..#.
            #.##.#.##..##.#
            ##..##........#
            .###.#.#.##.#.#
            ..#............
            .#..#...#..#...
            .#..#...#..#...
            ..#............
            .###.#.#.##.#.#
            ##..##........#
            #.####.##..##.#
                        
            #####.##.
            ......#.#
            #####..#.
            .##..#...
            ####..##.
            #..###..#
            .##..#.##
            .##..#..#
            #..###..#
                        
            .#.##.#.#..#.
            ....#....#..#
            .#..####..##.
            ..#.#########
            ....#.....##.
            .#####.#..##.
            #..#...###..#
            ###..#.###..#
            #.#..#.##....
            ...#.....####
            ..#...#......
            ..#...#.##..#
            ..#.##..#####
            ..#.##..#####
            ..#...#.##..#
                        
            #...######.
            ..#.######.
            ####.#..###
            .#...#..#..
            ###.#.##.#.
            .#..#....#.
            ##.#.#..#.#
            ##.#.#..#.#
            .#..#....#.
            ###.#.##.#.
            .#...#..#..
                        
            ...#.#.#.####.#.#
            ..###..###..###..
            ##.###..........#
            ....####...#..###
            ...#.#...#..#...#
            ##...##.##..##.##
            ###..##.#....#.##
            ..##.##...##...##
            ##.##............
                        
            #.##.##..#....##.
            #..####...#..##.#
            .#...####.##..###
            .#...####.##..###
            #..####...#..##.#
            #.##..#..#....##.
            .....#.####..#...
            .##..###..#######
            .##..###..#######
            .....#.####..#...
            #.##..#..#....##.
                        
            .#..#....#..#.###
            .####.##.####....
            .#.#.####.#.#.#..
            ...........#..#..
            ####.#..#.####...
            ##..#.##.#..##.##
            .############.#..
                        
            #..#....#
            #..##....
            ....#..#.
            #..#.#..#
            ...##...#
            #..##...#
            #..##.#..
            ......#.#
            .##.#..##
            .......#.
            ####.###.
            ####.###.
            .......#.
                        
            #.##..#
            ....##.
            ..####.
            .###.##
            .###.##
            .#####.
            ....##.
            #.##..#
            .#....#
            .#.###.
            #......
            ##.#.##
            ##.#.##
                        
            .###..####....#.#
            .#....##.#.##..##
            .#....##.#.##..##
            .###..####....#.#
            #....##.###.##.##
            #.##...#.#..#.##.
            ######..######...
            ..##..###...###..
            #..####.#.#..#.##
            #..####.#.#..#.##
            ..##..####..###..
                        
            ..##......#
            ##.#.#..#.#
            ##..#.##.#.
            ...##....##
            #####....##
            #####.##.##
            ..#...##.#.
            ##.........
            ##...#..#..
            ##.##....##
            ##..##..##.
                        
            ...#....##.
            ...#..##..#
            ###.#...##.
            ##..##.#..#
            .###.###..#
            ###.##..##.
            ##.##..####
            ##.#.#..##.
            .....#.#..#
            ..#########
            ##...#.####
            ...#..##..#
            ....#.#.##.
                        
            ##.....#.#.
            ###....#.#.
            ...#.###.#.
            .###..##..#
            ####.#.#...
            ..#.#####.#
            ..####..###
            #.##.###.##
            #.#.##.#..#
            #.#.##.#..#
            #.##.###.##
                        
            ..#..#..#..#.
            .#####..####.
            .##..##.####.
            .#.##.#.####.
            ###..####..##
            ##.##.###..##
            #.#..#.##..##
            ..####.......
            ..####..#..#.
            ..#..#.......
            ##....#######
            ###..########
            ...##........
                        
            #........
            ...##....
            #.#..#.##
            .#....#..
            #.####.##
            .#....#..
            .#.##.#..
            #......##
            ##....###
            #..##..##
            .#....#..
                        
            ..##..#..#.
            .###.##.###
            ##.##...#..
            ..#.###.#..
            ##.....##..
            #.###....##
            #.###....##
            ##.....##..
            ..#.###.#..
            ##.###..#..
            .###.##.###
            ..##..#..#.
            ..#......#.
            ###.#.##.#.
            .#..#.##.##
            #...#...##.
            #...#...##.
                        
            #..#..#.#.....#
            ####.####.##.#.
            .##....#.#...#.
            .....#..#...###
            #..#..#.##.##.#
            ####.....####..
            #####.....###.#
            .##.##.###..###
            #..#.#.#.#..##.
            #####..#..##.##
            .##..#......#..
            #####.....#..#.
            ###########..#.
            ###########..#.
            #####.#...#..#.
            .##..#......#..
            #####..#..##.##
                        
            #.##.#..###.#..#.
            .####..#..#..##.#
            ..##...##.##.....
            ..##...#...###..#
            ......#..###....#
            ........#..#.##..
            #....##.#.#..##..
            ..##..##.###.##.#
            ..##..##.###.##.#
            #....##.#.#..##..
            ........#..#.##..
            ......#..##.....#
            ..##...#...###..#
                        
            ...####....#..#..
            ####.#.##...#.#..
            #.#.#..#...#.####
            ...#.#.#######...
            #.####.##.#..#...
            #.####.##.#..#...
            ...#.#.#####.#...
                        
            #..##..
            #..#.#.
            .##....
            .##....
            #..#.#.
            #..#.#.
            .##....
            .##....
            #..#.#.
            #..##..
            .#...#.
            .....##
            ####..#
                        
            .##......##..
            .##..######.#
            ####.#.#....#
            .....###.##.#
            .##.###..##..
            ....####.##.#
            ....###......
            #..#..#.####.
            .##..........
            .....##......
            #..#..###..##
            #####..######
            .......#....#
            .##.#.##....#
            .##.....####.
                        
            ##..#..#.....
            ####.##.##..#
            .##..####.##.
            ##.#..##.....
            #.#.#...#####
            #...#.#.#.##.
            .#.###.......
            ..##.....#..#
            ###..#...#..#
            ..#.#.###.##.
            #..#.###.....
            #..#.###.....
            ..#.#####.##.
            ###..#...#..#
            ..##.....#..#
                        
            ###.#....##..
            ####.#...#...
            ####.##.##..#
            ..#..##..##..
            ...#...#.#.##
            ..#..#...####
            ###..##.#....
                        
            ##....##....#
            ...##....##..
            .##..####..##
            .#.##.##.##.#
            #.#..#..#..#.
            .############
            ##....##....#
            ###..####..##
            .############
            ##.##.##.##.#
            #..##....##..
            .############
            #.####..####.
            #..##....##..
            ##.#..##..#.#
            #..##....##..
            #............
                        
            .##..#.
            ###.#..
            ....##.
            #..#..#
            ####.#.
            .##.##.
            ....##.
            .##.#.#
            .##.#.#
            ....##.
            .##.##.
                        
            .###..#
            .#...##
            ....##.
            #...#.#
            #...#.#
            ....###
            .#...##
            .###..#
            ###....
            ....#..
            ###..#.
            ##.#.##
            #......
            ##.#..#
            ##.#..#
            #......
            ##.#.##
                        
            ##...##
            #...##.
            #...##.
            ##...##
            ...###.
            #.#....
            .#.....
            ###...#
            .....#.
            .#..#..
            ..#.##.
            ..#.##.
            .#..#..
            .....#.
            ###.#.#
                        
            ##..######...#.
            ##..######...#.
            .####.#.##.#...
            ##.##.#...#....
            ####.####.#...#
            #.#.##..#.#####
            ##...##..#.#.#.
            .#.##..##..##..
            #..##.##.###.##
            #..#####.###.##
            .#.##..##..##..
                        
            #....######
            .#..#...#..
            ######..#..
            #....##.#..
            ..#....##..
            ......#....
            #....##....
                        
            #..##.###
            .....#..#
            .##..##.#
            .##..#.#.
            #.##.####
            .......##
            #..##..#.
            #..#.#..#
            #..#.#..#
            #..##..#.
            .......##
                        
            ###........######
            #.#........#.#..#
            .....#..#.....##.
            #.#..#..#..###..#
            .####.##.####....
            ..##.#..#.##.....
            .#.########.#.##.
            #..#......#..#..#
            .##..####..##.##.
                        
            #.#..##.#..
            ####.#.#.##
            .#..#.##.##
            #..#..#..##
            #.#..###...
            ##.#####...
            ##.#####...
            #.#..###...
            #..#..#..##
            .#..#.##.##
            #.##.#.#.##
                        
            .#.#..###.#
            ####.#...#.
            ######...#.
            .#.#..###.#
            ....###..#.
            .####.#####
            ...#######.
            ...#######.
            .####.#####
                        
            .##...#
            ....##.
            .##.#..
            .##.#..
            ....##.
            .##...#
            #..#.#.
            ...####
            ####.##
            ....#.#
            #####.#
                        
            ###..##.##.####
            ..#.#..##.#..##
            ....#..##.#..##
            ###..##.##.####
            ##..#..######..
            #.###.##..###..
            ##....##.....##
            ....#.#..#.....
            .###########.##
            ##...#.##.#....
            #..#.##..#.....
            ###.#...#.###..
            ...#.....##....
            #..#.#####.####
            ###.##.#.#..###
                        
            #.##.#.#..#.#.#
            ......###.#....
            #######...#.##.
            ########.###...
            #....#.##..##.#
            ##..###....#.#.
            #.##.###.##.##.
            #.##.#.....#...
            #.##.#.....#...
            #.##.###.##.##.
            ##..###....#.#.
            #....#.##.###.#
            ########.###...
                        
            #..#..##..#
            ##.........
            #..#..##..#
            ..#........
            .#.........
            ##..##..##.
            ###.##..##.
            ...########
            ##.#..##..#
            .##........
            ...##.##.##
            .#.########
            ..#........
            ..#########
            #..........
                        
            #......
            ....###
            ##.#.##
            ##..#..
            ..#.###
            ..##...
            ...##..
                        
            #.##.##
            ..##.##
            ..###..
            .#..###
            #.#....
            #..####
            #..##..
            ###....
            .###...
                        
            ##..#..#...#.##
            #....##..#.#...
            #...#.#....#.##
            #..##.##.......
            ..###...#.##...
            .##.###.#..#.##
            #..##.#...##.##
            #.##.......##..
            ##.#####.###...
            ...#.#####.....
            ...#.##...#.###
            ..##..#...##.##
            ...#.#.#.##..##
            ...#.#.#..#..##
            ..##..#...##.##
            ...#.##...#.###
            ...#.#####.....
                        
            #.##.#.##.#..####
            ##..##.##..###..#
            ......####.#.#..#
            .#..##...#..#####
            ..##..#.##.#.....
            #.##.#####....##.
            ######..##.#..##.
                        
            .#.##.#..
            ###..####
            ###..##..
            ..#..#...
            #..##..##
            ##....###
            ...##....
            #..##..##
            ###..####
            ##.##.###
            ...##....
            #.#..#.##
            ###..####
            ###..####
            #.#..#.##
            ..#..#...
            .#....#..
                        
            ##.###..#....
            ##.###..#....
            #.....##..###
            #.#.#.#####..
            ####...#...##
            #.#..#.#.....
            ###..#.#...##
            .....#####..#
            ..##..##..#..
            ..##..##..#..
            ..#..#####..#
                        
            ..##.####.##.
            ...###.####..
            ###.######.##
            ....##..##...
            ..#.#.##.#.#.
            ....#.##.#...
            ..#.#....#.#.
            ..#..####..#.
            ...#..##..#..
            ....#.##.#...
            ###.#.##.#.##
            .....####....
            ####.####.###
                        
            .##.#......#...
            .......##.#.#..
            ....#...#.#....
            ####...#...#..#
            #..#...###..#.#
            ####.#.##.##.##
            ....#.##.#.#...
            #..##.#..##...#
            #..#.#.###..#.#
            #####...#######
            .....###.#.##.#
            #..#.#.##.#.#.#
            #..#...##.#.#.#
            .....###.#.##.#
            #####...#######
            #..#.#.###..#.#
            #..##.#..##...#
                        
            ###.##....##.
            #..#.######.#
            ..###..##..##
            ..###..##..##
            #..#.######.#
            ###.##....##.
            .#.#.##..##.#
            .#..#.#..#...
            #..##.#..#.##
            #.#.##....##.
            ...#.#.##.#.#
            .###.##..##.#
            .#..###..###.
                        
            #.#.##.##.##.#.
            ..#.########.#.
            ...#.#.##.#.#..
            .#.#.######.#.#
            #...#.#..#.#...
            ...##......##..
            ..##.######.##.
            ##.#..#..#.##.#
            .....#.##.#....
            #.#.#.####.#.#.
            ##.#.#.##.#.#.#
            ##.#..#..#..#.#
            ##..##.##.##..#
            ....#......#...
            .#..#......#..#
            #.####.##.####.
            #.####.##.####.
                        
            ....##.....#.
            ##.....#.##..
            .#..#.##.#.#.
            #####...##.##
            .##.####...#.
            ...#.####.#..
            ...#.####.#..
            .##.####...#.
            #####...##.##
            .#..#.##.#.#.
            ##.....#.##..
            #...##.....#.
            #...##.....#.
                        
            ##...#.####.#..##
            ##.##.##..##.##.#
            ###.#...##...#.##
            ...#..#.##.#..#..
            ##.#..######..#.#
            ....###....###...
            ##...#.#..#.#...#
            .....#.####.#....
            ##.##.##..##.##.#
            ..#####.##.#####.
            ..#.#.##..##.#.#.
            #####...##...####
            ......######.....
            ......#....#.....
            ###...#.##.#...##
                        
            .##....
            .####..
            #.#.#.#
            .#..#..
            #.###..
            #....#.
            #....#.
            #.###..
            .#..#..
            #.#.#.#
            .####..
            .##...#
            .##...#
                        
            ..##.######.##.
            .##...#..#...##
            .##...#..#...##
            ..##.######.##.
            ..##........##.
            #......##......
            .#...######...#
            ##....#..#..#.#
            ....#......#...
            .###..#..#..###
            #..#..#..#..#..
            ##.#.##..##.#.#
            .#..###..###..#
                        
            ####..#...#.#
            .##..#...##..
            .##..#...###.
            ####..#...#.#
            ####.#..#.###
            .##.#.......#
            #..#.#.......
            #######....#.
            ....##..####.
            .##.###.....#
            #..##..###.##
                        
            #.....###.#
            ..#..#.....
            ###.....###
            ........###
            .###.#.###.
            .##.##..#.#
            ##..####.#.
            ##..####.#.
            .##.##..#.#
            .###.#.###.
            ..#.....###
            ###.....###
            ..#..#.....
            #.....###.#
            #.....###.#
                        
            #.###..#...
            #.##...#...
            #..####..##
            .##.#.#..##
            #..###...##
            #..#..#.#..
            #..##..#...
            ##.#..##...
            ..#####.###
            #..##.##...
            .#.####.###
                        
            #.##.####.##.#.##
            .#..##..##.##.###
            ....##..##.......
            .#.#.#..#.#.#.#.#
            #....#..#....#.##
            ..#.#....#.#..#.#
            ..###.##.###....#
            ##.#......#.#####
            #####....#####..#
            #####....#####..#
            ##.#......#.#####
            ..###.##.###....#
            ..#.#....#.#..#.#
            #....#..#....#.##
            .#.#.#..#.#.#.#.#
                        
            ..#..#..#..#..#
            ..####........#
            #..##..#....#..
            #......#....#..
            .#....#..##..#.
            #..##..#######.
            .#.##.#.####.#.
            ...##...#..#...
            .######......##
            ##....##.##.##.
            #.#..#.######.#
            ..#..#..#..#..#
            #......######..
                        
            .#....#..
            ..####...
            #..##..#.
            ..#..#..#
            #..##..#.
            .#.####..
            .##..##.#
            .#.##.#.#
            #..##..#.
            #......##
            #......##
                        
            #..####..
            ##.##.###
            ##.#..##.
            ..###.###
            #.##....#
            ###.#.###
            #.#.#.###
            #.#.#.###
            ###.#.###
                        
            .##...##.#...#.
            ..##....####.#.
            ##..##..#...###
            ####.##.#....##
            #.##......##..#
            ..##..#.##.#.##
            ###..######...#
            ###..######...#
            ..##..#.##.#.##
            #.##......##..#
            ####.##.#....##
            ##..##..#...#.#
            ..##....####.#.
            .##...##.#...#.
            .##...##.#...#.
                        
            #......
            .#.....
            ..#####
            #.##..#
            ...####
            ###....
            ...####
            ..#....
            .#.####
                        
            ..##.###......###
            ##...###......###
            ##..####.####.###
            ..#.####..##.####
            ....##.##.##.##.#
            ####..##.####.##.
            ##.##.###.##.###.
            ##.##.###.##.###.
            ###..############
            ..#...##.####.##.
            ##...####....####
            ....#.###.##.###.
            ..##.............
                        
            ###...##...##
            ##.###.#..#..
            ######...#...
            ...#.######..
            .####.###....
            #.#.#######..
            ###.#.#..####
            ...#...#..#..
            ...###.###.##
            ..##.#.##....
            ##........###
            ##........###
            ..##.#.##....
            ...###.###.##
            ...#...#..#..
            ###.#.#..####
            #.#.###.###..
                        
            ########.#..###.#
            #..##..###.#...#.
            ..####.....##....
            ...##.......#....
            ##....##.....#...
            .........#####..#
            #.#..#.##..##...#
            ..#..#..#.#.#.#..
            .#.##.#....#...##
            ........#.....#..
            #.#..#.##.###.###
            #.#..#.##.###.#.#
            ........#.....#..
            .#.##.#....#...##
            ..#..#..#.#.#.#..
            #.#..#.##..##...#
            .........#####..#
                        
            #..#.###...
            #.##.....#.
            #.##.....#.
            #..#.###...
            ..#.#.##..#
            .##.#.#..##
            ...###...#.
            #.##....#.#
            #..##..###.
            #.#####.#.#
            ##....###..
            #.#.#..#...
            ...###...#.
            ###....#.#.
            ###....#.##
                        
            #.##.####.#..
            #######.###.#
            #....#####..#
            ..##..##.#..#
            ##..#####.#..
            ######.###..#
            .####..##.###
            .#..#.####..#
            #.##.#.#...#.
            .#..#..#.#.##
            ..##..##..##.
            .........#..#
            #.##.###..##.
            .####....#..#
            ..##....###..
            ..##....#.#..
            .####....#..#
                        
            ##.#.#.#.#.
            ....#.###.#
            #.....##..#
            ..#...#.#.#
            ##..#.#.###
            ##..#.#.###
            ..#...#.#.#
            #..#..##..#
            ....#.###.#
            ##.#.#.#.#.
            ....####...
            ##...#.#.##
            ####.##.#..
            #...#.#....
            #####....#.
            #####....#.
            #...#.#....
                        
            ..#.###
            ###.###
            ..#.###
            ....#.#
            ..#.##.
            ##.#.#.
            .....#.
            ##..#..
            ..#.###
            .#..##.
            ...#..#
            ##.....
            ##.....
                        
            .#####.
            #...##.
            #####..
            #..#.##
            #..#.##
            #####..
            #...##.
            .#####.
            .##.#..
            .....##
            ..#..##
                        
            ##.##.#.#
            #...#....
            #...#....
            ##.##.#.#
            ###.....#
            .....###.
            #.###...#
            #.###...#
            .....###.
            ###...#.#
            ##.##.#.#
                        
            .#.#.#..#
            #....#..#
            ..#.#.##.
            #.##.####
            #.#.#.##.
            #..###..#
            .#.#..##.
            #.#......
            ###......
                        
            ##.....#...#..#..
            ..#..##..#.####..
            #.##..#...#.#..##
            .#...###.####.#..
            ...##..###.#.#.##
            #.#..#######.....
            .###..#.#.#......
            ##.#.....########
            ##.##.#####..#.##
            ..#...#..#...####
            ####.#..#..##....
            #..#.#.##..###...
            #..#.#.##..##....
                        
            ..#....
            #.#....
            .#.#.##
            .#.#.##
            #.#....
            ..#..##
            ###.###
                        
            #....##..####..##
            #.#.....#....#...
            #..#....#....#...
            #..##....#..#....
            .#.....#......#..
            ...####.#....#.##
            ##..#.##########.
            .#.####.######.##
            ....######..#####
            #..#.....####....
            #.#.###.##..##.##
                        
            ##....#........
            ..##...##.##.##
            ###..#.#.####.#
            #.##.#.........
            ####.#.........
            ###..#.#.####.#
            ..##...##.##.##
            ##....#........
            #..####.##..##.
                        
            #.###.####.###.
            #.###.####.###.
            #.###..##..###.
            .####.#..######
            .#.#.#....#.#.#
            ..#.#..##..#.#.
            .#.#.#....#.#.#
            #.##.#....#.##.
            #.#..#.##.#..#.
            #..#.##..##.#..
            ..##...##...##.
            ..#.#......#.#.
            #.#.##....##.#.
            .##.##.##.##.##
            .#####.##.#####
            .#.#.#.##.#.#.#
            ###..######..##
                        
            #..###.....
            #..###.....
            #..#.#####.
            .#...###..#
            #.###..####
            ####..#####
            .#####.###.
            ..###.#..##
            #.##..#.#.#
            ####..#.#.#
            ..###.#..##
            .#####.###.
            ####..#####
                        
            ..####..###.#
            #.#..#.###..#
            #.####.#.##.#
            ..#..#...#..#
            .#....#.....#
            #..##..###..#
            #......#.#.#.
            .#.##.#...##.
            .######...#..
            .#....#.###..
            .#....#.###..
            .######...#..
            .#.##.#....#.
            #......#.#.#.
            #..##..###..#
                        
            ....##..#..
            #..#...#...
            .##...#..##
            .##..##..##
            ####.##....
            #..##.#.#.#
            ####.####.#
            #..##..#...
            .##..#..#.#
            .##.###.##.
            .##.###.##.
            .##..#..#.#
            #..#...#...
                        
            #..###...#..#...#
            ..#.#...##..##...
            ...#####.####.###
            ##.#.#####..#####
            ...#.#...#..#...#
            .##....##.##.##..
            ....#.###.##.###.
            #.#.#...#....#...
            .#.###.#..##..#.#
            ..#...#..#..#..#.
            .##..#...####...#
            ..##.#....##....#
            ...#.#....##....#
            .##..#...####...#
            ..#...#..#..#..#.
                        
            ##.##############
            #.####.##.##.##.#
            .####.####..####.
            .#..#............
            ###.#.####..####.
            .#..##....##....#
            #................
            .###.############
            ..###..###..###..
            ##.####..####..##
            ..#..##..####..##
            ....#.####..####.
            #...#.####..####.
            .###.##..####..##
            ..####.##.##.##.#
                        
            #.##.#.###.......
            #.##.#.###.......
            ###.#.....#.#.#..
            #.###..##.####.##
            .#.#..#.######...
            .....#.....##...#
            ##..#..##...##.#.
            ##..#..##...##.#.
            .....#.....##..##
            .#.#..#.######...
            #.###..##.####.##
            ###.#.....#.#.#..
            #.##.#.###.......
                        
            ...##..###...
            ###...####..#
            ##.###.#..###
            ###..#...###.
            ....#..#.#...
            ######......#
            ...##...##..#
            .....##..##.#
            ##.#..###...#
            ##...#.#....#
            ##...#.#....#
            ##.##.###...#
            .....##..##.#
            ...##...##..#
            ######......#
                        
            .##..#####.#.
            .##..#####.##
            .##.##.###.#.
            #..##...#.##.
            ######.##.###
            #..##.###.#.#
            ....#..#.#.#.
                        
            ###.....#....
            ##.#....#....
            ...#.##..####
            ####..#.#..##
            ###....#..#..
            #..#..#.#.###
            .###.###.####
            ####.###.##.#
            ###.##.#..#..
            ##..#..#.....
            ##..#..#.....
                        
            .....##..
            #...####.
            #...####.
            .....##..
            .##.#..#.
            #.#..###.
            ...#.##.#
            .########
            #..######
            #..#....#
            #####..##
            .#.#.##.#
            .#.......
            #.###..##
            ..##....#
            #..##..##
            .##.####.
                        
            .#.#.#..##.##....
            #.###......##...#
            #.#####.#..#.###.
            #.#.#.#.##..##.##
            .#..#.####.###..#
            .#..#.###..###..#
            #.#.#.#.##..##.##
            ##..###..#.###..#
            .###.##..##.#..#.
            .###.##..##.#..#.
            ##..###..#.###..#
            #.#.#.#.##..##.##
            .#..#.###..###..#
            .#..#.####.###..#
            #.#.#.#.##..##.##
                        
            ###.#...#..#...#.
            .#..##........##.
            ###..###.##.###..
            ###..####..####..
            ###..####..####..
            ###..###.##.###..
            .#..##........##.
            #.#.#...#..#...#.
            ###.#.#.#..#.#.#.
            ##.#.#.######.#.#
            ##.....######....
            .#######....#####
            ##.#.#...##...#.#
            #####...#..#...##
            .#.......##......
            ###...########...
            ####..#......#..#
                        
            .#.#.###.#.
            #..#.#..##.
            #..#.#..##.
            .#.#.###.#.
            ######.####
            ##.#.###..#
            ...##......
            .##.#.#..##
            .##.#.#..##
            .#....#..#.
            #..##.##.#.
            #..##.##.#.
            .#...##..#.
                        
            ###.###..#..#.###
            ###.#..#.##.#..##
            #......#.....#.##
            .##..#####.#..###
            ######.##.##...##
            #####..#..#.#....
            #####.....#.#....
                        
            #.##.#.#..##.##.#
            ##......#.#....#.
            ###.######..#..#.
            ##..#####.##....#
            .#.#....##..####.
            .##...#.##.######
            .###....#########
            #...########.##.#
            #...########.##.#
                        
            ..###.#..#..#####
            ########.##.##.#.
            ###.#.######.#...
            ##.#.#.#.....#.##
            ######.#...#...##
            .....##......#.#.
            ####..##.....##..
            ..####.##..#..###
            ...#..#.#.#..#.#.
            ...#..#.#.#..#.#.
            ..####.##..#..###
            ####..##.....##..
            .....##......#.#.
            ######.#...#..###
            ##.#.#.#.....#.##
            ###.#.######.#...
            ########.##.##.#.
                        
            ..##...##
            #.###..##
            #.###..##
            ..##...##
            ##.#...##
            ###...#..
            ##....###
            #.##.###.
            .....##..
                        
            ##...#.####..###.
            .#..###.#####..#.
            .#..###.#####..#.
            ##...#.####..#.#.
            ##...#.####..#.#.
            .#..###.#####..#.
            .#..###.#####..#.
            ##...#.####..###.
            ###...#...###..#.
            ###.####.#.#..###
            .#.#####.....####
            ..#####.##.###...
            ##.##...#...#.#.#
            .#.##.##...#.....
            .#...###.#.##.##.
                        
            ####.###.
            .......#.
            ##.#.#.##
            ...####.#
            ##...#...
            ..#.###..
            ....#..##
            ...#####.
            ####..#..
            ###.#.###
            ##......#
            ##......#
            ###.#.###
            ####..#..
            ...######
            ....#..##
            ..#.###..
                        
            .##.#..
            ....##.
            #..#..#
            #..###.
            #####..
            #####..
            #..##..
            #..#..#
            ....##.
                        
            #.##..#.#.#
            .#...###.#.
            .#..####.#.
            #.##..#.#.#
            #.##..#.#.#
            .#..####.#.
            .#...###.#.
            #.##..#.#.#
            #.#..##.#..
            #.#.###..##
            .####..#..#
                        
            #..##..#######..#
            .##..##.#.##....#
            ##.##.##..##...##
            .#....#..#.###.##
            ..####...#...##.#
            ###...####...#..#
            ########...##..##
            #......##....#.##
            ..#..#...###...#.
            #.####.#.#..#.#.#
            #.####.#.#..#.#.#
                        
            ########.##..
            ...##...#.##.
            #.#..#.##.#..
            #......#.#..#
            ........#.#..
            #.#..#.#..#..
            ..#..#..#..#.
            .##..##.####.
            #..##..####.#
            ###..####.###
            ###..###..###
            """;
}
