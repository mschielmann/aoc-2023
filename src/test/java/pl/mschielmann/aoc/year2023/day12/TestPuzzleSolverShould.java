package pl.mschielmann.aoc.year2023.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPuzzleSolverShould
{

    @Test
    void solvePartOneWithTestData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT);

        // WHEN
        String result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo("21");
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        String result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo("7705");
    }

    @Test
    void solvePartTwoWithTestData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT);

        // WHEN
        String result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo("525152");
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        String result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo("50338344809230");
    }

    private static final String TEST_INPUT = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
            """;

    static final String INPUT = """
            ?#.???#???????##?.? 2,1,7,3,1
            ?.##?###?#?##? 1,6,1,2
            ??.???.?.?.??.???. 2,2,1,1,2,1
            ?????#???#??#??..?#? 15,2
            #?????????#.?# 1,2,5,1
            ??..?.???# 1,4
            ?##???.???? 3,2,2
            ..??.????????.?? 1,3
            .?????????.#?? 1,6,2
            ?#?#??????##??#..# 1,2,7,1
            ??.?????###?? 3,4
            .???????#.? 4,3,1
            ??????#.?#???? 2,2,4
            ???.???#?..?????. 2,1,1,5
            ???#???#???#?#?????. 2,9,2
            ????.#.?????#?# 1,6
            ???????.#?? 1,1,2
            #????#??.???. 2,1,1,1
            ??#?????.???? 2,1,1,1
            .#?.?.#????#?.# 1,1,4,1
            ##?????##??? 3,3,2
            ??.?????#? 1,1,3
            ??????.??????##.??#? 2,2,1,6,3
            ?#???.?#?? 1,1,4
            #?#???.???? 4,1,2
            .#.?.??#?? 1,1,3
            .?#???#??#??#?#?. 1,10
            ?##...?##???.? 2,5
            .#?????????????? 8,1,1
            ???...???# 1,1,1
            ??#???.?????????#?? 3,4,4
            ????.?.???.#.?? 1,1,3,1,1
            ?#??.?#??? 2,1,3
            ?????##????#???? 11,1
            ??????.???? 4,1
            ?????#?..?#?.?? 2,2,3,1
            ??????.#..?#????# 2,1,4,1
            ??.???#..#? 1,2,2
            #?#??.#????#? 1,1,1,2
            ?.#?..?#?? 2,4
            ?.?????##??.#?????## 8,8
            ???.???.??. 1,2
            ??#??????# 7,1
            ????#?####?#?# 9,1,1
            ???#??#.????. 1,4,2
            ??#?#?????.##??.?.?? 5,1,1,4,1,1
            .#??.??????#?????#? 1,1,5,3
            ???#???.????#???. 4,1,4
            .?.??#??????#??. 3,5
            ???????..?? 1,2,2
            ????#.???????? 2,2
            ?.????????##?? 3,2
            ?.?.????????#??????# 1,2,4,4,1
            ???#??.????.???#??? 4,1,1,3
            ????????????.. 5,1
            ??.#??????## 1,1,1,2
            ..?.?#?????.?.. 3,1
            ??#??.?#??.??#????? 4,3,3,2
            ???#?#???##????##?? 5,7,2,1
            .????#?????#.. 5,2
            ..?#..##???.? 2,3
            ???????.?.? 4,1
            #????????#?????. 1,3,1,6
            ?#?#??.??.?#????. 1,3,1,6
            ?#???.??#??. 4,3
            ?#.#.????#?#?????#? 2,1,8,4
            ??.#???????.? 2,2,1
            ..???????.? 2,3
            ?#.?????????? 1,7
            ?.??#??#???????????? 1,7,3,1,1
            ???????.#????? 3,3,5
            ?..????.???#. 1,1,2,1
            ?.?????.?#.??..? 3,1
            .#?..?##???? 1,4
            ?????...??????? 3,2
            ???.?.?????? 2,1,1,3
            #???#.#?#??? 5,5
            ????#???.??? 5,1,2
            ????#????#?.??##?#? 1,4,2,4,2
            ??#???????????. 2,1,1,1
            #?.##??###????.????? 1,7,2,1,1,1
            ?#??##??.?#?#?##?? 5,7
            ?#???.?#?? 4,1
            ..??.??#???. 1,4,1
            ??.????.?? 1,2,2
            ???.??.??#???? 1,1,5
            ?..??#?##???#???# 1,6,5
            ???#?????????#?? 4,1,5
            ?#???#?#??.? 1,1,1,1
            ???.???.#?.???. 1,3,2,2
            ????###?..??.?#???. 7,1,4
            .??#?.????? 4,1,3
            .#.??????? 1,3
            #????##???.? 1,5
            ??.???#?????#???? 2,3,5
            ??#?##?????# 7,2
            ???#????#.. 4,2
            .????.????#?#???? 3,1,3,5
            ??.????.???.??#??#?? 2,1,2,6
            ?????#??#?#????? 1,5,3,1
            ??????.#?? 3,2
            ??#????.?#?. 4,3
            ???#???????????#?#. 2,1,3,1,1,3
            ???#.?###???##??# 1,1,12
            ?#????#?##.???.?.?#? 1,6,2,2
            .?#??.?#???#???????. 4,7,1
            ?###???#????##??.? 5,2,2,1
            ??#?#????#?#?..?? 1,1,4,4,1
            ???.#?.?????? 2,2
            ??#???#??????? 7,1,3
            ?.?##????#?. 3,4
            ??.??#???????. 1,1,4
            ?.?#???????...????? 7,2
            ????.???#??.?. 1,4
            .#?.?#?#??????? 1,6
            .?????###??????????. 8,3
            #?.#?#??#?????? 2,3,2,4
            #?#?#????.??????? 6,7
            ??.?#??.#?#? 1,2,1,4
            ??.?#???##?###?# 1,2,6,1
            ?.??...??? 2,2
            #??##?#??.?#??? 1,3,1,1,2
            ????##?????.??.?.. 4,1
            .?###????..?.?# 8,2
            .????#??.#.#?? 5,1,3
            ??#??.????##..? 4,6
            ?#?..???#?????????.? 2,1,4,5
            ..??..??#.?? 1,2
            ?.#?.?.???#?##?????? 1,2,1,1,5,1
            ??.?##.?..???? 1,2,1,2
            ??.??????#?.??? 1,1,4,2
            ???#...??#?#.# 1,5,1
            ????.???##?#?.# 1,8,1
            .???#?????.? 3,1
            .##???#.?#??#??. 3,1,2,3
            ??#??????#???.? 2,1,1,3,1
            ???#?.???#???#??? 2,3,2,2
            ???????#???????????? 2,2,10,1
            .???#??.?#??#?#. 1,1,1,6
            ????##??#?.. 6,2
            ????###?##?#?? 4,4,1
            ???#????#???? 3,7
            ?.?###?.#???#??? 3,1,3,1
            .#?.??#?.??#??? 1,1,2,2,2
            ????#??##?. 1,5
            ???????#?##.?.#.? 2,6,1,1
            ?#?..???.?#?..??? 2,1,1,3,1
            ??#???#.??.#?#???# 1,1,2,2,4,1
            .#.?#.?#???#?. 1,1,1,3
            ??.##??.??#.?.? 1,4,3,1
            .?#??.????# 4,1
            ???##?#????##? 2,10
            ??#???????#? 3,1,2
            ##.??#?#??##? 2,9
            ?#?#?#?????.???.?.? 10,1
            ?..#????#????#?. 1,1,1,6
            ...?????#???????# 9,1,2
            .???.???.?? 3,1,1
            ??#.?.????#?#??#??? 1,1,1,10
            ??.??.#??#?#?.??#??. 2,7,2
            ??##?#?..??.??? 7,1,1,1
            ?#????.#.???#.?#.# 5,1,4,2,1
            .?####??#.???? 8,1,1
            ????..#??????? 1,1,1,4
            ?.#???#..????? 3,1,1,1
            ???##?#??.#? 1,2,3,1
            ?????.#???#?.???. 1,6,3
            .?.???????. 1,2
            #?.?????##? 2,6
            ?##???????? 2,2,1
            ?#.##??##?# 2,2,4
            ??#?.#?.#?.?#??#.?# 1,1,1,1,5,2
            ????#???????#?.?#.. 9,2,1
            ?????#.??####? 1,2,7
            #???#?????? 6,1
            ???#?.????# 2,2,1
            .#.#.#??#?? 1,1,6
            ?#??##?#????.?..???? 8,1,2,1
            ?##???????????? 4,5
            ??#??.#?????##. 3,1,3
            ???.?#?????????#??.. 1,1,12
            ??????#?????.?# 1,7,1,1
            ??.#???????? 3,1
            ??.?#?#?.???#??? 1,3,6
            ?#??#???.??.??# 1,1,1,1,1
            #???#????????.???. 1,1,3,5,1,1
            #??..?.#????#? 1,1,1,4
            ?????.???? 3,1
            ??????###?.? 1,7,1
            .#..?##??#??.???? 1,6,2
            ?##???#?.## 6,2
            ?##.??.??#?.???#??? 3,2,1,1,1,1
            ???##.?#????#??? 5,1,1,3
            #.??#??.?#????.???#? 1,3,1,6,1,1
            ??#???#.??#.#??? 7,1,1,1,1
            #??#?????.?.? 7,1,1
            .?????.?#????..#?#? 4,5,3
            ?.?.#??#??.????? 4,1
            #.??#?##?...#. 1,5,1
            .??..?.##??? 1,1,4
            ???#????????#??.? 3,8
            ???????##?????? 2,1,4,1
            ???????.??. 2,2,1
            ?#.#??##???.???? 1,1,3,1,1
            ?.?????????. 1,1,3
            .???#??#??.#?####?# 6,8
            ?.?#??#??.?? 1,1,2,1
            .??.???#??????? 1,6,1
            ??#..#???.?? 1,1,1,1
            ?...#????.#? 1,1
            ??.??.???. 1,1,1
            ??#?##????.#?.?? 10,2,1
            ????????.?#????#???? 2,3,4,2,2
            ???????#?#????.##.? 2,8,2
            ??#?????.?. 4,1
            ??#???.?.??#?????? 1,1,2,1,5,2
            .??.????#?#?#?? 1,1,3,2
            #???.??.#??##?????.? 1,1,1,1,5,1
            ##??#?????#??????#?? 5,12
            .?#????..??#?#????. 5,7
            ????#.?????.?.?#? 4,1,3
            .#..?#?????.?# 1,5,2
            ??????.?????# 3,1,1
            ?#?????.??? 1,3,1
            #.??##???##?.???##? 1,10,2,2
            ??#???..#?#? 4,1,1,2
            .?.#?.?.???. 1,1,1,1
            .#?.#.#??.?????? 1,1,2,1,2
            ?#??.?#????#. 2,6
            ??????##??. 1,2,1
            ???????.??.????.?? 1,1,1,2,4,1
            ?#??????#??###.#.?? 3,10,1,1
            ..???#?????#?#?#? 3,7
            ?..#????????. 1,1,4
            ?#??#?#.?? 1,4,1
            ???##??##?.? 2,2,3,1
            #??#####???#.??? 8,2,1
            ??#?.????.? 3,1
            ...????#?? 1,4
            .?#?????###?##.? 3,4,2
            ????#.????##??? 1,4
            ?##??????.## 2,1,2
            .?..?#.????#????? 1,1,8
            .#?#?.?.???..? 4,3
            ??#??????#?#. 1,1,5
            #?????#?###????#??? 1,9,1,2,1
            ???????###??.??.? 1,1,8,1,1
            .??.???#?#.#??.?#.# 1,6,1,1,1,1
            ???#????????. 5,4
            .?##?????????? 7,4
            ?????.??.?#?? 5,1,2,1
            #.????????## 1,1,3,3
            ???#????.????##.?? 7,5
            .#?.??#??##??? 2,7
            #?..??#?#..???# 2,4,1,1
            ??#..??#??#????#???? 1,1,1,4,4
            ??????????????. 2,1,1,4,1
            #??#???.?#. 4,2,2
            #.??#???#??.?#??.?? 1,8,1,1
            ??.???#?????#?????? 1,2,3,3,5
            ????.#..???#??#?#? 1,1,1,7
            .#?????.???????.# 3,1,2,2,1
            ???##??.?#??.?? 5,2,1,1
            ????.?##????# 2,2,3
            ??????#.?#?? 1,2,2,1
            ##?.#.#??????#?? 3,1,1,7
            ?????.?????#???????? 6,1
            .?.???##????. 4,2
            ????#????#?????? 1,6,1,2
            ??#?##??????##?#??? 1,7,7
            ?..???#.??.? 2,1,1
            #?????#.??.?#.???? 1,5,1,2,1
            ???##?#??..??.?#?? 3,3,2,2
            ?#??.??#?.? 2,4
            ?????????#??#?????? 1,1,7,2
            ???#????#???#?#?. 1,7,6
            ?????#?..??#????#??? 4,4,5
            #?#???????????? 3,4,1,1,1
            ???#????#.#.???? 1,3,1,1,2
            ??.??#???#.?? 2,3,1,1
            ?????.??##???.??.?? 1,7,2
            .??#???????##??? 1,10
            ?###????#????#??# 4,1,3,4
            .?????.?.??. 3,2
            ?.?.?#??.?? 1,2
            #?????#???#? 7,2
            ??#??????.?#???#?#?? 5,8
            ????#??.????#.???#? 1,4,3,1,1,2
            ???#??.???? 4,1
            ?#?????#?#. 3,3
            #.?????#?.????#?# 1,6,4,1
            ??#??..#????. 2,1,2,2
            #?????#??#.??????? 2,3,1,3,1
            ?????###?#?.??#?? 7,1
            ###???????.?? 4,2,2
            ??#..??### 3,5
            ?.##.#?????? 1,2,1,3
            ??#?#?.#?.#???? 1,4,1,3,1
            .?????????? 1,2
            .????.#??#???##?# 1,1,1,2,5
            ?????##?????????. 4,5
            ???..???#?.#.?#?#?? 5,1,5
            ???.???????##???.?? 1,1,2,1,5,2
            ??.????#??##?????? 1,9,2,1
            #?#???#?###???#??##. 1,10,5
            ????##????##?..?# 2,4,2,1
            ##??..?#???????.? 3,5,1,1
            ??##.?#????#??#.?# 4,10,1
            ????????????? 1,1,1,1
            ?.????#.?.#?.? 1,5,2
            .?.#??.#...##???.?? 1,3,1,2,2,1
            ??##.??..?#?# 2,2,3
            .?.#????#??#? 2,6
            .???????#??#.???.? 1,7,1,1
            ??.???????? 1,3
            ??.??#?.?????##??.?? 1,3,6
            ????.?????#?##??.? 3,11,1
            ???..????#.##? 1,2,1,3
            .??#???.???. 3,1,1
            ??.???..?##???.. 2,2,4
            ???.#??##???????? 1,1,4,1,1
            .?..?????? 1,1,3
            ??#???..???? 5,2,1
            ?????##??#??????#? 1,9,1,1
            ??.?????.??????# 1,5,1,1,1
            ??????#???. 1,1,5
            .?????###..?.#?. 1,1,4,1,1
            ????#?#.???##? 1,3,1,5
            ?.?..????###??.. 1,5
            ???.#????. 3,1
            .#?#?.#?#?.???# 1,2,3,4
            ????#??#??#?#???? 2,7,2
            .?.???#?#??##???# 1,13
            #??#???#..?#??#????? 1,6,5,4
            #???????#?? 4,5
            .??#??????.??# 2,1,1,3
            ??#????.?.# 2,1,1
            .#??##?#.#?..???# 7,1,3
            ..?#??????##??.? 3,1,4
            #??#??#??.?#. 1,5,1,1
            .???#????.#?#? 6,4
            .??#???#?.??????? 3,1,2,3
            ????????.#?#.# 1,3,3,1
            ??.?#????#?????????? 1,11,1,1
            ..??#?.???#?.?.?. 3,1,1,1
            ?#?????##??##.# 2,8,1
            ????#???#??????? 2,1,6,1
            #???.?##???#?## 1,1,4,2,2
            ?????#??##?#??# 11,1
            ?.?#.#????#?#?#?#? 1,1,3,2,5
            ##???.????.???? 3,1,1,2
            ???..##?.??..#? 2,3,1
            ???#??#?.??.????? 6,1
            ?.?#??.????#??#?#?? 2,7
            .?#????.??#??#???#? 6,2,2,1
            ???.?#???.? 1,4,1
            ?##.????####? 2,6
            ###??????#?#? 5,3
            ?.#??#.?..##?????? 4,6,1
            ?.#.#.???#?##??????? 1,1,1,10,1
            ??.?#?????# 3,2,1
            ??#????#?#?? 2,3,1,1
            ?#?.??#.??? 1,3,1
            ????.?#???#?#????? 2,1,2,3,1,3
            ????#?.??#????.#? 1,1,5,1
            ????????#??#??#?#? 1,1,1,1,5,2
            ???.??????#? 1,2,4
            ????.??#???# 1,5
            ?????????#?????????? 2,10,2,1
            ??????#??.. 2,3
            ?????#.?#??#?? 3,1,5,1
            ?????#??#?..?? 3,4,1
            ??#???#???#???.?.?#? 3,10,1,1
            #?##??????#???????. 7,1,3
            ??#??????.?##??##??? 7,7
            ??????##?#?????? 1,8,1
            .????#??#???????. 7,3
            ?#?###???... 5,1
            .?#??????.????? 1,2
            ?.?#?????#.???#?? 1,5,1,3,1
            ????#?????#??#??. 5,2,2,1,1
            .?.?#?#???#??. 1,5,1
            ???.????#??##?? 1,8
            #???.????#???????? 2,8
            ??.##?#?#?? 1,6,1
            ?????????#..?#?#?..# 9,4,1
            ??????..?.???#.#?? 2,4,1
            ?.#.?#????. 1,1,2
            ?##???#?????##?.???# 3,5,4,1,1
            #??#?#.?????#??#?##? 1,1,1,1,7
            .?.????#???#??????? 1,1,1,2,6,1
            ??????????###?? 2,7
            #??????#?###?????? 1,10,2,1
            #??.?.??#?#? 2,1,5
            .#..?????#?.?? 1,1,4,2
            #?...#?.#???#? 2,1,1,3
            ###?#??##???????#?? 14,1
            ??#?.?#????#??#????? 2,11,1
            ???...??.#?#?. 2,1,4
            #??.#??#????#?? 1,4,6
            ???.#.??#?#???.?##. 1,1,1,6,3
            ???????..?????#??? 1,1,1,1,6,2
            ???##????.????#..?. 5,2
            .???#?.?????#. 4,2,1
            .??????????#.#? 1,1,5,1
            ????.?.??#?##???? 2,6
            #?.?#?.????#.?????# 1,2,1,1,1,4
            ?#.?#??????#? 1,9
            ??#?#???#?#?????.?#? 7,1,1,1,1
            ...?##?#.????. 4,1
            ?????????.#? 3,2
            ?.#?#?????#?? 5,1,1
            ?###.???#?. 3,2
            ?.???#.????#?? 2,6
            ???.??..???#??. 1,2,5
            ?#..?#?????##???. 2,9,1
            #?.??###?#?#??.????. 2,8,1,2
            ???#?.????.??????? 4,4,6
            ???#?????#?#?.# 2,1,5,1
            .?????#???#??? 1,2,4
            ???##??????? 6,2
            ???????#.????#??? 6,1,2
            ????????#???.?#??## 1,1,1,1,1,6
            ?#?.?#??#? 2,2,2
            #..?.?#.???????#???? 1,1,1,1,1,5
            ???.?.???? 2,1
            #???..???#??.??#.?? 1,1,2,3,1,1
            ???#..##?#???#? 3,4,1
            ??.#????????????? 7,3,1
            ?..?.?????? 1,1
            ?#????..#.#????? 2,1,1,1,2
            .?.???????????? 1,1,1,4,1
            ?.????????#? 2,3
            #?????#..???#? 1,3,1,1
            ???.?????##. 3,2
            ?.??????#??.???#??? 4,1
            .?????#??#???#?#?. 8,4
            ???..?##??.# 1,1,3,1
            ???.??#??#?## 1,7
            ????.#??#????. 1,5
            #?..????.#??.?? 2,3,3
            ???????##?????????# 3,1,9,2
            #??##?#??#.?#? 2,2,4,2
            ?.?#?#?????. 1,1,3,1
            ?#???#??????? 6,2,1
            ?????..?.#?. 1,1,1,2
            ?????.?#??#??. 1,5
            ??#??#.#?? 2,1,1
            ..?..#.#??##?#..#? 1,1,7,2
            ??????##?#?.??#??? 1,1,2,1,5
            ?#??#?#??.??.??? 2,2,2,1,1
            ??...??#?#?#????. 1,8
            ???#???#?# 1,2,4
            ?#??..??.?#.? 2,2
            #??#??#??? 1,6
            ?????...???##?????? 1,2,5,2
            ?#..#?????#???#..? 1,11
            ??????.????.## 2,1,2,2
            ??#????#??##??????. 1,9,1,2
            #??###???..???? 6,1,2
            ????????????#??.???? 9,2,1,1
            ??#???????? 4,1,1
            ?#????#.??#????? 4,1,1,1,2
            ?#..?#?????????#?? 2,3,1,1,1,2
            .???????#??.? 2,6,1
            ???#?????#?????#??#? 1,8,1,4
            ?#?.???.??? 1,3,1
            .??????...??.??#?.?? 5,1
            ??.?#????#???##???.? 2,1,11
            ???#?.???#.#.? 4,4,1
            #?###?????#.???? 1,4,1,1,1
            .???.?????.???? 1,1,2,1,1
            ???#??#????.??? 4,3,1
            ????.?????#?? 1,1,5
            ??##??.?..?????? 1,3,1,1,1
            ????????..#?. 7,2
            .????##??..? 1,3,1,1
            .##?#??.#?#?#??? 5,1,1,2,1
            .????##?.??????? 2,3
            ?#?#??.?.. 4,1
            ?????????#?.??? 1,6,1,1
            ????#?#.???. 7,3
            ?????#??#?##?????.#? 12,2,1
            ???#??#?.?#????#?? 7,7
            .#.?.????????#. 1,2,1,1,1
            .??.????## 1,5
            ????#??.?#????.. 2,1,4,1
            .???#??????#???????? 1,1,11
            .?.???.???#?#. 1,1,2,1
            ??#????#??#????.. 6,5
            #?##?.?#???#? 4,6
            ????#???????????? 1,2,1,1,4
            ??????#??.???????? 1,1,3,3,1
            ?????.###??#?? 1,1,8
            ??##?.?????.? 4,2
            ?#.??.???? 2,1,2
            ??#????.??#????. 4,1,3,1
            .?????#?##??. 1,7
            ?###.??#?????. 3,4
            ??#????##?##???.?? 4,6
            ????#?#????#???#??? 5,7
            ?#??#????? 2,1,4
            #?#??.??#???.? 5,2,1
            ?#?#?????? 3,1,1
            ?#?#??.????#? 5,6
            ????.???#.???# 1,1,1,4
            ?#????.????.??? 4,3,2
            .???.?.????#??##?.? 1,9,1
            ???.???#???.????#?? 1,1,7,2,1,1
            ?#??????.?#? 2,1,1,1
            .???#.???#?? 3,4
            ???#??#??? 2,1
            ???#?#?#?. 3,1,1
            ##???????? 4,1
            ?.?#???.##? 1,3,2
            .???.?.?#????? 1,1,1,3
            ??#?..??????? 2,1,1,1
            .#.??#?..? 1,3
            ?.???#????. 1,3
            ????????#?#??? 5,1,1,1
            ??#??????.?#???..? 4,1,1,1,1,1
            ??????.???##? 1,2,1,4
            ???????#???????? 1,12
            ..??..?#????###?.? 1,10
            ?????#?#??#?.??#?#? 1,1,7,1,1,1
            .?#????.??#?? 6,1
            ?#???..??????#?. 1,2,1,6
            ??.?#??#?##????#?#. 1,2,11
            ??#??.???#?#??#?#. 4,1,5,1,1
            .#.?????#????? 1,1,7
            ????#.????#?#?#?# 1,1,9
            #?.???#???.?#.?#? 2,4,1,2,1
            .???...???. 3,1,1
            ..?.???##?..??#???. 2,3
            ##?#?????????.??# 5,1,4,1,1
            ?#????.?.??#### 2,1,1,1,5
            ?#????#?#. 1,2,1
            ?##?#??????#???????# 8,1,1,1,1
            ?.??????#####?? 2,7
            #????#?????#????.??# 10,5,2
            ???#?....#? 3,1
            .?#???.???###????? 2,7,3
            .??????????????## 8,2,4
            ?????????.#?#??. 4,4
            ?????????.?###??? 1,2,3,5,1
            ??#??##.?????. 6,2
            ..?.?##.??? 1,2,1
            ?.?.????.#? 1,3,2
            ?#????????????.??? 2,4,3,2
            .??..??.?.?#??????? 2,1,8
            #????.?.#?????# 5,1,4
            ?#????.?#??#.?.? 2,5,1
            ??##???#???.??#.? 1,6,1,1,1
            .???????##???#?.. 1,10
            ?????.?..???..? 3,1,1,1,1
            ??.????.???.?#. 1,3,3,2
            ???????.#? 1,1,1
            ?.??#??????#??? 5,4,2
            .???#?????#.? 1,3,1,2
            ?#??#??.?????#?#??#? 5,1,1,1,1,3
            #.?#?????#??#??? 1,9,1,1
            ???..????#?#???#. 1,1,2,7
            .??#?#?#??#?.?###?? 2,3,3,3
            ??????#?.????# 3,2,2
            #?##?#??#?##? 1,10
            ?#??#.????#?##? 4,7
            ??????#???#?..?# 7,1,1
            ?????.?.?#?? 1,1,1,1
            .???#?????#.?#?#?.? 5,2,1,1
            ??.?#.????#? 1,2,1,1
            ????#?#??????? 2,6,1,1
            ?.????????#? 1,3,1,2
            ????????.#?#?? 5,3
            ??????#??.? 3,1
            ...?#?????#??.?# 3,5,2
            ?##?#????.??##?.? 6,1,4
            #?.?.??##?#? 1,1,5
            ????#?#???#?????#. 1,5,1,2,1
            ##?#?#??.??.????? 8,1,1,1,1
            ??#?#?????????#???? 11,1,1
            ??#.#???#???#????? 2,3,1,3,2
            ?.??#?.??.???. 1,3,1,1
            ?#????#?????? 1,5,3
            ?#.?.??#?.?#???# 1,1,6
            .??????.?#???????? 1,1,2,1,4
            ?#??.??.??.? 1,1,1,1
            ?#?##?#?????##?..## 9,1,3,2
            ????.?#.???????.# 1,2,1,4,1
            .????.???#?##? 1,1,4
            ?#?.?????#?..##? 1,2,2,3
            ?#?#?.?#????#. 3,2,2,1
            #.??#?#???? 1,3,1
            ???..##??.? 1,2,1
            ???##????.?????????# 9,1,3,2
            ####???.???#???#? 6,1,4,1
            .?#???????#. 2,1,1,1
            .??????????? 1,1,2,1
            #?##?#??##?? 6,3
            ..?.???.?. 1,3,1
            ?..?.?#??????.???.? 1,1,3,1,3,1
            .???.#???#??.? 1,7,1
            ?##?.??##?.????? 4,4,2
            ?.#####???#????? 7,4
            ????#??.?##?? 3,2,1
            ??#.????.?#???##?#? 1,1,1,3,3,1
            .????..??#?#?##?#??? 1,13
            ?.???.????#?????? 1,1,6,2
            ..??.?????????????.. 1,2
            ?.????#.?#?? 2,1,1,1
            ???..#??#?.? 1,1,2,1
            ??????.??#. 1,3,2
            ??#??#..#?. 3,1,1
            .?..???#??#. 1,4,2
            ?.#.????.? 1,1,1
            ?#?????.?.??.???#??? 4,1,4
            ?????#?#.??.? 4,3,1
            #?#??????##??#?#???. 4,2,5,1,1
            ????#?????#??## 2,3,3,2
            ?.???????.????? 1,2,1,1,4
            .???.?.??#.#?? 1,1,2,2
            ?###?##?#?????..?? 10,1
            ???????#?????.#.??.# 6,1,1,1,1,1
            ??.????#??????. 4,1
            ?#???#.??#?. 3,1,2
            ?##?#..?#?..??.. 5,3,1
            .####?#???.?.?#?... 8,3
            ????#?#????#??#????? 1,11,1,1,1
            ????.??????#???? 2,2,2,4
            ?.#???#?#??. 1,4
            ??#???????#??...??? 4,1,3,1,2
            ??.???#???#???#??.? 1,3,5
            ????#?..?? 3,1
            .????#??.?.?? 3,1,1
            ??#?##????. 1,4
            ?#?..?????????#?. 1,10
            #??##??..#.#? 7,1,2
            ???###?????.???## 1,8,1,2
            #??#??.?????????..? 1,1,9,1
            ????#?????#???.?? 12,2
            ?.????.?##????.?? 1,3,3,1,1
            ???##..????.? 1,2,4,1
            #?#??.???.??#?#?? 1,3,1,5
            ??#?#??????????? 1,8,1,2
            #?????#??. 3,3
            .????##??.????? 1,4,1,2
            ????#??????..??.# 9,1,1,1
            .??????.#.#? 1,1,1,1
            ?.??.#??.??.??#.?.? 2,3,1,3,1,1
            ????##???##???#?#? 1,9,3
            .???.??.?# 1,1,1
            ???#?????#??. 3,1,2
            ??????#.??????###.? 6,4,3
            ?#?.???#???.?#..?? 1,1,4,2,2
            ??.?????.???###.??? 2,2,1,5,2
            ?###????#?? 3,3
            ?????##?.?#?##???. 7,6
            ???#???##????? 3,4,1
            ..?????.?##????.??.. 4,7
            .?...???#? 1,3
            ??##??#????.#??????? 3,1,1,6
            ?..?.#?????#? 1,1,2,1
            ???.??#???? 1,5
            .?#??#??????????? 1,3,1,1,1
            ????#?#?.?#? 7,1
            ?.???#??????.. 5,1
            ?#???.????.?#??? 1,1,1,1,5
            ?##????.?. 2,1,1
            ?#.?.##??? 1,3
            ?#??????.?..?.???. 1,2
            #?????????#??#???? 5,1,8
            ???#.????.??? 1,1,2,1
            ????#??.##? 1,1,2
            ???????#??#? 1,8
            ?????.?????. 2,1,1,1
            ????.?#?###?.?#???. 2,7,2,1
            ???.##????????????. 2,3,7
            #???#????? 3,1,1
            ???##???##???# 1,3,3,1
            #???????#.?.?.?? 9,1,2
            ?#?????##??????? 3,10
            ???...?????###?? 1,7
            ???.??#?##?#? 1,1,2,1
            #.?#???#?????#. 1,7,1,1
            #??????????? 3,3,1
            .???.?.#?????# 1,7
            ?###???#??#?.?#????? 12,1,1,1
            .??????#????.??? 6,1
            ?#?.##?##??.? 2,7
            ???..??...??. 1,1,2
            ??#??#?#???#???????. 7,4,3
            ??.??#????. 2,7
            .?#?.????#??#.?? 2,7,1
            ?.#.??#??#???.?? 1,1,8,1
            ????#??#?#.??.?.#### 10,1,1,4
            .?#??????.?.???? 8,2
            ??#?.??.?? 2,2
            ??????.??????##?? 4,4,5
            ?#??#?.?#? 3,1,3
            ???###????..????..? 7,4
            ??.???????#?.? 2,5,1,1
            ?#.?#?????? 1,3,1
            ?#??.??..##?.???#.? 4,1,2,1,1,1
            ??????#????#?#??.??. 1,3,8,2
            ??????#?#???? 6,3
            ?????##????#?##? 2,3,1,5
            ??#??#??####??.??##? 12,4
            ..???##??#?#??.?. 10,1
            ?.?????##???? 7,1
            .????#??#?##????.#? 2,11,2
            ?????...?.##?##? 3,1,6
            ?????#????.? 1,7,1
            ?#???#?#?? 3,1,2
            ??#????????.?#?? 1,4,2,1
            ?????#?#??? 1,6
            ?...?#??..???????.? 1,4
            ??.???##?#??##?.??? 1,1,10,1,1
            ???#?.?.??#?#?# 1,1,1,7
            ?#?..???##?? 3,1,4
            ??.??.?#????##?.??#. 1,1,9,2
            ??????????? 2,1,2
            ..???????#? 1,2,1
            ##??##?.?. 2,2
            ??###??.????#? 3,1,1,3
            ?..???#?.? 1,5,1
            .#??????.#?. 1,1,2,1
            .????#??????????.? 1,5,1,1,2
            ????#????...??????#? 3,1,3,3,3
            .??????.??. 3,1,1
            ????#?#????#??#??? 1,1,1,2,2,1
            ???..#????.? 1,1,1,1
            ?##??##?#.?#?.??. 4,4,2,2
            ..??????##???? 3,4
            ????.?.??#??? 3,1,2,1
            ???????.?. 1,1,1
            ..#?.?##?#????##?? 2,11,1
            ??????????#???. 3,6
            .?????.??.?#??# 1,1,1,2,4
            ?.???????. 1,1,1
            ???#??????#?## 4,1,1,2
            .?...?????.???? 1,4,2
            ?##??#?????..?.? 9,1
            ??.??#????#?.#??#?? 2,6,1,2
            .?..#??#??#.?#?##?.# 1,1,4,4,1
            ??.?#??.#? 1,2,2
            .#??..???#. 1,3
            ???##.#?.?? 4,2,1
            ?.#??????.?## 2,1,3
            ##????.?##?#?????? 2,1,1,6,1,1
            #..???#???#??#???#?. 1,6,1,1,1,1
            ?.??.?#?#?.# 1,2,1,1
            ..##?????.## 5,2
            ??##..???#?##????? 1,2,8,3
            .??#?##?#?#?#??. 10,1
            #??##?#??.??.#? 8,2,1
            ????...??#?#?#??.??? 2,8,3
            ??..#.??#???.?#??? 1,1,4,1,1,1
            ???.?????.???.?# 3,1,1,1,1
            .??###.?#????. 3,3
            ##?#??.??..?##?. 4,1,1,4
            ##????.??#??????.??? 6,3,1,2,1
            ?#?#?#?#?????? 1,1,1,5
            .????##????. 2,6
            #???##???????.?????? 10,1,1,1
            ?#??.##?#?? 3,2,2
            ?#?????.#??## 1,2,2,2
            .?????????#??#?.?? 1,1,9,1
            ???????##?#? 1,1,4,2
            ??#?.??????.??#??#? 4,1,1,1,1,5
            ?#????.???? 3,1,1
            ??.?#??##? 1,2,3
            .????????.? 1,1
            ???.???#?##?#?#??## 1,2,9,2
            ?????#?##.?## 3,4,3
            #????????? 1,2,2
            ?#..?#?.?#..??##???? 2,1,2,1,3,1
            ..??????????#?#????? 2,4,5,1,2
            ??#?#?...???????? 3,2
            ??#.##??##?????? 1,1,2,2,4
            ?#?.??.???#?# 2,1,1,1
            .##.???#???#.?.? 2,1,2,1,1
            #???#???#????#??#. 1,1,1,4,5
            ?#?.????.? 1,1,1
            #?????..?. 1,1,1
            ??.?#???.??#? 2,1,2,3
            ..??.?#???##?#??#?#? 1,14
            ?.?#??#????##????##? 6,3,5
            ????..?.?.???###??. 1,1,1,1,5,1
            ?.??#?.??.?##?##### 1,8
            ?.?#?????.??? 5,1
            ???.##?????????? 2,2,2,1,2
            ?.##????##???????#.. 2,11
            #???????#??#???? 1,1,1,6,1
            #????#??????# 1,1,5
            ??.#??#???..##??#?#? 1,6,7
            #??##?????????###?.. 6,7
            .???????..#?.#??.? 2,2,1,2,3
            ????????#?#???#??? 11,1,1
            ??..??.????? 2,1,1,3
            ?.??#??#??? 1,1,2
            ??.??##?#.?#??#?.??? 1,6,5,1,1
            .#?#?#??#?#?????? 10,1,1
            ????????????#. 6,1,2
            ??.??##???.??? 5,1
            .??????#???###?.? 1,1,1,4,1
            #.??#???#????????. 1,4,5,1
            ##?##?????#???#??#?? 15,1,1
            .????#?#?#??? 2,1,5
            ??###??.?? 4,1
            ??#?#??.?? 4,1
            ??.????.#.??#??? 2,1,4
            ?????..???#?.?? 1,1,1,3,1
            ?.#???????????#???# 5,1,3,3
            .???????????.?? 2,6
            ??#?????#.?#????? 8,4
            ??#...#???? 1,3
            ?.??.#??.####? 2,3,4
            #?????#?????#??#?. 1,1,1,1,3,1
            ????#????### 2,8
            ?????..??. 2,2
            ?.??#..???#?##?? 3,7
            ?##????##??#. 2,1,4,1
            .#?????????##?.?#..# 1,3,1,4,1,1
            ?#?#??#????.#.#??. 1,1,3,1,1,1
            ??#???.??????#?.. 2,1,3,3
            #?##???#????#. 1,11
            ??..?..????#???# 1,1,1,3,2
            ??#?.?#?#? 3,4
            ??#???#??#?# 3,3,1,1
            #.?..?##?#????#??##? 1,1,3,4,1,2
            ???????#?.?????#?? 6,7
            ?#??#??.?????#. 5,6
            ?.??..???#?..??.??? 3,1
            ?#??#???#? 1,1,2
            ?.??##??????????? 10,3
            ##?..#???#?.?? 3,3,2,1
            ??#???..?#???? 5,6
            ?#?.??#???.??#?? 3,3,1,1
            .#???????.#????##?. 3,3,7
            ?.??##??..?? 3,2
            ?#??.???.???.?#? 4,1,1,2
            ?##?.#??#?? 2,1,2
            .???#?.???????##.?? 2,3
            ?#??#??.?.????..?? 1,4,1,1,1,1
            ?.????????. 1,6,1
            ?????##?#????.#. 8,1,1
            ?..????????????????? 1,11,1,1
            ..?#?????#...? 1,3
            ?.?#???#.?? 2,2,1
            ##??.???.?? 3,1,1
            ???##??.?.#? 5,1
            ??.?..#??.?.? 1,3
            .???.?##????????? 3,11
            #..?#???#??.???? 1,8,2
            .??????.?.?.????#?#? 5,1,2,5
            .?????#???#???..??? 5,3,1,2
            ?????#????.? 8,1
            .??#.??????##?.. 1,9
            #???#?#..??#?#???# 1,1,1,1,7,1
            #.?#??##???#. 1,1,2,1
            .??????.?#???? 4,5
            ???##?.??????.#.??.? 3,6,1,1
            ????.#??????? 1,4,1
            ??##?.???? 3,1
            .??##?????#?#???#? 1,4,6,1
            ??#?####?##?.??#?#? 12,3
            #.?.??##?#.????? 1,1,4,1,1
            ???????..# 1,1
            ?????#??????#. 2,5,4
            ??#???#???.??#? 7,1,1,1
            ??????.?###. 2,4
            ?#.??#??#????. 1,8
            ???#???##??#?? 1,9,1
            ??###?????????? 11,2
            ??...??.#?#??#??. 2,1,1,1,3
            ???.????#??.?. 3,1,2,1
            ??###??#????. 1,3,2,2
            ??#???????##?? 4,1,5
            ?#????????#.??? 1,7,1,1
            ???#???#?????# 1,1,7
            ?????.??.?????? 2,1,2,1
            ??????#?#??#?.?? 1,1,3,1,1
            ?##?#??#?.#? 2,1,1,1
            ?.???.??.??????? 1,1,2,2
            ????????..???#? 1,1,1,1,2
            ??????.##?.?###????? 5,3,3,2
            #???????## 1,1,5
            ???.??#??#??#.# 2,1,1,5,1
            ???#????.#?#? 4,2,3
            ??.#??##????? 2,7,1
            ???#?.?.?????? 5,1,2,1
            ???.????.? 1,1,1
            ???????##???????. 1,8,1
            ??.?#???.#??#?? 1,1,2,2,1
            ?.???????.??#??#?.?? 7,6
            ?????..?#???. 2,3
            ???##??#??.?? 9,1
            ??#?##??#??.???#?? 11,5
            ????????#??#??# 4,9
            .??.?.???? 1,1,1
            ??????##.???? 3,3,2,1
            ????????#???#??#???? 1,11
            #.????#?????#?#???? 1,1,1,4,5,1
            ??..????????.??.???? 2,1
            #.?#?#?..?.###?#?? 1,2,2,1,5,1
            ?????.#.??# 4,1,1
            ?.?.?????????????#? 1,9,1
            ???..###?.? 2,3
            ???#???#.####???? 3,1,6
            ?#???#??????##?..? 1,7,3
            ????#.????#??.??.?? 4,1,3,1,1
            ???#??????? 2,2
            #.??.???#??#???#? 1,1,4,2,3
            #???#.???????##??? 1,1,2,1,5
            .????#?????#????? 1,4,5
            ??#..#??#? 1,1,1
            ..?#?????. 3,1
            ??.???##?? 1,2,3
            ????.????? 1,1,1
            ?##???.#?#???.#.#? 2,1,1,3,1,1
            ??.#?????##?#..??.?? 1,2,1,5,2,1
            .??????#??#????#.? 1,4,1,4
            ??#?#?#?##????.?? 1,10,1,1
            .????????# 2,1,1
            ??#???.????.##?? 3,1,2,4
            ???.##?.???.???#?.?? 2,2,2,1,2,2
            ?##?????#?????#?? 2,4,1,4
            ??.?#??#???????.?? 2,2,1
            #??#??????.????#? 1,7,3
            ?.#???#.#? 1,5,1
            .?#?????#??.? 4,3,1
            .##??.?????.?? 4,1,2,1
            ???#??#?.?? 1,3,2
            ?##???#????. 2,1,1,2
            ????.###?? 2,3
            #.??#?????????????? 1,5,6,1,1
            ????#????#?.# 2,7,1
            ????.??#.? 3,1
            ??????..?##.?????? 2,1,3,1,1,1
            ?#??.?##?#??? 1,1,7
            #?.??.#..??? 2,1,1,1
            ??????#??.??#??# 1,2,3,1
            ??##??##??#?.. 1,2,3,3
            ??????????? 1,6,1
            ??????.##???#??? 2,8
            #????????#?? 1,1,1,2
            ???????.??? 2,2,1
            ?.????..??.?.?? 1,3,2,1
            #??.#?#???? 1,3,1
            ???###.?..#???.#??. 6,1,1,1,3
            .#??????#?#????? 2,2,3,4
            ?????..#.#? 3,1,1
            .?????#.???#?? 6,3
            .#??##.???#?#?#???? 1,2,1,6,1
            .??#????#?#.?????.? 9,1,1,1
            ?????????#??. 3,3
            ?#..?#???#? 2,1,3
            .#.??#?###.??? 1,6,2
            ?.??.?#?.? 1,2,3
            ??#.????????.???#? 1,1,1,2,4
            ???????#??#?#??.? 2,1,4,1,1
            ?????#???..#?? 1,2,1,2
            .???#?#???????? 3,1,2,4
            ???##??.??#?. 5,3
            ?#.??#??.? 2,3,1
            .#.?#?#????.??????? 1,6,1,3,1,1
            ?????#???????##??# 1,3,7,1
            .???????#??.? 1,2,2,1
            ?#??####??? 8,1
            ??????.??##???##?#?? 2,1,5,4
            ?.???????..??.?##??? 1,4,1,1,6
            ??#?#???????????..#? 3,5,1,1,2,1
            ??#???#.???.?? 2,1,1,2
            ?##?##.?????##??# 2,2,7,1
            ????.???#?#?.?#? 1,2,2,4,2
            ?#.?#?.?#? 2,1,3
            ???????#?#??? 1,8,1
            ??????#??#??. 4,3,1,1
            ???#??##?#??.?.???#? 2,5,2,1,1,1
            .??#??.??###??.?? 3,6
            ?????#.?#??? 3,1,3
            ???.??.??#???? 1,1,3,1
            .?.?.###???#?????# 1,1,7,1,2
            .?##?.??.#??###.?. 4,2,6,1
            ##.??#???#####?#??? 2,3,10
            ??????????? 1,1,3
            ?.???##???..?. 1,5,1,1
            ??#?#?#?#?#???# 10,2
            ???#?#??#?##??#???? 16,1
            .?..?.??.?#??.# 1,1,2,4,1
            ?#.?#????#.??#? 1,1,2,1,3
            ?.#???#???#?#??? 6,4
            ?..???.#?#??.??.?.? 1,2,5,1,1
            ?#.????##??###???? 2,5,7
            .##????###??#..#???? 10,1,3
            ?#??.???.?# 4,2,1
            ????##?????.??#?.?.? 1,7,3,1
            ?##??.???#????????#? 2,1,3,8
            ????#??..???.?????. 2,3,1,3
            ?#.#???#???.??.???? 2,2,1,1,2,1
            ...?????.?? 1,2,1
            """;
}
