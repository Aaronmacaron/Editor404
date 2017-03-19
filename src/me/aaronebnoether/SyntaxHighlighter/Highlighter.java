package me.aaronebnoether.SyntaxHighlighter;

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

    private ArrayList<Text> getHighlightedTexts() {

    }

    private ArrayList<Token> getTokens(ArrayList<Word> words) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        for (Word word : words) {
            for (int i = 0; i < word.getText().length(); i++) {
                if (TokenFamily.isSpecialCharacter(String.valueOf(word.getText().charAt(i)))) {
                    tokens.add(new Token(String.valueOf(word.getText().charAt(i)), new TokenType()));
                }
            }
        }
    }
}
