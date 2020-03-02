package ch.digity.pronostic;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    final Parser parser;
    final List<DayScoreCounter> dayScores = new ArrayList<>();

    public Calculator(Parser parser) {
        this.parser = parser;
    }

    public void calculate() {
        for (final CompetitionDay competitionDay : parser.days) {
            DayScoreCounter dayScoreCounter = new DayScoreCounter(parser.players, competitionDay);
            dayScoreCounter.computePronostics();
            dayScores.add(dayScoreCounter);
        }
    }

    public Ranking getCurrentRank() {
        return RankingFactory.create(dayScores, dayScores.size());
    }

    public Statistics produceStats() {
        return produceStats(dayScores.size() - 1, dayScores.size());
    }

    public Statistics produceStats(int dayFrom, int dayTo) {
        if (dayScores.isEmpty()) {
            return null;
        }
        Ranking actualRanking = RankingFactory.create(dayScores, dayTo);
        Ranking previousRanking = RankingFactory.create(dayScores, dayFrom);

        RankingComparison rankingComparison = new RankingComparison(previousRanking, actualRanking);
        rankingComparison.computeProgression();

        PointEarningComparison pointsEarningComparison = new PointEarningComparison(previousRanking, actualRanking);
        pointsEarningComparison.computePointEarning();

        return new Statistics(rankingComparison, pointsEarningComparison);
    }
}
