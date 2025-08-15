import java.time.{LocalDate, LocalDateTime, Month, YearMonth}

object Leap {

  def leapYear(year: Int): Boolean = {
    viaMath(year)
//    viaJava8DataTimeAPI(year)
  }
  private def viaMath(year: Int): Boolean = (year % 4 == 0) && !((year % 100 == 0) && !(year % 400 == 0))

  // via Java 8's Data/Time API
  private def viaJava8DataTimeAPI(year:Int): Boolean = YearMonth.of(year, Month.FEBRUARY).lengthOfMonth() == 29
}
