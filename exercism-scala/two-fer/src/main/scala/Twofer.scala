object Twofer{

  def statement(n: String) = s"One for ${n}, one for me."

  def twofer(name: String = "you"): String = if (name.isEmpty) statement("you") else statement(name)

  object TwoferOld1 {
    def statement(n: String) = s"One for ${n}, one for me."

    def twofer(name: String): String = if (name.isEmpty) statement("you") else statement(name)

    def twofer(): String = statement("you")
  }
}
