import scala.collection.mutable

/**
 * Functional Approach (mostly)
 */
object Sieve {
  /**
   *
   * @param num
   * @return
   */
  def primes(num: Int) =
    (2 to num)
      .foldLeft((mutable.SortedSet[Int](), mutable.SortedSet[Int]())) {
        (acc, i) => // This is complex
          if(!acc._2.contains(i)){
            acc._1 += i

            (2 to num/2).foldLeft(acc._2){(nonePrimes, j) => nonePrimes += i*j}
          }
          acc
      }._1.toList

}

/**
 * Imperative Approach
 */
object Sieve2 {

  val allPrimes = mutable.SortedSet.empty[Int]
  val nonePrimes = mutable.SortedSet.empty[Int]

  def primes(num: Int) = {
    for (i <- 2 to num) {
      if (!nonePrimes.contains(i)) {
        allPrimes += i
        for (j <- 2 to num / 2) {
          nonePrimes += i * j
        }
      }

    }
    allPrimes.toList
  }

  println(allPrimes)
  println(nonePrimes)

}