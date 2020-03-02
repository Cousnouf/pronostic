package ch.digity.pronostic;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    final RankingComparison rankingComparison;
    final PointEarningComparison pointsEarningComparison;

    public Statistics(RankingComparison rankingComparison, PointEarningComparison pointsEarningComparison) {
        this.rankingComparison = rankingComparison;
        this.pointsEarningComparison = pointsEarningComparison;
    }

    public List<Progression> getProgressions() {
        final List<Progression> result = new ArrayList<>();
        for (final Position position : this.rankingComparison.toPositionList()) {
            result.add(new Progression(position.player, position.points, pointsEarningComparison.pointEarning.get(position.player)));
        }
        return result;
    }
}
