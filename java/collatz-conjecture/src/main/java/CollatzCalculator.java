import java.util.stream.Stream;

class CollatzCalculator {

    private boolean even(int n) {
        return n % 2 == 0;
    }

    private boolean odd(int n) {
        return !even(n);
    }

    private int nextOp(int n) {
        if (even(n))
            return n / 2;
        else
            return 3 * n + 1;
    }

    int computeStepCount(int start) {
        if (start > 0)
            return (int) Stream.iterate(start, this::nextOp).takeWhile(i -> i != 1).count();
        else
            throw new IllegalArgumentException("Only natural numbers are allowed");
    }

}
