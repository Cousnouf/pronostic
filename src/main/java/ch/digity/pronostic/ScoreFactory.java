package ch.digity.pronostic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreFactory {
    private static final Pattern SCORE_PATTERN = Pattern.compile("\\s*(\\d+)\\s*[–-]\\s*(\\d+)\\s*");
    public static Score create(String scoreCellDisplayText) {

        Matcher scoreMatcher = SCORE_PATTERN.matcher(scoreCellDisplayText);
        if (!scoreMatcher.matches()) {
            return null;
        }
        return new Score(Integer.parseInt(scoreMatcher.group(1)), Integer.parseInt(scoreMatcher.group(2)));
    }
}
