import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class IsogramChecker {

    Function<String, Boolean> strategy;

    public IsogramChecker() {
        this.strategy = StrategyE.STREAM_REDUCE_V1;
    }

    public IsogramChecker(Function<String, Boolean> strategy) {
        this.strategy = strategy;
    }

    boolean isIsogram(String phrase) {
        return strategy.apply(phrase);
    }

    enum StrategyE implements Function<String, Boolean> {
        STREAM_REDUCE_V1 {
            @Override
            public Boolean apply(String phrase) {
                Map<Character, Optional<Map.Entry<Character, Integer>>> reducedCount = phrase.toLowerCase().chars().mapToObj(c -> (char) c)
                        .filter(c -> c != '-' && c != ' ')
                        .map(c -> Map.entry(c, 1)).collect(Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.reducing((a, b) -> Map.entry(a.getKey(), a.getValue() + b.getValue()))));

                return reducedCount.entrySet().stream().mapToInt(e -> e.getValue().get().getValue()).noneMatch(v -> v > 1);
            }
        },
        STREAM_REDUCE_V2 {
            @Override
            public Boolean apply(String phrase) {

                Map<Character, Map.Entry<Character, Integer>> reducedCount = phrase.toLowerCase().chars().mapToObj(c -> (char) c)
                        .filter(c -> c != '-' && c != ' ')
                        .map(c -> Map.entry(c, 1))
                        .collect(Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.reducing(Map.entry('\u0000', 0), (a, b) -> Map.entry(a.getKey(), a.getValue() + b.getValue())))); // reduce() with an identity; just like a Monoid

                return reducedCount.entrySet().stream().mapToInt(e -> e.getValue().getValue()).noneMatch(v -> v > 1);
            }
        }
    }
}
