import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class IsogramChecker {

    Function<String, Boolean> strategy;

    public IsogramChecker() {
        this.strategy = StrategyE.MAP_REDUCE_V1;
    }

    public IsogramChecker(Function<String, Boolean> strategy) {
        this.strategy = strategy;
    }

    boolean isIsogram(String phrase) {
        return strategy.apply(phrase);
    }

    enum StrategyE implements Function<String, Boolean> {
        SIMPLE_V1 {
            @Override
            public Boolean apply(String phrase) {
                phrase = phrase.toLowerCase().replaceAll("\\W", "");
                return phrase.length() == phrase.chars().distinct().count();
            }
        },
        SIMPLE_V2 {
            @Override
            public Boolean apply(String phrase) {
                List<Character> collectedChars = new ArrayList<>();
                List<Character> splitPhrase = phrase
                        .toLowerCase().chars().mapToObj(c -> (char) c)
                        .collect(Collectors.toList());

                for (Character c : splitPhrase) {
                    if (c != '-' && c != ' ') {
                        if (collectedChars.contains(c)) {
                            return false;
                        } else {
                            collectedChars.add(c);
                        }
                    }
                }
                return true;
            }
        },
        SIMPLE_V3 {
            @Override
            public Boolean apply(String phrase) {
                List<Character> letters = phrase
                        .toLowerCase()
                        .chars()
                        .mapToObj(i -> (char) i)
                        .filter(c -> Character.isAlphabetic(c))
                        .collect(Collectors.toList());
                return letters.size() == new HashSet<>(letters).size();
            }
        },
        MAP_REDUCE_V1 {
            @Override
            public Boolean apply(String phrase) {
                Map<Character, Optional<Map.Entry<Character, Integer>>> reducedCount = phrase
                        .toLowerCase().chars().mapToObj(c -> (char) c)
                        .filter(c -> c != '-' && c != ' ')
                        .map(c -> Map.entry(c, 1))
                        .collect(Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.reducing((a, b) -> Map.entry(a.getKey(), a.getValue() + b.getValue()))));

                return reducedCount.entrySet().stream().mapToInt(e -> e.getValue().get().getValue()).noneMatch(v -> v > 1);
            }
        },
        MAP_REDUCE_V2 {
            @Override
            public Boolean apply(String phrase) {

                Map<Character, Map.Entry<Character, Integer>> reducedCount = phrase
                        .toLowerCase().chars().mapToObj(c -> (char) c)
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
