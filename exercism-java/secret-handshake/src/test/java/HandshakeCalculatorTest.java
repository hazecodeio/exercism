import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HandshakeCalculatorTest {

    private HandshakeCalculator handshakeCalculator;

    public HandshakeCalculatorTest(HandshakeCalculator.StrategyE strategy) {
        this.handshakeCalculator = new HandshakeCalculator(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<HandshakeCalculator.StrategyE> getEnums() {
        return EnumSet.allOf(HandshakeCalculator.StrategyE.class);
    }

    @Test
    public void testThatInput1YieldsAWink() {
        assertEquals(
                singletonList(Signal.WINK),
                handshakeCalculator.calculateHandshake(1));
    }

    @Test
    public void testThatInput2YieldsADoubleBlink() {
        assertEquals(
                singletonList(Signal.DOUBLE_BLINK),
                handshakeCalculator.calculateHandshake(2));
    }

    @Test
    public void testThatInput4YieldsACloseYourEyes() {
        assertEquals(
                singletonList(Signal.CLOSE_YOUR_EYES),
                handshakeCalculator.calculateHandshake(4));
    }

    @Test
    public void testThatInput8YieldsAJump() {
        assertEquals(
                singletonList(Signal.JUMP),
                handshakeCalculator.calculateHandshake(8));
    }

    @Test
    public void testAnInputThatYieldsTwoActions() {
        assertEquals(
                asList(Signal.WINK, Signal.DOUBLE_BLINK),
                handshakeCalculator.calculateHandshake(3));
    }

    @Test
    public void testAnInputThatYieldsTwoReversedActions() {
        assertEquals(
                asList(Signal.DOUBLE_BLINK, Signal.WINK),
                handshakeCalculator.calculateHandshake(19));
    }

    @Test
    public void testReversingASingleActionYieldsTheSameAction() {
        assertEquals(
                singletonList(Signal.JUMP),
                handshakeCalculator.calculateHandshake(24));
    }

    @Test
    public void testReversingNoActionsYieldsNoActions() {
        assertEquals(
                emptyList(),
                handshakeCalculator.calculateHandshake(16));
    }

    @Test
    public void testInputThatYieldsAllActions() {
        assertEquals(
                asList(Signal.WINK, Signal.DOUBLE_BLINK, Signal.CLOSE_YOUR_EYES, Signal.JUMP),
                handshakeCalculator.calculateHandshake(15));
    }

    @Test
    public void testInputThatYieldsAllActionsReversed() {
        assertEquals(
                asList(Signal.JUMP, Signal.CLOSE_YOUR_EYES, Signal.DOUBLE_BLINK, Signal.WINK),
                handshakeCalculator.calculateHandshake(31));
    }

    @Test
    public void testThatInput0YieldsNoActions() {
        assertEquals(
                emptyList(),
                handshakeCalculator.calculateHandshake(0));
    }

}
