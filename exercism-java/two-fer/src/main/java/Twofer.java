import java.util.Objects;

public class Twofer {

    public String twofer(String name) {
        if (Objects.isNull(name))
            return String.format(getStatement(), getPronoun());

        return String.format(getStatement(), name);
    }

    String getStatement() {
        return "One for %s, one for me.";
    }

    String getPronoun() {
        return "you";
    }
}

