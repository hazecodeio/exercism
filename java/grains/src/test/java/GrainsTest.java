import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GrainsTest {

    private Grains grains = new Grains();

    public GrainsTest(Grains.StrategyE strategy) {
        this.grains = new Grains(strategy);
    }

    @Parameters(name = "Strategy = {0}")
    public static EnumSet<Grains.StrategyE> getEnums() {
        return EnumSet.allOf(Grains.StrategyE.class);
    }

    @Test
    public void countAtSquare1() {
        BigInteger result = grains.grainsOnSquare(1);
        assertEquals(new BigInteger("1"), result);
    }

    @Test
    public void countAtSquare2() {
        BigInteger result = grains.grainsOnSquare(2);
        assertEquals(new BigInteger("2"), result);
    }

    @Test
    public void countAtSquare3() {
        BigInteger result = grains.grainsOnSquare(3);
        assertEquals(new BigInteger("4"), result);
    }

    @Test
    public void countAtSquare4() {
        BigInteger result = grains.grainsOnSquare(4);
        assertEquals(new BigInteger("8"), result);
    }

    @Test
    public void countAtSquare16() {
        BigInteger result = grains.grainsOnSquare(16);
        assertEquals(new BigInteger("32768"), result);
    }

    @Test
    public void countAtSquare32() {
        BigInteger result = grains.grainsOnSquare(32);
        assertEquals(new BigInteger("2147483648"), result);
    }

    @Test
    public void countAtSquare64() {
        BigInteger result = grains.grainsOnSquare(64);
        assertEquals(new BigInteger("9223372036854775808"), result);
    }

    @Test
    public void errorOnNullBoardSize() {
        IllegalArgumentException expected =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> grains.grainsOnSquare(0));

        assertThat(expected)
                .hasMessage("square must be between 1 and 64");
    }

    @Test
    public void errorOnNegativeBoardSize() {
        IllegalArgumentException expected =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> grains.grainsOnSquare(-1));

        assertThat(expected)
                .hasMessage("square must be between 1 and 64");
    }

    @Test
    public void errorOnExcessiveBoardSize() {
        IllegalArgumentException expected =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> grains.grainsOnSquare(65));

        assertThat(expected)
                .hasMessage("square must be between 1 and 64");
    }

    @Test
    public void totalNumberOfGrainsOnABoard() {
        BigInteger total = grains.grainsOnBoard();
        assertEquals(new BigInteger("18446744073709551615"), total);
    }

}
