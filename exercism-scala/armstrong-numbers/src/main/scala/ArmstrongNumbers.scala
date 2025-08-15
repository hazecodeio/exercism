object ArmstrongNumbers {

  import Math.pow
  import String.valueOf

  def isArmstrongNumber(n: Int) =
    valueOf(n)
      .map(_.asDigit)
      .map(pow(_, valueOf(n).length))
      .sum == n
}