import scala.annotation.tailrec

object FlattenArray {

  def flatten(arr: List[_]): List[_] = {
//    viaNonTailRecursion(arr)
    viaTailRecursion(arr)
  }

  private def viaNonTailRecursion(arr: List[_]): List[_] = {
    arr match {
      case Nil => Nil
      case (x: List[_]) :: tail => flatten(x) ::: flatten(tail)
      case x :: tail => if (x != null) x :: flatten(tail) else flatten(tail)
    }
  }

  // a TailRec version
  private def viaTailRecursion(arr: List[_]): List[_] = {
    @tailrec
    def loop(leadArr: List[_], trailArr: List[_], flatAccumulator: List[_]): List[_] = {
      (leadArr, trailArr) match {
        case (Nil, Nil) => flatAccumulator
        case ((x: List[_]) :: tail, _) => loop(x, tail ::: trailArr, flatAccumulator)
        case (x :: tail, _) =>
          if (x != null) loop(Nil, tail ::: trailArr, flatAccumulator :+ x)
          else loop(Nil, tail ::: trailArr, flatAccumulator)
        case (Nil, _) => loop(trailArr, Nil, flatAccumulator)
      }
    }

    loop(arr, Nil, Nil)
  }
}