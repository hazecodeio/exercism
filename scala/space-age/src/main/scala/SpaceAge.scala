object SpaceAge {

  import Planets._

  private val SecondsInEarthYear = 31557600

  private def ageOn(planet: Planet)(seconds: Double) =
    seconds / (planet.orbitalPeriod * SecondsInEarthYear)

  def onEarth(seconds: Double) = ageOn(Earth)(seconds)

  def onMercury(seconds: Double) = ageOn(Mercury)(seconds)

  def onVenus(seconds: Double) = ageOn(Venus)(seconds)

  def onMars(seconds: Double) = ageOn(Mars)(seconds)

  def onJupiter(seconds: Double) = ageOn(Jupiter)(seconds)

  def onSaturn(seconds: Double) = ageOn(Saturn)(seconds)

  def onUranus(seconds: Double) = ageOn(Uranus)(seconds)

  def onNeptune(seconds: Double) = ageOn(Neptune)(seconds)

}

object Planets {

  sealed trait Planet {
    def orbitalPeriod: Double = orbitalPeriodViaLambda(this)

    // alternatively
    //    def earthPeriod: Double = orbitalPeriodViaPartialFunction(this)
  }

  final case object Mercury extends Planet

  final case object Venus extends Planet

  final case object Earth extends Planet

  final case object Mars extends Planet

  final case object Jupiter extends Planet

  final case object Saturn extends Planet

  final case object Uranus extends Planet

  final case object Neptune extends Planet

  /**
   * Higher order function
   *
   * @return (Planet => Double)
   */
  private def orbitalPeriodViaLambda: (Planet => Double) = { // Similarly, a PartialFunction can be used to achieve the same
    case Mercury => 0.2408467
    case Venus => 0.61519726
    case Earth => 1.0
    case Mars => 1.8808158
    case Jupiter => 11.862615
    case Saturn => 29.447498
    case Uranus => 84.016846
    case Neptune => 164.79132
  }

  private def orbitalPeriodViaPartialFunction: PartialFunction[Planet, Double] = {
    case Mercury => 0.2408467
    case Venus => 0.61519726
    case Earth => 1.0
    case Mars => 1.8808158
    case Jupiter => 11.862615
    case Saturn => 29.447498
    case Uranus => 84.016846
    case Neptune => 164.79132
  }
}


///////////////////////////////////////////////

import scala.language.dynamics

/**
 * DSL via Scala's metaprogramming
 */
object SpaceAge2 extends Dynamic { // A Marker trait

  val SecondsPerEarthYear = 31557600

  val orbitalPeriods = Map(
    "Earth" -> 1.0,
    "Mercury" -> 0.2408467,
    "Venus" -> 0.61519726,
    "Mars" -> 1.8808158,
    "Jupiter" -> 11.862615,
    "Saturn" -> 29.447498,
    "Uranus" -> 84.016846,
    "Neptune" -> 164.79132)


  def applyDynamic(methodName: String)(age: Double): Double = {

    // Extract planet name from method name (skip the "on")
    val planet = methodName.substring(2)

    // Get the period factor
    val period = orbitalPeriods(planet)

    // Calculate the result
    age / (period * SecondsPerEarthYear)
  }
}
