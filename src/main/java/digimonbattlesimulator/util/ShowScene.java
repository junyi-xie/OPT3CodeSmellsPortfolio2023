package digimonbattlesimulator.util;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowScene {
    public static void switchScene(Stage currentStage, FXMLLoader loader) {
        Scene scene;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();

        newStage.setTitle("Digimon Battle Simulator");
        newStage.setMinHeight(600);
        newStage.setMinWidth(800);

        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        newStage.setScene(scene);
        newStage.show();

        currentStage.close();
    }
}