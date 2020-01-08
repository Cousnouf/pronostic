package ch.digity.pronostic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

public class Parser {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private final Table sheet;
    final List<CompetitionDay> days = new ArrayList<>();
    List<Player> players = new ArrayList<>();

    public Parser(Table sheet) {
        this.sheet = sheet;
    }

    public void parseSheet(String maxUntil) {
        for (final Row row : sheet.getRowList()) {
            String displayText = row.getCellByIndex(0).getDisplayText();
            Optional<Date> dateFromText = getDateFromText(displayText);
            dateFromText.ifPresent(date -> days.add(CompetitionDayFactory.create(sheet, date, row.getRowIndex())));
            if (displayText.equals(maxUntil)) {
                break;
            }
        }

        Row firstNameRow = getFirstNameRow();
        boolean collectPlayers = true;
        int index = 4;
        while (collectPlayers) {
            Cell cellByIndex = firstNameRow.getCellByIndex(index);
            String playerName = cellByIndex.getDisplayText();
            if (StringUtils.isNotBlank(playerName)) {
                players.add(new Player(playerName, index++));
            } else {
                collectPlayers = false;
            }
        }
    }

    private Row getFirstNameRow() {
        return sheet.getRowList().stream()
                .filter(row -> "DATES".equals(row.getCellByIndex(0).getDisplayText().trim()))
                .findFirst().orElse(null);
    }

    private Optional<Date> getDateFromText(String displayText) {
        try {
            Date date = SIMPLE_DATE_FORMAT.parse(displayText);
            return Optional.of(date);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
