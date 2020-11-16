case class Clock(hour: Int, minute: Int) {

  def this(minute: Int) = this(0, minute)

  def +(that: Clock) = Clock(this.hour + that.hour, this.minute + that.minute) // Alternatively we can use copy()

  def -(that: Clock) = Clock(this.hour - that.hour, this.minute - that.minute) // Alternatively we can use copy()

  override def equals(that: Any): Boolean = {
    if (that == null || !that.isInstanceOf[Clock])
      return false

    val thatNormalized = that.asInstanceOf[Clock].normalizeClock
    val thisNormalized = this.normalizeClock

    thatNormalized.normalizeClock.hour == thisNormalized.normalizeClock.hour &&
      thatNormalized.normalizeClock.minute == thisNormalized.normalizeClock.minute
  }

  // Though normalizing beforehand relives us from overriding equal() but it changes the original values
  //  normalizeClock
  private def normalizeClock = {
    var m = minute
    var h = hour
    if (m < 0) { // e.g. (-1 == 59)
      h -= 1 - m / 60 // -ve minutes technically speaking means less time from the hour(s)
      m = 60 + (m % 60)
    }
    if (h < 0) // e.g. (-1 == 23)
      h = 24 + (h % 24)

    h = (h + m / 60) % 24 // normalize to 24 hours
    m = m % 60 // normalize to 60 minutes

    Clock(h, m)
  }

}

object Clock {
  def apply(minute: Int) = new Clock(minute)
}