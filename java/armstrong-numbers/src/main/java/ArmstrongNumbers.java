import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Stream;

class ArmstrongNumbers {

    IntFunction<Boolean> strategy;

    public ArmstrongNumbers() {
        strategy = StrategyE.STREAM_OF_STRINGS;
    }

    public ArmstrongNumbers(IntFunction<Boolean> strategy) {
        this.strategy = strategy;
    }

    boolean isArmstrongNumber(int numberToCheck) {

        return strategy.apply(numberToCheck);
    }

    enum StrategyE implements IntFunction<Boolean> {
        STREAM_OF_STRINGS {
            @Override
            public Boolean apply(int numberToCheck) {

                String numString = String.valueOf(numberToCheck);
                Stream<String> digitStream = Arrays.stream(numString.split(""));

                return digitStream.map(d -> Integer.valueOf(d))
                        .mapToInt(d -> (int) Math.pow(d, numString.length()))
                        .sum() == numberToCheck;
            }
        },
        STREAM_OF_CHARS {
            @Override
            public Boolean apply(int numberToCheck) {

                String numString = String.valueOf(numberToCheck);
                return numString.chars()
                        .map(d -> Character.digit(d, 10))
                        //.map(d -> Character.getNumericValue(d)) // Alternative to Character.digit()
                        .map(d -> (int) Math.pow(d, numString.length()))
                        .sum() == numberToCheck;
            }
        },
        LOOP {
            @Override
            public Boolean apply(int numberToCheck) {

                int sum = 0;
                String numString = String.valueOf(numberToCheck);
                for (String s : numString.split("")) {
                    Integer d = Integer.valueOf(s);
                    sum += (int) Math.pow(d, numString.length());
                }
                return sum == numberToCheck;
            }
        }

    }

}
