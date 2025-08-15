import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

class HandshakeCalculator {

    IntFunction<List<Signal>> strategy;

    public HandshakeCalculator() {
        strategy = StrategyE.S1;
    }

    public HandshakeCalculator(IntFunction<List<Signal>> strategy) {
        this.strategy = strategy;
    }

    List<Signal> calculateHandshake(int number) {

        return strategy.apply(number);
    }

    enum StrategyE implements IntFunction<List<Signal>> {
        S1 {
            @Override
            public List<Signal> apply(int number) {

                List<Signal> signals = new ArrayList<>();

                if ((number & 1) == 1) {
                    signals.add(Signal.WINK);
                }
                if ((number & 2) == 2) {
                    signals.add(Signal.DOUBLE_BLINK);
                }
                if ((number & 4) == 4) {
                    signals.add(Signal.CLOSE_YOUR_EYES);
                }
                if ((number & 8) == 8) {
                    signals.add(Signal.JUMP);
                }
                if ((number & 16) == 16) {
                    Collections.reverse(signals);
                }

                return signals;
            }
        },
        S2 {
            @Override
            public List<Signal> apply(int number) {

                List<Signal> result = new ArrayList<>();
                int highestTwoPower = Integer.highestOneBit(number);

                for (int i = 1; i <= highestTwoPower; i <<= 1) { // same as multiplying by 2
                    if ((number & i) == 0)
                        continue;
                    switch (i) {
                        case 1:
                            result.add(Signal.WINK);
                            break;
                        case 2:
                            result.add(Signal.DOUBLE_BLINK);
                            break;
                        case 4:
                            result.add(Signal.CLOSE_YOUR_EYES);
                            break;
                        case 8:
                            result.add(Signal.JUMP);
                            break;
                        default:
                            Collections.reverse(result);

                    }
                }
                return result;
            }
        }
    }

}
