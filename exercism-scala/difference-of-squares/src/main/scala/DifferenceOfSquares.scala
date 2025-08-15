object DifferenceOfSquares {

  def sumOfSquares(n: Int): Int = (1 to n).map(Math.pow(_, 2).toInt).iterator.sum

  def squareOfSum(n: Int): Int = Math.pow((1 to n).sum, 2).toInt

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
}
