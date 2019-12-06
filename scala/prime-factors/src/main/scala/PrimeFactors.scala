import scala.annotation.tailrec

object PrimeFactors {

  def factors(num: Long): List[Long] = {

    if (num <= 1) return Nil

    @tailrec
    def loop(num: Long, i: Long = 2, factors: List[Long] = Nil): List[Long] = i * i <= num match {
      case true if num % i == 0 => loop(num / i, i, i :: factors)
      case true => loop(num, i + 1, factors)
      case false => num :: factors
    }

    loop(num).sorted
  }
}
