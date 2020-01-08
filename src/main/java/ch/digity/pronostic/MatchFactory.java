package ch.digity.pronostic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;

public class MatchFactory {

    private static final Pattern MATCH_PATTERN = Pattern.compile("\\s*([\\p{L} _]+)\\s*[–-]\\s*([\\p{L} _]+)\\s*");

    public static Match create(Row row) {
        Cell matchCell = row.getCellByIndex(1);
        Matcher matcher = MATCH_PATTERN.matcher(matchCell.getDisplayText());
        if (!matcher.matches()) {
            return null;
        }
        Cell scoreCell = row.getCellByIndex(2);
        final Score score = ScoreFactory.create(scoreCell.getDisplayText());
        if (score == null) {
            return null;
        }
        return new Match(row, matcher.group(1).trim(), matcher.group(2).trim(), score);
    }
}
