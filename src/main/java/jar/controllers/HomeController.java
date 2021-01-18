package jar.controllers;

import jar.graphic.FileFx;
import javafx.event.Event;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HomeController {

    @FXML
    private static FileFx prevFile = null;
    @FXML
    private Image picture;
    @FXML
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
        String btnName = btn.getId();


        if (prevButton != null) {
            prevButton.setEffect(Efectos.grayOf());
            prevButton.setTextFill(Color.BLACK);
            picture = new Image("jar/images/"+ prevButton.getId() + "Black.png");
            ImageView icon = new ImageView(picture);
            icon.setFitHeight(40);
            icon.setFitWidth(30);
            icon.setPickOnBounds(true);
            icon.setPreserveRatio(true);
            icon.setTranslateX(-35.0);
            prevButton.setGraphic(icon);
        }

            prevButton = btn;
            prevButton.setId(btnName);
            btn.setEffect(Efectos.blueOn());
            btn.setTextFill(Color.rgb(76, 175, 232));
            picture = new Image("jar/images/"+ btnName + "Blue.png");
            ImageView ejemplo = new ImageView(picture);
            ejemplo.setFitHeight(40);
            ejemplo.setFitWidth(30);
            ejemplo.setPickOnBounds(true);
            ejemplo.setPreserveRatio(true);
            ejemplo.setTranslateX(-35.0);
            prevButton.setGraphic(ejemplo);
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
    @FXML
    public static void fileSelected(Event e) {
        FileFx actualFile = (FileFx) e.getSource();
        String btnName = actualFile.getId();

        if(prevFile != null){
            prevFile.changeTitleColor(0,0,0);
            prevFile.setEffect(Efectos.grayOf());
            System.out.println("PONGO NEGRO A" + prevFile.getId());
            prevFile.setStyle("-fx-border-color: #bababa; " +
                    "-fx-border-width: 3;" +
                    "-fx-border-radius: 10; " +
                    "-fx-padding: 0; " +
                    "-fx-border-insets: 10 0 0 10");
            prevFile.changeTitleBackground(Color.TRANSPARENT);
        }

        prevFile = actualFile;
        prevFile.setId(actualFile.getId());
        System.out.println(prevFile.getId());

        //actualFile.changeTitleColor(9,145,148);
        actualFile.setEffect(Efectos.blueOn());
        System.out.println("PONGO AZUL A " + actualFile.getId());
        /*actualFile.setStyle("-fx-border-color: #099194; " +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10; " +
                "-fx-padding: 20; " +
                "-fx-border-insets: 10 5 0 5");*/
        actualFile.changeTitleBackground(Color.TRANSPARENT);
        actualFile.changeTitleBackground();

    }

}
