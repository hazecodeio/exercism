import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class RnaTranscription {

    static Map<String, String> RNA_STRANDS = Map.of(
            "G", "C",
            "C", "G",
            "T", "A",
            "A", "U"

    );
    Function<String, String> strategy;

    public RnaTranscription() {
        strategy = StrategyE.LOOP;
    }

    public RnaTranscription(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    String transcribe(String dnaStrand) {
        return strategy.apply(dnaStrand);
    }

    enum StrategyE implements Function<String, String> {
        LOOP {
            @Override
            public String apply(String dnaStrand) {
                if (dnaStrand.isBlank())
                    return "";

                StringBuilder sb = new StringBuilder();
                for (String s : dnaStrand.split(""))
                    sb.append(RNA_STRANDS.get(s));
                return sb.toString();
            }
        },
        STREAM {
            @Override
            public String apply(String dnaStrand) {
                if (dnaStrand.isBlank())
                    return "";
                return dnaStrand.chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .map(s -> RNA_STRANDS.get(s))
                        .collect(Collectors.joining());
            }
        }
    }

}
