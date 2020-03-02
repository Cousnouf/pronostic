package ch.digity.pronostic;

import static ch.digity.pronostic.ScoreFactory.create;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PronosticTest {

    @Test
    public void compute() {
        assertEquals(PronosticStatus.EXACT_SCORE, new Pronostic(create("1-0"), create("1-0")).compute());
        assertEquals(PronosticStatus.WRONG, new Pronostic(create("1-0"), create("irrecevable")).compute());
        assertEquals(PronosticStatus.WRONG, new Pronostic(create("1-0"), create("2-2")).compute());
        assertEquals(PronosticStatus.GOOD_END, new Pronostic(create("1-0"), create("4-0")).compute());
    }
}