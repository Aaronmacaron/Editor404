package me.aaronebnoether;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InfoTab extends Tab {

    public InfoTab() {
        setText("404 Code Viewer");
        initGui();
    }

    private void initGui() {
        Background background = new Background(new BackgroundFill(Color.web("46494C"), CornerRadii.EMPTY, Insets.EMPTY));
        Paint textColor = Color.web("BABABA");
        Font titleFont = new Font("Open sans", 23);
        Font normalFont = new Font("Open sans", 16);
        Font semiboldFont = new Font("Open sans semibold", 16);
        Insets padding = new Insets(20);
        Insets shortcutInsets = new Insets(0, 0, 30, 0);

        VBox vBox = new VBox();
        vBox.setBackground(background);
        vBox.setPadding(padding);
        vBox.setAlignment(Pos.TOP_CENTER);

        HBox row1 = new HBox();
        row1.setPadding(shortcutInsets);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox();
        row2.setPadding(shortcutInsets);
        row2.setAlignment(Pos.CENTER);

        HBox row3 = new HBox();
        row3.setPadding(shortcutInsets);
        row3.setAlignment(Pos.CENTER);

        HBox row4 = new HBox();
        row4.setPadding(shortcutInsets);
        row4.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(row1, row2, row3, row4);

        Text welcomeText = new Text("Willkommen zum 404 Code Viewer");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setFill(textColor);
        welcomeText.setFont(titleFont);
        row1.getChildren().add(welcomeText);

        Text openText = new Text("Dokument öffnen: ");
        openText.setFill(textColor);
        openText.setFont(normalFont);
        row2.getChildren().add(openText);

        Text openShortcut = new Text("CTRL + O");
        openShortcut.setFill(textColor);
        openShortcut.setFont(semiboldFont);
        row2.getChildren().add(openShortcut);

        Text closeText = new Text("Dokument schliessen: ");
        closeText.setFill(textColor);
        closeText.setFont(normalFont);
        row3.getChildren().add(closeText);

        Text closeShortcut = new Text("CTRL + W");
        closeShortcut.setFill(textColor);
        closeShortcut.setFont(semiboldFont);
        row3.getChildren().add(closeShortcut);

        Text settingsText = new Text("Einstellungen öffnen: ");
        settingsText.setFill(textColor);
        settingsText.setFont(normalFont);
        row4.getChildren().add(settingsText);

        Text settingsShortcut = new Text("CTRL + E");
        settingsShortcut.setFill(textColor);
        settingsShortcut.setFont(semiboldFont);
        row4.getChildren().add(settingsShortcut);

        setContent(vBox);
    }
}
