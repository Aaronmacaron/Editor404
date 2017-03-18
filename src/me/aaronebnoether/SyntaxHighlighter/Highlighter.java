package me.aaronebnoether;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Highlighter {

    public static ArrayList<Text> highlight(String text) {
        ArrayList<Text> returnTexts = new ArrayList<>();
        for (Text text : texts) {
            if (text.getText().equals("public"))
            text.setFill(Color.RED);
            returnTexts.add(text);
        }
        return returnTexts;
    }

    private static void getWords(String text) {

    }
}
