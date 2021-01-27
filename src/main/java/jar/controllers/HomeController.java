package jar.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import jar.dao.AboutDAO;
import jar.dao.FileDAO;
import jar.graphic.FileFx;
import jar.graphic.FolderFx;
import jar.graphic.ISelectable;
import jar.graphic.SearchbarFx;
import jar.graphic.SidebarFx;
import jar.graphic.SpaceButtonFx;
import jar.model.dto.FileDTO;
import jar.model.dto.FolderDTO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class HomeController implements Initializable {

    private ISelectable prevSelectedFile = null;
    private ISelectable prevSelectedSpaceBtn = null;

    @FXML
    private Label spaceLbl;
    @FXML
    private Image picture;
    private Button prevButton = null;
    @FXML
    private Button newElementBtn;

    @FXML
    private FlowPane fileList;

    @FXML
    private FlowPane folderList;

    @FXML
    private VBox spaceVBox;

    @FXML
    public void goHome() {
        System.out.println("DRIVE");
    }

    @FXML
    public void blurNewBtn() {
        newElementBtn.setEffect(Efectos.newElementBtnOn());
    }

    @FXML
    public void blurOfNewBtn() {
        newElementBtn.setEffect(Efectos.newElementBtnOf());
    }

    public void updateSpace() throws IOException {
        String aux = "";
        Map<String, Map<String, Long>> info = AboutDAO.newQuery().getStorageInfo().build();

        try {
            Long space = info.get("storageQuota").get("usageInDrive");

            double spaceB = (double) space;

            spaceB = spaceB / 1048576;
            System.out.println(spaceB);
            if (spaceB > 1024) {
                spaceB = spaceB / 1024;
                spaceB = Math.floor(spaceB * 100) / 100;
                aux = aux + spaceB + "  GB utilizado";
            } else {
                spaceB = Math.floor(spaceB * 100) / 100;
                aux = aux + spaceB + "  MB utilizado";
            }
            spaceLbl.setText(aux);
            System.out.println("Almacenamiento: " + aux);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // @FXML
    // public void buttonBlue(Event e) {

    // Button btn = (Button) e.getSource();
    // String btnName = btn.getId();

    // if (prevButton != null) {
    // prevButton.setEffect(Efectos.grayOf());
    // prevButton.setTextFill(Color.BLACK);
    // picture = new Image("jar/images/" + prevButton.getId() + "Black.png");
    // ImageView icon = new ImageView(picture);
    // icon.setFitHeight(40);
    // icon.setFitWidth(30);
    // icon.setPickOnBounds(true);
    // icon.setPreserveRatio(true);
    // icon.setTranslateX(-35.0);
    // prevButton.setGraphic(icon);
    // } else {
    // prevButton = miUnidadBtn;
    // }

    // prevButton = btn;
    // prevButton.setId(btnName);
    // btn.setEffect(Efectos.blueOn());
    // btn.setTextFill(Color.rgb(76, 175, 232));
    // picture = new Image("jar/images/" + btnName + "Blue.png");
    // ImageView ejemplo = new ImageView(picture);
    // ejemplo.setFitHeight(40);
    // ejemplo.setFitWidth(30);
    // ejemplo.setPickOnBounds(true);
    // ejemplo.setPreserveRatio(true);
    // ejemplo.setTranslateX(-35.0);
    // prevButton.setGraphic(ejemplo);
    // }

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

    /**
     * When a file or folder is selected this method is called to unselect the
     * previous file/folder and select the new one
     */
    public void changeFileSelection(Event e) {
        ISelectable actualSelect = (ISelectable) e.getSource();

        if (prevSelectedFile != null && prevSelectedFile != actualSelect)
            prevSelectedFile.unselect();

        prevSelectedFile = actualSelect;
    }

    public void changeSpaceButtonSelection(Event e) {
        ISelectable actualSelect = (ISelectable) e.getSource();

        if (prevSelectedSpaceBtn != null && prevSelectedSpaceBtn != actualSelect)
            prevSelectedSpaceBtn.unselect();

        prevSelectedSpaceBtn = actualSelect;
    }

    public void changeSpace(SpaceButtonFx triggerBtn) throws IOException {
        Pair<String, List<Object>> rfi = null;
        Pair<String, List<Object>> rfo = null;
        if (triggerBtn.getId().equals("miUnidadBtn")) {
            rfi = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles().fromMyDrive().myOwnershipOnly()
                    .notOrdered().build();
            rfo = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders().fromMyDrive().myOwnershipOnly()
                    .notOrdered().build();
        } else if (triggerBtn.getId().equals("shareBtn")) {
            // TODO: Hacer metodo para mostrar archivos compartidos
            // rfi =
            // FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles().fromShared().notOrdered()
            // .build();
            // rfo =
            // FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders().fromShared().notOrdered()
            // .build();
        } else if (triggerBtn.getId().equals("recientBtn")) {
            rfi = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles().fromRecent().anyFiles().build();
        } else if (triggerBtn.getId().equals("starredBtn")) {
            rfi = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles().fromStarred().anyOwnership()
                    .notOrdered().build();
            rfo = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders().fromStarred().anyOwnership()
                    .notOrdered().build();
        } else if (triggerBtn.getId().equals("trashBtn")) {
            rfi = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles().fromTrashed().build();
            rfo = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders().fromTrashed().build();
        } else {
            // TODO: Hacer metodo para mostrar todos los archivos
        }
        fileList.getChildren().clear();
        folderList.getChildren().clear();
        if (rfi != null)
            for (Object obj : rfi.getValue())
                fileList.getChildren().add(new FileFx((FileDTO) obj, this));

        if (rfo != null)
            for (Object obj : rfo.getValue())
                folderList.getChildren().add(new FolderFx((FolderDTO) obj, this));
    }

    @FXML
    private SidebarFx detailSidebar;

    @FXML
    private SearchbarFx searchSidebar;

    @FXML
    public void toggleDetailSidebar() {
        detailSidebar.openClose();
    }

    @FXML
    public void toggleSearchSidebar() {
        searchSidebar.openClose();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<SpaceButtonFx> aux = new ArrayList<SpaceButtonFx>();
        aux.add(new SpaceButtonFx("miUnidadBtn", "Mi Unidad", this, new Insets(40, 0, 0, 0)));
        aux.add(new SpaceButtonFx("shareBtn", "Compartido", this));
        aux.add(new SpaceButtonFx("recientBtn", "Reciente", this));
        aux.add(new SpaceButtonFx("starredBtn", "Destacados", this));
        aux.add(new SpaceButtonFx("trashBtn", "Papelera", this));
        aux.add(new SpaceButtonFx("storageBtn", "Almacenamiento", this, new Insets(60, 0, 0, 0)));

        aux.get(0).select();
        prevSelectedSpaceBtn = aux.get(0);

        spaceVBox.getChildren().addAll(1, aux);
        aux = null;

        try {
            Pair<String, List<Object>> r1 = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles()
                    .fromMyDrive().myOwnershipOnly().notOrdered().build();

            for (Object obj : r1.getValue())
                fileList.getChildren().add(new FileFx((FileDTO) obj, this));

            Pair<String, List<Object>> r2 = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders()
                    .fromMyDrive().myOwnershipOnly().notOrdered().build();

            for (Object obj : r2.getValue())
                folderList.getChildren().add(new FolderFx((FolderDTO) obj, this));

            updateSpace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            // prevButton = miUnidadBtn;
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

        try {
            updateSpace();
        } catch (Exception about) {
            System.out.println(about);
        }

    }

    @FXML
    public void buttonGray(Event e) {

        Button btn = (Button) e.getSource();

        if (prevButton == null)
            // prevButton = miUnidadBtn;

            if (prevButton != btn) {
                btn.setEffect(Efectos.grayOn(btn.getId()));
            }
    }

    @FXML
    public void buttonNormal(Event e) {

        Button btn = (Button) e.getSource();

        if (prevButton == null)
            // prevButton = miUnidadBtn;

            if (prevButton != btn) {
                btn.setEffect(Efectos.grayOf());
            }
    }
}
