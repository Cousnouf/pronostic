package ch.digity.pronostic;

public class Position {
    final Player player;
    final Integer points;

    public Position(Player player, Integer points) {
        this.player = player;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Position{" +
                "player=" + player +
                ", points=" + points +
                '}';
    }
}
