object Pangrams {
  val alphabets = ('a' to 'z').toSet

  def isPangram(input: String): Boolean = {
    viaForAll(input)
//    viaMap(input)
  }

  def viaMap(input: String): Boolean =
    input.filter(c => alphabets.contains(c.toLower))
      .map(c => (c.toLower, 1))
      .toMap
      .values
      .iterator.sum == alphabets.size

  // Another version; more succinct
  def viaForAll(input: String): Boolean =
    alphabets.forall(input.toLowerCase.contains(_))
}

