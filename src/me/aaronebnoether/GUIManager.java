package me.aaronebnoether;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class GUIManager extends Application{

    BorderPane root;
    Scene scene;
    Stage stage;
    File fileToOpen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        showStartDialog();
        init(primaryStage);
    }

    private void init(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, 800, 500);

        //Menubar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(new Document(fileToOpen));

        fileMenu.getItems().addAll(openMenuItem);
        menuBar.getMenus().addAll(fileMenu);
        root.setTop(menuBar);
        root.setCenter(tabPane);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showStartDialog() {
        Stage stage = new Stage();
        VBox vBox = new VBox(20);
        HBox row1 = new HBox(20);
        HBox row2 = new HBox(20);
        HBox row3 = new HBox(20);
        Scene scene = new Scene(vBox, 500, 300);

        Text title = new Text("Bitte wählen Sie eine Datei aus.");
        Text fileChoosed = new Text("Noch keine Datei ausgewählt.");
        Button openFileChooserButton = new Button("Datei auswählen");
        FileChooser fileChooser = new FileChooser();
        Button openMainWindow = new Button("Datei öffnen");
        openMainWindow.setOnAction(value -> stage.close());
        openFileChooserButton.setOnAction(value -> {
            fileToOpen = fileChooser.showOpenDialog(scene.getWindow());
            fileChoosed.setText(fileToOpen.getPath());
        });

        row1.getChildren().addAll(title);
        row1.setAlignment(Pos.CENTER);

        row2.getChildren().addAll(fileChoosed, openFileChooserButton);
        row2.setAlignment(Pos.CENTER);

        row3.getChildren().addAll(openMainWindow);
        row3.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(row1, row2, row3);

        stage.setScene(scene);
        stage.setTitle("Datei auswählen");
        stage.showAndWait();
    }

    public static void launch() {
        Application.launch();
    }
}
