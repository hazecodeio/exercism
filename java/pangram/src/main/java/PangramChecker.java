import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PangramChecker {

    static List<Character> ALPHABETS = IntStream.rangeClosed('a', 'z')
            .mapToObj(i -> (char) i)
            .collect(Collectors.toList());

    Function<String, Boolean> strategy;

    public PangramChecker() {
        strategy = StrategyE.INPUT_AGAINST_ALPHAB;
    }

    public PangramChecker(Function<String, Boolean> strategy) {
        this.strategy = strategy;
    }

    public boolean isPangram(String input) {

        return strategy.apply(input);
    }

    enum StrategyE implements Function<String, Boolean> {
        INPUT_AGAINST_ALPHAB {
            @Override
            public Boolean apply(String input) {
                Set<Character> uniqueChars = input.chars()
                        .mapToObj(i -> (char) i)
                        .map(Character::toLowerCase)
                        .filter(c -> ALPHABETS.contains(c))
                        .collect(Collectors.toSet());

                return uniqueChars.size() == ALPHABETS.size();
            }

        },
        ALPHAB_AGAINST_INPUT {
            @Override
            public Boolean apply(String input) {
                String inputLowerCase = input.toLowerCase();
                return ALPHABETS.stream().allMatch(c -> inputLowerCase.contains(String.valueOf(c)));
            }
        }
    }
}