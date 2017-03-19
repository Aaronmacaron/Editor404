package me.aaronebnoether.SyntaxHighlighter;

public class Token {
    private String value;
    private int startIndex;
    private int endIndex;
    private String tokenType;

    public Token(String value, int startIndex, int endIndex, String tokenType) {
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getTokenType() {
        return tokenType;
    }
}
