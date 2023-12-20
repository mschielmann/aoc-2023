package pl.mschielmann.aoc.year2023.day18;

import org.junit.jupiter.api.Disabled;
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
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(62L);
    }

    @Test
    void solvePartOneWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartOne();

        // THEN
        assertThat(result).isEqualTo(62500L);
    }

    @Test
    void solvePartTwoWithTestData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(TEST_INPUT);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(952408144115L);
    }

    @Test
    @Disabled
    void solvePartTwoWithActualData()
    {
        // GIVEN
        PuzzleSolver puzzleSolver = new PuzzleSolver(INPUT);

        // WHEN
        long result = puzzleSolver.solvePartTwo();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    private static final String TEST_INPUT = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
            """;

    static final String INPUT = """
            R 9 (#0a7ad2)
            U 3 (#0b23e1)
            R 9 (#6c6182)
            U 5 (#949661)
            R 2 (#6c6180)
            U 3 (#44d691)
            R 7 (#71da52)
            U 3 (#03dd73)
            R 8 (#0d7ce2)
            U 4 (#4305d3)
            L 8 (#5f36e2)
            U 7 (#7dab93)
            L 7 (#2e7ac2)
            U 6 (#2ea391)
            R 4 (#442ec2)
            U 8 (#565441)
            L 4 (#442ec0)
            U 3 (#3bb991)
            L 5 (#52e6c2)
            D 5 (#03dd71)
            L 6 (#567402)
            D 8 (#503b81)
            R 6 (#75a860)
            D 6 (#7f2f71)
            L 2 (#3cb9d0)
            D 5 (#531401)
            L 4 (#92b290)
            U 8 (#531403)
            L 3 (#714ed0)
            U 4 (#7278b1)
            L 3 (#985d92)
            D 10 (#0e2ae1)
            L 2 (#383f42)
            D 3 (#2fc341)
            L 3 (#2dbf02)
            U 9 (#6ab2a1)
            L 4 (#15a422)
            U 4 (#4fedd1)
            L 3 (#715bc2)
            U 3 (#635721)
            L 3 (#320aa2)
            U 3 (#87e763)
            L 8 (#453b52)
            U 4 (#87e761)
            R 8 (#41f042)
            U 3 (#11ba03)
            R 4 (#4c3372)
            U 9 (#2e4f63)
            R 5 (#0a59d2)
            U 5 (#138663)
            L 10 (#02e272)
            U 2 (#6648c3)
            L 7 (#8dab52)
            U 5 (#1b16f3)
            L 6 (#083552)
            D 7 (#490823)
            L 8 (#089522)
            D 3 (#2fc343)
            R 8 (#2523c2)
            D 5 (#638003)
            L 3 (#4b1fd2)
            D 4 (#69a7a3)
            R 5 (#3e5fc2)
            D 3 (#44a7f3)
            L 5 (#583992)
            D 4 (#6df291)
            L 5 (#094eb2)
            D 2 (#02c911)
            L 7 (#7f49f2)
            D 2 (#02c913)
            L 2 (#363e62)
            D 3 (#2856e1)
            R 10 (#0447c0)
            D 6 (#0a2e21)
            L 10 (#8e0b00)
            D 3 (#6feb51)
            L 5 (#15aa60)
            D 3 (#0089e1)
            L 5 (#16d9e0)
            D 3 (#00e2d1)
            L 3 (#79f0d2)
            D 5 (#566b51)
            R 4 (#6e5770)
            D 2 (#3e43d1)
            L 4 (#094fe0)
            D 4 (#5d96f1)
            L 5 (#698f20)
            U 4 (#2e8521)
            L 3 (#16e4b0)
            U 2 (#01fd31)
            L 9 (#5ec470)
            U 3 (#301bd1)
            R 5 (#08eb40)
            U 2 (#829ba1)
            R 7 (#695a40)
            U 3 (#116d91)
            L 4 (#608360)
            U 4 (#054803)
            L 3 (#4f2220)
            U 6 (#054801)
            L 5 (#19e1f0)
            U 4 (#01f163)
            L 6 (#73d2b0)
            U 10 (#44d011)
            L 5 (#5ab060)
            U 6 (#44d013)
            L 6 (#26b720)
            U 4 (#10b273)
            R 8 (#2337d0)
            U 7 (#765673)
            L 8 (#1feb20)
            U 4 (#6bb9a1)
            L 5 (#8b9490)
            U 3 (#1b4f41)
            L 4 (#26d790)
            U 3 (#01f161)
            L 6 (#39cf40)
            U 10 (#4f5ed1)
            L 3 (#209c40)
            U 5 (#163fd1)
            R 3 (#053302)
            U 2 (#80e4d1)
            R 10 (#799072)
            U 4 (#247461)
            R 9 (#7ec370)
            D 4 (#2b79f1)
            R 4 (#209c42)
            D 6 (#0f69e1)
            R 2 (#53cf92)
            D 3 (#528351)
            R 4 (#0bd6c0)
            D 2 (#585001)
            R 7 (#0bd6c2)
            D 5 (#8dc481)
            R 3 (#68ca02)
            D 10 (#01cde1)
            R 3 (#64c482)
            D 3 (#725de3)
            R 4 (#2ebf62)
            D 3 (#8d71c3)
            R 4 (#3815d2)
            D 6 (#096853)
            R 4 (#637652)
            U 6 (#6eef73)
            R 3 (#224b62)
            U 4 (#4b71f3)
            R 3 (#3320a2)
            U 3 (#344e71)
            L 11 (#7f2192)
            U 4 (#7603b1)
            R 11 (#7f2190)
            U 5 (#053781)
            R 4 (#68b582)
            D 7 (#5d2a11)
            R 4 (#68b580)
            U 7 (#5846f1)
            R 4 (#01efd2)
            U 3 (#087781)
            R 4 (#01efd0)
            U 10 (#562731)
            R 5 (#419bf2)
            D 10 (#01cde3)
            R 4 (#74bec2)
            U 4 (#8c3643)
            R 8 (#4bb1f2)
            U 3 (#3e9233)
            R 7 (#4bb1f0)
            U 7 (#6dcf63)
            R 4 (#038162)
            U 3 (#1bbbf3)
            R 3 (#565582)
            U 4 (#84e503)
            R 7 (#565580)
            U 9 (#00f4e3)
            R 6 (#018082)
            U 5 (#166073)
            R 4 (#6f6ba2)
            U 8 (#886071)
            R 7 (#3600f2)
            U 8 (#23a411)
            R 3 (#5ca882)
            U 5 (#450111)
            R 9 (#5f0b72)
            U 5 (#450113)
            L 2 (#67d222)
            U 3 (#2ae133)
            L 3 (#223062)
            U 5 (#812353)
            L 6 (#56e5d2)
            U 3 (#893e63)
            L 5 (#191772)
            U 3 (#01fd53)
            L 5 (#0bb5b2)
            U 10 (#3d2d33)
            L 4 (#5ad942)
            D 7 (#85e073)
            L 3 (#106702)
            D 6 (#0813c3)
            L 7 (#7d2d32)
            U 2 (#1451c3)
            L 3 (#20c442)
            U 5 (#1eefe3)
            L 3 (#402a12)
            U 2 (#85bcf3)
            L 5 (#2ba542)
            U 4 (#401223)
            R 7 (#2bc0b2)
            U 6 (#654553)
            R 3 (#92f0c2)
            U 5 (#20b413)
            R 6 (#290370)
            U 7 (#499a53)
            R 3 (#17f140)
            D 7 (#3c7fe3)
            R 3 (#17f142)
            D 2 (#28b743)
            R 10 (#3748a0)
            D 3 (#56e3e1)
            R 3 (#3736b0)
            U 6 (#4200f1)
            R 3 (#3736b2)
            U 6 (#15eca1)
            R 3 (#5e6560)
            U 2 (#484663)
            R 5 (#0c3562)
            U 8 (#953733)
            R 2 (#5bbd22)
            U 6 (#191053)
            R 5 (#5f2532)
            U 7 (#948a03)
            R 2 (#5c9a12)
            U 4 (#2c1b31)
            R 8 (#804f02)
            U 4 (#3f2791)
            R 2 (#804f00)
            U 3 (#425791)
            L 10 (#54b5e2)
            U 6 (#3306f3)
            L 7 (#5600f2)
            U 2 (#549bf3)
            L 5 (#4421c2)
            U 8 (#3fffd3)
            R 3 (#4421c0)
            U 8 (#2eeab3)
            R 2 (#196ec2)
            U 4 (#8375d3)
            R 5 (#08be10)
            U 10 (#227fe3)
            R 3 (#631ba0)
            D 10 (#7267f3)
            R 2 (#6f6ec0)
            D 8 (#678743)
            R 4 (#27e480)
            D 4 (#8994b3)
            R 3 (#04be02)
            U 3 (#477d03)
            R 4 (#1eda82)
            D 7 (#6761b3)
            R 3 (#3ebc32)
            U 11 (#6761b1)
            R 2 (#4d0a32)
            U 2 (#3c95c3)
            R 4 (#53ce12)
            D 3 (#6e01c3)
            R 2 (#219ef0)
            D 10 (#501bc3)
            R 4 (#75b650)
            D 4 (#8b9b33)
            R 5 (#156510)
            D 2 (#4d6c43)
            R 4 (#556360)
            D 6 (#42b123)
            R 4 (#1dda10)
            D 6 (#47f713)
            L 2 (#6e7640)
            D 7 (#839fd3)
            L 4 (#6e7642)
            D 3 (#3de353)
            L 4 (#4dc060)
            D 3 (#4f1aa3)
            L 7 (#98fea0)
            U 8 (#201083)
            L 7 (#1484c0)
            D 8 (#1f6af3)
            L 4 (#8442a0)
            D 3 (#1f6af1)
            R 8 (#2f8420)
            D 3 (#6b1b33)
            R 2 (#487c30)
            D 7 (#466db3)
            R 4 (#63c190)
            U 6 (#3cdff3)
            R 3 (#46bd82)
            U 4 (#4faac3)
            R 4 (#658042)
            D 4 (#757783)
            R 3 (#4e9d80)
            D 5 (#525b83)
            R 3 (#187360)
            D 5 (#00d3b3)
            R 5 (#4c84b0)
            D 7 (#529d91)
            R 8 (#174970)
            D 8 (#529d93)
            R 4 (#5b67b0)
            D 2 (#285493)
            R 5 (#791a70)
            D 3 (#285491)
            R 4 (#5197a0)
            D 5 (#597071)
            R 5 (#78f090)
            D 8 (#546f81)
            R 2 (#1eb280)
            D 6 (#5d7571)
            R 3 (#241f72)
            D 6 (#4f2811)
            R 10 (#436630)
            D 3 (#23a351)
            R 8 (#436632)
            D 3 (#2e37b1)
            R 3 (#3196e2)
            D 3 (#a10313)
            R 9 (#514c52)
            D 4 (#4e9431)
            R 3 (#6046b2)
            D 7 (#96e791)
            R 2 (#6046b0)
            D 6 (#146a51)
            R 5 (#0f4fd0)
            D 7 (#915911)
            R 4 (#6cce90)
            D 8 (#104061)
            R 5 (#2ae440)
            D 9 (#6ce031)
            R 2 (#97a312)
            D 9 (#0df1e1)
            R 4 (#53af70)
            U 5 (#8483d1)
            R 4 (#853c50)
            U 6 (#1720d1)
            R 3 (#51ce30)
            U 4 (#24cb21)
            R 3 (#789642)
            U 8 (#768481)
            R 5 (#789640)
            U 2 (#450ed1)
            R 5 (#7ebe70)
            U 3 (#595d13)
            L 7 (#3b77a2)
            U 8 (#2bb771)
            R 7 (#26f5f2)
            U 8 (#2bb773)
            R 5 (#577a02)
            D 3 (#52c7d3)
            R 9 (#11c420)
            D 7 (#189281)
            L 9 (#8b5790)
            D 5 (#189283)
            R 6 (#1ccbe0)
            D 5 (#227163)
            L 5 (#0b94d0)
            D 5 (#11c833)
            L 2 (#445330)
            D 6 (#517361)
            R 4 (#445060)
            D 5 (#75c231)
            R 3 (#071910)
            D 8 (#169701)
            R 5 (#2fbdc0)
            D 8 (#327e41)
            L 5 (#96e8a0)
            D 5 (#4e2841)
            L 4 (#5dd6d2)
            D 6 (#355c43)
            L 8 (#9466e2)
            U 7 (#355c41)
            L 3 (#4886d2)
            U 6 (#905c81)
            L 6 (#78da50)
            U 3 (#85e9a3)
            L 4 (#631060)
            D 5 (#85e9a1)
            L 7 (#5ed9d0)
            D 2 (#83fd71)
            L 2 (#34fb30)
            D 9 (#895fc3)
            L 6 (#7d37b0)
            D 5 (#4fda83)
            L 2 (#2f8562)
            D 5 (#00e4c3)
            R 2 (#5516f2)
            D 3 (#00e4c1)
            R 10 (#1d1a02)
            D 2 (#201573)
            R 6 (#0f5462)
            D 3 (#459793)
            L 6 (#138400)
            D 3 (#204d01)
            L 6 (#9761f0)
            D 5 (#204d03)
            L 4 (#0624c0)
            D 3 (#408143)
            L 3 (#7d37b2)
            D 3 (#1597f3)
            L 4 (#167030)
            D 8 (#49d863)
            L 2 (#94c2c0)
            D 2 (#49d861)
            L 9 (#1f01e0)
            D 4 (#2b4dc3)
            L 2 (#45c9a0)
            D 3 (#310cf3)
            L 5 (#66c6d0)
            D 3 (#302013)
            R 8 (#90fba0)
            D 6 (#563f13)
            R 8 (#90fba2)
            D 6 (#1ec8a3)
            R 2 (#4f6102)
            D 9 (#7ecac1)
            R 4 (#6f8d92)
            D 7 (#37bfa1)
            R 3 (#172422)
            D 3 (#2086c3)
            R 3 (#67a502)
            D 4 (#9603a3)
            L 8 (#3a4d72)
            D 5 (#58e9c3)
            R 8 (#6db970)
            D 4 (#212483)
            R 5 (#136650)
            D 11 (#348073)
            R 4 (#90fc80)
            D 2 (#425823)
            R 3 (#012b00)
            D 2 (#97fd11)
            R 7 (#64bde0)
            D 3 (#454f83)
            R 3 (#389c10)
            D 10 (#5600c3)
            R 5 (#3e8fd0)
            D 5 (#583a43)
            R 2 (#923702)
            D 7 (#287cc3)
            R 7 (#117392)
            D 2 (#670023)
            R 3 (#149ff0)
            D 9 (#183503)
            R 6 (#8f0aa0)
            U 5 (#5a8393)
            R 4 (#772be2)
            U 4 (#7165f3)
            R 5 (#291ab2)
            D 3 (#3b9a43)
            R 3 (#8375c2)
            D 4 (#4e22a3)
            L 4 (#24a6e0)
            D 8 (#7cc603)
            L 5 (#61b520)
            D 9 (#7cc601)
            L 6 (#301880)
            D 2 (#0032c1)
            L 2 (#32ee20)
            D 9 (#1cdeb1)
            L 6 (#4d2f70)
            D 5 (#331db1)
            L 5 (#60b9a0)
            D 3 (#3f6961)
            R 4 (#11e950)
            D 2 (#028281)
            R 9 (#2df060)
            D 5 (#4c6b51)
            R 6 (#5a87f0)
            D 4 (#7f6c11)
            R 4 (#274b50)
            D 3 (#06a2d1)
            R 6 (#66b940)
            U 6 (#5dde91)
            R 3 (#72deb0)
            U 4 (#2fbbb1)
            R 5 (#897602)
            U 2 (#348e61)
            R 7 (#441bd2)
            U 3 (#7332b1)
            R 4 (#5ab8a0)
            U 8 (#2fe673)
            L 4 (#4036c0)
            U 3 (#2fe671)
            R 5 (#32a270)
            U 11 (#3e8741)
            L 5 (#6ae282)
            U 4 (#41b591)
            R 8 (#37d3b2)
            D 8 (#09f5a1)
            R 4 (#51bf10)
            D 2 (#9810a1)
            R 5 (#51bf12)
            D 4 (#1426e1)
            L 7 (#406a12)
            D 3 (#076043)
            L 2 (#22fbc2)
            D 8 (#2b9963)
            R 5 (#233950)
            D 4 (#645ad3)
            R 4 (#233952)
            D 8 (#1b9843)
            R 2 (#22fbc0)
            D 4 (#034073)
            R 6 (#44b9a2)
            U 3 (#13c341)
            R 3 (#6181b2)
            U 11 (#49de31)
            R 5 (#661882)
            U 3 (#2a4b71)
            L 4 (#292860)
            U 8 (#4b2101)
            R 4 (#292862)
            U 3 (#4601c1)
            L 5 (#661880)
            U 7 (#4ab4c1)
            L 8 (#4b8900)
            U 5 (#5cba01)
            L 4 (#740e40)
            U 9 (#4c1181)
            R 7 (#6ad450)
            U 5 (#1e8f13)
            R 10 (#789d00)
            U 5 (#56d4e3)
            R 9 (#6906d0)
            D 4 (#274cd3)
            R 2 (#6906d2)
            D 6 (#5606b3)
            R 4 (#789d02)
            D 7 (#0f9013)
            R 2 (#941130)
            D 5 (#03ece1)
            R 2 (#16dad0)
            D 3 (#40c361)
            R 3 (#7a97f0)
            D 8 (#48e781)
            R 2 (#9172c2)
            D 4 (#74afc1)
            R 4 (#64e300)
            D 5 (#1563a1)
            R 7 (#643860)
            D 3 (#834d81)
            R 4 (#1cc3b0)
            D 8 (#84faa1)
            L 6 (#1455a0)
            D 9 (#69a921)
            L 5 (#016b10)
            D 3 (#121301)
            L 8 (#8896d0)
            U 4 (#7e3141)
            L 6 (#586070)
            U 6 (#443441)
            L 3 (#6b2500)
            U 2 (#18af21)
            L 8 (#0ba460)
            D 3 (#0759a1)
            L 3 (#70f710)
            D 3 (#95f351)
            L 4 (#3d9420)
            D 3 (#6b0ba1)
            L 7 (#237490)
            D 5 (#4ab631)
            L 5 (#650c80)
            D 5 (#6d55b3)
            L 7 (#09aa90)
            U 5 (#5ff8d1)
            L 2 (#73f7a0)
            U 5 (#3c8631)
            L 4 (#0070d0)
            D 8 (#9c7f03)
            L 4 (#238390)
            D 2 (#6beb03)
            L 3 (#2d0292)
            D 6 (#202e03)
            L 3 (#4e7522)
            D 7 (#202e01)
            L 3 (#261ee2)
            D 3 (#316023)
            L 8 (#8745e0)
            D 6 (#611d13)
            L 3 (#744880)
            D 3 (#466c61)
            L 6 (#550620)
            D 4 (#2c40e1)
            L 3 (#3950f0)
            D 3 (#11c341)
            L 3 (#03cb60)
            D 3 (#658971)
            L 10 (#440f70)
            D 2 (#2d7601)
            L 4 (#3822d0)
            D 5 (#24e871)
            L 5 (#110ab0)
            D 2 (#4e2931)
            L 5 (#8cbc82)
            D 5 (#5fded1)
            L 4 (#255e92)
            D 7 (#101971)
            L 4 (#2c5b40)
            D 9 (#843b71)
            L 2 (#2c5b42)
            D 4 (#291691)
            L 5 (#79bb32)
            U 7 (#516d91)
            L 4 (#703a92)
            U 4 (#0b33c3)
            L 4 (#362eb2)
            U 7 (#223f13)
            L 2 (#081312)
            U 7 (#5994f3)
            L 7 (#8b63c2)
            U 6 (#44b181)
            L 7 (#0846a2)
            U 7 (#425641)
            L 8 (#5a9522)
            U 2 (#089c01)
            L 3 (#6ea4c0)
            D 6 (#73c7a3)
            L 8 (#679440)
            D 3 (#73c7a1)
            L 4 (#12fc40)
            U 3 (#7c8171)
            L 4 (#5b6be2)
            U 5 (#22af01)
            L 4 (#983a90)
            U 5 (#71f411)
            L 8 (#01d330)
            D 3 (#2f6621)
            L 3 (#9a0dc2)
            D 3 (#1a8e21)
            L 5 (#300de2)
            U 11 (#3fdd13)
            L 5 (#457be0)
            D 9 (#797403)
            L 4 (#457be2)
            D 2 (#254643)
            L 4 (#5dbb82)
            U 6 (#7bf431)
            L 6 (#72ae42)
            D 3 (#865831)
            L 3 (#04c812)
            D 5 (#6f17b3)
            L 3 (#014132)
            D 5 (#3d8773)
            L 4 (#0869e2)
            U 7 (#6007f3)
            L 5 (#9534c2)
            U 5 (#535c73)
            L 3 (#516e42)
            U 4 (#5cda51)
            L 2 (#463320)
            U 5 (#32cec1)
            L 10 (#463322)
            U 6 (#7d6161)
            R 7 (#771382)
            U 9 (#52f911)
            R 4 (#1c72e2)
            U 5 (#0b33f1)
            R 4 (#73c102)
            U 2 (#0b33f3)
            R 5 (#1bdac2)
            U 7 (#2bcb73)
            R 7 (#328d92)
            U 4 (#012873)
            R 4 (#1e8c52)
            U 4 (#500831)
            R 6 (#709d42)
            U 8 (#500833)
            R 5 (#1511d2)
            U 7 (#012871)
            R 3 (#1722e2)
            U 2 (#136aa3)
            R 5 (#787732)
            U 6 (#2d2563)
            L 4 (#447100)
            U 2 (#6ec813)
            L 6 (#447102)
            U 4 (#3f6f43)
            L 2 (#4e6870)
            U 7 (#5fbc43)
            L 2 (#2ceaa0)
            U 2 (#6f6a63)
            L 7 (#704390)
            U 6 (#1a14c3)
            L 9 (#0e3f60)
            U 3 (#849b21)
            L 5 (#73a6c0)
            D 10 (#1c7cc1)
            L 3 (#20fc00)
            U 10 (#3657e3)
            L 4 (#0bbcc2)
            U 2 (#22d3d3)
            L 5 (#0bbcc0)
            U 4 (#47ec33)
            R 5 (#46a9c0)
            U 4 (#8f39d3)
            R 9 (#41cea0)
            U 5 (#1fc343)
            R 3 (#3180d2)
            U 3 (#012153)
            L 4 (#5b5e50)
            U 8 (#11dbe3)
            L 7 (#7771d0)
            U 6 (#11dbe1)
            R 7 (#06f310)
            U 6 (#6d4b03)
            L 5 (#0bed90)
            U 9 (#91aac3)
            L 5 (#4a8b62)
            U 2 (#406ca1)
            L 3 (#34e292)
            U 2 (#37b021)
            L 3 (#34e290)
            U 3 (#593ca1)
            L 5 (#437f52)
            U 6 (#520cb3)
            L 8 (#082890)
            U 3 (#594993)
            L 6 (#082892)
            U 6 (#260323)
            R 4 (#57a612)
            U 5 (#1c63d3)
            R 4 (#037fb2)
            D 7 (#129b43)
            R 4 (#5dd9d2)
            U 7 (#057391)
            R 5 (#1baaa2)
            D 5 (#5218a1)
            R 3 (#32ad40)
            U 2 (#1ed2f1)
            R 6 (#32ad42)
            U 6 (#556531)
            L 4 (#1baaa0)
            U 3 (#01bb71)
            L 5 (#35b492)
            U 9 (#4d97d3)
            L 7 (#431fc2)
            D 9 (#7fe7f3)
            L 3 (#097032)
            U 6 (#4f74d1)
            L 7 (#7e4702)
            U 6 (#5f0441)
            L 3 (#3e36b2)
            U 5 (#809d11)
            L 5 (#4554a2)
            D 10 (#252373)
            L 7 (#2741b2)
            D 2 (#1e3303)
            R 7 (#70c402)
            D 9 (#2bc5f3)
            L 3 (#9fb6f2)
            D 9 (#2bc5f1)
            L 5 (#611422)
            D 4 (#420763)
            L 3 (#092f42)
            D 3 (#4861a3)
            L 5 (#9bc3f2)
            U 5 (#25d313)
            L 11 (#32b1e0)
            U 5 (#063e81)
            R 11 (#52a210)
            U 3 (#063e83)
            L 2 (#1f9f40)
            U 8 (#059533)
            L 3 (#5bbba2)
            U 4 (#57d773)
            L 7 (#5ea432)
            U 4 (#686ab3)
            """;
}
