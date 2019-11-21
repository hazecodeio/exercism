object ScrabbleScore {
  val scoreMap = Map(
    List('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T') -> 1,
    List('D', 'G') -> 2,
    List('B', 'C', 'M', 'P') -> 3,
    List('F', 'H', 'V', 'W', 'Y') -> 4,
    List('K') -> 5,
    List('J', 'X') -> 8,
    List('Q', 'Z') -> 10
  )

  // Flatten the HashMap so each List Key is an individual element of its own
  val scores = scoreMap.flatMap(t => t._1.map((_, t._2)))

  def score(s: String) = s.map(_.toUpper).map(scores(_)).sum

}