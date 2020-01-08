package ch.digity.pronostic;

public class Pronostic {
    final Score result;
    private final Score wanted;

    public Pronostic(Score result, Score wanted) {
        this.result = result;
        this.wanted = wanted;
    }

    PronosticStatus compute() {
        if (wanted == null) {
            return PronosticStatus.LOSE;
        }
        if (result.equals(wanted)) {
            return PronosticStatus.WIN;
        } else if (result.isSameEndingAs(wanted)) {
            return PronosticStatus.SEMI_WIN;
        }
        return PronosticStatus.LOSE;
    }
}
