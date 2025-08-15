import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {

    static double GIGASECOND = 1E9;
    LocalDateTime momentPlusGigaSec;

    public Gigasecond(LocalDate moment) {
        this(moment.atStartOfDay());
    }

    public Gigasecond(LocalDateTime moment) {
        this.momentPlusGigaSec = moment.plusSeconds((long) GIGASECOND);
    }

    public LocalDateTime getDateTime() {
        return momentPlusGigaSec;
    }
}
