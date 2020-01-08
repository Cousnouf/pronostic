package ch.digity.pronostic;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.BooleanUtils;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import com.google.common.base.Stopwatch;

public class Main {

    public static void main(String[] args) throws Exception {
        Stopwatch started = Stopwatch.createStarted();

        Option odsFile = new Option("f", "file", true, "Chemin d'accès vers le fichier ODS");
        odsFile.setRequired(true);
        Option untilDate = new Option("d", "date", true, "Date jusqu'à laquelle les calculs sont faits");
        Option updateSheet = new Option("u", "update", false, "Met à jour le fichier ODS ou non");
        Options options = new Options();
        options.addOption(odsFile);
        options.addOption(untilDate);
        options.addOption(updateSheet);

        DefaultParser defaultParser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = defaultParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("Pronostic calculator", options);
            return;
        }
        File file = new File(commandLine.getOptionValue("file"));
        SpreadsheetDocument document = SpreadsheetDocument.loadDocument(file);
        Table sheet = document.getSheetByIndex(0);

        Parser parser = new Parser(sheet);
        String maxUntil = commandLine.getOptionValue("date");
        parser.parseSheet(maxUntil);

        Calculator calculator = new Calculator(parser);
        calculator.calculate();

        String update = commandLine.getOptionValue("u");
        if (BooleanUtils.isTrue(Boolean.valueOf(update))) {
            SheetUpdater sheetUpdater = new SheetUpdater(calculator);
            sheetUpdater.update();
            document.save(file);
        }

        Statistics statistics = calculator.produceStats();
        Statistics otherDay = calculator.produceStats(0, 1);
        System.out.println("Time taken: " + started);
    }
}
