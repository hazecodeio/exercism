import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ResistorColorDuoTest {

    private ResistorColorDuo resistorColorDuo;

    public ResistorColorDuoTest(Function<String[], Integer> strategy) {
        this.resistorColorDuo = new ResistorColorDuo(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<ResistorColorDuo.StrategyE> getEnums() {
        return EnumSet.allOf(ResistorColorDuo.StrategyE.class);
    }

    @Test
    public void testBrownAndBlack() {
        String[] input = {"brown", "black"};
        int expected = 10;
        int actual = resistorColorDuo.value(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testBlueAndGrey() {
        String[] input = {"blue", "grey"};
        int expected = 68;
        int actual = resistorColorDuo.value(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testYellowAndViolet() {
        String[] input = {"yellow", "violet"};
        int expected = 47;
        int actual = resistorColorDuo.value(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testOrangeAndOrange() {
        String[] input = {"orange", "orange"};
        int expected = 33;
        int actual = resistorColorDuo.value(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testIgnoreAdditionalColors() {
        String[] input = {"green", "brown", "orange"};
        int expected = 51;
        int actual = resistorColorDuo.value(input);

        assertEquals(expected, actual);
    }
}
