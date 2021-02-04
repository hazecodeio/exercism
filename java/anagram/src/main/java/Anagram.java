import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Anagram {

    String word;

    BiFunction<String, List<String>, List<String>> strategy;

    public Anagram(String word) {
        this.word = word;
        this.strategy = StrategyE.MAP_DECLARATIVE;
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
        MAP_DECLARATIVE {
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
                Map<Character, Long> charCountMap = new HashMap<>();
                for(char c : word.toLowerCase().toCharArray()){
                    if(!charCountMap.containsKey(c))
                        charCountMap.put(c, 1L);
                    else
                        charCountMap.put(c, charCountMap.get(c)+1L);
                }
                return charCountMap;
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