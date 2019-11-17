object FlattenArray {
  //ToDo - Implement a TailRec solution
  def flatten(arr: List[_]): List[_] = {
    arr match {
      case Nil => Nil
      case (x: List[_]) :: tail => flatten(x) ::: flatten(tail)
      case x :: tail => if (x != null) x :: flatten(tail) else flatten(tail)
    }
  }
}