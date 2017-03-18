package me.aaronebnoether.SyntaxHighlighter;

import java.util.ArrayList;

public class Word {
    private String text;
    private int start;
    private int end;

    public Word(String text, int start, int end) {
        this.text = text;
        this.start = start;
        this.end = end;
    }

    public static ArrayList<Word> getWords(String text) {
        text += " ";
        String currentWord = "";
        int currentWordStart = 0;
        Character currentChar;
        boolean currentCharIsWhitespace;
        ArrayList<Word> wordList = new ArrayList<>();
        outer:
        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            currentCharIsWhitespace = isWhiteSpace(currentChar);
            while (!currentCharIsWhitespace && text.length() != i) {
                currentWord += currentChar;
                continue outer;
            }
            wordList.add(new Word(currentWord, currentWordStart, i - 1));
            currentWord = "";
            if (currentCharIsWhitespace) {
                currentWordStart = i + 1;
            }
        }
        return wordList;
    }

    private static boolean isWhiteSpace(char c) {
        return (c == ' ') || (c == '\t') || (c == '\r') || (c == '\n') || (c == '\u000B');
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
