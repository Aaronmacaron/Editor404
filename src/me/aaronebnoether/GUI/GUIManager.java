package me.aaronebnoether.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.aaronebnoether.SettingsManager;

import java.io.File;

/**
 * GUIManager is a general class for managing GUI related stuff. It creates the Main window and adds the GUI Layout.
 *
 * @author Aaron Ebnöther
 */

public class GUIManager extends Application {

    private static Scene scene;
    private static TabPane tabPane;

    /**
     * The start method is called by JavaFX as soon as the whole bootstrapping of JavaFX is done.
     *
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
    }

    /**
     * The init method is responsible for initializing the main window. It sets all components and listeners.
     *
     * @param primaryStage
     */

    private void init(Stage primaryStage) {

        primaryStage.setOnCloseRequest(value -> Platform.exit()); //All open windows should be closed when the main window is closed.

        BorderPane rootPane = new BorderPane();
        scene = new Scene(rootPane, 800, 500);

        //Menubar
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        //Create "File -> Open" MenuItem
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(this::onFileOpenClick);

        //Create "File -> Close" MenuItem
        MenuItem closeMenuItem = new MenuItem("Close");
        closeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
        closeMenuItem.setOnAction(this::onTabCloseClick);

        //Create "File -> Settings" MenuItem
        MenuItem settingsMenuItem = new MenuItem("Settings");
        settingsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        settingsMenuItem.setOnAction(value -> new SettingsManager());

        fileMenu.getItems().addAll(openMenuItem, closeMenuItem, settingsMenuItem);
        menuBar.getMenus().addAll(fileMenu);

        //Tabpane
        tabPane = new TabPane();
        tabPane.getTabs().addListener(this::onTabPaneChange);
        tabPane.getTabs().add(new InfoTab());

        //Add Controls to BorderPane
        rootPane.setTop(menuBar);
        rootPane.setCenter(tabPane);

        //Create Window
        primaryStage.setTitle("404 Code Viewer");
        primaryStage.getIcons().add(new Image("/img/icon.png", 100, 0, false, false)); //This sets the icon of the Application.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method is called when the user clicks the "open" menuItem.
     *
     * @param value is the default event object provided by the action listener. It's never used in this method though.
     */

    private void onFileOpenClick(ActionEvent value) {
        File file = new FileChooser().showOpenDialog(scene.getWindow());
        if (file != null) { //If the file doesn't exists or the user didn't choose a file.
            tabPane.getTabs().add(new Document(file));
        }
    }

    /**
     * This method is called when a tab is opened or closed.
     *
     * @param change is the default event object provided by the action listener. It's never used in this method though.
     */

    private void onTabPaneChange(ListChangeListener.Change change) {
        if (tabPane.getTabs().size() == 1) { //If there's only one tab the close button gets removed
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        } else { //Otherwise the close buttons are added
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        }
    }

    /**
     * This method is called when a tab gets closed.
     *
     * @param event is the default event object provided by the action listener. It's never used in this method though.
     */

    private void onTabCloseClick(ActionEvent event) {
        if (tabPane.getTabs().size() != 1) { //If there's only one tab you can't close it
            tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * This is a setter for the tabPane.
     *
     * @return The method returns the tabPane of the main window.
     */

    public static TabPane getTabPane() {
        return tabPane;
    }
}
