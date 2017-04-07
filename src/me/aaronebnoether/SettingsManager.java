package me.aaronebnoether;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.aaronebnoether.GUI.Document;
import me.aaronebnoether.GUI.GUIManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * The settings Manager is general class that is responsible for manging the settings.
 *
 * @author Aaron Ebnöther
 */

public class SettingsManager {
    private Stage stage;
    private BorderPane root;
    private static final String fileName = "settings.properties";
    private static Properties properties = new Properties();
    private static Insets settingsEntryInsets = new Insets(10);
    private HashMap<ColorPicker, Setting> colorPickers = new HashMap<>();

    /**
     * The constructor shows the settings window and calls the initGui method.
     */

    public SettingsManager() {
        root = new BorderPane();
        Scene scene = new Scene(root, 400, 320);
        stage = new Stage();
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        initGui();
    }

    /**
     * The initGui method initializes the Gui which means that it adds all the components to the window.
     */

    private void initGui() {
        stage.getIcons().add(new Image("/img/icon.png", 100, 0, false, false)); //Sets the icon of the window
        root.setBackground(new Background(new BackgroundFill(Color.web("46494C"), CornerRadii.EMPTY, Insets.EMPTY))); //Sets the background of the BorderPane to a color
        VBox vBox = new VBox();

        //The following for loop iterates through all settings entries specified in the Setting enum.
        //It creates a settings entry for every setting.
        for (Setting setting : Setting.values()) {
            HBox hBox = new HBox();
            BorderPane borderPane = new BorderPane();
            hBox.setPadding(settingsEntryInsets);
            Text text = new Text(setting.getDisplayText());
            text.setFill(Color.web("BABABA"));
            ColorPicker colorPicker = new ColorPicker(Color.web(get(setting.getSettingsKey())));
            borderPane.setLeft(text);
            borderPane.setRight(colorPicker);
            colorPickers.put(colorPicker, setting);
            hBox.getChildren().addAll(borderPane);
            hBox.setFillHeight(true);
            HBox.setHgrow(borderPane, Priority.ALWAYS);
            vBox.getChildren().add(hBox);
            colorPicker.setOnAction(value -> save()); //If a color was chosen the settings are automatically saved.
        }

        root.setCenter(vBox);
    }

    /**
     * The save method stores all settings to disk. The settings are stored in the settings.properties file.
     */

    private void save() {
        //Iterates through all settings and sets the value in the properties object.
        for (ColorPicker key : colorPickers.keySet()) {
            set(colorPickers.get(key).getSettingsKey(), key.getValue().toString());
        }

        //The following loop iterates through all Tabs and re-renders them. This achieves an "auto/live update" effect.
        for (Tab tab : GUIManager.getTabPane().getTabs()) {
            if (tab instanceof Document) { //It's necessary to check if the tab is a Document or not.
                // Only the Document Class has the reRender method.
                // Not all tabs are necessarily Documents because of the InfoTab.
                ((Document) tab).reRender(); // Cast to Document to get access to reRender method.
            }
        }
    }

    /**
     * The get method is a wrapper for the properties class.
     *
     * @param key is the key of the desired value.
     * @return This method returns the value of the specified key.
     */

    public static String get(String key) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String) properties.get(key);
    }

    /**
     * The set method sets a value in the settings with the according key.
     *
     * @param key is the key of the setting.
     * @param value is the value of the setting.
     */

    public static void set(String key, String value) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            properties.setProperty(key, value);
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
