package ch.digity.pronostic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player {
    final String name;
    final int columnIndex;
    final Map<Match, Pronostic> result = new HashMap<>();

    public Player(String name, int columnIndex) {
        this.name = name;
        this.columnIndex = columnIndex;
    }

    public void put(Match match, Pronostic status) {
        result.put(match, status);
    }

    public Integer getPointsForMatches(CompetitionDay competitionDay) {
        return competitionDay.matchList.stream().map(result::get).filter(Objects::nonNull).mapToInt(ps -> ps.compute().points).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
