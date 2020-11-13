import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SumOfMultiples {

    BiFunction<Integer, int[], Integer> strategy;

    int sum;

    SumOfMultiples(int number, int[] set) {
        sum = StrategyE.D1.apply(number, set);
    }

    int getSum() {
        return sum;
    }

    enum StrategyE implements BiFunction<Integer, int[], Integer> {
        D1 {
            @Override
            public Integer apply(Integer number, int[] set) {
                return Arrays.stream(set).mapToObj(i -> i)
                        .flatMap(n -> multiples(n, number))
                        .collect(Collectors.toSet()) // remove duplicates before summing up
                        .stream().reduce(Integer::sum).orElse(0);
            }

            private Stream<Integer> multiples(int n, int max) {
                return Stream.iterate(1, i -> i + 1).map(i -> n * i).takeWhile(i -> i < max && i > 0);
            }
        },
        D2 {
            @Override
            public Integer apply(Integer number, int[] set) {
                Set<Integer> uniqueMultiples = new HashSet<>();
                for (int i : set) {
                    for (int j = 1; j * i < number && j * i > 0; j++)
                        uniqueMultiples.add(j * i);
                }
                return uniqueMultiples.stream().reduce(Integer::sum).orElse(0);
            }
        }
    }

}
