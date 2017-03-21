package me.aaronebnoether;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextFlow;
import me.aaronebnoether.SyntaxHighlighter.Highlighter;

import java.io.File;

public class Document extends Tab{
    private File file;
    private BorderPane pane;
    private TextFlow textFlow;
    private ScrollPane textFlowWrapper;

    public Document(File file) {
        setText(file.getName());
        this.file = file;
        initComponents();
        setText();
    }

    private void initComponents() {
        pane = new BorderPane();
        textFlow = new TextFlow();
        textFlow.setBackground(new Background(new BackgroundFill(Color.web("2C2C2C"), CornerRadii.EMPTY, Insets.EMPTY)));
        textFlow.setPrefSize();
        textFlowWrapper = new ScrollPane(textFlow);
        pane.setCenter(textFlowWrapper);
        setContent(pane);
    }

    private void setText() {
        String text = "import java.util.Random;\n" +
                "\n" +
                "/**\n" +
                " * Models a playing die with sides numbered 1 to N.\n" +
                " * All sides have uniform probablity of being rolled.\n" +
                " *\n" +
                " * @author Summer CS 307 class\n" +
                " */\n" +
                "public class Die\n" +
                "{   public static final int DEFAULT_SIDES = 6;\n" +
                "\n" +
                "    private static Random ourRandNumGen = new Random();\n" +
                "\n" +
                "    private final int iMyNumSides;\n" +
                "    private int iMyResult;\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * Default constructor.<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: getNumSides() = DEFAULT_SIDES, getResult() = 1\n" +
                "     */\n" +
                "    public Die()\n" +
                "    {   this(DEFAULT_SIDES);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     //Das ist ein Test\n" +
                "     * Create a Die with numSides sides<p>\n" +
                "     * pre: numSides > 1<br>\n" +
                "     * post: getNumSides() = numSides, getResult() = 1<br>\n" +
                "     * An exception will be generated if the preconditions are not met\n" +
                "     */\n" +
                "    public Die(int numSides)\n" +
                "    {   assert numSides > 1 : \"Violation of precondition: numSides = \" + numSides + \"numSides must be greater than 1\";\n" +
                "\n" +
                "        iMyNumSides = numSides;\n" +
                "        iMyResult = 1;\n" +
                "        assert getResult() == 1 && getNumSides() == numSides;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * Create a Die with numSides and top side and result set to result<p>\n" +
                "     * pre: numSides > 1, 1 <= result <= numSides<br>\n" +
                "     * post: getNumSides() = numSides, getResult() = 1<br>\n" +
                "     * An exception will be generated if the preconditions are not met\n" +
                "     */\n" +
                "    public Die(int numSides, int result)\n" +
                "    {   assert numSides > 1 && 1 <= result && result <= numSides : \"Violation of precondition\";\n" +
                "\n" +
                "        iMyNumSides = numSides;\n" +
                "        iMyResult = result;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * roll this Die. Every side has an equal chance of being the new result<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: 1 <= getResult() <= getNumSides()\n" +
                "     * @return the result of the Die after the roll\n" +
                "     */\n" +
                "    public int roll()\n" +
                "    {   iMyResult = ourRandNumGen.nextInt(iMyNumSides) + 1;\n" +
                "\n" +
                "        assert ( 1 <= getResult() ) && ( getResult() <= getNumSides() );\n" +
                "\n" +
                "        return iMyResult;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * return how many sides this Die has<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: return how many sides this Die has\n" +
                "     * @return the number of sides on this Die\n" +
                "     */\n" +
                "    public int getNumSides()\n" +
                "    {   return iMyNumSides; }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * get the current result or top number of this Die<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: return the number on top of this Die\n" +
                "     * @return the current result of this Die\n" +
                "     */\n" +
                "    public int getResult()\n" +
                "    {   return iMyResult;   }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * returns true if this Die and the parameter otherObj are equal<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: return true if the parameter is a Die object with the same number of sides as this Die and currently has the same result.\n" +
                "     * @return true if the the two Dice are equal, false otherwise\n" +
                "     */\n" +
                "    public boolean equals(Object otherObj)\n" +
                "    {   boolean result = true;\n" +
                "        if(otherObj == null)\n" +
                "            result = false;\n" +
                "        else if(this == otherObj)\n" +
                "            result = true;\n" +
                "        else if(this.getClass() != otherObj.getClass())\n" +
                "            result = false;\n" +
                "        else\n" +
                "        {   Die otherDie = (Die)otherObj;\n" +
                "            result = this.iMyResult == otherDie.iMyResult\n" +
                "                && this.iMyNumSides == otherDie.iMyNumSides;\n" +
                "        }\n" +
                "        return result;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * returns a String containing information about this Die<p>\n" +
                "     * pre: none<br>\n" +
                "     * post: return a String with information about the current state of this Die\n" +
                "     * @return: A String with the number of sides and current result of this Die\n" +
                "     */\n" +
                "    public String toString()\n" +
                "    {   return \"Num sides \" + getNumSides() + \" result \" + getResult();\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}// end of Die class";

        textFlow.getChildren().addAll(Highlighter.texts(text));
    }
}
