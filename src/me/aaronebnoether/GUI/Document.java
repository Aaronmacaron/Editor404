package me.aaronebnoether.GUI;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import me.aaronebnoether.SyntaxHighlighter.Highlighter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The document class represents a tab in which a document is shown.
 *
 * @author Aaron Ebnöther
 */

public class Document extends Tab {
    private File file;
    private BorderPane pane;
    private TextFlow textFlow;
    private ScrollPane textFlowWrapper;

    /**
     * The constructor calls the initComponents and the setText method.
     *
     * @param file is the file whose contents are displayed in the document tab.
     */

    public Document(File file) {
        setText(file.getName());
        this.file = file;
        initComponents();
        setText();
    }

    /**
     * This method initializes the GUI and the components of the tab.
     */

    private void initComponents() {
        pane = new BorderPane();
        textFlow = new TextFlow();
        textFlow.setBackground(new Background(new BackgroundFill(Color.web("2C2C2C"), CornerRadii.EMPTY, Insets.EMPTY)));
        textFlowWrapper = new ScrollPane(textFlow);
        textFlowWrapper.setFitToHeight(true);
        textFlowWrapper.setFitToWidth(true);
        pane.setCenter(textFlowWrapper);
        setContent(pane);
    }

    /**
     * The setText method fetches the highlighted text and sets this a content of the textflow.
     */

    private void setText() {
        String fileContent = removeWindowsLineEndings(readFile(file.getPath()));
        textFlow.getChildren().addAll(Highlighter.getHighlightedTexts(fileContent));
    }

    /**
     * The reRender method is very similar to the setText method. The difference is that it doesn't add the texts but it sets the texts.
     */

    public void reRender() {
        String fileContent = removeWindowsLineEndings(readFile(file.getPath()));
        textFlow.getChildren().setAll(Highlighter.getHighlightedTexts(fileContent));
    }

    /**
     * This method returns a string with all windows line endings (\r) removed. This is necessary because the windows
     * line endings consist of two characters (\r and \n). The textflow interprets this as two line endings. So there is
     * an empty line between every line.
     *
     * @param s is the String of which the windowsLineEndings are removed.
     * @return This method returns a string with all windows line endings (\r) removed.
     */

    private static String removeWindowsLineEndings(String s) {
        return s.replaceAll("\\r", "");
    }

    /**
     * This method return a Stirng with the contents of a file.
     *
     * @param path the path of the file to be read out.
     * @return This method return a Stirng with the contents of a file.
     */

    private static String readFile(String path) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, Charset.defaultCharset());
    }
}
