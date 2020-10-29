import java.time.LocalDate;
import java.util.function.IntFunction;

class Leap {

    IntFunction<Boolean> strategy;

    public Leap() {
        this.strategy = StrategyE.DEFAULT;
    }

    public Leap(IntFunction<Boolean> strategy) {
        this.strategy = strategy;
    }

    boolean isLeapYear(int year) {
        return strategy.apply(year);
    }

    enum StrategyE implements IntFunction<Boolean> {
        DEFAULT {
            @Override
            public Boolean apply(int year) {
                return LocalDate.of(year, 1, 1).isLeapYear();
            }
        },
        CALC_01 {
            @Override
            public Boolean apply(int year) {
                return (year % 4 == 0) && !((year % 100 == 0) && !(year % 400 == 0));
            }
        },
        CALC_02 {
            @Override
            public Boolean apply(int year) {
                return (year % 400 == 0) || ((year % 4 == 0) && !(year % 100 == 0));
            }

        }
    }

}
