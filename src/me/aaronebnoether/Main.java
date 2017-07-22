package me.aaronebnoether;

import javafx.application.Application;
import me.aaronebnoether.GUI.GUIManager;

/**
 * This is the Main class which contains the main method.
 *
 * @author Aaron Ebnöther
 */
public class Main {

    /**
     * This is the main method. It only contains one statement which launches the JavaFX Application.
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(GUIManager.class, args);
    }
}
