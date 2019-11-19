import java.time.{LocalDate, LocalDateTime}

object Gigasecond {

  val gigaseconds = 1E9

  def add(startDate: LocalDate): LocalDateTime = add(startDate.atStartOfDay())

  def add(startDateTime: LocalDateTime): LocalDateTime = startDateTime.plusSeconds(gigaseconds.toLong)
}
