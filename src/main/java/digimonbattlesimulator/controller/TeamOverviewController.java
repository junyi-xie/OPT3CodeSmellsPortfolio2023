package digimonbattlesimulator.controller;

import digimonbattlesimulator.team.Team;
import digimonbattlesimulator.team.TeamBuilderFactory;
import digimonbattlesimulator.utils.ShowScene;
import digimonbattlesimulator.utils.layout.LayoutUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamOverviewController implements Initializable {
    @FXML
    private BorderPane teamOverviewBorderPane;
    @FXML
    private Label teamCount;
    @FXML
    private MFXButton newTeamButton;
    @FXML
    private MFXButton newBoxButton;
    public static ObservableList<Team> teams = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTeamOverview();
    }

    public void updateTeamOverview() {
        // Get the current team overview container and remove existing elements from index 1 and above
        VBox currentTeamOverviewContainer = (VBox) teamOverviewBorderPane.getCenter();
        currentTeamOverviewContainer.getChildren().remove(1, currentTeamOverviewContainer.getChildren().size());

        // Set team count
        teamCount.setText("All teams (" + teams.size() + ")");

        // Check if teams is empty; if empty, add "you have no team lol" text
        if (teams.isEmpty()) {
            currentTeamOverviewContainer.getChildren().add(new HBox(new Label("you have no team lol")));
        }

        // Create a container to hold all team VBoxes with vertical spacing
        VBox allTeamsVBox = new VBox();
        allTeamsVBox.setSpacing(5.0);

        for (Team team : teams) {
            // Create VBox for each team info view
            VBox teamInfoVBox = LayoutUtils.createCustomVBox(405.0, 50.0, Pos.CENTER, new Insets(5.0));
            teamInfoVBox.setOnMouseClicked(event -> onClickLoadTeamButton(event, team));

            // Create team name label
            Label teamNameLabel = new Label();
            teamNameLabel.setText("[" + team.getTeamBuilder().getTeamName() + "]");
            teamNameLabel.setFont(new Font("System Italic", 12.0));

            // Create an HBox for the team name label with center alignment and add team name label
            HBox teamNameLabelHBox = new HBox();
            teamNameLabelHBox.setAlignment(Pos.CENTER);
            teamNameLabelHBox.getChildren().add(teamNameLabel);

            // Create GridPane for digimon sprites
            GridPane spriteGridPane = new GridPane();
            spriteGridPane.setHgap(5.0);
            spriteGridPane.setMaxHeight(45.0);
            spriteGridPane.setPrefHeight(45.0);

            // Create column constraints to accommodate all possible Digimon in a single team container
            for (int i = 0; i < 8; i++) {
                spriteGridPane.getColumnConstraints().add(LayoutUtils.createColumn(45.0, 45.0, Priority.NEVER, HPos.CENTER));
            }

            // Add Digimon sprite to sprite GridPane
            for (int i = 0; i < team.getDigimonTeam().size(); i++) {
                spriteGridPane.add(LayoutUtils.createImageView(team.getDigimonTeam().get(i).getSpritePath(), 45.0, 45.0, true), i, 0);
            }

            // Create an HBox to wrap the SpriteGridPane
            HBox spriteGridPaneHBox = new HBox();
            spriteGridPaneHBox.getChildren().add(spriteGridPane);

            // Add Team name label and Digimon sprite VBox into singleTeamViewHBox
            teamInfoVBox.getChildren().addAll(teamNameLabelHBox, spriteGridPaneHBox);

            // Create HBox to hold teamInfoVBox and delete team button
            HBox singleTeamViewHBox = new HBox();
            singleTeamViewHBox.setAlignment(Pos.CENTER_LEFT);
            singleTeamViewHBox.setSpacing(10.0);
            singleTeamViewHBox.getChildren().addAll(teamInfoVBox, LayoutUtils.createCustomButton("Delete team", new Insets(5.0, 12.5, 5.0, 12.5), event -> onClickRemoveTeamButton(team)));

            // All the single team VBox to all teams VBox, so we can apply padding
            allTeamsVBox.getChildren().add(singleTeamViewHBox);
        }

        // Set the new content of the team overview container to the 'digimonTeamViewGridPane' wrapped in an VBox and update the center view
        currentTeamOverviewContainer.getChildren().addAll(allTeamsVBox);
        teamOverviewBorderPane.setCenter(currentTeamOverviewContainer);
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

        // Add observer
        TeamBuilderController.digimonTeam.addObserver(TeamBuilderController.digimonTeam.getTeamBuilder());
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }

    public void onClickLoadTeamButton(MouseEvent mouseEvent, Team team) {
        TeamBuilderController.digimonTeam = team;
        TeamBuilderController.digimonTeam.removeObserver(TeamBuilderController.digimonTeam.getTeamBuilder());
        TeamBuilderController.digimonTeam.addObserver(TeamBuilderController.digimonTeam.getTeamBuilder());

        ShowScene.switchScene((BorderPane) ((Node) mouseEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }

    public void onClickRemoveTeamButton(Team team) {
        teams.remove(team);
        updateTeamOverview();
    }
}