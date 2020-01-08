package ch.digity.pronostic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

public class CompetitionDayFactory {

    public static CompetitionDay create(Table sheet, Date date, int rowIndex) {
        boolean collectMatches = true;
        List<Match> matchList = new ArrayList<>();
        Row rowByIndex = null;
        while (collectMatches) {
            rowByIndex = sheet.getRowByIndex(rowIndex++);
            Match match = MatchFactory.create(rowByIndex);
            collectMatches = matchList.isEmpty() || match != null;
            if (match != null) {
                matchList.add(match);
            }
        }
        return new CompetitionDay(date, matchList, rowByIndex, rowByIndex.getNextRow());
    }
}
