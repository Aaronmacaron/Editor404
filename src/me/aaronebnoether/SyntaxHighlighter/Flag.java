package me.aaronebnoether.SyntaxHighlighter;

public enum Flag {
    KW_FLAG("\uE010", true, "CC7832"),
    KW_END_FLAG("\uE011", false, null),
    ST_FLAG("\uE012", true, "6A8759"),
    ST_END_FLAG("\uE013", false, null),
    CO_FLAG("\uE015", true, "629755"),
    CO_END_FLAG("\uE016", false, null),
    SC_FLAG("\uE017", true, "70637A"),
    SC_END_FLAG("\uE018", false, null),
    AN_FLAG("\uE020", true, "BBB529"),
    AN_END_FLAG("\uE021", false, null),
    DI_FLAG("\uE022", true, "6897BB"),
    DI_END_FLAG("\uE023", false, null),
    NM_FLAG("\uE014", true, "AAAAAA"),
    START_FLAG("\uE019", false, null);

    static {
        KW_FLAG.expected = KW_END_FLAG;
        ST_FLAG.expected = ST_END_FLAG;
        CO_FLAG.expected = CO_END_FLAG;
        SC_FLAG.expected = SC_END_FLAG;
        AN_FLAG.expected = AN_END_FLAG;
        DI_FLAG.expected = DI_END_FLAG;
    }


    private String flag;
    private boolean isStartFlag;
    private String color;
    private Flag expected;

    Flag(String flag, boolean isStartFlag, String color) {
        this.flag = flag;
        this.isStartFlag = isStartFlag;
        this.color = color;
    }

    public static int getFlagLength() {
        return 1; //All Flags have to be one Char long
    }

    public boolean isStartFlag() {
        return isStartFlag;
    }

    public static boolean contains(String flagName) {
        for (Flag flag : Flag.values()) {
            if (flag.flag.equals(flagName)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return flag;
    }

    public String getColor() {
        if (!isStartFlag) {
            return NM_FLAG.getColor();
        }
        return color;
    }

    public static Flag getFlagByString(String string) {
        for (Flag flag : Flag.values()) {
            if (flag.flag.equals(string)) {
                return flag;
            }
        }
        return null;
    }

    public Flag getExpected() {
        return expected;
    }
}
