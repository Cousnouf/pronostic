package ch.digity.pronostic;

import static ch.digity.pronostic.ScoreFactory.create;
import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {

    @Test
    public void isSameEndingAs() {
        assertTrue(create("2-1").isSameEndingAs(create("1-0")));
        assertTrue(create("1-     0").isSameEndingAs(create("12-3")));
        assertTrue(create("6-     06").isSameEndingAs(create("0 - 0")));
        assertTrue(create("6-8").isSameEndingAs(create("0 - 1")));
        assertTrue(create("1-     5").isSameEndingAs(create("3 - 4")));
    }

    @Test
    public void testEquals() {
        assertEquals(create("2-2"), create("2-2"));
        assertEquals(create("2-0"), create("2-0"));
        assertEquals(create("2-4"), create("2-4"));
    }
}