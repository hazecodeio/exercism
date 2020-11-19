import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

class Transpose {

    Function<String, String> strategy;

    public Transpose() {
        this.strategy = StrategyE.DEFAULT;
    }

    public Transpose(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    public String transpose(String input) {
        return strategy.apply(input);
    }

    enum StrategyE implements Function<String, String> {
        DEFAULT {
            @Override
            public String apply(String input) {
                String[] rows = input.split("\n");

                int nRows = Stream.of(rows).mapToInt(String::length).max().orElse(0);
                int nCols = rows.length;

                List<String> transposed = new ArrayList<>();

                for (int i = 0; i < nRows; i++) {

                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < nCols; j++) {
                        char c = rows[j].length() > i ? rows[j].charAt(i) : ' ';
                        sb.append(c);
                    }
                    transposed.add(sb.toString());

                    while (nCols > 0 && rows[nCols - 1].length() <= i + 1)
                        nCols--;
                }
                return String.join("\n", transposed);
            }
        }
    }
}