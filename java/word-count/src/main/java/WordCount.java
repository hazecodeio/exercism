import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount{

    Function<String, Map<String, Integer>> strategy;

    public WordCount(Function<String, Map<String, Integer>> strategy) {
        this.strategy = strategy;
    }

    public WordCount() {
        strategy = StrategyE.VIA_STREAMS;
    }

    public Map<String, Integer> phrase(String word) {
        return strategy.apply(word);
    }

    enum StrategyE implements Function<String, Map<String, Integer>>{
        VIA_STREAMS {
            @Override
            public Map<String, Integer> apply(String word) {
                return Arrays.stream(word.split("[^a-zA-Z1-9']")).filter(i -> !i.equals(""))
                        .map(String::toLowerCase)
                        .map(s -> {
                            if(s.indexOf("'") == 0)
                                return s.replace("'","");
                            return s;
                        })
                        .collect(Collectors.groupingBy(i->i, Collectors.summingInt(x -> 1)));
            }
        }
    }
}