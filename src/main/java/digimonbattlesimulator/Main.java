package digimonbattlesimulator;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Digimon Battle Simulator");
        stage.setMinHeight(600);
        stage.setMinWidth(800);

        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("images/digimon_icon.png"))));

        MFXThemeManager.addOn(scene, Themes.DEFAULT);

        stage.setScene(scene);
        stage.show();
    }
}