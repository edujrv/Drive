package jar.graphic;


import jar.controllers.HomeController;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;




public class FileFx extends VBox {
    public String id;
    private FileFx prevFile = null;
    private Label title = new Label();
    private Pane  pane = new Pane();
   // @FXML
    public FileFx(){
        this.setPrefSize(200,175);
        try {
            Image pic = new Image("jar/images/office.png");
            setTitle("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

            ImageView icon = new ImageView(pic);
            icon.setFitWidth(200);
            icon.setFitHeight(150);

            title.setMaxWidth(200);
            title.setStyle("-fx-font-size: 18;" +
                           " -fx-font: Normal 18 'Agency FB';" +
                           " -fx-padding: 10;" +
                           " -fx-text-fill: black");

            pane.getChildren().addAll(title);
            this.getChildren().addAll(icon,pane);

            this.setStyle("-fx-border-color: #bababa; " +
                            "-fx-border-width: 3;" +
                            "-fx-border-radius: 10; " +
                            "-fx-padding: 0; " +
                            "-fx-border-insets: 10 0 0 10");

            this.setAlignment(Pos.CENTER);
            this.setPrefSize(225,200);

            this.setOnMouseClicked(HomeController::fileSelected);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void setTitle(String title) {
        this.title.setText(title);

    }

    public void changeTitleBackgroundBlue(){
        title.setStyle("-fx-font-size: 18; " +
                       "-fx-font: Normal 18 'Agency FB';" +
                       "-fx-padding: 10;" +
                       "-fx-text-fill: #0b7bf3");

        this.pane.setStyle("-fx-background-color: #c4d5ff;");

        this.setStyle("-fx-border-color: #bababa; " +
                      "-fx-border-width: 3;" +
                      "-fx-border-radius: 10 ;" +
                      " -fx-border-insets: 10 0 0 10;" );

        this.setAlignment(Pos.CENTER);
        this.setPrefSize(225,200);

    }

    public void changeTitleBackgroundGray(){
        title.setStyle("-fx-font-size: 18; " +
                "-fx-font: Normal 18 'Agency FB';" +
                "-fx-padding: 10;" +
                "-fx-text-fill: #black");

        this.pane.setStyle("-fx-background-color: #transparent;");

        this.setStyle("-fx-border-color: #bababa; " +
                "-fx-border-width: 3;" +
                "-fx-border-radius: 10 ;" +
                " -fx-border-insets: 10 0 0 10;" );

        this.setAlignment(Pos.CENTER);
        this.setPrefSize(225,200);

    }
}
