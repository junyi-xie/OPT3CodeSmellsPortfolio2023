package digimonbattlesimulator.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ShowScene {
    public static void switchScene(BorderPane currentPane, FXMLLoader loader) {
        try {
            currentPane.setCenter(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}