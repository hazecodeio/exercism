import java.util.Arrays;
import java.util.stream.Collectors;

class Matrix {

    int[][] matrixAsInt;
    private String matrixAsString;

    Matrix(String matrixAsString) {
        this.matrixAsString = matrixAsString;
        matrixAsInt = mapMatrixToInt(matrixAsString);
    }

    private int[][] mapMatrixToInt(String matrixAsString) {
        return Arrays.stream(matrixAsString.split("\n"))
                .map(r -> Arrays.stream(r.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .collect(Collectors.toList()).toArray(new int[][]{});
    }

    int[] getRow(int rowNumber) {
        return Arrays.stream(matrixAsInt[rowNumber - 1]).toArray();
    }

    int[] getColumn(int columnNumber) {
        return Arrays.stream(matrixAsInt).map(r -> r[columnNumber - 1]).mapToInt(i -> i).toArray();
    }
}
