package jar.graphic;

import java.io.IOException;

import jar.controllers.HomeController;
import jar.model.dto.FolderDTO;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class FolderFx extends HBox implements ISelectable {

        private Label label = new Label("");

        public FolderFx(FolderDTO folder, HomeController hcontroller) {
                unselect();

                label.setText(folder.getName());

                ImageView imageView = new ImageView(new Image("/jar/images/folderRed.png"));
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);

                getChildren().addAll(imageView, label);

                setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                                select();
                                if (event.getClickCount() > 1) {
                                        try {
                                                hcontroller.changeFolder(folder.getIdElement(), folder.getName(), true);
                                        } catch (IOException e) {
                                                System.out.println("No se pudo cambiar de carpeta");
                                        }
                                } else {
                                        hcontroller.changeFileSelection(event);
                                }
                        }
                });
        }

        @Override
        public void select() {
                label.getStyleClass().setAll("folder-button__label", "folder-button__label--selected");
                getStyleClass().setAll("center-pane__folder-button", "center-pane__folder-button--selected");
        }

        @Override
        public void unselect() {
                label.getStyleClass().setAll("folder-button__label", "folder-button__label--unselected");
                getStyleClass().setAll("center-pane__folder-button", "center-pane__folder-button--unselected");
        }

}