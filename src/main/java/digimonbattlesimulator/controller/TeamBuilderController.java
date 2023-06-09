package digimonbattlesimulator.controller;

import digimonbattlesimulator.util.ShowScene;
import digimonbattlesimulator.digimon.Agumon;
import digimonbattlesimulator.digimon.Deathmon;
import digimonbattlesimulator.digimon.Digimon;
import digimonbattlesimulator.digimon.Yukidarumon;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeamBuilderController implements Initializable {
    @FXML
    private BorderPane teamBuilderBorderPane;

    private ObservableList<Digimon> addedDigimon = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add all available Digimon to list
        List<Digimon> digimonList = new ArrayList<>();
        digimonList.add(new Agumon(100, 130, 80, 90));
        digimonList.add(new Deathmon(65, 136, 94, 135));
        digimonList.add(new Yukidarumon(101, 150, 100, 139));
        createDigimonCell(digimonList);
    }

    public void onClickBackButton(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/Main.fxml"));
        ShowScene.switchScene(currentStage, loader);
    }

    public void onClickAddDigimonButton(Digimon digimon) {
        addedDigimon.add(digimon);
        updateTeamViewCell();
    }


    public void updateTeamViewCell() {

        // Get the current team view container and remove existing elements from index 1 and above
        VBox currentTeamViewContainer = (VBox) teamBuilderBorderPane.getTop();
        currentTeamViewContainer.getChildren().remove(1, currentTeamViewContainer.getChildren().size());

        // Create a new GridPane for Digimon
        GridPane digimonViewGridPane = new GridPane() {{
            setHgap(10);
        }};

        for (int i = 0; i < addedDigimon.size(); i++) {
            Digimon digimon = addedDigimon.get(i);

            // Create column constraint
            ColumnConstraints digimonColumn = makeColumn(90.0, 90.0, Priority.NEVER);
            digimonViewGridPane.getColumnConstraints().add(digimonColumn);

            // Create VBox for Digimon
            VBox digimonVBox = new VBox() {{
               setAlignment(Pos.BOTTOM_CENTER);
               setMaxSize(100.0, 80.0);
               setStyle("-fx-border-color: linear-gradient(to left, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4");
            }};

            // Add Digimon sprite and name label
            digimonVBox.getChildren().addAll(loadSprite(digimon.getSpritePath()), new Label(digimon.getName()));
            digimonVBox.setPadding(new Insets(5.0));

            // Add the Digimon VBox to digimonViewGridPane
            digimonViewGridPane.add(digimonVBox, i, 0);
        }

        // Set the new content of the team view container to the digimonViewGridPane wrapped in an HBox and update the top view
        currentTeamViewContainer.getChildren().addAll(new HBox(digimonViewGridPane));
        teamBuilderBorderPane.setTop(currentTeamViewContainer);
    }

    public void createDigimonCell(List<Digimon> digimonList) {

        VBox test = (VBox) teamBuilderBorderPane.getCenter();

        for (Digimon digimon : digimonList) {

            GridPane gridPane = new GridPane() {{
                setPadding(new Insets(10));
                setMinSize(10.0, 10.0);
                setMaxSize(780.0, 50.0);
                setHgap(10);
            }};

            ColumnConstraints spriteColumn = makeColumn(50.0, 50.0, Priority.SOMETIMES);
            ColumnConstraints nameColumn = makeColumn(10.0, null, Priority.ALWAYS);
            ColumnConstraints typeColumn = makeColumn(100.0, null, Priority.SOMETIMES);
            ColumnConstraints abilityColumn = makeColumn(100.0, null, Priority.SOMETIMES);
            ColumnConstraints statsColumn = makeColumn(100.0, null, Priority.NEVER);
            ColumnConstraints buttonColumn = makeColumn(10.0, null, Priority.SOMETIMES);

            gridPane.getColumnConstraints().addAll(spriteColumn, nameColumn, typeColumn, abilityColumn, statsColumn, buttonColumn);

            gridPane.add(loadSprite(digimon.getSpritePath()), 0, 0);
            gridPane.add(new Label(digimon.getName()), 1, 0);
            gridPane.add(new Label(digimon.getType().toString()), 2, 0);
            gridPane.add(new Label(digimon.getAbility().toString()), 3, 0);

            MFXButton addButton = new MFXButton("Add to team");

            addButton.setOnAction(event -> onClickAddDigimonButton(digimon));

            gridPane.add(addButton, 5, 0);


            GridPane innerGridPane = new GridPane();

            ColumnConstraints hpColumn = makeColumn(10.0, null, Priority.SOMETIMES);
            ColumnConstraints atkColumn = makeColumn(10.0, null, Priority.SOMETIMES);
            ColumnConstraints defColumn = makeColumn(10.0, null, Priority.SOMETIMES);
            ColumnConstraints spdColumn = makeColumn(10.0, null, Priority.SOMETIMES);

            innerGridPane.getColumnConstraints().addAll(hpColumn, atkColumn, defColumn, spdColumn);

            VBox hpVBox = makeStatsVbox(new Label("HP"), new Text(digimon.getHitpoints()));
            VBox atkVBox = makeStatsVbox(new Label("Atk"), new Text(digimon.getAttack()));
            VBox defVBox = makeStatsVbox(new Label("Def"), new Text(digimon.getDefense()));
            VBox spdVBox = makeStatsVbox(new Label("Spd"), new Text(digimon.getAgility()));

            innerGridPane.add(hpVBox, 0, 0);
            innerGridPane.add(atkVBox, 1, 0);
            innerGridPane.add(defVBox, 2, 0);
            innerGridPane.add(spdVBox, 3, 0);

            gridPane.add(innerGridPane, 4, 0);

            test.getChildren().add(gridPane);
        }

        teamBuilderBorderPane.setCenter(test);
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