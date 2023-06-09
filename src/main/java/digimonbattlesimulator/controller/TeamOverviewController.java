package digimonbattlesimulator.controller;

import digimonbattlesimulator.team.Team;
import digimonbattlesimulator.team.TeamBuilderFactory;
import digimonbattlesimulator.util.ShowScene;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamOverviewController implements Initializable {
    @FXML
    private Label teamCount;
    @FXML
    private MFXButton newTeamButton;
    @FXML
    private MFXButton newBoxButton;
    public static ObservableList<Team> teams = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teamCount.setText("All teams (" + teams.size() + ")");
    }

    public void onClickBackToMainButton(ActionEvent actionEvent) {
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/Main.fxml")));
    }

    public void onClickTeambuilderButton(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == newTeamButton) {
            TeamBuilderController.digimonTeam = TeamBuilderFactory.createRegularTeam("Regular Team", 4);
        } else if (source == newBoxButton) {
            TeamBuilderController.digimonTeam = TeamBuilderFactory.createCustomTeam("Custom Team", 8);
        }

        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }

    public void onClickLoadTeamButton(ActionEvent actionEvent) {
        //TODO
    }
}