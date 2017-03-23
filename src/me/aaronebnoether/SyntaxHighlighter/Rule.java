package me.aaronebnoether.SyntaxHighlighter;

public class Rule {
    private String pattern;
    private String ruleName;

    public Rule(String pattern, String ruleName) {
        this.pattern = pattern;
        this.ruleName = ruleName;
    }

    public String getPattern() {
        return pattern;
    }

    public String getRuleName() {
        return ruleName;
    }
}
