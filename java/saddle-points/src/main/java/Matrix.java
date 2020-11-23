import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

class Matrix {

    private Function<List<List<Integer>>, Set<MatrixCoordinate>> strategy;
    private List<List<Integer>> matrix;

    public Matrix(Function<List<List<Integer>>, Set<MatrixCoordinate>> strategy) {
        this.strategy = strategy;
    }

    Matrix(List<List<Integer>> values) {
        this.matrix = values;
        this.strategy = StrategyE.DECLARATIVE;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        return strategy.apply(matrix);
    }

    enum StrategyE implements Function<List<List<Integer>>, Set<MatrixCoordinate>> {
        DECLARATIVE {
            @Override
            public Set<MatrixCoordinate> apply(List<List<Integer>> matrix) {
                var set = new HashSet<MatrixCoordinate>();

                for (int i = 0; i < matrix.size(); i++) {
                    int rowMax = Collections.max(matrix.get(i));

                    for (int j = 0; j < matrix.get(i).size(); j++)
                        if (matrix.get(i).get(j) == rowMax) {
                            int colMin = matrix.get(i).get(j);

                            for (var row : matrix) {
                                if (row.get(j) < rowMax)
                                    colMin = row.get(j);
                            }
                            if (rowMax == colMin) {
                                set.add(new MatrixCoordinate(i + 1, j + 1));
                            }
                        }
                }
                return set;
            }
        }
    }
}
