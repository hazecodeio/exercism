object Darts {
  def score(x: Double, y: Double) = {
    val r = Math.sqrt(x * x + y * y)
    if (r <= 1) 10
    else if (r <= 5) 5
    else if (r <= 10) 1
    else 0
  }
}