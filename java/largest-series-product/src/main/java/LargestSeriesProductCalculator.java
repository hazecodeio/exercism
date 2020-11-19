import java.util.Arrays;
import java.util.stream.IntStream;

class LargestSeriesProductCalculator {

    private String inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {

        if (!inputNumber.isBlank() && !inputNumber.matches("\\d+"))
            throw new IllegalArgumentException("String to search may only contain digits.");

        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {

        if (numberOfDigits < 0)
            throw new IllegalArgumentException("Series length must be non-negative.");

        if (numberOfDigits > inputNumber.length())
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");

        long[] digits = inputNumber.chars().mapToLong(Character::getNumericValue).toArray();

        return IntStream.rangeClosed(0, digits.length - numberOfDigits)
                .mapToLong(i -> process(digits, i, numberOfDigits))
                .max()
                .getAsLong();
    }

    private long process(long[] digits, int skip, int size) {
        return Arrays.stream(digits)
                .skip(skip)
                .limit(size)
                .reduce((a, b) -> a * b)
                .orElse(1);
    }
}
