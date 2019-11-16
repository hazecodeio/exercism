import scala.annotation.tailrec

class Accumulate {
  def accumulate[A, B](f: (A) => B, list: List[A]): List[B] = list match {
    case Nil => List[B]()
    case x :: tail => f(x) :: accumulate(f, tail)
  }

  // tailrec version
  def accumulate2[A, B](f: (A) => B, list: List[A]): List[B] = {

    @tailrec
    def loop[A, B](f: (A) => B, list: List[A], acc: List[B]): List[B] =
      list match {
        case Nil => acc
        case x :: tail => loop(f, tail, acc :+ f(x))
      }

    loop(f, list, List[B]())
  }
}
