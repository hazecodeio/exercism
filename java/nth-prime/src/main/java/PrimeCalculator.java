import java.util.stream.IntStream;

class PrimeCalculator {

    int nth(int nth) {
        if (nth <= 0)
            throw new IllegalArgumentException();

        return IntStream.iterate(2, i -> i + 1).filter(this::isPrime).skip(nth - 1).findFirst().getAsInt();
    }

    boolean isPrime(int x) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(x)).dropWhile(i -> x % i != 0).findFirst().isEmpty();
    }
}
