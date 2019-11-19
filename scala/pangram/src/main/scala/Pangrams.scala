object Pangrams {
  val alphabets = ('a' to 'z').toSet

  def isPangram(input: String): Boolean =
    input.filter(c => alphabets.contains(c.toLower))
      .map(c => (c.toLower, 1))
      .toMap
      .values
      .iterator.sum == alphabets.size

  // Another version; more succinct
  def isPangram2(input: String): Boolean =
    alphabets.forall(input.toLowerCase.contains(_))
}

