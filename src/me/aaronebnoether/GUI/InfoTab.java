package me.aaronebnoether.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The InfoTab class is a class that represents a Tab with static content. The InfoTab is shown at the start of the
 * Application. It provides a quick explanation of the shortcuts that are available in the Application.
 *
 * @author Aaron Ebnöther
 */

public class InfoTab extends Tab {

    /**
     * The constructor consists of only one call to the initGui method.
     */

    public InfoTab() {
        initGui();
    }

    /**
     * The initGui method sets the contents of the Tab.
     */

    private void initGui() {
        setText("404 Code Viewer"); //Sets the Tab name

        // Define colors, insets, colors and backgrounds
        Background background = new Background(new BackgroundFill(Color.web("46494C"), CornerRadii.EMPTY, Insets.EMPTY));
        Paint textColor = Color.web("BABABA");
        Font titleFont = new Font("Open sans", 23);
        Font normalFont = new Font("Open sans", 16);
        Font semiboldFont = new Font("Open sans semibold", 16);
        Insets padding = new Insets(20);
        Insets shortcutInsets = new Insets(0, 0, 30, 0);

        //VBox that contains all lines of text
        VBox vBox = new VBox();
        vBox.setBackground(background);
        vBox.setPadding(padding);
        vBox.setAlignment(Pos.TOP_CENTER);

        //Every line of text gets its own HBox.
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

        //The following blocks adds the text to the HBoxes.
        Text welcomeText = new Text("Welcome to the 404 Code Viewer!");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setFill(textColor);
        welcomeText.setFont(titleFont);
        row1.getChildren().add(welcomeText);

        Text openText = new Text("Open document: ");
        openText.setFill(textColor);
        openText.setFont(normalFont);
        row2.getChildren().add(openText);

        Text openShortcut = new Text("CTRL + O");
        openShortcut.setFill(textColor);
        openShortcut.setFont(semiboldFont);
        row2.getChildren().add(openShortcut);

        Text closeText = new Text("Close document: ");
        closeText.setFill(textColor);
        closeText.setFont(normalFont);
        row3.getChildren().add(closeText);

        Text closeShortcut = new Text("CTRL + W");
        closeShortcut.setFill(textColor);
        closeShortcut.setFont(semiboldFont);
        row3.getChildren().add(closeShortcut);

        Text settingsText = new Text("Open settings: ");
        settingsText.setFill(textColor);
        settingsText.setFont(normalFont);
        row4.getChildren().add(settingsText);

        Text settingsShortcut = new Text("CTRL + E");
        settingsShortcut.setFill(textColor);
        settingsShortcut.setFont(semiboldFont);
        row4.getChildren().add(settingsShortcut);

        setContent(vBox); //Finally set the content of the tab to the vBox
    }
}
