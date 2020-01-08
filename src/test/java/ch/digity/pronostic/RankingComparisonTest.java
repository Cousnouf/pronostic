package ch.digity.pronostic;

import static ch.digity.pronostic.RankingTest.DAVID;
import static ch.digity.pronostic.RankingTest.MARC;
import static ch.digity.pronostic.RankingTest.ROMAIN;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RankingComparisonTest {

    private static final Player DENEG = new Player("DENEG", 29);

    private Ranking from = new Ranking(List.of(
            new Position(MARC, 10),
            new Position(DAVID, 8),
            new Position(DENEG, 7),
            new Position(ROMAIN, 6)
    ), Map.of());

    private Ranking to = new Ranking(List.of(
            new Position(DAVID, 28),
            new Position(ROMAIN, 16),
            new Position(DENEG, 13),
            new Position(MARC, 12)
    ), Map.of());
    private RankingComparison rankingComparison = new RankingComparison(from, to);

    @Test
    public void computeProgression() {
        rankingComparison.computeProgression();
        assertEquals(Integer.valueOf(-3), rankingComparison.progression.get(MARC));
        assertEquals(Integer.valueOf(2), rankingComparison.progression.get(ROMAIN));
        assertEquals(Integer.valueOf(1), rankingComparison.progression.get(DAVID));
        assertEquals(Integer.valueOf(0), rankingComparison.progression.get(DENEG));
    }
}