package jar.controllers;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class HomeController {

    @FXML
    private Button newElementBtn;

    @FXML
    private Button shareBtn;

    @FXML
    private Button recientBtn;

    @FXML
    private Button starredBtn;

    @FXML
    private Button trashBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Button searchExpBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button userBtn;

    @FXML
    private Button viewBtn;

    @FXML
    private Button infoBtn;

    @FXML
    private Button shopBtn;

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

    @FXML
    public void buttonBlue() {

        shareBtn.setEffect(Efectos.blueOn());
        recientBtn.setEffect(Efectos.blueOn());
        trashBtn.setEffect(Efectos.blueOn());
        starredBtn.setEffect(Efectos.blueOn());
        storageBtn.setEffect(Efectos.blueOn());

    }

    @FXML
    public void buttonGray() {

        shareBtn.setEffect(Efectos.grayOn());
        recientBtn.setEffect(Efectos.grayOn());
        trashBtn.setEffect(Efectos.grayOn());
        starredBtn.setEffect(Efectos.grayOn());
        storageBtn.setEffect(Efectos.grayOn());
        searchBtn.setEffect(Efectos.grayOn());
        infoBtn.setEffect(Efectos.grayOn());
        searchExpBtn.setEffect(Efectos.grayOn());
        viewBtn.setEffect(Efectos.grayOn());
        userBtn.setEffect(Efectos.grayOn());
        settingsBtn.setEffect(Efectos.grayOn());
        shopBtn.setEffect(Efectos.grayOn());

    }

    @FXML
    public void buttonNormal() {

        shareBtn.setEffect(Efectos.grayOf());
        recientBtn.setEffect(Efectos.grayOf());
        trashBtn.setEffect(Efectos.grayOf());
        starredBtn.setEffect(Efectos.grayOf());
        storageBtn.setEffect(Efectos.grayOf());
        searchBtn.setEffect(Efectos.grayOf());
        infoBtn.setEffect(Efectos.grayOf());
        searchExpBtn.setEffect(Efectos.grayOf());
        viewBtn.setEffect(Efectos.grayOf());
        userBtn.setEffect(Efectos.grayOf());
        settingsBtn.setEffect(Efectos.grayOf());
        shopBtn.setEffect(Efectos.grayOf());
    }

    @FXML
    public void buy() {

        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(
                            "https://one.google.com/storage?i=m&utm_source=drive&utm_medium=web&utm_campaign=widget_normal#upgrade");
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                }
            }

        }

    }

}
