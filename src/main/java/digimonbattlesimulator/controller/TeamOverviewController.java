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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Objects;
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
        teamCount.setText("All teams (" + teams.size() + ")");

        loadTeams();
    }

    //TODO fix all the variables names and make it better
    public void loadTeams() {

        VBox t = (VBox) teamOverviewBorderPane.getCenter();
        t.getChildren().remove(1, t.getChildren().size());

        if (teams.isEmpty()) {
            t.getChildren().add(new HBox(new Label("you have no team lol")));
        }

        VBox aa = new VBox();
        aa.setSpacing(5.0);

        for (Team team : teams) {
            // Create VBox with properties
            VBox vBox = new VBox();
            vBox.setMaxWidth(405.0);
            vBox.setStyle("-fx-border-color: linear-gradient(to right, #fe9819, #008cc7); -fx-border-radius: 4; -fx-background-color: transparent;");

            vBox.setOnMouseClicked(event -> onClickLoadTeamButton(event, team));


            // Create HBox for "Regular Team" label
            HBox labelHBox = new HBox();
            labelHBox.setAlignment(Pos.CENTER);
            Label label = new Label("[" + team.getTeamBuilder().getName() + "]");
            label.setFont(new Font("System Italic", 12.0));
            labelHBox.getChildren().add(label);

            // Create GridPane for images
            GridPane gridPane = new GridPane();
            gridPane.setHgap(5.0);
            gridPane.setMaxHeight(45.0);
            gridPane.setPrefHeight(45.0);

            // Create column constraints
            for (int i = 0; i < 8; i++) {
                gridPane.getColumnConstraints().add(makeColumn(45.0, 45.0, Priority.NEVER, HPos.CENTER));
            }

            for (int i = 0; i < team.getDigimonTeam().size(); i++) {
                // Create ImageViews and add them to the GridPane
                gridPane.add(loadSprite(team.getDigimonTeam().get(i).getSpritePath()), i, 0);
            }

            // Create HBox for GridPane
            HBox gridPaneHBox = new HBox();
            gridPaneHBox.getChildren().add(gridPane);

            // Set padding for VBox
            vBox.setPadding(new Insets(5.0));

            // Add children to VBox
            vBox.getChildren().addAll(labelHBox, gridPaneHBox);

            aa.getChildren().add(vBox);
        }


        t.getChildren().addAll(aa);

        teamOverviewBorderPane.setCenter(t);
    }


    //TODO remove code smells, duplicate code
    public ImageView loadSprite(String spritePath) {
        ImageView sprite = new ImageView();
        sprite.setFitWidth(45.0);
        sprite.setFitHeight(45.0);
        sprite.setPreserveRatio(true);
        sprite.setImage(new Image(Objects.requireNonNull(getClass().getResource(spritePath)).toExternalForm()));
        return sprite;
    }

    public ColumnConstraints makeColumn(Double minWidth, Double maxWidth, Priority hgrow, HPos halignment) {
        ColumnConstraints column = new ColumnConstraints();
        if (minWidth != null) column.setMinWidth(minWidth);
        if (maxWidth != null) column.setMaxWidth(maxWidth);
        column.setHgrow(hgrow);
        column.setHalignment(halignment);
        return column;
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

    public void onClickLoadTeamButton(MouseEvent actionEvent, Team team) {

        //TODO fix bug
        TeamBuilderController.digimonTeam = team;

        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }
}