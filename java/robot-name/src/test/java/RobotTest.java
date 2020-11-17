import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotTest {

    private static final String EXPECTED_ROBOT_NAME_PATTERN = "[A-Z]{2}\\d{3}";
    private Robot robot;

    private static void assertIsValidName(String name) {
        assertThat(name).matches(EXPECTED_ROBOT_NAME_PATTERN);
    }

    @Before
    public void setUp() {
        robot = new Robot();
    }

    @Test
    public void hasName() {
        assertIsValidName(robot.getName());
    }

    @Test
    public void differentRobotsHaveDifferentNames() {
        assertThat(robot.getName()).isNotEqualTo(new Robot().getName());
    }

    @Test
    public void resetName() {
        final String name = robot.getName();
        robot.reset();
        final String name2 = robot.getName();
        assertThat(name).isNotEqualTo(name2);
        assertIsValidName(name2);
    }

    // Making this test pass is an optional extra exercise, should you want more of a challenge.
    // There are 26^2 * 1,000 = 676,000 possible robot names - you have to ensure that none are repeated.
    // The Robot code needs to be efficient enough to allow all 676,000 unique names to be generated.
    @Test
    public void allRobotsMustHaveUniqueNames() {
        Set<String> alreadySet = new HashSet<>();
        for(int i : IntStream.rangeClosed(0, 67600).toArray()) {
            String name = new Robot().getName();
            if (alreadySet.contains(name)) {
                throw new IllegalArgumentException(String.format("%s is repeated", name));
            }
            alreadySet.add(name);
        }
    }
}
