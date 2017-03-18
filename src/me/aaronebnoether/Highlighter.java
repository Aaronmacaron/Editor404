package me.aaronebnoether;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Highlighter {

    public static ArrayList<Text> highlight(ArrayList<Text> texts) {
        ArrayList<Text> returnTexts = new ArrayList<>();
        for (Text text : texts) {
            if (text.getText().equals("public"))
            text.setFill(Color.RED);
            returnTexts.add(text);
        }
        return returnTexts;
    }
}
