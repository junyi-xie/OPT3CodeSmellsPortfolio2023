package digimonbattlesimulator.utils.layout;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class LayoutUtils {
    public static GridPane createGridPaneWithColumns(ColumnConstraints... columnConstraints) {
        GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(columnConstraints);
        return gridPane;
    }

    public static ColumnConstraints createColumn(Double minWidth, Double maxWidth, Priority hgrow, HPos halignment) {
        ColumnConstraints column = new ColumnConstraints();
        if (minWidth != null) column.setMinWidth(minWidth);
        if (maxWidth != null) column.setMaxWidth(maxWidth);
        column.setHgrow(hgrow);
        column.setHalignment(halignment);
        return column;
    }

    public static ImageView createSpriteImageView(String spritePath) {
        ImageView sprite = new ImageView();
        sprite.setFitWidth(45.0);
        sprite.setFitHeight(45.0);
        sprite.setPreserveRatio(true);
        sprite.setImage(new Image(Objects.requireNonNull(LayoutUtils.class.getResource(spritePath)).toExternalForm()));
        return sprite;
    }

    public static VBox createStatsVbox(Label label, Text text) {
        VBox statsVBox = new VBox();
        statsVBox.getChildren().addAll(label, text);
        return statsVBox;
    }
}