import scala.collection.mutable

object RobotName {

  val random = scala.util.Random

  val letters = 'A' to 'Z'

  class Robot() {

    // LazyList is the 2.13's scala stream. Stream has been deprecated
    var name = LazyList.continually(generateName()).filter(Robot.checkThenStore(_)).take(1).last
    //    var name = Stream.continually(generateName).filter(Robot.checkThenStore(_)).take(1).last

    // ToDo - Perhaps generalize via ControlAbstraction/BehaviorParametrization and utilize default/implicit vals as well?
    def generateName(nL:Int = 2, nD:Int = 3) = LazyList.continually(L).take(nL).concat(LazyList.continually(D).take(nD)).mkString // utilizing LazyLis/Stream

    // another version via Ranges
    def generateName2(nL:Int = 2, nD:Int = 3) = ((0 until nL).map(l => L).mkString).concat((0 until nD).map(d => D).mkString)


    def L = String.valueOf(letters(random.nextInt(letters.size)))

    def D = String.valueOf(random.nextInt(10))

    def reset() = name = generateName()

  }

  /**
   * CompanionObject to maintain the generated RobotNames
   */
  object Robot {
    private val robotNames = mutable.Set[String]()

    /**
     * This is inefficient since collision will worsen as more names are stored in the set.
     *
     * Possible solution:
     * one solution maybe to maintain the left permutations of each slot and let the RandomGenerator to randomly pick from the leftover?
     * each slot will maintain its own list of leftover letters/digits?
     *
     * @param robotName
     * @return
     */
    private def checkThenStore(robotName: String) = robotNames add robotName

  }

}