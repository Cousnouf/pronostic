package ch.digity.pronostic;

public class Progression {
    final Player player;
    final Integer ranksEarned;
    final Integer pointsEarned;

    public Progression(Player player, Integer ranksEarned, Integer pointsEarned) {
        this.player = player;
        this.ranksEarned = ranksEarned;
        this.pointsEarned = pointsEarned;
    }
}
