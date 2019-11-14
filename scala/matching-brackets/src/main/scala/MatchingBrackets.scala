import scala.collection.mutable

object MatchingBrackets {

  case class Pair(L: Char, R: Char)

  object Brackets extends Pair('[', ']')

  object Braces extends Pair('{', '}')

  object Parens extends Pair('(', ')')

  val allLeft = Set(Parens.L, Brackets.L, Braces.L)
  val allRight = Set(Parens.R, Brackets.R, Braces.R)

  def pairIt(c: Char) = c match {
    case '(' | ')' => Parens
    case '{' | '}' => Braces
    case '[' | ']' => Brackets
  }

  def isPaired(brackets: String): Boolean = {
    val parens = brackets.filter((allLeft union allRight).contains(_))
    val leftParenStack = mutable.Stack[Char]()

    for (p <- parens) {
      if (allLeft.contains(p)) // push to the stack if a Left paren
        leftParenStack.push(p)
      else if (leftParenStack.nonEmpty && pairIt(leftParenStack.pop()) != pairIt(p)) // pop out otherwise
        return false
    }

    if (leftParenStack.isEmpty)
      true
    else
      false
  }
}