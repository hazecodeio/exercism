import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Sieve {

    private List<Integer> allPrimes = new ArrayList<>();
    private List<Integer> nonePrimes = new ArrayList<>();

    Sieve(int maxPrime) {
        for (int i : IntStream.rangeClosed(2, maxPrime).toArray()) {
            if (!nonePrimes.contains(i)) {
                allPrimes.add(i);
                for (int j : IntStream.rangeClosed(2, maxPrime / 2).toArray()) {
                    nonePrimes.add(i * j);
                }
            }
        }
    }

    List<Integer> getPrimes() {
        return allPrimes;
    }
}
