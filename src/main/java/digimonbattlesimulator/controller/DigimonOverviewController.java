package digimonbattlesimulator.controller;

import digimonbattlesimulator.digimon.AttackTechnique;
import digimonbattlesimulator.digimon.Digimon;
import digimonbattlesimulator.utils.ShowScene;
import digimonbattlesimulator.utils.layout.LayoutUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DigimonOverviewController implements Initializable {
    @FXML
    private BorderPane digimonOverviewBorderPane;
    @FXML
    private ImageView digimonSprite;
    @FXML
    private Label digimonNameLabel;
    @FXML
    private Label digimonCharacteristicsLabel;
    @FXML
    private Label digimonTypeLabel;
    @FXML
    private Label digimonAbilityLabel;
    @FXML
    private Label digimonHpStats;
    @FXML
    private Label digimonAtkStats;
    @FXML
    private Label digimonDefStats;
    @FXML
    private Label digimonSpeStats;
    public static Digimon selectedDigimon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDigimonDetails(selectedDigimon);
        createAttackTechniqueCell();
    }

    public void updateDigimonDetails(Digimon digimon) {
        // Update the details of the Digimon based on the provided Digimon object
        digimonSprite.setImage(new Image(Objects.requireNonNull(LayoutUtils.class.getResource(digimon.getSpritePath())).toExternalForm()));
        digimonNameLabel.setText(digimon.getName());
        digimonCharacteristicsLabel.setText(digimon.getCharacteristic());
        digimonTypeLabel.setText(digimon.getType().toString());
        digimonAbilityLabel.setText(digimon.getAbility().toString());
        digimonHpStats.setText(digimon.getHitpoints());
        digimonAtkStats.setText(digimon.getAttack());
        digimonDefStats.setText(digimon.getDefense());
        digimonSpeStats.setText(digimon.getAgility());
    }

    public void createAttackTechniqueCell() {

        //TODO fix variable names, was too lazy
        VBox t = (VBox) digimonOverviewBorderPane.getCenter();
        t.getChildren().remove(1, t.getChildren().size());

        VBox aa = new VBox();
        aa.setSpacing(10.0);

        for (AttackTechnique attackTechnique : selectedDigimon.getAttackTechniques()) {

            ColumnConstraints iconColumn        = LayoutUtils.createColumn(10.0, null, Priority.NEVER, HPos.LEFT);
            ColumnConstraints nameColumn        = LayoutUtils.createColumn(150.0, 150.0, Priority.SOMETIMES, HPos.LEFT);
            ColumnConstraints typeColumn        = LayoutUtils.createColumn(60.0, 60.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints powerColumn       = LayoutUtils.createColumn(80.0, 80.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints descriptionColumn = LayoutUtils.createColumn(10.0, null, Priority.ALWAYS, HPos.LEFT);

            GridPane gridPane = LayoutUtils.createGridPaneWithColumns(iconColumn, nameColumn, typeColumn, powerColumn, descriptionColumn);
            gridPane.setHgap(10.0);

            gridPane.add(LayoutUtils.createImageView(attackTechnique.getIcon(), 40.0, 40.0, true), 0, 0);
            gridPane.add(new Label(attackTechnique.getName()), 1, 0);
            gridPane.add(new Label(attackTechnique.getType().toString()), 2, 0);
            gridPane.add(new Label(attackTechnique.getPower()), 3, 0);
            gridPane.add(new Label(attackTechnique.getDescription()), 4, 0);

            //TODO fix text overflowing

            aa.getChildren().add(gridPane);
        }


        t.getChildren().addAll(aa);
        digimonOverviewBorderPane.setCenter(t);
    }

    public void onClickBackToTeambuilderButton(ActionEvent actionEvent) {
        ShowScene.switchScene((BorderPane) ((Node) actionEvent.getSource()).getScene().getRoot(), new FXMLLoader(getClass().getResource("/digimonbattlesimulator/fxml/TeamBuilder.fxml")));
    }

    public void onClickRemoveDigimon(ActionEvent actionEvent) {
        TeamBuilderController.digimonTeam.removeDigimon(selectedDigimon);

        // Back to TeamBuilderController
        onClickBackToTeambuilderButton(actionEvent);
    }
}