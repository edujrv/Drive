package jar.graphic;

import jar.controllers.HomeController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class FolderFX extends HBox {

    public String id;
    private FolderFX prevFolder = null;
    private Label label = new Label("Folder");

    public FolderFX(){
            this.setPrefSize(300,75);

            try{
                ImageView imageView = new ImageView( new Image("/jar/images/folderRed.png"));
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);


                label.setMaxWidth(130);
                label.setStyle("-fx-font-size: 18;" +
                               " -fx-font: Normal 18 'Agency FB';" +
                               " -fx-padding: 0 0 0 25;" +
                               " -fx-text-fill: #3e3e3e");

                this.getChildren().addAll(imageView,label);

                this.setStyle("-fx-border-color: #bababa; " +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 10; " +
                        "-fx-padding: 0 0 0 25; " +
                        "-fx-border-insets: 15 0 0 20");

                this.setAlignment(Pos.CENTER_LEFT);
                this.setPrefSize(300,75);

                this.setOnMouseClicked(HomeController::folderSelected);

            }catch (Exception e){
                System.out.println(e);
            }


    }

    public void changeBlue(){
        label.setStyle("-fx-font-size: 18; " +
                "-fx-font: Normal 18 'Agency FB';" +
                " -fx-padding: 0 0 0 25;" +
                "-fx-text-fill: #0b7bf3");

        this.setStyle("-fx-border-color: #bababa; " +
                      " -fx-background-insets: 15 0 0 20;" +
                      " -fx-background-radius: 10;" +
                      " -fx-background-color: #c4d5ff;"+
                      "-fx-border-width: 1;" +
                      "-fx-border-radius: 10; " +
                      "-fx-padding: 0 0 0 25; " +
                      "-fx-border-insets: 15 0 0 20;"
                );

        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefSize(300,75);
    }


    public void changeGray(){
        label.setStyle("-fx-font-size: 18;" +
                " -fx-font: Normal 18 'Agency FB';" +
                " -fx-padding: 0 0 0 25;" +
                " -fx-text-fill: #3e3e3e");

        this.setStyle("-fx-border-color: #bababa; " +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10; " +
                "-fx-padding: 0 0 0 25; " +
                "-fx-border-insets: 15 0 0 20;" +
                " -fx-background-color: transparent");

        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefSize(300,75);
    }


}
