import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Anagram {

    String word;

    BiFunction<String, List<String>, List<String>> strategy;

    public Anagram(String word) {
        this.word = word;
        this.strategy = StrategyE.MAP_REDUCE;
    }

    public List<String> match(List<String> toBeMatched) {
        return strategy.apply(word, toBeMatched);
    }

    enum StrategyE implements BiFunction<String, List<String>, List<String>> {
        SIMPLE {
            @Override
            public List<String> apply(String word, List<String> toBeMatched) {

                List<String> matches = new ArrayList<>();

                char[] wordInChars = word.toLowerCase().toCharArray();
                Arrays.sort(wordInChars);

                for (String str : toBeMatched) {
                    if (word.equalsIgnoreCase(str))
                        continue;

                    char[] strInChars = str.toLowerCase().toCharArray();
                    Arrays.sort(strInChars);
                    if (String.valueOf(wordInChars).equals(String.valueOf(strInChars)))
                        matches.add(str);
                }
                return matches;
            }
        },
        MAP_REDUCE {
            @Override
            public List<String> apply(String word, List<String> toBeMatched) {
                Map<Character, Long> charCountInWord = mapToCharCount(word);

                List<String> matches = new ArrayList<>();

                for (String str : toBeMatched) {
                    if (word.equalsIgnoreCase(str))
                        continue;

                    Map<Character, Long> charCountInStr = mapToCharCount(str);

                    if (charCountInStr.equals(charCountInWord))
                        matches.add(str);
                }
                return matches;
            }

            private Map<Character, Long> mapToCharCount(String word) {
                return word.chars().mapToObj(c -> Character.toLowerCase((char) c))
                        .map(c -> Map.entry(c, 1))
                        .collect(Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.counting()));
            }
        }
    }
}