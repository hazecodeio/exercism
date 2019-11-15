import NumberType._

object PerfectNumbers {

  def classify(n: Int): Either[String, INumberType] =
    if (n <= 0)
      Left("Classification is only possible for natural numbers.")
    else factorize(n).sum match { //aliquot
      case a if a == n => Right(Perfect)
      case a if a > n => Right(Abundant)
      case a if a < n => Right(Deficient)
    }

  def factorize(n: Int) = (1 to n/2).filter(n % _ == 0)
}


object NumberType {

  sealed trait INumberType

  object Perfect extends INumberType

  object Abundant extends INumberType

  object Deficient extends INumberType

}
// Alternatively
/*
object NumberType extends Enumeration {
  type NumberType = Value
  val Deficient, Perfect, Abundant = Value
}
 */
