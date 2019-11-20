import scala.collection.SortedMap

object Raindrops {
  val factors = SortedMap(3 -> "Pling", 5 -> "Plang", 7 -> "Plong")

  def convert(n: Int): String = factors.keys.filter(n % _ == 0).map(factors(_)).toList match {
    case Nil => String.valueOf(n)
    case l => l.mkString
  }

  //Another version using reduceOption() and getOrElse()
  //equivalent to (... if statement ...).getOrElse(... else statement ...)
  def convert2(n: Int): String = factors.keys.filter(n % _ == 0).map(factors(_)).reduceOption(_ + _).getOrElse(String.valueOf(n))
}

