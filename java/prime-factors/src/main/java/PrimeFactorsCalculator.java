import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PrimeFactorsCalculator {

    LongFunction<List<Long>> strategy;

    public PrimeFactorsCalculator() {
        this.strategy = StrategyE.ITERATIVE;
    }

    public PrimeFactorsCalculator(LongFunction<List<Long>> strategy) {
        this.strategy = strategy;
    }

    public List<Long> calculatePrimeFactorsOf(long n) {
        return strategy.apply(n);
    }

    enum StrategyE implements LongFunction<List<Long>> {
        ITERATIVE {
            @Override
            public List<Long> apply(long n) {
                List<Long> factors = new ArrayList<>();
                long i = 2;
                while (n > 1) {
                    if (n % i == 0) {
                        n /= i;
                        factors.add(i);
                    } else {
                        i++;
                    }
                }
                return factors;
            }
        },
        RECURSIVE { // Fails at large numbers!! Unlike Scala compiler, Java compiler doesn't seem to perform tail call optimization
            @Override
            public List<Long> apply(long n) {
                return loop(n, 2L, new ArrayList<Long>());
            }

            private List<Long> loop(long n, long i, List<Long> factors) {
                if (n == 1)
                    return factors;

                if (n % i == 0)
                    return loop(n / i, i, Stream.concat(factors.stream(), Stream.of(i)).collect(Collectors.toList()));
                else
                    return loop(n, i + 1, factors);
            }
        }
    }


}