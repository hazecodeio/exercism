import java.util.ArrayList;
import java.util.List;

//ToDo - TBC
public class Say {
    public static void main(String[] args) {
        long n = 12345;
        String s = String.valueOf(n);
        List<String> l = groupNumber(n);
        System.out.println(l);

    }

    private static List<String> groupNumber(long n) {
        String s = String.valueOf(n);
        int msd = s.length() % 3;
        List<String> grouped = new ArrayList<>();

        if (msd != 0)
            grouped.add(s.substring(0, msd));

        for (int i = msd; i < Math.min(i + 3, s.length()); i += 3)
            grouped.add(s.substring(i, i + 3));

        return grouped;
    }

    public String say(long n) {
        return null;
    }

    enum N {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN,
        TWENTY, THIRTY, FORTY, FIFTY, SIXTY, SEVENTY, EIGHTY, NINETY
    }

    enum N2 {
        HUNDRED,
        THOUSAND,
        MILLION,
        TRILLION
    }
}
