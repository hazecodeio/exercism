object SumOfMultiples {

  def multiples2(n: Int, max: Int) =
    LazyList.from(1).map(_ * n).takeWhile(_ < max).toSet

  def sum(factors: Set[Int], limit: Int): Int =
    factors.foldLeft(Set[Int]())((acc, n) => acc union multiples(n, limit)).sum

  def multiples(n: Int, max: Int) =
    (n until max by n).toSet
}

