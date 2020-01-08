package ch.digity.pronostic;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RankingTest {

    static final Player MARC = new Player("MARC", 5);
    static final Player DAVID = new Player("DAVID", 6);
    static final Player ROMAIN = new Player("ROMAIN", 7);

    private Ranking ranking = new Ranking(List.of(
            new Position(MARC, 10),
            new Position(DAVID, 8),
            new Position(ROMAIN, 6)
    ), Map.of());

    @Test
    public void players() {
        assertEquals(MARC, ranking.players().get(0));
        assertEquals(DAVID, ranking.players().get(1));
        assertEquals(ROMAIN, ranking.players().get(2));
    }

    @Test
    public void rankOf() {
        assertEquals(2, ranking.rankOf(DAVID).intValue());
    }

    @Test
    public void testRankOf() {
        assertEquals(3, ranking.rankOf("ROMAIN").intValue());
    }
}