package jar.graphic;

import jar.controllers.HomeController;
import jar.model.Folder;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class FolderFX extends HBox implements ISelectable {

        private Label label = new Label("Folder");

        public FolderFX() {
                this.setPrefSize(300, 75);

                ImageView imageView = new ImageView(new Image("/jar/images/folderRed.png"));
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);

                label.setMaxWidth(130);
                label.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 0 0 0 25;"
                                + " -fx-text-fill: #3e3e3e");

                this.getChildren().addAll(imageView, label);

                this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; "
                                + "-fx-padding: 0 0 0 25; " + "-fx-border-insets: 15 0 0 20");

                this.setAlignment(Pos.CENTER_LEFT);
                this.setPrefSize(300, 75);

                this.setOnMouseClicked(HomeController::fileSelected);
        }

        public FolderFX(Folder folder) {
                this.setPrefSize(300, 75);

                ImageView imageView = new ImageView(new Image("/jar/images/folderRed.png"));
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);

                label.setMaxWidth(130);
                label.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 0 0 0 25;"
                                + " -fx-text-fill: #3e3e3e");

                this.getChildren().addAll(imageView, label);

                this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; "
                                + "-fx-padding: 0 0 0 25; " + "-fx-border-insets: 15 0 0 20");

                this.setAlignment(Pos.CENTER_LEFT);
                this.setPrefSize(300, 75);

                this.setOnMouseClicked(HomeController::fileSelected);
        }

        @Override
        public void select() {
                label.setStyle("-fx-font-size: 18; " + "-fx-font: Normal 18 'Agency FB';" + " -fx-padding: 0 0 0 25;"
                                + "-fx-text-fill: #0b7bf3");

                this.setStyle("-fx-border-color: #bababa; " + " -fx-background-insets: 15 0 0 20;"
                                + " -fx-background-radius: 10;" + " -fx-background-color: #c4d5ff;"
                                + "-fx-border-width: 1;" + "-fx-border-radius: 10; " + "-fx-padding: 0 0 0 25; "
                                + "-fx-border-insets: 15 0 0 20;");

                this.setAlignment(Pos.CENTER_LEFT);
                this.setPrefSize(300, 75);

        }

        @Override
        public void unselect() {
                label.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 0 0 0 25;"
                                + " -fx-text-fill: #3e3e3e");

                this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; "
                                + "-fx-padding: 0 0 0 25; " + "-fx-border-insets: 15 0 0 20;"
                                + " -fx-background-color: transparent");

                this.setAlignment(Pos.CENTER_LEFT);
                this.setPrefSize(300, 75);
        }

}
