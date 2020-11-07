import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private Set<String> stopCodons = Set.of("UAA", "UAG", "UGA");

    List<String> translate(String rnaSequence) {

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

    private Stream<String> getSlidingStream(char[] chars, int size, int step) {
        return IntStream.range(0, chars.length / step)
                .map(i -> i * step)
                .mapToObj(i -> Arrays.copyOfRange(chars, i, Math.min(i + size, chars.length)))
                .map(cs -> String.valueOf(cs));
    }
}
