import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PrimeFactorsCalculatorTest {

    private PrimeFactorsCalculator primeFactorsCalculator;

    public PrimeFactorsCalculatorTest(PrimeFactorsCalculator.StrategyE strategy) {
        this.primeFactorsCalculator = new PrimeFactorsCalculator(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<PrimeFactorsCalculator.StrategyE> getEnums() {
        return EnumSet.allOf(PrimeFactorsCalculator.StrategyE.class);
    }

    @Test
    public void testNoFactors() {
        assertEquals(Collections.emptyList(), primeFactorsCalculator.calculatePrimeFactorsOf(1L));
    }

    @Test
    public void testPrimeNumber() {
        assertEquals(Collections.singletonList(2L), primeFactorsCalculator.calculatePrimeFactorsOf(2L));
    }

    @Test
    public void testSquareOfAPrime() {
        assertEquals(Arrays.asList(3L, 3L), primeFactorsCalculator.calculatePrimeFactorsOf(9L));
    }

    @Test
    public void testCubeOfAPrime() {
        assertEquals(Arrays.asList(2L, 2L, 2L), primeFactorsCalculator.calculatePrimeFactorsOf(8L));
    }

    @Test
    public void testProductOfPrimesAndNonPrimes() {
        assertEquals(Arrays.asList(2L, 2L, 3L), primeFactorsCalculator.calculatePrimeFactorsOf(12L));
    }

    @Test
    public void testProductOfPrimes() {
        assertEquals(Arrays.asList(5L, 17L, 23L, 461L), primeFactorsCalculator.calculatePrimeFactorsOf(901255L));
    }

    @Test
    public void testFactorsIncludingALargePrime() {
        assertEquals(Arrays.asList(11L, 9539L, 894119L), primeFactorsCalculator.calculatePrimeFactorsOf(93819012551L));
    }

}
