package jar.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class HomeController {

    @FXML
    private ImageView newElementBtn;

    @FXML
    public void goHome() {
        System.out.println("BOTON DRIVE");
    }

    // TODO: Cambiar de Boton a MenuBar el newElementBtn
    @FXML
    public void blurNewBtn() {

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLUE);
        dropShadow.setHeight(25.83);
        dropShadow.setOffsetY(2.0);
        dropShadow.setRadius(10.7075);
        newElementBtn.setEffect(dropShadow);

    }

    @FXML
    public void blurOfNewBtn() {

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.TRANSPARENT);
        dropShadow.setHeight(0);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(0);
        newElementBtn.setEffect(dropShadow);
        // newElementBtn.setStyle("-fx-background-color:transparent;");
    }

}
