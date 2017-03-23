package me.aaronebnoether;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class GUIManager extends Application{

    BorderPane root;
    Scene scene;
    Stage stage;
    TabPane tabPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        openMenuItem.setOnAction(value -> {
            File file = new FileChooser().showOpenDialog(scene.getWindow());
            tabPane.getTabs().add(new Document(file));
        });

        tabPane = new TabPane();
        tabPane.getTabs().add(new InfoTab());

        fileMenu.getItems().addAll(openMenuItem);
        menuBar.getMenus().addAll(fileMenu);
        root.setTop(menuBar);
        root.setCenter(tabPane);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launch() {
        Application.launch();
    }
}
