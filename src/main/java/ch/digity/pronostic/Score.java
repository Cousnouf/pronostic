package ch.digity.pronostic;

import java.util.Objects;

public class Score {
    final int local;
    final int visitor;

    public Score(int local, int visitor) {
        this.local = local;
        this.visitor = visitor;
    }

    public boolean isSameEndingAs(Score other) {
        int diff = getDiff();
        int otherDiff = other.getDiff();
        return diff == 0 && otherDiff == 0 ||
            diff > 0 && otherDiff > 0 ||
            diff < 0 && otherDiff < 0;
    }

    private int getDiff() {
        return local - visitor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return local == score.local &&
                visitor == score.visitor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(local, visitor);
    }

    @Override
    public String toString() {
        return String.format("%d - %d", local, visitor);
    }
}
