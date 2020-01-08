package ch.digity.pronostic;

import static ch.digity.pronostic.ScoreFactory.create;
import static org.junit.Assert.*;

import org.junit.Test;

public class PronosticTest {

    @Test
    public void compute() {
        assertEquals(PronosticStatus.WIN, new Pronostic(create("1-0"), create("1-0")).compute());
        assertEquals(PronosticStatus.LOSE, new Pronostic(create("1-0"), create("irrecevable")).compute());
        assertEquals(PronosticStatus.LOSE, new Pronostic(create("1-0"), create("2-2")).compute());
        assertEquals(PronosticStatus.SEMI_WIN, new Pronostic(create("1-0"), create("4-0")).compute());
    }
}