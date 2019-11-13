object SumOfMultiples {

  def multiples(n: Int, max: Int) =
    (n until max by n).toSet

  def sum(factors: Set[Int], limit: Int): Int =
    factors.foldLeft(Set[Int]())((acc, n) => acc union multiples(n, limit)).sum
}

