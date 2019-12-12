object PascalsTriangle {

  val nextRow = (currentRow: List[Int]) => currentRow.head +: currentRow.sliding(2).map(_.sum).toList :+ currentRow.last

  def rows(r: Int): List[List[Int]] = {
    viaRecursion(r)
//    viaListIteratorWithSliding(r)
//    viaStreamIteratorWithSliding(r)
//    viaStreamIteratorWithZipping(r)
  }

  private def viaRecursion(r: Int): List[List[Int]] = {

    if (r <= 0) return Nil

    def loop(r: Int, c: Int, pascals: List[List[Int]]): List[List[Int]] = c match {
      case _ if c == r => pascals
      case 0 => loop(r, c + 1, pascals :+ List(1))
      case 1 => loop(r, c + 1, pascals :+ List(1, 1))
      case _ => loop(r, c + 1, pascals :+ nextRow(pascals.last))
    }

    loop(r, 0, Nil)
  }

  private def viaListIteratorWithSliding(r: Int): List[List[Int]] = {

    if (r <= 0) return Nil

    //Alternatively -> LazyList.iterate()
    List.iterate(List(1), r) { xs =>
      (0 +: xs :+ 0).sliding(2).map(_.sum).toList
    }
  }

  private def viaStreamIteratorWithSliding(r: Int): List[List[Int]] = {
    LazyList.iterate(List(1)) { xs => // you also can pass in 'r' as a second parameter similar to List.iterate()
      (0 +: xs :+ 0).sliding(2).map(_.sum).toList
    }
      .take(r).toList
  }

  private def viaStreamIteratorWithZipping(r: Int): List[List[Int]] = {
    LazyList.iterate(List(1)) { xs => // you also can pass in 'r' as a second parameter similar to List.iterate()
      (0 +: xs) zip (xs :+ 0) map { case (l, r) => l + r } // same logic can be used in "viaListIteratorWithSliding()"
    }
      .take(r).toList
  }

}