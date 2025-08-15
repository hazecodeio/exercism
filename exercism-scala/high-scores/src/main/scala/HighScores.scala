object HighScores {

  def report(scores: List[Int]) = s"Your latest score was ${scores.last}. That's${compose(scores)} your personal best!"

  private def compose(scores: List[Int]) =
    (scores.last, scores.max - scores.last) match {
      case (l, s) if s <= 0 => ""
      case (l, s) => s" $s short of"
    }

  def personalTop(scores: List[Int]) = scores.sorted.reverse.take(3)

  def personalBest(scores: List[Int]) = scores.max

  def latest(scores: List[Int]) = scores.last

  /**
   * Not making a reuse of the repeated string!!
   *
   * @param scores
   * @return
   */
  def report2(scores: List[Int]) =
    (scores.last, scores.max - scores.last) match {
      case (l, s) if s <= 0 => s"Your latest score was $l. That's your personal best!"
      case (l, s) => s"Your latest score was $l. That's $s short of your personal best!"
    }

}