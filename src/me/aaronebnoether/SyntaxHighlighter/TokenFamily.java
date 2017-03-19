package me.aaronebnoether.SyntaxHighlighter;

public class TokenFamily {
    private String tokenFamily;

    public TokenFamily(String tokenFamily) {
        this.tokenFamily = tokenFamily;
    }

    public String getTokenFamily() {
        return tokenFamily;
    }

    public static boolean isSpecialCharacter(String text) {
        for (TokenType tokenType : TokenType.getTokenTypes()) {
            return tokenType.getTokenFamily().getTokenFamily().equals(text);
        }
        return false;
    }
}
