package ch.digity.pronostic;

public class Statistics {

    final RankingComparison rankingComparison;
    final PointEarningComparison pointsEarningComparison;

    public Statistics(RankingComparison rankingComparison, PointEarningComparison pointsEarningComparison) {
        this.rankingComparison = rankingComparison;
        this.pointsEarningComparison = pointsEarningComparison;
    }
}
