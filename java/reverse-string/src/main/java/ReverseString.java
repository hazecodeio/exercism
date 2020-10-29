import java.util.function.Function;


class ReverseString {

    Function<String, String> strategy;

    ReverseString() {
        strategy = StrategyE.DEFAULT;
    }

    String reverse(String inputString) {
        return strategy.apply(inputString);
    }

    enum StrategyE implements Function<String, String> {
        DEFAULT {
            @Override
            public String apply(String s) {
                char[] chars = s.toCharArray();
                for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                return String.valueOf(chars);
            }
        },
        SB_REVERSE {
            @Override
            public String apply(String s) {
                return new StringBuilder(s).reverse().toString();
            }
        }
    }
}