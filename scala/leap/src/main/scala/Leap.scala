import java.time.{LocalDate, LocalDateTime, YearMonth}

object Leap {
  def leapYear(year: Int): Boolean = (year % 4 == 0) && !((year % 100 == 0) && !(year % 400 == 0))

  // via Java 8's Data/Time API
  def leapYear2(year:Int): Boolean = YearMonth.of(year, 2).lengthOfMonth() == 29
}
