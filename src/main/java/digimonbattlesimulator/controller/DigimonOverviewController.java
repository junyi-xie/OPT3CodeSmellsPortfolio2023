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
        // Get the current digimon overview container and remove existing elements from index 1 and above
        VBox currentDigimonOverviewContainer = (VBox) digimonOverviewBorderPane.getCenter();
        currentDigimonOverviewContainer.getChildren().remove(1, currentDigimonOverviewContainer.getChildren().size());

        // Create a container for the attack techniques
        VBox attackTechniqueContainer = new VBox();
        attackTechniqueContainer.setSpacing(10.0);

        for (AttackTechnique attackTechnique : selectedDigimon.getAttackTechniques()) {
            // Create column constraints for the gridpane
            ColumnConstraints iconColumn        = LayoutUtils.createColumn(10.0, null, Priority.NEVER, HPos.LEFT);
            ColumnConstraints nameColumn        = LayoutUtils.createColumn(100.0, 100.0, Priority.SOMETIMES, HPos.LEFT);
            ColumnConstraints typeColumn        = LayoutUtils.createColumn(50.0, 50.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints powerColumn       = LayoutUtils.createColumn(50.0, 50.0, Priority.NEVER, HPos.LEFT);
            ColumnConstraints descriptionColumn = LayoutUtils.createColumn(10.0, null, Priority.ALWAYS, HPos.LEFT);

            // Create the gridpane and set column constraints
            GridPane attackTechniqueGridPane = LayoutUtils.createGridPaneWithColumns(iconColumn, nameColumn, typeColumn, powerColumn, descriptionColumn);
            attackTechniqueGridPane.setHgap(10.0);

            // Add attack technique information to the gridpane
            attackTechniqueGridPane.add(LayoutUtils.createImageView(attackTechnique.getIcon(), 40.0, 40.0, true), 0, 0);
            attackTechniqueGridPane.add(new Label(attackTechnique.getName()), 1, 0);
            attackTechniqueGridPane.add(new Label(attackTechnique.getType().toString()), 2, 0);
            attackTechniqueGridPane.add(new Label(attackTechnique.getPower()), 3, 0);
            attackTechniqueGridPane.add(new Label(attackTechnique.getDescription()), 4, 0);

            // Add the attack technique gridpane to the attack technique container
            attackTechniqueContainer.getChildren().add(attackTechniqueGridPane);
        }

        // Add the attack technique container to the digimon overview container
        currentDigimonOverviewContainer.getChildren().addAll(attackTechniqueContainer);
        digimonOverviewBorderPane.setCenter(currentDigimonOverviewContainer);
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