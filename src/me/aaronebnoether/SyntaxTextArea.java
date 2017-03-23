package me.aaronebnoether;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SyntaxTextArea extends StackPane{
    private TextArea textArea;
    private TextFlow textFlow;

    public SyntaxTextArea() {
        init();
    }

    public void init() {
        textArea = new TextArea();
        textArea.setMaxSize(0, 0);
        textArea.textProperty().addListener(value -> {
            textFlow.getChildren().clear();
            textFlow.getChildren().addAll(new Text(textArea.getText()));
        });

        textFlow = new TextFlow();
        textFlow.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), null, null)));

        getChildren().addAll(textArea, textFlow);
    }
}
