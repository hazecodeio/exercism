import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Robot {
    Random random = new Random();
    List<Character> letters = IntStream.rangeClosed('A', 'Z').mapToObj(c -> (char) c).collect(Collectors.toList());

    String name;

    public Robot() {
        name = generateName(2, 3);
    }

    public String getName() {
        return name;
    }

    public void reset() {
        name = generateName(2, 3);
    }

    private String generateName(int nL, int nD) {
        return Stream.concat(
                Stream.generate(this::L).limit(nL),
                Stream.generate(this::D).limit(nD))
                .collect(Collectors.joining());
    }

    private String L() {
        return String.valueOf(letters.get(random.nextInt(letters.size())));
    }

    private String D() {
        return String.valueOf(random.nextInt(10));
    }
}