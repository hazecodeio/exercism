object SpaceAge {

  import PlanetsToEarth._

  trait BasePlanetPeriod { def basePeriod: Int }
  final case object EarthPeriod extends BasePlanetPeriod {
    override def basePeriod: Int = 31557600
  }
  object BasePlanetPeriod { implicit val earthPeriod: BasePlanetPeriod = EarthPeriod }

  private def ageOn(planet: Planet, seconds: Double)(implicit basePlanetPeriod: BasePlanetPeriod) =
    seconds / (planet.orbitalPeriod * basePlanetPeriod.basePeriod)

  val onEarth = ageOn(Earth, _)

  val onMercury = ageOn(Mercury, _)

  val onVenus = ageOn(Venus, _)

  val onMars = ageOn(Mars, _)

  val onJupiter = ageOn(Jupiter, _)

  val onSaturn = ageOn(Saturn, _)

  val onUranus = ageOn(Uranus, _)

  val onNeptune = ageOn(Neptune, _)


  sealed trait Planet {
    val orbitalPeriod: Double
  }
  object PlanetsToEarth {

    final case object Mercury extends Planet {
      override val orbitalPeriod: Double = 0.2408467
    }

    final case object Venus extends Planet {
      override val orbitalPeriod: Double = 0.61519726
    }

    final case object Earth extends Planet {
      override val orbitalPeriod: Double = 1.0
    }

    final case object Mars extends Planet {
      override val orbitalPeriod: Double = 1.8808158
    }

    final case object Jupiter extends Planet {
      override val orbitalPeriod: Double = 11.862615
    }

    final case object Saturn extends Planet {
      override val orbitalPeriod: Double = 29.447498
    }

    final case object Uranus extends Planet {
      override val orbitalPeriod: Double = 84.016846
    }

    final case object Neptune extends Planet {
      override val orbitalPeriod: Double = 164.79132
    }

  }

}

/////////////////////////////////////////////

object SpaceAge3 {

  import Planets._

  private def ageOn(planet: Planet)(seconds: Double) =
    seconds / (planet.calculatedPeriod)

  val onEarth = ageOn(Earth) _

  val onMercury = ageOn(Mercury) _

  val onVenus = ageOn(Venus) _

  val onMars = ageOn(Mars) _

  val onJupiter = ageOn(Jupiter) _

  val onSaturn = ageOn(Saturn) _

  val onUranus = ageOn(Uranus) _

  val onNeptune = ageOn(Neptune) _

  object Planets {

    sealed trait Planet {
      val orbitalPeriod: Double
      def calculatedPeriod:Double = orbitalPeriod * 31557600 // 31557600 is secondsInEarthYear
    }

    final case object Mercury extends Planet {
      override val orbitalPeriod: Double = 0.2408467
    }

    final case object Venus extends Planet {
      override val orbitalPeriod: Double = 0.61519726
    }

    final case object Earth extends Planet {
      override val orbitalPeriod: Double = 1.0
    }

    final case object Mars extends Planet {
      override val orbitalPeriod: Double = 1.8808158
    }

    final case object Jupiter extends Planet {
      override val orbitalPeriod: Double = 11.862615
    }

    final case object Saturn extends Planet {
      override val orbitalPeriod: Double = 29.447498
    }

    final case object Uranus extends Planet {
      override val orbitalPeriod: Double = 84.016846
    }

    final case object Neptune extends Planet {
      override val orbitalPeriod: Double = 164.79132
    }

  }

}

/**
 * Too granular separation of concerns -> over engineered
 * Stick to KISS principle
 */
object SpaceAge2 {

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

  object Planets {

    sealed trait Planet {
      def orbitalPeriod: Double = orbitalPeriodViaLambda(this)

      /**
       * Alternatively,
       * However, PartialFunction is suited when combining filter() and map() or refining a type; comment by @LoafingBunny
       */
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

    /**
     * PartialFunction is suited when combining filter() and map() or refining a type; comment by @LoafingBunny
     *
     * @return
     */
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


}


///////////////////////////////////////////////


import scala.language.dynamics

/**
 *  Via Scala's Dynamic Programming (not recommended for it renders compiler help useless)
 */
object SpaceAge1 extends Dynamic { // A Marker trait

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
