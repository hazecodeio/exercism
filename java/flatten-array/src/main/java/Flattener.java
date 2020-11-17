import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class Flattener {

    Function<List<? super Object>, List<? extends Object>> strategy;

    public Flattener() {
        this.strategy = StrategyE.FUNCTIONAL;
    }

    public Flattener(Function<List<? super Object>, List<? extends Object>> strategy) {
        this.strategy = strategy;
    }

    public List<? extends Object> flatten(List<? super Object> target) {
        return strategy.apply(target);
    }

    enum StrategyE implements Function<List<? super Object>, List<? extends Object>> {
        FUNCTIONAL {
            @Override
            public List<? extends Object> apply(List<? super Object> target) {
                Function<? super Object, List<?>> mapper =
                        param ->
                                (param instanceof List) ? apply((List<? super Object>) param) : List.of(param);
                return target.stream()
                        .filter(Objects::nonNull)
                        .map(mapper)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
            }
        },
        DECLARATIVE {
            @Override
            public List<? extends Object> apply(List<? super Object> target) {
                List<? super Object> flattenedList = new ArrayList<>();

                for (Object o : target) {
                    if (o instanceof List<?>)
                        flattenedList.addAll(apply((List<? super Object>) o));
                    else if (o != null)
                        flattenedList.add(o);
                }
                return flattenedList;
            }
        }
    }
}