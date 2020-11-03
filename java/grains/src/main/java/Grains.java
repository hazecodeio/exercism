import java.math.BigInteger;
import java.util.stream.Stream;

class Grains {

    GrainsStrategy strategy;

    public Grains() {
        strategy = StrategyE.EFFICIENT;
    }

    public Grains(StrategyE strategy) {
        this.strategy = strategy;
    }

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64)
            throw new IllegalArgumentException("square must be between 1 and 64");

        return strategy.grainsOnSquare(square);
    }

    BigInteger grainsOnBoard() {
        return strategy.grainsOnBoard();
    }

    enum StrategyE implements GrainsStrategy {
        EFFICIENT {
            @Override
            public BigInteger grainsOnSquare(int square) {
                return BigInteger.TWO.pow(square - 1);
            }

            @Override
            public BigInteger grainsOnBoard() {
                return BigInteger.TWO.pow(64).subtract(BigInteger.ONE);
            }
        },
        INEFFICIENT {
            @Override
            public BigInteger grainsOnSquare(int square) {
                return Stream
                        .iterate(BigInteger.ONE, bigInteger -> bigInteger.multiply(BigInteger.TWO))
                        .limit(square)
                        .skip(square - 1)
                        .findFirst()
                        .get();
            }

            @Override
            public BigInteger grainsOnBoard() {
                BigInteger allGrains = BigInteger.ZERO;
                for (int i = 1; i < 65; i++)
                    allGrains = allGrains.add(grainsOnSquare(i));
                return allGrains;
            }
        }
    }

    interface GrainsStrategy {
        BigInteger grainsOnSquare(int square);

        BigInteger grainsOnBoard();
    }
}
