import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PascalsTriangleGeneratorTest {

    private PascalsTriangleGenerator pascalsTriangleGenerator = new PascalsTriangleGenerator();

    public PascalsTriangleGeneratorTest(PascalsTriangleGenerator.StrategyE strategy) {
        this.pascalsTriangleGenerator = new PascalsTriangleGenerator(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<PascalsTriangleGenerator.StrategyE> getEnums() {
        return EnumSet.allOf(PascalsTriangleGenerator.StrategyE.class);
    }

    @Test
    public void testTriangleWithZeroRows() {
        int[][] expectedOutput = new int[][]{};

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(0));
    }

    @Test
    public void testTriangleWithOneRow() {
        int[][] expectedOutput = new int[][]{
                {1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(1));
    }

    @Test
    public void testTriangleWithTwoRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(2));
    }

    @Test
    public void testTriangleWithThreeRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1},
                {1, 2, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(3));
    }

    @Test
    public void testTriangleWithFourRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1},
                {1, 2, 1},
                {1, 3, 3, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(4));
    }

    @Test
    public void testTriangleWithFiveRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1},
                {1, 2, 1},
                {1, 3, 3, 1},
                {1, 4, 6, 4, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(5));
    }

    @Test
    public void testTriangleWithSixRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1},
                {1, 2, 1},
                {1, 3, 3, 1},
                {1, 4, 6, 4, 1},
                {1, 5, 10, 10, 5, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(6));
    }

    @Test
    public void testTriangleWithTenRows() {
        int[][] expectedOutput = new int[][]{
                {1},
                {1, 1},
                {1, 2, 1},
                {1, 3, 3, 1},
                {1, 4, 6, 4, 1},
                {1, 5, 10, 10, 5, 1},
                {1, 6, 15, 20, 15, 6, 1},
                {1, 7, 21, 35, 35, 21, 7, 1},
                {1, 8, 28, 56, 70, 56, 28, 8, 1},
                {1, 9, 36, 84, 126, 126, 84, 36, 9, 1}
        };

        assertArrayEquals(expectedOutput, pascalsTriangleGenerator.generateTriangle(10));
    }
}
