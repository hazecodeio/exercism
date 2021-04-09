import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MinesweeperBoard {

    Function<List<String>, List<String>> strategy;
    List<String> inputBoard;

    public MinesweeperBoard(List<String> inputBoard) {
        this.inputBoard = inputBoard;
        this.strategy = StrategyE.D;
    }

    public List<String> withNumbers() {
        return strategy.apply(inputBoard);
    }

    enum StrategyE implements Function<List<String>, List<String>> {
        D {
            int[][] board;

            @Override
            public List<String> apply(List<String> inputBoard) {
                if (inputBoard.isEmpty())
                    return Collections.emptyList();

                board = convertTo2DArr(inputBoard);

                process(board);

                int rSize = board.length;
                int cSize = board[0].length;

                List<String> l = new ArrayList<>();
                for (int r = 0; r < rSize; r++) {
                    StringBuilder sb = new StringBuilder();
                    for (int c = 0; c < cSize; c++) {
                        Object obj = board[r][c] == -1 ? "*" : board[r][c] == 0 ? " " : board[r][c];
                        sb.append(obj);
                    }
                    l.add(sb.toString());
                }

                return l;
            }

            private void process(int[][] board) {
                int rSize = board.length;
                int cSize = board[0].length;

                for (int r = 0; r < rSize; r++) {
                    for (int c = 0; c < cSize; c++) {
                        if (board[r][c] == -1) {
                            calc(board, r - 1, c - 1);
                            calc(board, r - 1, c);
                            calc(board, r - 1, c + 1);
                            calc(board, r, c - 1);
                            calc(board, r, c + 1);
                            calc(board, r + 1, c - 1);
                            calc(board, r + 1, c);
                            calc(board, r + 1, c + 1);
                        }
                    }
                }
            }

            private void calc(int[][] board, int row, int column) {
                int rSize = board.length;
                int cSize = board[0].length;

                if (row < 0 || column < 0 || row > rSize - 1 || column > cSize - 1) {
                    return;
                }
                if (board[row][column] != -1) {
                    board[row][column]++;
                }
            }


            private int[][] convertTo2DArr(List<String> inputBoard) {
                int rSize = inputBoard.size();
                int cSize = inputBoard.get(0).length();
                int[][] board = new int[rSize][cSize];

                for (int i = 0; i < rSize; i++) {
                    for (int j = 0; j < cSize; j++) {
                        char c = inputBoard.get(i).charAt(j);
                        if (c == '*')
                            board[i][j] = -1;
                        else
                            board[i][j] = 0;
                    }
                }
                return board;
            }
        }
    }
}