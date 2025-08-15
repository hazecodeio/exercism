import java.util.List;

class ResistorColor {
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

    int colorCode(String color) {
        return COLORS.indexOf(color);
    }

    String[] colors() {
        return COLORS.toArray(new String[]{});
    }
}
