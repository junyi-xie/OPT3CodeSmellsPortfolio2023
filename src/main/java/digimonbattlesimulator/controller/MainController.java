package digimonbattlesimulator.controller;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickBattleButton(ActionEvent actionEvent) {
        //TODO v1.1
    }

    public void onClickTeambuilderButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml"));

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            Stage newStage = new Stage();

            newStage.setTitle("Digimon Battle Simulator");
            newStage.setMinHeight(600);
            newStage.setMinWidth(800);

            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            newStage.setScene(scene);
            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}