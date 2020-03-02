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
            return PronosticStatus.WRONG;
        }
        if (result.equals(wanted)) {
            return PronosticStatus.EXACT_SCORE;
        } else if (result.isSameEndingAs(wanted)) {
            return PronosticStatus.GOOD_END;
        }
        return PronosticStatus.WRONG;
    }
}
