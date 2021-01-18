package jar.graphic;

import jar.controllers.Efectos;
import jar.controllers.HomeController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.awt.*;
import javafx.event.Event;



public class FileFx extends VBox {
    public String id;
    private FileFx prevFile = null;
    private Label title = new Label();
    private Pane  pane = new Pane();
   // @FXML
    public FileFx(){
        this.setPrefSize(200,175);
        try {
            Image pic = new Image("jar/images/logoDrive.png");
            setTitle("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            changeTitleColor(0,0,0);

            ImageView icon = new ImageView(pic);
            icon.setFitWidth(200);
            icon.setFitHeight(150);

            pane.setPrefSize(200,50);
            pane.setStyle("-fx-padding: 0; -fx-border-radius: 10");

            title.setMaxWidth(200);
            title.setStyle("-fx-font-size: 18; -fx-font: Normal 18 'Agency FB'; -fx-padding: 0 0 0 0");


            pane.getChildren().addAll(title);
            this.getChildren().addAll(icon,pane);

            this.setStyle("-fx-border-color: #bababa; " +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 10; " +
                            "-fx-padding: 20; " +
                            "-fx-border-insets: 10 0 0 10");

             this.setOnMouseClicked(HomeController::fileSelected);



        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void setTitle(String title) {
        this.title.setText(title);

    }
    public void changeTitleColor(int red, int green, int blue){
        this.title.setTextFill(Color.rgb(red,green,blue));
    }
    public void changeTitleBackground(int red, int green, int blue){

        this.title.setStyle("-fx-background-color: "+red +" "+green+" "+blue);
    }

    public void changeTitleBackground(Color color){

        this.title.setStyle("-fx-background-color: color");
    }

    public void changeTitleBackground(){

        this.pane.setStyle("-fx-background-color: #80e0e2; "+
                            "-fx-font-size: 18; " +
                            "-fx-font: Normal 18 'Agency FB';" +
                            "-fx-padding: 0; -fx-border-insets: 0 0 0 0");


        this.setStyle("-fx-border-color: #099194; " +

                "-fx-border-width: 3;" +
                "-fx-border-radius: 10; " +
                "-fx-padding: 0; " +
                "-fx-border-insets: 10 0 0 10; -fx-pref-width: 200; -fx-pref-height: 175");
        this.setPrefSize(250,175);


    }


}
