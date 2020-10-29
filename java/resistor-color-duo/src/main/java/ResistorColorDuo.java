import java.util.List;

class ResistorColorDuo {

    private static List<String> COLORS = List.of(
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "violet",
            "grey",
            "white"
    );

    int value(String[] colors) {
        return Integer.valueOf(COLORS.indexOf(colors[0]) + "" + COLORS.indexOf(colors[1]));
    }
}
