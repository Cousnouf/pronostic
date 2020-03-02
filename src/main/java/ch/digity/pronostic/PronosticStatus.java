package ch.digity.pronostic;

import org.odftoolkit.odfdom.type.Color;

public enum PronosticStatus {
    WRONG(0, Color.RED),
    GOOD_END(1, Color.YELLOW),
    EXACT_SCORE(3, Color.GREEN);

    final int points;
    final Color color;

    PronosticStatus(int points, Color color) {
        this.points = points;
        this.color = color;
    }
}
