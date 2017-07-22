package me.aaronebnoether.SyntaxHighlighter;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Highlighter class is responsible for syntax highlighting text.
 *
 * @author Aaron Ebnöther
 */

public class Highlighter {

    /**
     * This method consists only of one statement which makes use of the other methods in this class.
     *
     * @param text is the String that should be highlighted.
     * @return This method returns a list of JavaFX Text objects that are highlighted.
     */

    public static ArrayList<Text> getHighlightedTexts(String text) {
        return getTextsOfHighlightedString(getHighlightedString(text));
    }

    /**
     * This method processes and highlights a String that contains highlighting flags.
     *
     * @param hlString is the String that already contains the highlighting flags.
     * @return This method returns a list of JavaFX Text objects that are highlighted.
     */

    private static ArrayList<Text> getTextsOfHighlightedString(String hlString) {
        ArrayList<Text> texts = new ArrayList<>();
        Flag currentFlag = Flag.START_FLAG; //The first flag has to be a start flag.

        //This loop iterates through all chars of the hlString
        for (int i = 0; i < hlString.length(); i++) {

            //flagRange is the range in which a flag could possibly be. The length of the flagRange depends on the flag length.
            String flagRange = hlString.substring(i, (i + Flag.getFlagLength() > hlString.length()) ? i : (i + Flag.getFlagLength()));

            if (Flag.contains(flagRange)) { //If the current flagRange is a flag.
                Flag flag = Flag.getFlagByString(flagRange);
                if (flag == currentFlag.getExpected() || !currentFlag.isStartFlag()) { //If the current flag is expected or if it's the first flag.
                    currentFlag = flag;  //Set the new current flag
                }
                hlString = new StringBuilder(hlString).delete(i, i + flagRange.length()).toString(); //Remove the flag out of the string
                i--; //Decrement i so that the next char is not skipped.
                continue; //If the current char is a flag continue because it's not necessary to create a Text object.
            }
            Text text = new Text();
            if (i >= hlString.length()) { // Check if is last char in hlString to prevent ArrayOutOfBound Exception
                text.setText("");
            } else {
                text.setText(String.valueOf(hlString.charAt(i))); // Set content of text object
            }
            text.setFill(Color.web(currentFlag.getColor())); // Set color of Text
            texts.add(text);
        }
        return texts;
    }

    /**
     * This method iterates through all rules and creates a string with highlight flags.
     *
     * @param text is the to be highlighted string.
     * @return This method returns a String with highlighting flags.
     */

    private static String getHighlightedString(String text) {
        for (Rule rule : Rule.values()) { // Iterate through all rules
            Pattern pattern = Pattern.compile(rule.getPattern());
            Matcher matcher = pattern.matcher(text); //Find all matches of rule
            int offset = 0;
            while (matcher.find()) { // Iterate through all matches of rule
                StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, rule.getStartFlag()); //Insert corresponding end flag.
                offset += rule.getStartFlag().toString().length();
                text = stringBuilder.toString();
                stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, rule.getEndFlag()); //Insert corresponding end flag.
                text = stringBuilder.toString();
                offset += Flag.getFlagLength();
            }
        }
        return text;
    }
}
