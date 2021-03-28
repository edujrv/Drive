package jar.graphic;

import java.io.IOException;

import jar.controllers.HomeController;
import jar.dao.FileDAO;
import jar.model.ContentType;
import jar.model.File;
import jar.model.dto.FileDTO;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FileFx extends VBox implements ISelectable {
    private Label title = new Label();
    private Pane pane = new Pane();

    public FileFx(FileDTO file, HomeController hcontroller) {
        getStyleClass().setAll("center-pane__file-button");
        unselect();

        title.setText(file.getName());

        ImageView icon = new ImageView(chooseImage(file.getContent().getContentType().getType()));
        icon.setFitWidth(275);
        icon.setFitHeight(210);

        pane.getChildren().addAll(title);
        getChildren().addAll(icon, pane);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                select();
                if (event.getClickCount() > 1) {
                    File aux = null;
                    try {
                        aux = FileDAO.getFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(aux.getName() + " || " + aux.getPath() + " || "
                            + aux.getContent().getContentType().getType());
                } else {
                    hcontroller.changeFileSelection(event);
                }
            }
        });
    }

    private Image chooseImage(ContentType.TYPE type) {
        switch (type) {
        case OFFICE:
            return new Image("jar/images/office.png");

        // TODO: Poner imagen para UNKNOWN
        default:
            return new Image("jar/images/office.png");
        }
    }

    @Override
    public void select() {
        title.getStyleClass().setAll("file-button__title", "file-button__title--selected");
        pane.getStyleClass().setAll("file-button__pane", "file-button__pane--selected");
    }

    @Override
    public void unselect() {
        title.getStyleClass().setAll("file-button__title", "file-button__title--unselected");
        pane.getStyleClass().setAll("file-button__pane", "file-button__pane--unselected");
    }
}
