object Isogram {
  val allowedChars = Set('-', ' ')

  def isIsogram(str: String) =
    str.filter(!allowedChars.contains(_))
      .map(_.toLower).groupMapReduce(identity)(_ => 1)(_ + _).values
      .filter(_ > 1)
      .headOption.map(_ => false).getOrElse(true) // or check the .size() == 0

}