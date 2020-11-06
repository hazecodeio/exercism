import java.util.Arrays;
import java.util.stream.IntStream;

class NaturalNumber {


    private int n;

    public NaturalNumber(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        this.n = n;
    }

    public Classification getClassification() {

        int sum = Arrays.stream(factorize(n)).sum();

        if (sum == n)
            return Classification.PERFECT;
        else if (sum > n)
            return Classification.ABUNDANT;
        else
            return Classification.DEFICIENT;
    }

    private int[] factorize(int n) {
        return IntStream.rangeClosed(1, n / 2).filter(i -> n % i == 0).toArray();
    }
}
