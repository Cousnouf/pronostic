package ch.digity.pronostic;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

import com.google.common.base.Stopwatch;

public class Executer {

    private static final int ROW_START_INDEX = 2;

    public String execute(File file, int dayFrom, int dayTo) throws Exception {
        Stopwatch started = Stopwatch.createStarted();
        SpreadsheetDocument document = SpreadsheetDocument.loadDocument(file);
        IntStream.range(1, 10).forEach(document::removeSheet);
        Table sheet = document.getSheetByIndex(0);

        Parser parser = new Parser(sheet);
        parser.parseSheet();

        Calculator calculator = new Calculator(parser);
        calculator.calculate();
        writeRanking(document.appendSheet("Rankings"), calculator.getCurrentRank().positions, 0);

        Statistics lastDayStatistics = calculator.produceStats();
        Statistics otherDay = calculator.produceStats(dayFrom, dayTo);

        writeStatistics(document.appendSheet("Rank evolution last day"), lastDayStatistics);
        writeStatistics(document.appendSheet(String.format("Rank evolution custom days (day %d - day %d)", dayFrom, dayTo)), otherDay);

        Table allRankings = document.appendSheet("All rankings");
        for (int dayUntil = 1; dayUntil <= calculator.dayScores.size(); dayUntil++) {
            Ranking ranking = RankingFactory.create(calculator.dayScores, dayUntil);
            int columnIndex = (dayUntil - 1) * 4;
            allRankings.getRowByIndex(0).getCellByIndex(columnIndex).setStringValue("Day " + dayUntil);
            writeRanking(allRankings, ranking.positions, columnIndex);
        }

        SheetUpdater sheetUpdater = new SheetUpdater(calculator);
        sheetUpdater.update();
        final String newPath = file.getAbsolutePath().replace(".ods", ".done.ods");
        document.save(new File(newPath));

        String message = String.format("Result written in file %s\nTime taken: %s", newPath, started.toString());
        System.out.println(message);
        return message;
    }

    private void writeStatistics(Table rankEvolutionLastDay, Statistics statistics) {
        int index = 1;
        Row row = rankEvolutionLastDay.appendRow();
        row.getCellByIndex(0).setStringValue("Rank");
        row.getCellByIndex(1).setStringValue("Name");
        row.getCellByIndex(2).setStringValue("Points earned");
        row.getCellByIndex(3).setStringValue("Rank evolution");
        for (final Progression progression : statistics.getProgressions()) {
            Row newRow = rankEvolutionLastDay.appendRow();
            newRow.getCellByIndex(0).setStringValue((index++) + "");
            newRow.getCellByIndex(1).setStringValue(progression.player.name);
            newRow.getCellByIndex(2).setStringValue(progression.pointsEarned + "");
            newRow.getCellByIndex(3).setStringValue(progression.ranksEarned + "");
        }
    }

    private void writeRanking(Table table, List<Position> toPositionList, int offset) {
        int index = 1;
        Row row = table.getRowByIndex(ROW_START_INDEX);
        row.getCellByIndex(offset).setStringValue("Rank");
        row.getCellByIndex(offset + 1).setStringValue("Name");
        row.getCellByIndex(offset + 2).setStringValue("Points");
        for (final Position position : toPositionList) {
            Row newRow = table.getRowByIndex(ROW_START_INDEX + index);
            newRow.getCellByIndex(offset).setStringValue((index++) + "");
            newRow.getCellByIndex(offset + 1).setStringValue(position.player.name);
            newRow.getCellByIndex(offset + 2).setStringValue(position.points + "");
        }
    }

    public static void main(String[] args) throws Exception {
        new Executer().execute(new File("/home/xsimci/Downloads/pronostic/results.ods"), 8, 15);
    }
}
