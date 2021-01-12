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
        newElementBtn.setEffect(Efectos.newElementBtnOn());
    }

    @FXML
    public void blurOfNewBtn() {
        newElementBtn.setEffect(Efectos.newElementBtnOf());
    }

}
