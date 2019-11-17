object CollatzConjecture {

  def even(n: Int) = n % 2 == 0

  def odd(n: Int) = !even(n)

  def steps2(n: Int): Option[Int] = {
    def loop(n: Int, acc: Int): Option[Int] = n match {
      case 1 => Some(acc)
      case n if n <= 0 => None
      case n if even(n) => loop(n / 2, acc + 1)
      case n if odd(n) => loop(n * 3 + 1, acc + 1)
    }

    loop(n, 0)
  }

  /*------------------- Another version via LazyList/Stream ---------------*/

  def nextOp(n: Int): Int = if (even(n)) n / 2 else n * 3 + 1

  def steps(n: Int): Option[Int] = n match {
    case n if n > 0 => Some(LazyList.iterate(n)(nextOp).takeWhile(_ != 1).length)
    case _ => None
  }
}