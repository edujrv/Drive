package jar.controllers;

import jar.graphic.FileFx;
import jar.graphic.FolderFX;
import jar.graphic.SidebarFx;
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
    private Button miUnidadBtn;
    @FXML
    private static FileFx prevFile = null;
    @FXML
    private static FolderFX prevFolder = null;
    @FXML
    private Image picture;
    @FXML
    private Button prevButton = null;
    @FXML
    private Button newElementBtn;

    @FXML
    public void goHome() {
        System.out.println("BOTON DRIVE");
    }

    // TODO: Cambiar de Boton a MenuBar el newElementBtn


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
            picture = new Image("jar/images/" + prevButton.getId() + "Black.png");
            ImageView icon = new ImageView(picture);
            icon.setFitHeight(40);
            icon.setFitWidth(30);
            icon.setPickOnBounds(true);
            icon.setPreserveRatio(true);
            icon.setTranslateX(-35.0);
            prevButton.setGraphic(icon);
        } else {
            prevButton = miUnidadBtn;
        }

        prevButton = btn;
        prevButton.setId(btnName);
        btn.setEffect(Efectos.blueOn());
        btn.setTextFill(Color.rgb(76, 175, 232));
        picture = new Image("jar/images/" + btnName + "Blue.png");
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

        if (prevButton == null)
            prevButton = miUnidadBtn;

        if (prevButton != btn) {
            btn.setEffect(Efectos.grayOn(btn.getId()));
        }
    }

    @FXML
    public void buttonNormal(Event e) {

        Button btn = (Button) e.getSource();

        if (prevButton == null)
            prevButton = miUnidadBtn;

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
                    System.out.println("Explorador no encontrado");
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

        if (prevFile != null) {
            System.out.println("PONGO GRIS FILE " + prevFile.getId());
            prevFile.changeTitleBackgroundGray();
        }

        prevFile = actualFile;
        prevFile.setId(actualFile.getId());
        System.out.println(prevFile.getId());

        System.out.println("PONGO AZUL FILE " + actualFile.getId());
        actualFile.changeTitleBackgroundBlue();

        prevFolder.changeGray();
    }

    @FXML
    public static void folderSelected(Event e) {
        FolderFX actualFolder = (FolderFX) e.getSource();

        if (prevFolder != null) {
            System.out.println("PONGO GRIS FOLDER " + prevFolder.getId());
            prevFolder.changeGray();
        }

        prevFolder = actualFolder;
        prevFolder.setId(actualFolder.getId());
        System.out.println(prevFolder.getId());

        System.out.println("PONGO AZUL FOLDER " + actualFolder.getId());
        actualFolder.changeBlue();

        prevFile.changeTitleBackgroundGray();
    }

    @FXML
    private SidebarFx detailSidebar;

    @FXML
    public void toggleDetailSidebar() {
        detailSidebar.openClose();
    }

}
