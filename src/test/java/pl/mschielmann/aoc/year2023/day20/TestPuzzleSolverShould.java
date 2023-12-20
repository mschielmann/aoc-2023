package pl.mschielmann.aoc.year2023.day20;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        assertThat(result).isEqualTo(32000000L);
    }

    @Test
    void solvePartOneWithTestData2()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT_2);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(11687500L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(886701120L);
    }

    @Test
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        BigDecimal result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(BigDecimal.valueOf(228134431501037L));
    }

    private static final String TEST_INPUT_1 = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
            """;

    private static final String TEST_INPUT_2 = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
            """;

    static final String INPUT = """
            %jb -> ps
            %cm -> ps, tm
            %sl -> ml, cp
            %qr -> ml
            %hf -> kh, jg
            %jg -> kk
            %jt -> pq
            %qv -> kv
            %rj -> mm, kh
            %kf -> xt
            %kx -> vk, mk
            %dq -> qn
            &ps -> xc, mq, jt, zs, sr, nt, pq
            %jk -> hh, ps
            %rr -> mk, nh
            %hs -> kh, mb
            %mg -> mk, kf
            %xt -> dq, mk
            &xc -> zh
            %mq -> nt
            %nh -> bm
            &ml -> bp, gd, qv, kq
            %md -> hs
            %vk -> mk, vl
            %mm -> kh
            &th -> zh
            &zh -> rx
            %kc -> ps, jk
            %kk -> dm
            %jn -> ll, ml
            &pd -> zh
            &kh -> jg, qx, md, th, hf, dm, kk
            %pp -> kh, md
            %zf -> ml, bd
            %qx -> pp
            &mk -> kf, qn, nh, pd, dq, mg, bm
            %qn -> rr
            %mb -> qb, kh
            %nt -> jt
            %vl -> zk, mk
            %gd -> ml, rm
            %hh -> ps, jb
            %tm -> ps, mq
            %kv -> jn, ml
            %zs -> kc
            %ll -> ml, kq
            %cp -> qv, ml
            %rm -> sl, ml
            %bd -> qr, ml
            %dm -> qx
            %qb -> rj, kh
            %pq -> zs
            %bm -> kx
            %sr -> cm, ps
            %zk -> mk
            broadcaster -> sr, gd, mg, hf
            %kq -> zf
            &bp -> zh
            """;
}
