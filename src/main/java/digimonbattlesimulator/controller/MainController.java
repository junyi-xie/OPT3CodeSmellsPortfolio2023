package digimonbattlesimulator.controller;

import digimonbattlesimulator.utils.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO v1.1
    }

    public void onClickBattleButton(ActionEvent actionEvent) {
        //TODO v1.1
        System.out.println("Coming soon...");
    }

    public void onClickTeambuilderButton(ActionEvent actionEvent) {
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamOverview.fxml")));
    }
}