package me.aaronebnoether;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

/**
 * GUIManager is a general class for managing GUI related stuff. It creates the Main window and adds the GUI Layout.
 *
 * @author Aaron Ebnöther
 */

public class GUIManager extends Application {

    private Scene scene;
    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
    }

    private void init(Stage primaryStage) {

        BorderPane rootPane = new BorderPane();
        scene = new Scene(rootPane, 800, 500);

        //Menubar
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        //Create "File -> Open" MenuItem
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(this::onFileOpen);

        fileMenu.getItems().addAll(openMenuItem);
        menuBar.getMenus().addAll(fileMenu);

        //Tabpane
        tabPane = new TabPane();
        tabPane.getTabs().add(new InfoTab());

        //Add Controls to BorderPane
        rootPane.setTop(menuBar);
        rootPane.setCenter(tabPane);

        //Create Window
        primaryStage.setTitle("404 Code Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onFileOpen(ActionEvent value) {
        File file = new FileChooser().showOpenDialog(scene.getWindow());
        tabPane.getTabs().add(new Document(file));
    }

    static void launch() {
        Application.launch();
    }
}
