package me.aaronebnoether;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;

import java.io.File;

public class Document extends Tab{
    private File file;
    private BorderPane pane;
    private TextFlow textFlow;

    public Document(File file) {
        setText(file.getName());
        this.file = file;

        initComponents();
    }

    private void initComponents() {
        pane = new BorderPane();
        textFlow = new TextFlow();
        pane.setCenter(textFlow);
        setContent(pane);
    }

    private void setText() {

    }
}
