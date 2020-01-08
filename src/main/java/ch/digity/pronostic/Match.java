package ch.digity.pronostic;

import org.odftoolkit.simple.table.Row;

public class Match {
    final Row row;
    final String local;
    final String visitor;
    final Score score;

    public Match(Row row, String local, String visitor, Score score) {
        this.row = row;
        this.local = local;
        this.visitor = visitor;
        this.score = score;
    }

    @Override
    public String toString() {
        return local + " - " + visitor + " " + score.toString();
    }
}
