package me.aaronebnoether;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SettingsManager {
    private Stage stage;
    private Scene scene;
    private BorderPane root;

    public SettingsManager() {
        root = new BorderPane();
        scene = new Scene(root, 600, 400);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
