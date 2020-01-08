package ch.digity.pronostic;

import static ch.digity.pronostic.RankingFactory.mapToPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingComparison {
    private final Ranking from;
    private final Ranking to;

    final Map<Player, Integer> progression = new HashMap<>();

    public RankingComparison(Ranking from, Ranking to) {
        this.from = from;
        this.to = to;
    }

    List<Position> toPositionList() {
        return mapToPosition(progression);
    }

    void computeProgression() {
        for (final Player player : from.players()) {
            progression.put(player, getProgression(player));
        }
    }

    private Integer getProgression(Player player) {
        return from.rankOf(player) - to.rankOf(player);
    }
}
