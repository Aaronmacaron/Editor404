package me.aaronebnoether.SyntaxHighlighter;

import me.aaronebnoether.SettingsManager;

/**
 * This is a enum which contains all possible highlighting Flags.
 *
 * @author Aaron Ebnöther
 */

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

    /**
     * This block sets properties for some enum values.
     */

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
    private String color = "#FFFFFF"; //The default color is white.
    private Flag expected;

    /**
     * The constructor assigns the passed values to the fields.
     * @param flag is the flag text.
     * @param isStartFlag indicates whether the flag is a start flag or not.
     */

    Flag(String flag, boolean isStartFlag) {
        this.flag = flag;
        this.isStartFlag = isStartFlag;
    }

    /**
     *
     * @return The flag length is always one. If that isn't the case the application doesn't work.
     */

    public static int getFlagLength() {
        return 1; //All Flags have to be one Char long
    }

    /**
     * Getter for isStartFlag.
     * @return This method returns whether the flag is a start flag or not.
     */

    public boolean isStartFlag() {
        return isStartFlag;
    }

    /**
     * This method returns whether this enum contains a flag with the value of flagName.
     *
     * @param flagName is the value that must exist in the enum.
     * @return This method returns whether this enum contains a flag with the value of flagName.
     */

    public static boolean contains(String flagName) {
        for (Flag flag : Flag.values()) { //All values of the enum
            if (flag.flag.equals(flagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method overrides the toString method.
     * @return This method return the flag value of a flag.
     */

    public String toString() {
        return flag;
    }

    /**
     * @return This method returns the color of a flag.
     */

    public String getColor() {
        if (!isStartFlag) {
            return NM_FLAG.getColor(); //When a start flag appears the color should be reset.
        }
        return SettingsManager.get(color);
    }

    /**
     * @param string is the searched string.
     * @return returns the flag whose flag value is equal to the passed string.
     */

    public static Flag getFlagByString(String string) {
        for (Flag flag : Flag.values()) {
            if (flag.flag.equals(string)) {
                return flag;
            }
        }
        return null;
    }

    /**
     * This method is a getter for the expected field.
     *
     * @return This method return the expected flag of a flag.
     */

    public Flag getExpected() {
        return expected;
    }
}
