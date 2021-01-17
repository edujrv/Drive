package jar.graphic;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.awt.*;

public class FileFx extends Pane{

   // @FXML
    public FileFx(){
        System.out.println("GSDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        this.setPrefSize(200,175);
        try {
            Image pic = new Image("jar/images/logoDrive.png");
            Label title = new Label("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");


            ImageView icon = new ImageView(pic);
            icon.setFitWidth(200);
            icon.setFitHeight(150);

            title.relocate(0,155);
            title.setMaxWidth(200);
            title.setStyle("-fx-font-size: 18; -fx-font: Normal 18 'Agency FB'");
            this.setPadding(new javafx.geometry.Insets(0,0,0,1000));
            //this.snapSpaceX(100000);
            this.setStyle("-fx-border-color: #bababa; " +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 10");
            this.getChildren().addAll(icon,title);

        }catch (Exception e){
            System.out.println(e);
        }








    }
}
