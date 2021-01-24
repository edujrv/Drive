package jar.graphic;

import java.io.IOException;

import jar.controllers.HomeController;
import jar.dao.FileDAO;
import jar.model.ContentType;
import jar.model.File;
import jar.model.dto.FileDTO;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FileFx extends VBox implements ISelectable {
    private Label title = new Label();
    private Pane pane = new Pane();

    public FileFx(FileDTO file) {

        title.setText(file.getName());
        title.setMaxWidth(230);
        title.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 20 0 0 30;"
                + " -fx-text-fill: black");

        ImageView icon = new ImageView(chooseImage(file.getContent().getContentType().getType()));
        icon.setFitWidth(275);
        icon.setFitHeight(210);

        pane.setPrefSize(300, 55);
        pane.getChildren().addAll(title);

        getChildren().addAll(icon, pane);

        setPrefSize(300, 300);
        setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; " + "-fx-padding: 0; "
                + "-fx-border-insets: 15 0 0 20");
        setAlignment(Pos.BOTTOM_CENTER);

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
                    HomeController.fileSelected(event);
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
        title.setStyle("-fx-font-size: 18; " + "-fx-font: Normal 18 'Agency FB';" + " -fx-padding: 20 0 0 30;"
                + "-fx-text-fill: #0b7bf3;");

        this.pane.setStyle("-fx-background-color: #c4d5ff;" + " -fx-background-radius: 0 0 10 10");

        this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10 ;"
                + " -fx-border-insets: 15 0 0 20;");

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefSize(300, 300);
    }

    @Override
    public void unselect() {
        title.setStyle("-fx-font-size: 18; " + "-fx-font: Normal 18 'Agency FB';" + " -fx-padding: 20 0 0 30;"
                + "-fx-text-fill: black");

        this.pane.setStyle("-fx-background-color: transparent;");

        this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10 ;"
                + " -fx-border-insets: 15 0 0 20;");

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefSize(300, 300);
    }
}
