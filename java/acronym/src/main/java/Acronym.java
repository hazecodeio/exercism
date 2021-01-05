import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String[] split = phrase.split("[^a-zA-Z']");
        StringBuilder acronymBuilder = new StringBuilder();
        for (String s : split) {
            if (s.isBlank())
                continue;
            acronymBuilder.append(Character.toUpperCase(s.charAt(0)));
        }

        return acronymBuilder.toString();

        // Via Streams
        //return Arrays.stream(split).filter(str -> !str.isBlank()).map(str -> String.valueOf(str.charAt(0)).toUpperCase()).collect(Collectors.joining());
    }

}
