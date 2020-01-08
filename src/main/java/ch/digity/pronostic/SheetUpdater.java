package ch.digity.pronostic;

import java.util.Map;

import org.odftoolkit.simple.table.Cell;

public class SheetUpdater {
    private final Calculator calculator;

    public SheetUpdater(Calculator calculator) {
        this.calculator = calculator;
    }

    public void update() {
        for (final Player player : calculator.parser.players) {
            updateSheetFor(player);
        }
    }

    private void updateSheetFor(Player player) {
        for (final Map.Entry<Match, Pronostic> entry : player.result.entrySet()) {
            Cell cellByIndex = entry.getKey().row.getCellByIndex(player.columnIndex);
            cellByIndex.setCellBackgroundColor(entry.getValue().compute().color);
        }
        for (final DayScoreCounter dayScore : calculator.dayScores) {
            Cell dayPointsCell = dayScore.competitionDay.dayPointRow.getCellByIndex(player.columnIndex);
            dayPointsCell.setStringValue(dayScore.getPoints().get(player) + "pts");

            Ranking ranking = RankingFactory.create(calculator.dayScores, calculator.dayScores.indexOf(dayScore) + 1);
            Cell rankingRowCell = dayScore.competitionDay.rankingRow.getCellByIndex(player.columnIndex);
            rankingRowCell.setStringValue(String.format("%d pts (%d)", ranking.pointsOf(player), ranking.rankOf(player)));
        }
    }
}
