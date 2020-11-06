import java.util.Map;
import java.util.TreeMap;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

class RaindropConverter {

    private static Map<Integer, String> factors = new TreeMap<>(
            Map.of(
                    3, "Pling",
                    5, "Plang",
                    7, "Plong"
            ));
    IntFunction<String> strategy;

    public RaindropConverter() {
        strategy = StrategyE.S2;
    }

    public RaindropConverter(IntFunction<String> strategy) {
        this.strategy = strategy;
    }

    String convert(int number) {
        return strategy.apply(number);
    }

    enum StrategyE implements IntFunction<String> {
        S1 {
            @Override
            public String apply(int number) {
                String collect = factors.entrySet().stream().filter(e -> number % e.getKey() == 0).map(e -> e.getValue()).collect(Collectors.joining());
                if (collect.isBlank())
                    return String.valueOf(number);
                return collect;
            }
        },
        S2 {
            @Override
            public String apply(int number) {
                StringBuilder builder = new StringBuilder();
                if (number % 3 == 0)
                    builder.append("Pling");
                if (number % 5 == 0)
                    builder.append("Plang");
                if (number % 7 == 0)
                    builder.append("Plong");
                return builder.length() > 0 ? builder.toString() : Integer.toString(number);

            }
        }
    }

}
