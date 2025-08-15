import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class RnaTranscriptionTest {

    private RnaTranscription rnaTranscription;

    public RnaTranscriptionTest(Function<String, String> strategy) {
        this.rnaTranscription = new RnaTranscription(strategy);
    }

    @Parameters(name ="Strategy -> {0}")
    public static EnumSet<RnaTranscription.StrategyE> getEnums(){
        return EnumSet.allOf(RnaTranscription.StrategyE.class);
    }
    @Test
    public void testEmptyRnaSequence() {
        assertEquals("", rnaTranscription.transcribe(""));
    }

    @Test
    public void testRnaTranscriptionOfCytosineIsGuanine() {
        assertEquals("G", rnaTranscription.transcribe("C"));
    }

    @Test
    public void testRnaTranscriptionOfGuanineIsCytosine() {
        assertEquals("C", rnaTranscription.transcribe("G"));
    }

    @Test
    public void testRnaTranscriptionOfThymineIsAdenine() {
        assertEquals("A", rnaTranscription.transcribe("T"));
    }

    @Test
    public void testRnaTranscriptionOfAdenineIsUracil() {
        assertEquals("U", rnaTranscription.transcribe("A"));
    }

    @Test
    public void testRnaTranscription() {
        assertEquals("UGCACCAGAAUU", rnaTranscription.transcribe("ACGTGGTCTTAA"));
    }

}
