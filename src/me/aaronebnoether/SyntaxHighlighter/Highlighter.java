package me.aaronebnoether.SyntaxHighlighter;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {

    public static ArrayList<Text> texts(String text){
        ArrayList<Text> texts = new ArrayList<>();
        for (Token token : getTokens(text)) {
            Text textNode = new Text();
            textNode.setText(token.getValue());
            if (Pattern.matches("^KW.+", token.getTokenType())) {
                textNode.setFill(Color.web("CC7832"));
            }
            if (Pattern.matches("^LI.+", token.getTokenType())) {
                textNode.setFill(Color.web("6A8759"));
            }
            if (Pattern.matches("^CO.+", token.getTokenType())) {
                textNode.setFill(Color.web("629755"));
            }
            texts.add(textNode);
            System.out.printf("Type: %s, Start: %s, End: %s, Value: %s\n", token.getTokenType(), token.getStartIndex(), token.getEndIndex(), token.getValue());
        }
        return texts;
    }

    public static ArrayList<Token> getTokens(String text) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (Rule rule : getRules()) {
            Pattern pattern = Pattern.compile(rule.getPattern());
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                tokens.add(new Token(matcher.group(), matcher.start(), matcher.end(), rule.getRuleName()));
            }
        }
        tokens.sort((token1, token2) -> {
            if (token1.getStartIndex() == token2.getStartIndex()) {
                return 0;
            } else if (token1.getStartIndex() < token2.getStartIndex()) {
                return -1;
            } else return 1;
        });
        return tokens;
    }

    private static ArrayList<Rule> getRules() {
        ArrayList<Rule> returnList = new ArrayList<>();
        returnList.add(new Rule("\\babstract\\b", "KW_ABSTRACT"));
        returnList.add(new Rule("\\bassert\\b", "KW_ASSERT"));
        returnList.add(new Rule("\\bboolean\\b", "KW_BOOLEAN"));
        returnList.add(new Rule("\\bbreak\\b", "KW_BREAK"));
        returnList.add(new Rule("\\bbyte\\b", "KW_BYTE"));
        returnList.add(new Rule("\\bcase\\b", "KW_CASE"));
        returnList.add(new Rule("\\bcatch\\b", "KW_CATCH"));
        returnList.add(new Rule("\\bchar\\b", "KW_CHAR"));
        returnList.add(new Rule("\\bclass\\b", "KW_CLASS"));
        returnList.add(new Rule("\\bconst\\b", "KW_CONST"));
        returnList.add(new Rule("\\bcontinue\\b", "KW_CONTINUE"));
        returnList.add(new Rule("\\bdefault\\b", "KW_DEFAULT"));
        returnList.add(new Rule("\\bdo\\b", "KW_DO"));
        returnList.add(new Rule("\\bdouble\\b", "KW_DOUBLE"));
        returnList.add(new Rule("\\belse\\b", "KW_ELSE"));
        returnList.add(new Rule("\\benmu\\b", "KW_ENUM"));
        returnList.add(new Rule("\\bextends\\b", "KW_EXTENDS"));
        returnList.add(new Rule("\\bfinal\\b", "KW_FINAL"));
        returnList.add(new Rule("\\bfinally\\b", "KW_FINALLY"));
        returnList.add(new Rule("\\bfloat\\b", "KW_FLOAT"));
        returnList.add(new Rule("\\bfor\\b", "KW_FOR"));
        returnList.add(new Rule("\\bgoto\\b", "KW_GOTO"));
        returnList.add(new Rule("\\bif\\b", "KW_IF"));
        returnList.add(new Rule("\\bimplements\\b", "KW_IMPLEMENTS"));
        returnList.add(new Rule("\\bimport\\b", "KW_IMPORT"));
        returnList.add(new Rule("\\binstanceof\\b", "KW_INSTANCEOF"));
        returnList.add(new Rule("\\bint\\b", "KW_INT"));
        returnList.add(new Rule("\\binterface\\b", "KW_INTERFACE"));
        returnList.add(new Rule("\\blong\\b", "KW_LONG"));
        returnList.add(new Rule("\\bnative\\b", "KW_NATIVE"));
        returnList.add(new Rule("\\bnew\\b", "KW_NEW"));
        returnList.add(new Rule("\\bpackage\\b", "KW_PACKAGE"));
        returnList.add(new Rule("\\bprivate\\b", "KW_PRIVATE"));
        returnList.add(new Rule("\\bprotected\\b", "KW_PROTECTED"));
        returnList.add(new Rule("\\bpublic\\b", "KW_PUBLIC"));
        returnList.add(new Rule("\\breturn\\b", "KW_RETURN"));
        returnList.add(new Rule("\\bshort\\b", "KW_SHORT"));
        returnList.add(new Rule("\\bstatic\\b", "KW_STATIC"));
        returnList.add(new Rule("\\bstrictfp\\b", "KW_STRICTFP"));
        returnList.add(new Rule("\\bsuper\\b", "KW_SUPER"));
        returnList.add(new Rule("\\bswitch\\b", "KW_SWITCH"));
        returnList.add(new Rule("\\bsynchronized\\b", "KW_SYNCHRONIZED"));
        returnList.add(new Rule("\\bthis\\b", "KW_THIS"));
        returnList.add(new Rule("\\bthrow\\b", "KW_THROW"));
        returnList.add(new Rule("\\bthrows\\b", "KW_THROWS"));
        returnList.add(new Rule("\\btransient\\b", "KW_TRANSIENT"));
        returnList.add(new Rule("\\btry\\b", "KW_TRY"));
        returnList.add(new Rule("\\bvoid\\b", "KW_VOID"));
        returnList.add(new Rule("\\bvolatile\\b", "KW_VOLATILE"));
        returnList.add(new Rule("\\bwhile\\b", "KW_WHILE"));
        returnList.add(new Rule("\\bfalse\\b", "KW_FALSE"));
        returnList.add(new Rule("\\bnull\\b", "KW_NULL"));
        returnList.add(new Rule("\\btrue\\b", "KW_TRUE"));
        returnList.add(new Rule("\\s+", "SC_WHITESPACE"));
        returnList.add(new Rule("\".+\"", "LI_STRING"));
        returnList.add(new Rule("\'.+\'", "LI_CHAR"));
        returnList.add(new Rule("//.*", "CO_ONE_LINE"));
        returnList.add(new Rule("/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/", "CO_MULTI_LINE"));
        return returnList;
    }
}
