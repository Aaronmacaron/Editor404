package me.aaronebnoether.SyntaxHighlighter;

import java.util.ArrayList;

public class TokenType {
    private String tokenType;
    private TokenFamily tokenFamily;

    public TokenType(String tokenType, TokenFamily tokenFamily) {
        this.tokenType = tokenType;
        this.tokenFamily = tokenFamily;
    }

    public static ArrayList<TokenType> getTokenTypes(){
        ArrayList<TokenType> returnList = new ArrayList<>();
        returnList.add(new TokenType("T_PUBLIC", new TokenFamily("KEYWORD")));
        return returnList;
    }

    public String getTokenType() {
        return tokenType;
    }

    public TokenFamily getTokenFamily() {
        return tokenFamily;
    }
}
