import scala.annotation.tailrec

object PrimeFactors {

  def factors(num: Long): List[Long] = {

    @tailrec
    def loop(num: Long, i: Long = 2, factors: List[Long] = Nil): List[Long] = {
      if (num == 1)
        return factors

      num % i match {
        case 0 => loop(num / i, i, i :: factors)
        case _ => loop(num, i + 1, factors)
      }
    }

    loop(num).sorted
  }
}
