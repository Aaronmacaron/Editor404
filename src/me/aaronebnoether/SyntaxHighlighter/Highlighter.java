package me.aaronebnoether.SyntaxHighlighter;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Highlighter {

    public static ArrayList<Text> highlight(String text) {
        ArrayList<Word> words = Word.getWords(text);

        ArrayList<Text> returnTexts = new ArrayList<>();
        for (Word word : words) {
            returnTexts.add(new Text(word.getText()));
        }
        return returnTexts;
    }
}
