import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Scrabble {

    static Map<List<Character>, Integer> SCORE_MAP = Map.of(
            List.of('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'), 1,
            List.of('D', 'G'), 2,
            List.of('B', 'C', 'M', 'P'), 3,
            List.of('F', 'H', 'V', 'W', 'Y'), 4,
            List.of('K'), 5,
            List.of('J', 'X'), 8,
            List.of('Q', 'Z'), 10
    );
    static Stream<Map<Character, Integer>> FLATTENED_STREAM_OF_MAPS = SCORE_MAP
            .entrySet()
            .stream()
            .flatMap(e -> e.getKey().stream().map(k -> Map.of(k, e.getValue())));//ToDo - Replace with Map.entry()

    static Map<Character, Integer> FLAT_SCORE_MAP = FLATTENED_STREAM_OF_MAPS
            .collect(Collectors.toMap
                    (
                            m -> m.keySet().stream().findAny().get(),
                            m -> m.values().stream().findAny().get()
                    ));

    int score;

    Scrabble(String word) {
        score = word.chars()
                .mapToObj(i -> Character.toUpperCase((char) i))
                .mapToInt(c -> FLAT_SCORE_MAP.getOrDefault(c, 0))
                .sum();
    }

    int getScore() {
        return score;
    }

}
