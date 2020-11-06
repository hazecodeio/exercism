import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class RaindropConverter {

    private static Map<Integer, String> factors = new TreeMap<>(
            Map.of(
                    3, "Pling",
                    5, "Plang",
                    7, "Plong"
            ));

    String convert(int number) {
        String collect = factors.entrySet().stream().filter(e -> number % e.getKey() == 0).map(e -> e.getValue()).collect(Collectors.joining());
        if (collect.isBlank())
            return String.valueOf(number);
        return collect;
    }

}
