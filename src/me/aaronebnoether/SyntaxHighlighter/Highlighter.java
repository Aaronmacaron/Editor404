package me.aaronebnoether.SyntaxHighlighter;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {

    private final static String KW_FLAG = "\\:k:\\";
    private final static String KW_END_FLAG = "\\.k.\\";
    private final static String ST_FLAG = "\\:s:\\";
    private final static String ST_END_FLAG = "\\.s.\\";
    private final static String NM_FLAG = "\\:n:\\";
    private final static String NM_END_FLAG = "\\.n.\\";
    private final static String CO_FLAG = "\\:c:\\";
    private final static String CO_END_FLAG = "\\.c.\\";
    private final static String SC_FLAG = "\\:a:\\";
    private final static String SC_END_FLAG = "\\.a.\\";

    public static ArrayList<Text> texts(String text){
        return getTextsOfHighlightedString(getHighlightedString(text));
    }

    public static ArrayList<Text> getTextsOfHighlightedString(String hlString) {
        ArrayList<Text> texts = new ArrayList<>();
        String currentColor = "AAAAAA";
        String typeExpected = NM_FLAG;
        for (int i = 0; i < hlString.length(); i++) {
            String flagRange = hlString.substring(i, i + 5 > hlString.length() ? i : i + 5);
            switch (flagRange) {
                case KW_FLAG: {
                    if (typeExpected.equals(NM_FLAG)) {
                        if (currentColor.equals("AAAAAA")) {
                            currentColor = "CC7832";
                        }
                        typeExpected = KW_END_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + 5).toString();
                    i--;
                    continue;
                }
                case ST_FLAG: {
                    if (typeExpected.equals(NM_FLAG)) {
                        if (currentColor.equals("AAAAAA")) {
                            currentColor = "6A8759";
                        }
                        typeExpected = ST_END_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + ST_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case CO_FLAG: {
                    if (typeExpected.equals(NM_FLAG)) {
                        if (currentColor.equals("AAAAAA")) {
                            currentColor = "629755";
                        }
                        typeExpected = CO_END_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + CO_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case SC_FLAG: {
                    if (typeExpected.equals(NM_FLAG)) {
                        if (currentColor.equals("AAAAAA")) {
                            currentColor = "AAAAAA";
                        }
                        typeExpected = SC_END_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + SC_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case KW_END_FLAG: {
                    if (typeExpected.equals(KW_END_FLAG)) {
                        currentColor = "AAAAAA";
                        typeExpected = NM_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + KW_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case ST_END_FLAG: {
                    if (typeExpected.equals(ST_END_FLAG)) {
                        currentColor = "AAAAAA";
                        typeExpected = NM_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + ST_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case CO_END_FLAG: {
                    if (typeExpected.equals(CO_END_FLAG)) {
                        currentColor = "AAAAAA";
                        typeExpected = NM_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + CO_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case SC_END_FLAG: {
                    if (typeExpected.equals(SC_END_FLAG)) {
                        currentColor = "AAAAAA";
                        typeExpected = NM_FLAG;
                    }
                    hlString = new StringBuilder(hlString).delete(i, i + SC_FLAG.length()).toString();
                    i--;
                    continue;
                }
                case NM_FLAG: {
                    currentColor = "AAAAAA";
                    hlString = new StringBuilder(hlString).delete(i, i + 5).toString();
                    i--;
                    continue;
                }
            }
            Text text = new Text();
            if (i >= hlString.length()) {
                text.setText("");
            } else {
                text.setText(String.valueOf(hlString.charAt(i)));
            }
            text.setFill(Color.web(currentColor));
            texts.add(text);
        }
        return texts;
    }

    public static String getHighlightedString(String text) {
        for (Rule rule : getRules()) {
            Pattern pattern = Pattern.compile(rule.getPattern());
            Matcher matcher = pattern.matcher(text);
            int offset = 0;
            while (matcher.find()) {
                switch (rule.getRuleName()) {
                    case "KW": {
                        StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, KW_FLAG);
                        offset += KW_FLAG.length();
                        text = stringBuilder.toString();
                        stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, KW_END_FLAG);
                        text = stringBuilder.toString();
                        offset += NM_FLAG.length();
                        break;
                    }
                    case "ST": {
                        StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, ST_FLAG);
                        offset += ST_FLAG.length();
                        text = stringBuilder.toString();
                        stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, ST_END_FLAG);
                        text = stringBuilder.toString();
                        offset += NM_FLAG.length();
                        break;
                    }
                    case "CO": {
                        StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, CO_FLAG);
                        offset += CO_FLAG.length();
                        text = stringBuilder.toString();
                        stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, CO_END_FLAG);
                        text = stringBuilder.toString();
                        offset += NM_FLAG.length();
                        break;
                    }
                    case "SC": {
                        StringBuilder stringBuilder = new StringBuilder(text).insert(matcher.start() + offset, SC_FLAG);
                        offset += SC_FLAG.length();
                        text = stringBuilder.toString();
                        stringBuilder = new StringBuilder(text).insert(matcher.end() + offset, SC_END_FLAG);
                        text = stringBuilder.toString();
                        offset += NM_FLAG.length();
                        break;
                    }
                }
            }
        }
        return text;
    }

    private static ArrayList<Rule> getRules() {
        ArrayList<Rule> returnList = new ArrayList<>();
        returnList.add(new Rule("\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue" +
                "|default|do|double|else|enmu|extends|final|finally|float|for|goto|if|implements|import|instanceof|int" +
                "|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch" +
                "|synchronized|this|throw|throws|transient|try|void|volatile|while|false|null|true)\\b", "KW"));
        returnList.add(new Rule("\"[^\\\"]+\"|'[^\\\"]+'", "ST"));
        returnList.add(new Rule("//.*|/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/", "CO"));
        returnList.add(new Rule("(\\(|\\)|=|&|\\*|\\+|\\-|\\/|\\<|\\>|\\{|\\}|\\;|\\|)", "SC"));
        return returnList;
    }
}
