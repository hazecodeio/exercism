import java.util.stream.Stream;

class IsbnVerifier {

    boolean isValid(String stringToVerify) {

        String cleanedStr = stringToVerify.replaceAll("-", "");

        if (!cleanedStr.matches("^\\d{9}[X0-9]$"))
            return false;

        char[] digits = cleanedStr.toCharArray();
        int checkDigit = (digits[9] == 'X') ? 10 : Character.getNumericValue(digits[9]);

        int sumOfMultiples = 0;
        int[] factors = Stream.iterate(10, i -> i - 1).mapToInt(i -> i).limit(10).toArray();

        for (int i = 0; i < digits.length - 1; i++)
            sumOfMultiples += Character.getNumericValue(digits[i]) * factors[i];

        return (sumOfMultiples + checkDigit) % 11 == 0;
    }
}
