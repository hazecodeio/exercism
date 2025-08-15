import java.util.Arrays;
import java.util.stream.IntStream;

class LuhnValidator {

    boolean isValid(String candidate) {

        String str = candidate.replaceAll(" ", "");
        if (str.length() < 2 || !str.matches("\\d+"))
            return false;

        char[] reversedChars = new StringBuilder(str).reverse().toString().toCharArray();

        int[] processedDigits = IntStream.range(0, reversedChars.length)
                .map(i -> mapWithPredicate(i, Character.getNumericValue(reversedChars[i]), i % 2 == 0))
                .toArray();

        if (Arrays.stream(processedDigits).sum() % 10 == 0)
            return true;

        return false;
    }

    private int mapWithPredicate(int i, int n, boolean p) {
        if (p)
            return n;
        n *= 2;
        if (n > 9)
            n -= 9;
        return n;
    }
}
