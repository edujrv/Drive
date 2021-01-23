package jar.graphic;

import jar.controllers.HomeController;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import jar.model.ContentType;
import jar.model.File;

public class FileFx extends VBox implements ISelectable {
    private Label title = new Label();
    private Pane pane = new Pane();

    // TODO: BORRAR
    public FileFx() {
        this.setPrefSize(300, 300);

        setTitle("TEST");

        title.setMaxWidth(230);
        title.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 20 0 0 30;"
                + " -fx-text-fill: black");

        pane.setPrefSize(300, 55);
        pane.getChildren().addAll(title);

        ImageView icon = new ImageView(chooseImage(ContentType.TYPE.OFFICE));
        icon.setFitWidth(275);
        icon.setFitHeight(210);

        this.getChildren().addAll(icon, pane);

        this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; "
                + "-fx-padding: 0; " + "-fx-border-insets: 15 0 0 20");

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefSize(300, 300);

        this.setOnMouseClicked(HomeController::fileSelected);

    }

    public FileFx(File file) {
        this.setPrefSize(300, 300);

        setTitle(file.getName());

        title.setMaxWidth(230);
        title.setStyle("-fx-font-size: 18;" + " -fx-font: Normal 18 'Agency FB';" + " -fx-padding: 20 0 0 30;"
                + " -fx-text-fill: black");

        pane.setPrefSize(300, 55);
        pane.getChildren().addAll(title);

        ImageView icon = new ImageView(chooseImage(file.getContent().getContentType().getType()));
        icon.setFitWidth(275);
        icon.setFitHeight(210);

        this.getChildren().addAll(icon, pane);

        this.setStyle("-fx-border-color: #bababa; " + "-fx-border-width: 1;" + "-fx-border-radius: 10; "
                + "-fx-padding: 0; " + "-fx-border-insets: 15 0 0 20");

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefSize(300, 300);

        this.setOnMouseClicked(HomeController::fileSelected);
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

    private void setTitle(String title) {
        this.title.setText(title);
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
