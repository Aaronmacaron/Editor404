package me.aaronebnoether;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class SettingsManager {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private static final String fileName = "settings.properties";
    private static Properties properties = new Properties();
    private static Insets settingsEntryInsets = new Insets(10);
    private HashMap<ColorPicker, ColorSetting> colorPickers = new HashMap<>();

    public SettingsManager() {
        root = new BorderPane();
        scene = new Scene(root, 400, 320);
        stage = new Stage();
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        initGui();
    }

    private void initGui() {
        stage.getIcons().add(new Image("/img/icon.png", 100, 0, false, false));
        root.setBackground(new Background(new BackgroundFill(Color.web("46494C"), CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vBox = new VBox();

        for (ColorSetting colorSetting : ColorSetting.values()) {
            HBox hBox = new HBox();
            BorderPane colorBorderPane = new BorderPane();
            hBox.setPadding(settingsEntryInsets);
            Text colorText = new Text(colorSetting.getDisplayText());
            colorText.setFill(Color.web("BABABA"));
            ColorPicker colorPicker = new ColorPicker(Color.web(get(colorSetting.getSettingsKey())));
            colorBorderPane.setLeft(colorText);
            colorBorderPane.setRight(colorPicker);
            colorPickers.put(colorPicker, colorSetting);
            hBox.getChildren().addAll(colorBorderPane);
            hBox.setFillHeight(true);
            HBox.setHgrow(colorBorderPane, Priority.ALWAYS);
            vBox.getChildren().add(hBox);
            colorPicker.setOnAction(value -> save());
        }

        root.setCenter(vBox);
    }

    private void save() {
        for (ColorPicker key : colorPickers.keySet()) {
            set(colorPickers.get(key).getSettingsKey(), key.getValue().toString());
        }
        for (Tab tab : GUIManager.getTabPane().getTabs()) {
            if (tab instanceof Document) {
                ((Document) tab).reRender();
            }
        }
    }

    public static String get(String key) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String) properties.get(key);
    }

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
