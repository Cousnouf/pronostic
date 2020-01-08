package ch.digity.pronostic;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.odftoolkit.simple.table.Cell;

public class DayScoreCounter {
    final List<Player> players;
    final CompetitionDay competitionDay;

    public DayScoreCounter(List<Player> players, CompetitionDay competitionDay) {
        this.players = players;
        this.competitionDay = competitionDay;
    }

    public void computePronostics() {
        for (final Player player : players) {
            computePronostics(player);
        }
    }

    private void computePronostics(Player player) {
        for (final Match match : competitionDay.matchList) {
            Cell pronoCell = match.row.getCellByIndex(player.columnIndex);
            Score pronoScore = ScoreFactory.create(pronoCell.getDisplayText());
            Pronostic pronostic = new Pronostic(match.score, pronoScore);
            player.put(match, pronostic);
        }
    }

    Map<Player, Integer> getPoints() {
        return players.stream().collect(toMap(player -> player, player -> player.getPointsForMatches(competitionDay), (a, b) -> b, LinkedHashMap::new));
    }
}
