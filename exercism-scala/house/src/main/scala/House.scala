import scala.annotation.tailrec

object House {

  val clauses = List(
    "the house" -> "Jack built.",
    "the malt" -> "lay in ",
    "the rat" -> "ate ",
    "the cat" -> "killed ",
    "the dog" -> "worried ",
    "the cow with the crumpled horn" -> "tossed ",
    "the maiden all forlorn" -> "milked ",
    "the man all tattered and torn" -> "kissed ",
    "the priest all shaven and shorn" -> "married ",
    "the rooster that crowed in the morn" -> "woke ",
    "the farmer sowing his corn" -> "kept ",
    "the horse and the hound and the horn" -> "belonged to ")

  def recite(clauseStart: Int, rhymeCount: Int): String = {

    @tailrec
    def loop(clauseStart: Int, rhymeCount: Int, str: String): String = {
      (clauseStart == rhymeCount) match {
        case (true) => str.appendedAll(recite(clauses.slice(0, clauseStart).reverse, "This is ")).appendedAll("\n\n")
        case _ => loop(clauseStart + 1, rhymeCount, str.appendedAll(recite(clauses.slice(0, clauseStart).reverse, "This is ")).appended('\n'))
      }
    }

    loop(clauseStart, rhymeCount, "")
  }

  private def recite(clauses: List[(String, String)], str: => String): String =
    clauses match {
      case Nil => str
      case head :: tail => recite(tail, str.appendedAll(head._1).appendedAll(" that ").appendedAll(head._2))
    }

}