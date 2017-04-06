package me.aaronebnoether.SyntaxHighlighter;

import me.aaronebnoether.SettingsManager;

import java.util.Set;

public enum Flag {
    KW_FLAG("\uE010", true),
    KW_END_FLAG("\uE011", false),
    ST_FLAG("\uE012", true),
    ST_END_FLAG("\uE013", false),
    CO_FLAG("\uE015", true),
    CO_END_FLAG("\uE016", false),
    SC_FLAG("\uE017", true),
    SC_END_FLAG("\uE018", false),
    AN_FLAG("\uE020", true),
    AN_END_FLAG("\uE021", false),
    DI_FLAG("\uE022", true),
    DI_END_FLAG("\uE023", false),
    NM_FLAG("\uE014", true),
    START_FLAG("\uE019", false);

    static {
        KW_FLAG.expected = KW_END_FLAG;
        ST_FLAG.expected = ST_END_FLAG;
        CO_FLAG.expected = CO_END_FLAG;
        SC_FLAG.expected = SC_END_FLAG;
        AN_FLAG.expected = AN_END_FLAG;
        DI_FLAG.expected = DI_END_FLAG;

        KW_FLAG.color = "kwColor";
        ST_FLAG.color = "stColor";
        CO_FLAG.color = "coColor";
        SC_FLAG.color = "scColor";
        AN_FLAG.color = "anColor";
        DI_FLAG.color = "diColor";
        NM_FLAG.color = "nmColor";
    }


    private String flag;
    private boolean isStartFlag;
    private String color = "#FFFFFF";
    private Flag expected;

    Flag(String flag, boolean isStartFlag) {
        this.flag = flag;
        this.isStartFlag = isStartFlag;
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
        return SettingsManager.get(color);
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
