import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

class ResistorColorDuo {

    Function<String[], Integer> strategy;

    public ResistorColorDuo() {
        strategy = StrategyE.DEFAULT;
    }

    public ResistorColorDuo(Function<String[], Integer> strategy) {
        this.strategy = strategy;
    }

    private static List<String> COLORS = List.of(
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "violet",
            "grey",
            "white"
    );

    int value(String[] colors) {
        return strategy.apply(colors);
    }

    enum StrategyE implements Function<String[], Integer>{
        DEFAULT{
            @Override
            public Integer apply(String[] colors) {
                return Integer.valueOf(COLORS.indexOf(colors[0]) + "" + COLORS.indexOf(colors[1]));
            }
        },
        CALC{
            @Override
            public Integer apply(String[] colors) {
                return COLORS.indexOf(colors[0]) * 10 + COLORS.indexOf(colors[1]);
            }
        }
    }
}
