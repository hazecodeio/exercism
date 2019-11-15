object BeerSong {

  def lyrics(beerCount: Int) = beerCount match {
    case 0 =>
      """No more bottles of beer on the wall, no more bottles of beer.
         |Go to the store and buy some more, 99 bottles of beer on the wall.
         |""".stripMargin
    case 1 =>
      """1 bottle of beer on the wall, 1 bottle of beer.
         |Take it down and pass it around, no more bottles of beer on the wall.
         |""".stripMargin
    case 2 =>
      """2 bottles of beer on the wall, 2 bottles of beer.
         |Take one down and pass it around, 1 bottle of beer on the wall.
         |""".stripMargin
    case _ =>
      s"""${beerCount} bottles of beer on the wall, ${beerCount} bottles of beer.
         |Take one down and pass it around, ${beerCount - 1} bottles of beer on the wall.
         |""".stripMargin
  }

  def recite(totalLeft: Int, takeOut: Int) =
    totalLeft until(totalLeft - takeOut) by -1  map(lyrics) mkString("\n")

}