package jar.graphic;

import jar.controllers.HomeController;
import jar.dao.FileDAO;
import jar.model.ContentType;
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
                    FileDAO.downloadFile(file);
                } else {
                    hcontroller.changeFileSelection(event);
                }
            }
        });
    }

    private Image chooseImage(ContentType.TYPE type) {
        switch (type) {
            case OFFICE:
                return new Image("jar/images/by-content-type/office.png");
            case IMAGE:
                return new Image("jar/images/by-content-type/image.png");
            case PDF:
                return new Image("jar/images/by-content-type/pdf.png");
            case VIDEO:
                return new Image("jar/images/by-content-type/video.png");
            case AUDIO:
                return new Image("jar/images/by-content-type/audio.png");
            case TEXT:
                return new Image("jar/images/by-content-type/text.png");
                
            default:
                return new Image("jar/images/by-content-type/unknown.png");
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
