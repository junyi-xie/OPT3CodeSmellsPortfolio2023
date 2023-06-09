package digimonbattlesimulator.controller;

import digimonbattlesimulator.digimon.Digimon;
import digimonbattlesimulator.digimon.Agumon;
import digimonbattlesimulator.digimon.Betamon;
import digimonbattlesimulator.digimon.Birdramon;
import digimonbattlesimulator.digimon.Deathmon;
import digimonbattlesimulator.digimon.Yukidarumon;
import digimonbattlesimulator.team.Team;
import digimonbattlesimulator.util.ShowScene;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Objects;
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
        availableDigimon.add(new Agumon(100, 130, 80, 90));
        availableDigimon.add(new Deathmon(65, 136, 94, 135));
        availableDigimon.add(new Yukidarumon(101, 150, 100, 139));
        availableDigimon.add(new Birdramon(85, 170, 50, 125));
        availableDigimon.add(new Betamon(73, 97, 150, 120));
        createDigimonCell(availableDigimon);
        // Add observer
        digimonTeam.addObserver(digimonTeam.getTeamBuilder());

        // Initialize
        updateTeamViewCell();
        //TODO fix causing duplicates

        if (!TeamOverviewController.teams.contains(digimonTeam)) {
            TeamOverviewController.teams.add(digimonTeam);
        }

        labelTeamName.setText(digimonTeam.getTeamBuilder().getName());
    }

    public void onClickBackButton(ActionEvent actionEvent) {
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamOverview.fxml")));
    }

    public void onClickOverviewDigimonButton(ActionEvent actionEvent) {
        //TODO view digimon detail like attack techniques, typing, stats and ability
    }

    public void onClickAddDigimonButton(Digimon digimon) {
        if (digimonTeam.getDigimonTeam().size() >= digimonTeam.getTeamBuilder().getMaxTeamSize()) return;
        digimonTeam.addDigimon(digimon);
        updateTeamViewCell();
    }

    public void onClickRemoveDigimonButton(Digimon digimon) {
        digimonTeam.removeDigimon(digimon);
        updateTeamViewCell();
    }

    public void updateTeamViewCell() {
        // Get the current team view container and remove existing elements from index 1 and above
        VBox currentTeamViewContainer = (VBox) teamBuilderBorderPane.getTop();
        currentTeamViewContainer.getChildren().remove(1, currentTeamViewContainer.getChildren().size());

        // Check if Digimon team is empty; if empty, add "you have no digimon lol" text
        if (digimonTeam.getDigimonTeam().isEmpty()) {
            currentTeamViewContainer.getChildren().add(new HBox(new Label("you have no digimon lol")));
        }

        // Create a new GridPane for Digimon
        GridPane digimonTeamViewGridPane = new GridPane() {{
            setHgap(10);
        }};

        for (int i = 0; i < digimonTeam.getDigimonTeam().size(); i++) {
            Digimon digimon = digimonTeam.getDigimonTeam().get(i);

            // Create column constraint
            ColumnConstraints digimonColumn = makeColumn(87.5, 87.5, Priority.NEVER);
            digimonTeamViewGridPane.getColumnConstraints().add(digimonColumn);

            // Create VBox for Digimon
            VBox digimonVBox = new VBox() {{
               setAlignment(Pos.BOTTOM_CENTER);
               setMaxSize(97.5, 200.0);
               setStyle("-fx-border-color: linear-gradient(to left, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4");
            }};

            // Add Digimon sprite and name label
            digimonVBox.getChildren().addAll(createRemoveDigimonButton(digimon), loadSprite(digimon.getSpritePath()), new Label(digimon.getName()));
            digimonVBox.setPadding(new Insets(5.0));

            // Add the Digimon VBox to digimonViewGridPane
            digimonTeamViewGridPane.add(digimonVBox, i, 0);
        }

        // Set the new content of the team view container to the digimonTeamViewGridPane wrapped in an HBox and update the top view
        currentTeamViewContainer.getChildren().addAll(new HBox(digimonTeamViewGridPane));
        teamBuilderBorderPane.setTop(currentTeamViewContainer);
    }

    public void createDigimonCell(List<Digimon> digimonList) {
        // Get the current digimon cell container
        VBox currentDigimonCells = (VBox) teamBuilderBorderPane.getCenter();

        for (Digimon digimon : digimonList) {
            // Create GridPane for each Digimon
            GridPane digimonGridPane = new GridPane() {{
                setPadding(new Insets(10));
                setMinSize(10.0, 10.0);
                setMaxSize(780.0, 50.0);
                setHgap(10);
            }};

            // Set up column constraints for the digimonGridPane
            ColumnConstraints spriteColumn  = makeColumn(50.0, 50.0, Priority.SOMETIMES);
            ColumnConstraints nameColumn    = makeColumn(10.0, null, Priority.ALWAYS);
            ColumnConstraints typeColumn    = makeColumn(100.0, 100.0, Priority.SOMETIMES);
            ColumnConstraints abilityColumn = makeColumn(100.0, 100.0, Priority.SOMETIMES);
            ColumnConstraints statsColumn   = makeColumn(100.0, 100.0, Priority.NEVER);
            ColumnConstraints buttonColumn  = makeColumn(10.0, 100.0, Priority.SOMETIMES);

            // Add the column constraints to the digimonGridPane
            digimonGridPane.getColumnConstraints().addAll(spriteColumn, nameColumn, typeColumn, abilityColumn, statsColumn, buttonColumn);

            // Add digimon sprite, name, type, ability, stats, and add button to the digimonGridPane
            digimonGridPane.add(loadSprite(digimon.getSpritePath()), 0, 0);
            digimonGridPane.add(new Label(digimon.getName()), 1, 0);
            digimonGridPane.add(new Label(digimon.getType().toString()), 2, 0);
            digimonGridPane.add(new Label(digimon.getAbility().toString()), 3, 0);
            digimonGridPane.add(createDigimonStatsGridPane(digimon), 4, 0);
            digimonGridPane.add(createAddDigimonButton(digimon), 5, 0);

            // Add the digimonGridPane to the digimon cell container
            currentDigimonCells.getChildren().add(digimonGridPane);
        }

        // Update the center view of the BorderPane with the digimon cell container
        teamBuilderBorderPane.setCenter(currentDigimonCells);
    }

    public GridPane createDigimonStatsGridPane(Digimon digimon) {
        // Create a new GridPane to hold the stats of the Digimon
        GridPane digimonStatsGridPane = new GridPane();

        // Create column constraints for each Digimon stat column
        ColumnConstraints hpColumn  = makeColumn(10.0, null, Priority.SOMETIMES);
        ColumnConstraints atkColumn = makeColumn(10.0, null, Priority.SOMETIMES);
        ColumnConstraints defColumn = makeColumn(10.0, null, Priority.SOMETIMES);
        ColumnConstraints spdColumn = makeColumn(10.0, null, Priority.SOMETIMES);

        // Add the column constraints to the digimonStatsGridPane
        digimonStatsGridPane.getColumnConstraints().addAll(hpColumn, atkColumn, defColumn, spdColumn);

        // Create VBox for each stat with corresponding label and value
        VBox hpVBox  = makeStatsVbox(new Label("HP"), new Text(digimon.getHitpoints()));
        VBox atkVBox = makeStatsVbox(new Label("Atk"), new Text(digimon.getAttack()));
        VBox defVBox = makeStatsVbox(new Label("Def"), new Text(digimon.getDefense()));
        VBox speVBox = makeStatsVbox(new Label("Spe"), new Text(digimon.getAgility()));

        // Add the stat VBoxes to the digimonStatsGridPane
        digimonStatsGridPane.add(hpVBox, 0, 0);
        digimonStatsGridPane.add(atkVBox, 1, 0);
        digimonStatsGridPane.add(defVBox, 2, 0);
        digimonStatsGridPane.add(speVBox, 3, 0);

        return digimonStatsGridPane;
    }

    public MFXButton createAddDigimonButton(Digimon digimon) {
        MFXButton addDigimonButton = new MFXButton("Add to team");
        addDigimonButton.setStyle("-fx-font-weight: 700; -fx-border-color: linear-gradient(to right, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4; -fx-background-radius: 4; -fx-background-color: transparent; -fx-text-fill: rgba(0, 0, 0, 1);");
        addDigimonButton.setOnAction(event -> onClickAddDigimonButton(digimon));
        return addDigimonButton;
    }

    public MFXButton createRemoveDigimonButton(Digimon digimon) {
        MFXButton removeDigimonButton = new MFXButton("Remove");
        removeDigimonButton.setOnAction(event -> onClickRemoveDigimonButton(digimon));
        return removeDigimonButton;
    }

    public ColumnConstraints makeColumn(Double minWidth, Double maxWidth, Priority hgrow) {
        ColumnConstraints column = new ColumnConstraints();
        if (minWidth != null) column.setMinWidth(minWidth);
        if (maxWidth != null) column.setMaxWidth(maxWidth);
        column.setHgrow(hgrow);
        return column;
    }

    public VBox makeStatsVbox(Label label, Text text) {
        VBox statsVBox = new VBox();
        statsVBox.getChildren().addAll(label, text);
        return statsVBox;
    }

    public ImageView loadSprite(String spritePath) {
        ImageView sprite = new ImageView();
        sprite.setFitWidth(45.0);
        sprite.setFitHeight(45.0);
        sprite.setPreserveRatio(true);
        sprite.setImage(new Image(Objects.requireNonNull(getClass().getResource(spritePath)).toExternalForm()));
        return sprite;
    }
}