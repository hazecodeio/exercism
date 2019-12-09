object NthPrime {
  val isPrime = (x: Int) => (2 to math.sqrt(x).toInt).dropWhile(x % _ != 0).headOption.map(_ => false).getOrElse(true)

  def prime(n: Int): Option[Int] = if (n <= 0) None else LazyList.from(2).filter(isPrime).map(Some(_))(n - 1)
}