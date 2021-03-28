package jar.controllers;

import jar.dao.AboutDAO;
import jar.dao.FileDAO;
import jar.graphic.*;
import jar.model.dto.FileDTO;
import jar.model.dto.FolderDTO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label spaceLbl;
    @FXML
    private Image picture;
    @FXML
    private FlowPane fileList = new FlowPane();
    @FXML
    private FlowPane folderList = new FlowPane();
    @FXML
    private VBox spaceVBox;
    @FXML
    private VBox contentView = new VBox();
    @FXML
    private Button viewBtn;
    @FXML
    private Button userBtn;
    @FXML
    private Tooltip toolUser;
    @FXML
    private Pane popupPane;
    @FXML
    private SidebarFx detailSidebar;
    @FXML
    private Button cancelSearchBtn;
    @FXML
    private TextField searchBarTxtf;

    private FlowPane folderLbls = new FlowPane();
    private FlowPane fileLbls = new FlowPane();
    private boolean normalViewFiles;
    Label carpeta = new Label("Carpetas");
    Label archivos = new Label("Archivos");
    private Label prevLabel = null;
    private Button prevButton = null;
    private ISelectable prevSelectedFile = null;
    private ISelectable prevSelectedSpaceBtn = null;

    @FXML
    public void goHome() {
        System.out.println("DRIVE");
    }

    @FXML
    public void menuval(javafx.scene.input.MouseEvent e) {
        System.out.println(e.getClass().getModifiers());
    }

    @FXML
    public void toggleDetailSidebar() {
        detailSidebar.openClose();
    }

    @FXML
    public void changeFileView() {
        if (!normalViewFiles) {
            picture = new Image("jar/images/Eye2.png");
            normalViewFiles = true;
            detailView();
        } else {
            picture = new Image("jar/images/Eye.png");
            normalViewFiles = false;
            normalView();
        }

        ImageView icon = new ImageView(picture);
        icon.setFitHeight(40);
        icon.setFitWidth(30);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        viewBtn.setGraphic(icon);
        System.out.println("Cambio de vista");
    }

    @FXML
    public void searchBarTxtDetection() {

        if (!searchBarTxtf.getText().equals("")) {
            cancelSearchBtn.setVisible(true);
        } else {
            cancelSearchBtn.setVisible(false);
        }
        System.out.println(searchBarTxtf.getText());
    }

    @FXML
    public void searchBarEnter(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    @FXML
    public void clearSearchBarTxt() {
        searchBarTxtf.setText("");
        cancelSearchBtn.setVisible(false);
    }

    @FXML
    public void search() {
        System.out.println("Lo que esta buscando es: " + searchBarTxtf.getCharacters());
    }

    public void updateSpace() throws IOException {
        String aux = "";
        Map<String, Map<String, Long>> info = AboutDAO.newQuery().getStorageInfo().build();

        try {
            long space = info.get("storageQuota").get("usageInDrive");
            double spaceB = (double) space;
            spaceB = spaceB / 1048576;
            System.out.println(spaceB);

            if (spaceB > 1000) {
                spaceB = spaceB / 1000;
                spaceB = Math.round(spaceB * 10) / 10.0;
                aux = aux + spaceB + "  GB utilizado";
            } else {
                spaceB = Math.round(spaceB * 10) / 10.0;
                aux = aux + spaceB + "  MB utilizado";
            }
            spaceLbl.setText(aux);
            System.out.println("Almacenamiento: " + aux);

        } catch (Exception ex) {
            System.out.println(ex);
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
    public void toggleSearchSidebar() throws IOException {
        SearchbarFx pop = new SearchbarFx();
        pop.hideOnEscapeProperty().set(true);
        pop.autoHideProperty().set(true);
        pop.show(popupPane, 0, 0);
        pop.openClose();
    }

    /**
     * When a file or folder is selected this method is called to unselect the
     * previous file/folder and select the new one
     */
    public void changeFileSelection(Event e) {
        ISelectable actualSelect = (ISelectable) e.getSource();

        if (prevSelectedFile != null && prevSelectedFile != actualSelect) {
            prevSelectedFile.unselect();
        }
        prevSelectedFile = actualSelect;
    }

    public void changeSpaceButtonSelection(Event e) {
        ISelectable actualSelect = (ISelectable) e.getSource();

        if (prevSelectedSpaceBtn != null && prevSelectedSpaceBtn != actualSelect) {
            prevSelectedSpaceBtn.unselect();
        }
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
        if (rfi != null) {
            for (Object obj : rfi.getValue()) {
                fileList.getChildren().add(new FileFx((FileDTO) obj, this));
            }
        }

        if (rfo != null) {
            for (Object obj : rfo.getValue()) {
                folderList.getChildren().add(new FolderFx((FolderDTO) obj, this));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // VBox.setVgrow(fileList, Priority.SOMETIMES);
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
            try {
                for (Object obj : r1.getValue())
                    fileList.getChildren().add(new FileFx((FileDTO) obj, this));
            } catch (Exception e) {
                System.out.println(e);
            }
            Pair<String, List<Object>> r2 = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders()
                    .fromMyDrive().myOwnershipOnly().notOrdered().build();
            for (Object obj : r2.getValue())
                folderList.getChildren().add(new FolderFx((FolderDTO) obj, this));

            updateSpace();
            loadImageUser();
            setLabels();
            normalView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImageUser() throws IOException {
        Map<String, Map<String, String>> info = AboutDAO.newQuery().getUserInfo().build();

        Image picture;
        if (info.get("user").containsKey("photoLink"))
            picture = new Image(info.get("user").get("photoLink"));
        else
            picture = new Image("/jar/images/penguin.jpg");

        Circle circ = new Circle(90, 60, 30);
        circ.setFill(new ImagePattern(picture));

        userBtn.setGraphic(circ);

        toolUser.setText("Cuenta de Google Drive\n" + info.get("user").get("displayName") + "\n"
                + info.get("user").get("emailAddress"));
    }

    public void setLabels() {
        try {
            archivos.setStyle(
                    "-fx-text-fill: #3e3e3e; -fx-font: Normal 18 'Agency FB'; -fx-border-width: 3; -fx-border-color: transparent;");
            fileLbls.setId("fileLbls");

            fileLbls.setMaxSize(fileLbls.getMinWidth(), fileLbls.getMinHeight());

            fileLbls.getChildren().add(archivos);

            carpeta.setStyle(
                    "-fx-text-fill: #3e3e3e; -fx-font: Normal 18 'Agency FB'; -fx-border-width: 3; -fx-border-color: transparent;");
            folderLbls.setId("folderLbls");

            folderLbls.setMaxSize(folderLbls.getMinWidth(), folderLbls.getMinHeight());

            folderLbls.getChildren().add(carpeta);

            VBox.setVgrow(fileLbls, Priority.NEVER);
            VBox.setVgrow(folderLbls, Priority.NEVER);
            VBox.setVgrow(folderList, Priority.SOMETIMES);
            VBox.setVgrow(fileList, Priority.ALWAYS);

            // Para hacer debug
            // fileLbls.setBorder(new Border(new BorderStroke(Paint.valueOf("#AAAAAA"),
            // BorderStrokeStyle.SOLID,
            // new CornerRadii(0), BorderStroke.THICK)));
            // folderLbls.setBorder(new Border(new BorderStroke(Paint.valueOf("#B6B6B7"),
            // BorderStrokeStyle.SOLID,
            // new CornerRadii(0), BorderStroke.THICK)));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void normalView() {
        try {
            contentView.getChildren().removeAll(contentView.getChildren());
            contentView.getChildren().addAll(folderLbls, folderList, fileLbls, fileList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void detailView() {
        try {
            Label det = new Label("DETALLES");
            det.setStyle("-fx-font-size: 40");
            contentView.getChildren().removeAll(contentView.getChildren());
            contentView.getChildren().addAll(det);
            // contentView.getChildren().addAll(folderList,folderLbls,fileList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}