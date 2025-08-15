import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.function.IntFunction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LeapTest {

    private Leap leap;

    public LeapTest(IntFunction<Boolean> strategy) {
        this.leap = new Leap(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<Leap.StrategyE> getEnums() {
        return EnumSet.allOf(Leap.StrategyE.class);
    }

    @Test
    public void testYearNotDivBy4InCommonYear() {
        assertFalse(leap.isLeapYear(2015));
    }

    @Test
    public void testYearDivBy2NotDivBy4InCommonYear() {
        assertFalse(leap.isLeapYear(1970));
    }

    @Test
    public void testYearDivBy4NotDivBy100InLeapYear() {
        assertTrue(leap.isLeapYear(1996));
    }

    @Test
    public void testYearDivBy4And5InLeapYear() {
        assertTrue(leap.isLeapYear(1960));
    }

    @Test
    public void testYearDivBy100NotDivBy400InCommonYear() {
        assertFalse(leap.isLeapYear(2100));
    }

    @Test
    public void testYearDivBy100NotDivBy3IsNotLeapYear() {
        assertFalse(leap.isLeapYear(1900));
    }

    @Test
    public void testYearDivBy400InLeapYear() {
        assertTrue(leap.isLeapYear(2000));
    }

    @Test
    public void testYearDivBy400NotDivBy125IsLeapYear() {
        assertTrue(leap.isLeapYear(2400));
    }

    @Test
    public void testYearDivBy200NotDivBy400InCommonYear() {
        assertFalse(leap.isLeapYear(1800));
    }

}
