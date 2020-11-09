import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ProteinTranslator {

    private static Map<Set<String>, String> aminos = Map.of(
            Set.of("AUG"), "Methionine",
            Set.of("UUU", "UUC"), "Phenylalanine",
            Set.of("UUA", "UUG"), "Leucine",
            Set.of("UCU", "UCC", "UCA", "UCG"), "Serine",
            Set.of("UAU", "UAC"), "Tyrosine",
            Set.of("UGU", "UGC"), "Cysteine",
            Set.of("UGG"), "Tryptophan"
    );
    private static Set<String> stopCodons = Set.of("UAA", "UAG", "UGA");

    Function<String, List<String>> strategy;

    public ProteinTranslator() {
        strategy = StrategyE.FLATTENED_EARLY;
    }

    public ProteinTranslator(Function<String, List<String>> strategy) {
        this.strategy = strategy;
    }

    List<String> translate(String rnaSequence) {
        return strategy.apply(rnaSequence);
    }

    enum StrategyE implements Function<String, List<String>> {

        FLATTENED_LATER {
            @Override
            public List<String> apply(String rnaSequence) {
                char[] chars = rnaSequence.toCharArray();

                Stream<String> slidingInputStream = getSlidingStream(chars, 3, 3);

                return slidingInputStream
                        .takeWhile(s -> !stopCodons.contains(s))
                        .flatMap(codon -> aminos.keySet()
                                .stream()
                                .filter(codonKey -> codonKey.contains(codon))
                                .map(codonKey -> aminos.get(codonKey)))
                        .collect(Collectors.toList());
            }
        },
        FLATTENED_EARLY {
            Map<String, String> flattenedMap = aminos.entrySet()
                    .stream()
                    .flatMap(entry -> entry.getKey().stream().map(key -> Map.entry(key, entry.getValue())))
                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

            @Override
            public List<String> apply(String rnaSequence) {
                char[] chars = rnaSequence.toCharArray();

                Stream<String> slidingInputStream = getSlidingStream(chars, 3, 3);

                return slidingInputStream
                        .takeWhile(s -> !stopCodons.contains(s))
                        .filter(s -> flattenedMap.containsKey(s))
                        .map(codonKey -> flattenedMap.get(codonKey))
                        .collect(Collectors.toList());

            }
        };

        private static Stream<String> getSlidingStream(char[] chars, int size, int step) {
            return IntStream.range(0, (int) Math.ceil(chars.length / (double)step))
                    .map(i -> i * step)
                    .mapToObj(i -> Arrays.copyOfRange(chars, i, Math.min(i + size, chars.length)))
                    .map(cs -> String.valueOf(cs));
        }
    }
}
