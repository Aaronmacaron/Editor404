package me.aaronebnoether;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public SettingsManager() {
        root = new BorderPane();
        scene = new Scene(root, 600, 400);
        initGui();
        stage = new Stage();
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void initGui() {
        VBox vBox = new VBox();

        HashMap<ColorPicker, ColorSetting> colorPickers = new HashMap<>();

        for (ColorSetting colorSetting : ColorSetting.values()) {
            HBox hBox = new HBox();
            BorderPane colorBorderPane = new BorderPane();
            hBox.setPadding(settingsEntryInsets);
            Text colorText = new Text(colorSetting.getDisplayText());
            ColorPicker colorPicker = new ColorPicker(Color.web(get(colorSetting.getSettingsKey())));
            colorBorderPane.setLeft(colorText);
            colorBorderPane.setRight(colorPicker);
            colorPickers.put(colorPicker, colorSetting);
            hBox.getChildren().addAll(colorBorderPane);
            hBox.setFillHeight(true);
            HBox.setHgrow(colorBorderPane, Priority.ALWAYS);
            vBox.getChildren().add(hBox);
        }

        HBox saveHbox = new HBox();
        saveHbox.setPadding(settingsEntryInsets);
        saveHbox.setAlignment(Pos.CENTER_RIGHT);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(value -> {
            for (ColorPicker key : colorPickers.keySet()) {
                set(colorPickers.get(key).getSettingsKey(), key.getValue().toString());
            }
        });
        saveHbox.getChildren().addAll(saveButton);
        vBox.getChildren().addAll(saveHbox);

        root.setCenter(vBox);
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
