package me.aaronebnoether.SyntaxHighlighter;

public enum Flag {
    KW_FLAG("\uE010", true, "CC7832"),
    KW_END_FLAG("\uE011", false, null),
    ST_FLAG("\uE012", true, "6A8759"),
    ST_END_FLAG("\uE013", false, null),
    NM_FLAG("\uE014", true, "AAAAAA"),
    CO_FLAG("\uE015", true, "629755"),
    CO_END_FLAG("\uE016", false, null),
    SC_FLAG("\uE017", true, "AAAAAA"),
    SC_END_FLAG("\uE018", false, null);

    private String flag;
    private boolean isStartFlag;
    private String color;

    private Flag(String flag, boolean isStartFlag, String color) {
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

}
