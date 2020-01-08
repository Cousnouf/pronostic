package ch.digity.pronostic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranking {

    List<Position> positions;
    private final Map<Player, Integer> playerPoints;

    public Ranking(List<Position> positions, Map<Player, Integer> playerPoints) {
        this.positions = positions;
        this.playerPoints = playerPoints;
    }

    List<Player> players() {
        return positions.stream().map(r -> r.player).collect(Collectors.toList());
    }

    public Integer rankOf(Player player) {
        return rankOf(player.name);
    }

    public Integer rankOf(String playerName) {
        Position position = positions.stream().filter(p -> p.player.name.equals(playerName)).findFirst().orElse(null);
        if (position == null) {
            return -1;
        }
        return positions.indexOf(position) + 1;
    }

    public Integer pointsOf(Player player) {
        return playerPoints.get(player);
    }
}
