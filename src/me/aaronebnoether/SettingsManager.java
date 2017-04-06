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
import sun.security.krb5.internal.LoginOptions;

import java.io.*;
import java.util.Properties;

public class SettingsManager {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private static final String fileName = "settings.properties";
    private static InputStream inputStream;
    private static FileOutputStream outputStream;
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

        HBox kwColor = new HBox();
        BorderPane kwColorBorderPane = new BorderPane();
        kwColor.setPadding(settingsEntryInsets);
        Text kwColorText = new Text("String Color: ");
        ColorPicker kwColorPicker = new ColorPicker(Color.web(get("kwColor")));
        kwColorBorderPane.setLeft(kwColorText);
        kwColorBorderPane.setRight(kwColorPicker);
        kwColor.getChildren().addAll(kwColorBorderPane);
        kwColor.setFillHeight(true);
        HBox.setHgrow(kwColorBorderPane, Priority.ALWAYS);

        HBox stColor = new HBox();
        BorderPane stColorBorderPane = new BorderPane();
        stColor.setPadding(settingsEntryInsets);
        Text stColorText = new Text("String Color: ");
        ColorPicker stColorPicker = new ColorPicker(Color.web(get("stColor")));
        stColorBorderPane.setLeft(stColorText);
        stColorBorderPane.setRight(stColorPicker);
        stColor.getChildren().addAll(stColorBorderPane);
        HBox.setHgrow(stColorBorderPane, Priority.ALWAYS);

        HBox coColor = new HBox();
        BorderPane coColorBorderPane = new BorderPane();
        coColor.setPadding(settingsEntryInsets);
        Text coColorText = new Text("Comment Color: ");
        ColorPicker coColorPicker = new ColorPicker(Color.web(get("coColor")));
        coColorBorderPane.setLeft(coColorText);
        coColorBorderPane.setRight(coColorPicker);
        coColor.getChildren().addAll(coColorBorderPane);
        HBox.setHgrow(coColorBorderPane, Priority.ALWAYS);

        HBox scColor = new HBox();
        BorderPane scColorBorderPane = new BorderPane();
        scColor.setPadding(settingsEntryInsets);
        Text scColorText = new Text("Special Character Color: ");
        ColorPicker scColorPicker = new ColorPicker(Color.web(get("scColor")));
        scColorBorderPane.setLeft(scColorText);
        scColorBorderPane.setRight(scColorPicker);
        scColor.getChildren().addAll(scColorBorderPane);
        HBox.setHgrow(scColorBorderPane, Priority.ALWAYS);

        HBox anColor = new HBox();
        BorderPane anColorBorderPane = new BorderPane();
        anColor.setPadding(settingsEntryInsets);
        Text anColorText = new Text("Annotation Color: ");
        ColorPicker anColorPicker = new ColorPicker(Color.web(get("anColor")));
        anColorBorderPane.setLeft(anColorText);
        anColorBorderPane.setRight(anColorPicker);
        anColor.getChildren().addAll(anColorBorderPane);
        HBox.setHgrow(anColorBorderPane, Priority.ALWAYS);

        HBox diColor = new HBox();
        BorderPane diColorBorderPane = new BorderPane();
        diColor.setPadding(settingsEntryInsets);
        Text diColorText = new Text("Digit Color: ");
        ColorPicker diColorPicker = new ColorPicker(Color.web(get("diColor")));
        diColorBorderPane.setLeft(diColorText);
        diColorBorderPane.setRight(diColorPicker);
        diColor.getChildren().addAll(diColorBorderPane);
        HBox.setHgrow(diColorBorderPane, Priority.ALWAYS);

        HBox nmColor = new HBox();
        BorderPane nmColorBorderPane = new BorderPane();
        nmColor.setPadding(settingsEntryInsets);
        Text nmColorText = new Text("Digit Color: ");
        ColorPicker nmColorPicker = new ColorPicker(Color.web(get("nmColor")));
        nmColorBorderPane.setLeft(nmColorText);
        nmColorBorderPane.setRight(nmColorPicker);
        nmColor.getChildren().addAll(nmColorBorderPane);
        HBox.setHgrow(nmColorBorderPane, Priority.ALWAYS);

        vBox.getChildren().addAll(kwColor, stColor, coColor, scColor, anColor, diColor, nmColor);

        HBox saveHbox = new HBox();
        saveHbox.setPadding(settingsEntryInsets);
        saveHbox.setAlignment(Pos.CENTER_RIGHT);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(value -> {
            set("kwColor", kwColorPicker.getValue().toString());
            set("stColor", stColorPicker.getValue().toString());
            set("coColor", coColorPicker.getValue().toString());
            set("scColor", scColorPicker.getValue().toString());
            set("anColor", anColorPicker.getValue().toString());
            set("diColor", diColorPicker.getValue().toString());
            set("nmColor", nmColorPicker.getValue().toString());

        });
        saveHbox.getChildren().addAll(saveButton);
        vBox.getChildren().addAll(saveHbox);

        root.setCenter(vBox);
    }

    public static String get(String key) {
        try {
            inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String) properties.get(key);
    }

    public static void set(String key, String value) {
        try {
            outputStream = new FileOutputStream(fileName);
            properties.setProperty(key, value);
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
