package jar.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class HomeController {
    private Button prevButton = null;
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
    private ImageView personaIg;

    @FXML
    private ImageView clockIg;

    @FXML
    private ImageView storageIg;

    @FXML
    private ImageView trashIg;

    @FXML
    private ImageView starIg;

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
    public void buttonBlue(Event e) {

        Button btn = (Button) e.getSource();

        if (prevButton != null) {
            prevButton.setEffect(Efectos.grayOf());
            prevButton.setTextFill(Color.BLACK);
        }
        prevButton = btn;
        btn.setEffect(Efectos.blueOn());
        btn.setTextFill(Color.BLUE);

    }

    @FXML
    public void buttonGray(Event e) {

        Button btn = (Button) e.getSource();
        if (prevButton != btn) {
            btn.setEffect(Efectos.grayOn(btn.getId()));
        }
    }

    @FXML
    public void buttonNormal(Event e) {

        Button btn = (Button) e.getSource();
        if (prevButton != btn) {
            btn.setEffect(Efectos.grayOf());
        }
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

    @FXML
    public void menuGray(Event e) {

        MenuBar b = (MenuBar) e.getSource();
        System.out.println(b.getId());
        b.setEffect(Efectos.grayOn(b.getId()));

    }

    @FXML
    public void menuNormal(Event e) {

        MenuBar b = (MenuBar) e.getSource();
        System.out.println(b.getId());
        b.setEffect(Efectos.grayOf());

    }

}
