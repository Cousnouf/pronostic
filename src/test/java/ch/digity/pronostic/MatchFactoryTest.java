package ch.digity.pronostic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Answers;
import org.odftoolkit.simple.table.Row;

public class MatchFactoryTest {

    @Test
    public void create() {
        final Row row = getRow("Russie – Arabie Saoudite", " 5-0");

        Match match = MatchFactory.create(row);

        assertNotNull(match);
        assertEquals("Russie", match.local);
        assertEquals("Arabie Saoudite", match.visitor);
        assertEquals(5, match.score.local);
        assertEquals(0, match.score.visitor);
    }

    @Test
    public void create_other() {
        final Row row = getRow("    Suède – République de Corée    ", " 1                    -0");

        Match match = MatchFactory.create(row);

        assertNotNull(match);
        assertEquals("Suède", match.local);
        assertEquals("République de Corée", match.visitor);
        assertEquals(1, match.score.local);
        assertEquals(0, match.score.visitor);
    }

    private Row getRow(String matchString, String scoreString) {
        final Row row = mock(Row.class, Answers.RETURNS_DEEP_STUBS);
        when(row.getCellByIndex(1).getDisplayText()).thenReturn(matchString);
        when(row.getCellByIndex(2).getDisplayText()).thenReturn(scoreString);
        return row;
    }
}