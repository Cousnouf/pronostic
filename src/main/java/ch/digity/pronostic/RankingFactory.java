package ch.digity.pronostic;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingFactory {

    public static Ranking create(List<DayScoreCounter> dayScores, int dayUntil) {
        Map<Player, Integer> sumMap = dayScores.stream()
                .limit(dayUntil)
                .flatMap(ds -> ds.getPoints().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));

        return new Ranking(mapToPosition(sumMap), sumMap);
    }

    static List<Position> mapToPosition(Map<Player, Integer> playerIntegerMap) {
        List<Position> positions = playerIntegerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> new Position(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        Collections.reverse(positions);
        return positions;
    }
}
