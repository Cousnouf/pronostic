package ch.digity.pronostic;

import java.util.Date;
import java.util.List;

import org.odftoolkit.simple.table.Row;

public class CompetitionDay {
    final Date date;
    final List<Match> matchList;
    final Row dayPointRow;
    final Row rankingRow;

    public CompetitionDay(Date date, List<Match> matchList, Row dayPointRow, Row rankingRow) {
        this.date = date;
        this.matchList = matchList;
        this.dayPointRow = dayPointRow;
        this.rankingRow = rankingRow;
    }

    @Override
    public String toString() {
        return "CompetitionDay{" +
                "date=" + date +
                ", matchList=" + matchList +
                '}';
    }
}
