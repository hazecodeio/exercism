import java.util.Objects;

class Clock {

    private int hour;
    private int minute;

    public Clock(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    private Clock normalizeClock(int hour, int minute) {
        int m = minute;
        int h = hour;
        if (m < 0) { // e.g. (-1 == 59)
            h -= 1 - m / 60; // -ve minutes technically speaking means less time from the hour(s)
            m = 60 + (m % 60);
        }
        if (h < 0)// e.g. (-1 == 23)
            h = 24 + (h % 24);

        h = (h + m / 60) % 24; // normalize to 24 hours
        m = m % 60; // normalize to 60 minutes

        return new Clock(h, m);
    }

    public void add(int thatMinute) {
        this.minute = this.minute + thatMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clock thatClock = (Clock) o;

        Clock thisClockNorm = normalizeClock(hour, minute);
        Clock thatClockNorm = normalizeClock(thatClock.hour, thatClock.minute);

        return thisClockNorm.hour == thatClockNorm.hour &&
                thisClockNorm.minute == thatClockNorm.minute;
    }

    @Override
    public int hashCode() {
        Clock normalizedClock = normalizeClock(hour, minute);
        return Objects.hash(normalizedClock.hour, normalizedClock.minute);
    }

    @Override
    public String toString() {
        Clock normalizedClock = normalizeClock(hour, minute);
        return new StringBuilder()
                .append(String.format("%02d:%02d", normalizedClock.hour, normalizedClock.minute))
                .toString();
    }
}