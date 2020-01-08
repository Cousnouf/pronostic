package ch.digity.pronostic;

import org.odftoolkit.odfdom.type.Color;

public enum PronosticStatus {
    LOSE(0, Color.RED),
    SEMI_WIN(1, Color.YELLOW),
    WIN(3, Color.GREEN);

    final int points;
    final Color color;

    PronosticStatus(int points, Color color) {
        this.points = points;
        this.color = color;
    }
}
