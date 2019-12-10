object PascalsTriangle {

  val nextRow = (currentRow: List[Int]) => currentRow.head +: currentRow.sliding(2).map(_.sum).toList :+ currentRow.last

  def rows(r: Int): List[List[Int]] = {

    if (r <= 0) return Nil

    def loop(r: Int, c: Int, pascals: List[List[Int]]): List[List[Int]] = c match {
      case _ if c == r => pascals
      case 0 => loop(r, c + 1, pascals :+ List(1))
      case 1 => loop(r, c + 1, pascals :+ List(1, 1))
      case _ => loop(r, c + 1, pascals :+ nextRow(pascals.last))
    }

    loop(r, 0, Nil)
  }

}