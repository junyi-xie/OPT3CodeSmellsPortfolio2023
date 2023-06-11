package digimonbattlesimulator.utils.layout;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public static ImageView createImageView(String path, double width, double height, boolean preserveRatio) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setImage(new Image(Objects.requireNonNull(LayoutUtils.class.getResource(path)).toExternalForm()));
        return imageView;
    }

    public static VBox createStatsVbox(Label label, Text text) {
        VBox statsVBox = new VBox();
        statsVBox.getChildren().addAll(label, text);
        return statsVBox;
    }

    public static VBox createCustomVBox(double maxWidth, double maxHeight, Pos alignment, Insets insets) {
        VBox customVBox = new VBox();
        customVBox.setMaxSize(maxWidth, maxHeight);
        customVBox.setAlignment(alignment);
        customVBox.setPadding(insets);
        customVBox.setStyle("-fx-border-color: linear-gradient(to right, #fe9819, #008cc7); -fx-border-radius: 4; -fx-background-color: transparent;");
        return customVBox;
    }

    public static MFXButton createCustomButton(String text, Insets insets, EventHandler<ActionEvent> onClick) {
        MFXButton customButton = new MFXButton(text);
        customButton.setPadding(insets);
        customButton.setStyle("-fx-border-color: linear-gradient(to left, #fe9819, #008cc7); -fx-border-width: 1; -fx-border-radius: 4; -fx-background-radius: 4; -fx-background-color: transparent; -fx-text-fill: rgba(0, 0, 0, 1);");
        customButton.setOnAction(onClick);
        return customButton;
    }
}