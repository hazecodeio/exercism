import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PrimeFactorsCalculator {

    public List<Long> calculatePrimeFactorsOf(long n) {
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