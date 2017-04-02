package me.aaronebnoether;

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

public class Document extends Tab {
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
        textFlowWrapper = new ScrollPane(textFlow);
        textFlowWrapper.setFitToHeight(true);
        textFlowWrapper.setFitToWidth(true);
        pane.setCenter(textFlowWrapper);
        setContent(pane);
    }

    private void setText() {
        String fileContent = removeWindowsLineEndings(readFile(file.getPath()));
        textFlow.getChildren().addAll(Highlighter.getHighlightedTexts(fileContent));
    }

    static String removeWindowsLineEndings(String s) {
        return s.replaceAll("\\r", "");
    }

    static String readFile(String path) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, Charset.defaultCharset());
    }
}
