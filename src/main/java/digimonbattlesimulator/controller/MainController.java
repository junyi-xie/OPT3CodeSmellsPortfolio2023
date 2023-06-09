package digimonbattlesimulator.controller;

import digimonbattlesimulator.util.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO v1.1
    }

    public void onClickBattleButton(ActionEvent actionEvent) {
        //TODO v1.1
    }

    public void onClickTeambuilderButton(ActionEvent actionEvent) {
        ShowScene.switchScene((Stage) ((Node) actionEvent.getSource()).getScene().getWindow(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }
}