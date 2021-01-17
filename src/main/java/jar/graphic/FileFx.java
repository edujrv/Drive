package jar.graphic;

import jar.controllers.Efectos;
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
    private FileFx prevFile = null;
    private Label title = new Label();
   // @FXML
    public FileFx(){
        this.setPrefSize(200,175);
        try {
            Image pic = new Image("jar/images/logoDrive.png");
            setTitle("HOLAAAAAAAAAAAAAAAAAA");
            changeTitleColor(0,0,0);

            ImageView icon = new ImageView(pic);
            icon.setFitWidth(200);
            icon.setFitHeight(150);

            title.relocate(0,155);
            title.setMaxWidth(200);
            title.setStyle("-fx-font-size: 18; -fx-font: Normal 18 'Agency FB'");
            title.setMinSize(200,25);

            this.getChildren().addAll(icon,title);

            this.setStyle("-fx-border-color: #bababa; " +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 10; " +
                            "-fx-padding: 20; " +
                            "-fx-border-insets: 10 5 0 5");

             this.setOnMouseClicked(this::fileSelected);



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
    public void changeTitleBackground(){

        this.title.setStyle("-fx-background-color: #099194; "+
                            "-fx-font-size: 18; " +
                            "-fx-font: Normal 18 'Agency FB'");
      /*  this.setStyle("-fx-border-color: #099194; " +
                "-fx-background-color: #80e0e2;"+
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10; " +
                "-fx-padding: 20; " +
                "-fx-border-insets: 10 5 0 5");
                */

    }

    @FXML
    public void fileSelected (Event e) {
        FileFx actualFile = (FileFx) e.getSource();
        String btnName = actualFile.getId();

        if(prevFile != null){
            prevFile.changeTitleColor(0,0,0);
            prevFile.setStyle("-fx-border-color: #000000; " +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 10; " +
                    "-fx-padding: 20; " +
                    "-fx-border-insets: 10 5 0 5");
        }
        prevFile = actualFile;
        prevFile.setId(actualFile.getId());

        //actualFile.changeTitleColor(9,145,148);
        actualFile.setEffect(Efectos.blueOn());
        actualFile.setStyle("-fx-border-color: #099194; " +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10; " +
                "-fx-padding: 20; " +
                "-fx-border-insets: 10 5 0 5");
        actualFile.changeTitleBackground();
    }
}
