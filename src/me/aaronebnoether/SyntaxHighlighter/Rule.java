package me.aaronebnoether.SyntaxHighlighter;

public enum Rule {

    KW("\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue" +
            "|default|do|double|else|enmu|extends|final|finally|float|for|goto|if|implements|import|instanceof|int" +
            "|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch" +
            "|synchronized|this|throw|throws|transient|try|void|volatile|while|false|null|true)\\b", Flag.KW_FLAG, Flag.KW_END_FLAG),
    ST("\"([^\\\"]+)*\"|'[^\\\"]+'", Flag.ST_FLAG, Flag.ST_END_FLAG),
    CO("(\\/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+\\/)|(\\/\\/.*)", Flag.CO_FLAG, Flag.CO_END_FLAG),
    AN("@.+", Flag.AN_FLAG, Flag.AN_END_FLAG),
    SC("\\+|-|\\*|\\/|=|<|>|\\.|\\||\\(|\\)|\\{|\\}|\\;|\\,|:", Flag.SC_FLAG, Flag.SC_END_FLAG);

    private String pattern;
    private Flag startFlag;
    private Flag endFlag;

    Rule(String pattern, Flag startFlag, Flag endFlag) {
        this.pattern = pattern;
        this.startFlag = startFlag;
        this.endFlag = endFlag;
    }

    public String getPattern() {
        return pattern;
    }

    public Flag getStartFlag() {
        return startFlag;
    }

    public Flag getEndFlag() {
        return endFlag;
    }
}
