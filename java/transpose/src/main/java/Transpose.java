import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Observations:
 *      - Row-Based: calculate number of Cols from the largest Row
 *      - Col-Based: calculate number of Rows from the largest Col?? Is It Possible? How?
 */
class Transpose {

    Function<String, String> strategy;

    public Transpose() {
        this.strategy = StrategyE.ROW_BASED;
    }

    public Transpose(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    public String transpose(String input) {
        return strategy.apply(input);
    }

    enum StrategyE implements Function<String, String> {
        ROW_BASED {
            @Override
            public String apply(String input) {
                String[] rows = input.split("\n");

                int nRows = Stream.of(rows).mapToInt(String::length).max().orElse(0);
                int nCols = rows.length;

                List<String> transposed = new ArrayList<>();

                for (int i = 0; i < nRows; i++) {

                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < nCols; j++) {
                        /*
                         * Checking the length is to match this case (current line is shorter than next and to append the ' '):
                         *  AB
                         *  DEF
                         */
                        char c = rows[j].length() > i ? rows[j].charAt(i) : ' ';
                        sb.append(c);
                    }
                    transposed.add(sb.toString());

                    /*
                     * Adjust nCols to match this case (current line is longer than next):
                     *  ABC
                     *  DE
                     */
                    while (nCols > 0 && rows[nCols - 1].length() <= i + 1)
                        nCols--;
                }
                return String.join("\n", transposed);
            }
        }
    }
}