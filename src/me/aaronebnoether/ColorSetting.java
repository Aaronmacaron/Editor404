package me.aaronebnoether;

public enum ColorSetting {
    KW("Keyword Color: ", "kwColor"),
    ST("String Color: ", "stColor"),
    CO("Comment Color: ", "coColor"),
    SC("Special Character Color: ", "scColor"),
    AN("Annotation Color: ", "anColor"),
    DI("Digit Color", "diColor"),
    NM("Normal Color: ", "nmColor");

    private String displayText;
    private String settingsKey;

    ColorSetting(String displayText, String settingsKey) {

        this.displayText = displayText;
        this.settingsKey = settingsKey;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getSettingsKey() {
        return settingsKey;
    }
}
