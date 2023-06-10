package digimonbattlesimulator.controller;

import digimonbattlesimulator.digimon.*;
import digimonbattlesimulator.team.Team;
import digimonbattlesimulator.utils.ShowScene;
import digimonbattlesimulator.utils.layout.LayoutUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamBuilderController implements Initializable {
    @FXML
    private BorderPane teamBuilderBorderPane;
    @FXML
    private Label labelTeamName;
    public static Team digimonTeam = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add all available Digimon to list
        List<Digimon> availableDigimon = new ArrayList<>();
        availableDigimon.add(new Herta(92, 78, 150, 190));
        availableDigimon.add(new Agumon(100, 130, 80, 90));
        availableDigimon.add(new Deathmon(65, 136, 94, 135));
        availableDigimon.add(new Yukidarumon(101, 150, 100, 139));
        availableDigimon.add(new Birdramon(85, 170, 50, 125));
        availableDigimon.add(new Betamon(73, 97, 150, 120));
        createDigimonCell(availableDigimon);
        updateTeamViewCell();
    }

    public void updateTeamViewCell() {
        // Get the current team view container and remove existing elements from index 1 and above
        VBox currentTeamViewContainer = (VBox) teamBuilderBorderPane.getTop();
        currentTeamViewContainer.getChildren().remove(1, currentTeamViewContainer.getChildren().size());

        // Set the label text to the team name from the team builder
        labelTeamName.setText(digimonTeam.getTeamBuilder().getTeamName());

        // Check if Digimon team is empty; if empty, add "you have no digimon lol" text
        if (digimonTeam.getDigimonTeam().isEmpty()) {
            currentTeamViewContainer.getChildren().add(new HBox(new Label("you have no digimon lol")));
        }

        // Create a new GridPane for Digimon
        GridPane digimonTeamViewGridPane = new GridPane();
        digimonTeamViewGridPane.setHgap(10);

        for (int i = 0; i < digimonTeam.getDigimonTeam().size(); i++) {
            Digimon digimon = digimonTeam.getDigimonTeam().get(i);

            // Create column constraint
            ColumnConstraints digimonColumn = LayoutUtils.createColumn(87.5, 87.5, Priority.NEVER, HPos.LEFT);
            digimonTeamViewGridPane.getColumnConstraints().add(digimonColumn);

            // Create VBox for Digimon
            VBox digimonVBox = new VBox();
            digimonVBox.setAlignment(Pos.BOTTOM_CENTER);
            digimonVBox.setMaxSize(87.5, 200.0);
            digimonVBox.setPadding(new Insets(5.0));
            digimonVBox.setStyle("-fx-border-color: linear-gradient(to right, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4");
            digimonVBox.setOnMouseClicked(event -> onClickOverviewDigimonButton(event, digimon));

            // Add Digimon sprite and name label
            digimonVBox.getChildren().addAll(LayoutUtils.createImageView(digimon.getSpritePath(), 45.0, 45.0, true), new Label(digimon.getName()));

            // Add the Digimon VBox to digimonViewGridPane
            digimonTeamViewGridPane.add(digimonVBox, i, 0);
        }

        // Set the new content of the team view container to the digimonTeamViewGridPane wrapped in an HBox and update the top view
        currentTeamViewContainer.getChildren().addAll(new HBox(digimonTeamViewGridPane));
        teamBuilderBorderPane.setTop(currentTeamViewContainer);
    }

    public void createDigimonCell(List<Digimon> digimonList) {
        // Get the current digimon cell container
        VBox currentDigimonCellContainer = (VBox) teamBuilderBorderPane.getCenter();

        for (Digimon digimon : digimonList) {
            // Set up column constraints for the digimonGridPane
            ColumnConstraints spriteColumn  = LayoutUtils.createColumn(50.0, 50.0, Priority.SOMETIMES, HPos.CENTER);
            ColumnConstraints nameColumn    = LayoutUtils.createColumn(10.0, null, Priority.ALWAYS, HPos.LEFT);
            ColumnConstraints typeColumn    = LayoutUtils.createColumn(80.0, 80.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints abilityColumn = LayoutUtils.createColumn(90.0, 90.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints statsColumn   = LayoutUtils.createColumn(100.0, 100.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints buttonColumn  = LayoutUtils.createColumn(10.0, 100.0, Priority.NEVER, HPos.LEFT);

            // Add the column constraints to the digimonGridPane
            GridPane digimonGridPane = LayoutUtils.createGridPaneWithColumns(spriteColumn, nameColumn, typeColumn, abilityColumn, statsColumn, buttonColumn);
            digimonGridPane.setMinSize(10.0, 10.0);
            digimonGridPane.setMaxSize(800.0, 50.0);
            digimonGridPane.setHgap(10);

            // Add digimon sprite, name, type, ability, stats, and add button to the digimonGridPane
            digimonGridPane.add(LayoutUtils.createImageView(digimon.getSpritePath(), 45.0, 45.0, true), 0, 0);
            digimonGridPane.add(new Label(digimon.getName()), 1, 0);
            digimonGridPane.add(new Label(digimon.getType().toString()), 2, 0);
            digimonGridPane.add(new Label(digimon.getAbility().toString()), 3, 0);
            digimonGridPane.add(createDigimonStatsGridPane(digimon), 4, 0);
            digimonGridPane.add(createAddDigimonButton(digimon), 5, 0);

            // Add the digimonGridPane to the digimon cell container
            currentDigimonCellContainer.getChildren().add(digimonGridPane);
        }

        // Update the center view of the BorderPane with the digimon cell container
        teamBuilderBorderPane.setCenter(currentDigimonCellContainer);
    }

    public GridPane createDigimonStatsGridPane(Digimon digimon) {
        // Create column constraints for each Digimon stat column
        ColumnConstraints hpColumn  = LayoutUtils.createColumn(10.0, null, Priority.SOMETIMES, HPos.LEFT);
        ColumnConstraints atkColumn = LayoutUtils.createColumn(10.0, null, Priority.SOMETIMES, HPos.LEFT);
        ColumnConstraints defColumn = LayoutUtils.createColumn(10.0, null, Priority.SOMETIMES, HPos.LEFT);
        ColumnConstraints spdColumn = LayoutUtils.createColumn(10.0, null, Priority.SOMETIMES, HPos.LEFT);

        // Add the column constraints to the digimonStatsGridPane
        GridPane digimonStatsGridPane = LayoutUtils.createGridPaneWithColumns(hpColumn, atkColumn, defColumn, spdColumn);

        // Create VBox for each stat with corresponding label and value
        VBox hpVBox  = LayoutUtils.createStatsVbox(new Label("HP"), new Text(digimon.getHitpoints()));
        VBox atkVBox = LayoutUtils.createStatsVbox(new Label("Atk"), new Text(digimon.getAttack()));
        VBox defVBox = LayoutUtils.createStatsVbox(new Label("Def"), new Text(digimon.getDefense()));
        VBox speVBox = LayoutUtils.createStatsVbox(new Label("Spe"), new Text(digimon.getAgility()));

        // Add the stat VBoxes to the digimonStatsGridPane
        digimonStatsGridPane.add(hpVBox, 0, 0);
        digimonStatsGridPane.add(atkVBox, 1, 0);
        digimonStatsGridPane.add(defVBox, 2, 0);
        digimonStatsGridPane.add(speVBox, 3, 0);

        return digimonStatsGridPane;
    }

    public void onClickBackToTeamOverviewButton(ActionEvent actionEvent) {
        if (!TeamOverviewController.teams.contains(digimonTeam)) TeamOverviewController.teams.add(digimonTeam);
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamOverview.fxml")));
    }

    public void onClickOverviewDigimonButton(MouseEvent mouseEvent, Digimon digimon) {
        DigimonOverviewController.selectedDigimon = digimon;
        ShowScene.switchScene((BorderPane) ((Node) mouseEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/DigimonOverview.fxml")));
    }

    public void onClickAddDigimonButton(Digimon digimon) {
        if (digimonTeam.getDigimonTeam().size() >= digimonTeam.getTeamBuilder().getMaxTeamSize()) return;
        digimonTeam.addDigimon(digimon);
        updateTeamViewCell();
    }

    public MFXButton createAddDigimonButton(Digimon digimon) {
        MFXButton addDigimonButton = new MFXButton("Add to team");
        addDigimonButton.setStyle("-fx-border-color: linear-gradient(to left, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4; -fx-background-radius: 4; -fx-background-color: transparent; -fx-text-fill: rgba(0, 0, 0, 1);");
        addDigimonButton.setPadding(new Insets(5.0, 12.5, 5.0, 12.5));
        addDigimonButton.setOnAction(event -> onClickAddDigimonButton(digimon));
        return addDigimonButton;
    }
}