import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IsogramCheckerTest {

    IsogramChecker iso;

    public IsogramCheckerTest(IsogramChecker.StrategyE strategy) {
        iso = new IsogramChecker(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<IsogramChecker.StrategyE> getEnums() {
        return EnumSet.allOf(IsogramChecker.StrategyE.class);
    }

    @Test
    public void testEmptyString() {
        assertTrue(iso.isIsogram(""));
    }

    @Test
    public void testLowercaseIsogram() {
        assertTrue(iso.isIsogram("isogram"));
    }

    @Test
    public void testNotIsogram() {
        assertFalse(iso.isIsogram("eleven"));
    }

    @Test
    public void testDuplicateEndAlphabet() {
        assertFalse(iso.isIsogram("zzyzx"));
    }

    @Test
    public void testMediumLongIsogram() {
        assertTrue(iso.isIsogram("subdermatoglyphic"));
    }

    @Test
    public void testCaseInsensitive() {
        assertFalse(iso.isIsogram("Alphabet"));
    }

    @Test
    public void testDuplicatMixedCase() {
        assertFalse(iso.isIsogram("alphAbet"));
    }

    @Test
    public void testIsogramWithHyphen() {
        assertTrue(iso.isIsogram("thumbscrew-japingly"));
    }

    @Test
    public void testIsogramWithDuplicatedCharAfterHyphen() {
        assertFalse(iso.isIsogram("thumbscrew-jappingly"));
    }

    @Test
    public void testIsogramWithDuplicatedHyphen() {
        assertTrue(iso.isIsogram("six-year-old"));
    }

    @Test
    public void testMadeUpNameThatIsAnIsogram() {
        assertTrue(iso.isIsogram("Emily Jung Schwartzkopf"));
    }

    @Test
    public void testDuplicatedCharacterInTheMiddleIsNotIsogram() {
        assertFalse(iso.isIsogram("accentor"));
    }

    @Test
    public void testSameFirstAndLast() {
        assertFalse(iso.isIsogram("angola"));
    }

}
