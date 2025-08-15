import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    static Map<Character, Integer> FLAT_SCORE_MAP = SCORE_MAP
            .entrySet()
            .stream()
            .flatMap(entry -> entry.getKey().stream().map(key -> Map.entry(key, entry.getValue())))
            .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

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
