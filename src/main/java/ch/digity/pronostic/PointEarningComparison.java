package ch.digity.pronostic;

import java.util.HashMap;
import java.util.Map;

public class PointEarningComparison {
    private final Ranking from;
    private final Ranking to;
    Map<Player, Integer> pointEarning = new HashMap<>();

    public PointEarningComparison(Ranking from, Ranking to) {
        this.from = from;
        this.to = to;
    }

    void computePointEarning() {
        for (final Player player : from.players()) {
            pointEarning.put(player, getPointsEarned(player));
        }
    }

    private Integer getPointsEarned(Player player) {
        return to.pointsOf(player) - from.pointsOf(player);
    }
}
