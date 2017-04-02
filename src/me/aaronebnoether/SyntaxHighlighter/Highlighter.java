package me.aaronebnoether.SyntaxHighlighter;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {

    public static ArrayList<Text> getHighlightedTexts(String text) {
        return getTextsOfHighlightedString(getHighlightedString(text));
    }

    public static ArrayList<Text> getTextsOfHighlightedString(String hlString) {
        ArrayList<Text> texts = new ArrayList<>();
        Flag currentFlag = Flag.START_FLAG;
        for (int i = 0; i < hlString.length(); i++) {
            String flagRange = hlString.substring(i, (i + Flag.getFlagLength() > hlString.length()) ? i : (i + Flag.getFlagLength()));

            if (Flag.contains(flagRange)) {
                Flag flag = Flag.getFlagByString(flagRange);
                if (flag == currentFlag.getExpected() || !currentFlag.isStartFlag()) {
                    currentFlag = flag;
                }
                hlString = new StringBuilder(hlString).delete(i, i + flagRange.length()).toString();
                i--;
                continue;
            }
            Text text = new Text();
            if (i >= hlString.length()) {
                text.setText("");
            } else {
                text.setText(String.valueOf(hlString.charAt(i)));
            }
            text.setFill(Color.web(currentFlag.getColor()));
            texts.add(text);
        }
        return texts;
    }

    public static String getHighlightedString(String text) {
        for (Rule rule : Rule.values()) {
            Pattern pattern = Pattern.compile(rule.getPattern());
            Matcher matcher = pattern.matcher(text);
            int offset = 0;
            while (matcher.find()) {
                StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, rule.getStartFlag());
                offset += rule.getStartFlag().toString().length();
                text = stringBuilder.toString();
                stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, rule.getEndFlag());
                text = stringBuilder.toString();
                offset += Flag.getFlagLength();
            }
        }
        return text;
    }
}
