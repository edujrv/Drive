package jar.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
import jar.model.dto.FileDTO;
import jar.model.dto.FolderDTO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

public class HomeController implements Initializable {

    private Label prevLabel = null;
    @FXML
    private Label spaceLbl;
    @FXML
    private Button miUnidadBtn;
    private static ISelectable prevSelected = null;
    @FXML
    private Image picture;
    @FXML
    private Button prevButton = null;
    @FXML
    private Button newElementBtn;

    @FXML
    private FlowPane fileList = new FlowPane();

    @FXML
    private FlowPane folderList = new FlowPane();

    @FXML
    public void goHome() {
        System.out.println("BOTON DRIVE");
    }


    @FXML
    public void menuval(ActionEvent e){
        /*
        if ((e.getModifiers() & 4) !=0){
            // boton derecho
        }
        */
    }


    @FXML
    private Button viewBtn;
    private boolean normalViewFiles;


    @FXML
    public void changeFileView(){
        if(!normalViewFiles){
            picture = new Image("jar/images/Eye2.png");
            normalViewFiles = true;
            detailView();
        }else{
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
    private Button cancelSearchBtn;

    @FXML
    private TextField searchBarTxtf;

    @FXML
    public void searchBarTxtDetection(){

            if(!searchBarTxtf.getText().equals("")){
                cancelSearchBtn.setVisible(true);
            }else {
                cancelSearchBtn.setVisible(false);
            }
            System.out.println(searchBarTxtf.getText());
        }

    @FXML
    public void searchBarEnter(javafx.scene.input.KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    @FXML
    public  void clearSearchBarTxt(){
        searchBarTxtf.setText("");
        cancelSearchBtn.setVisible(false);
    }

    @FXML
    public  void search(){
        System.out.println("Lo que esta buscando es: "+searchBarTxtf.getCharacters());
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

        try {
            updateSpace();
        } catch (Exception about) {
            System.out.println(about);
        }

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
    public void borderBlue(Event e){
        Label lbl = (Label) e.getSource();
        if(prevLabel != lbl){
            lbl.setStyle("-fx-border-color: transparent transparent red transparent; " +
                    "-fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");
        }
    }

    @FXML
    public void borderNormal(Event e){
        Label lbl = (Label) e.getSource();
        if(prevLabel != lbl){
            lbl.setStyle("-fx-border-color: transparent;" +
                    " -fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");
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

    /**
     * When a file or folder is selected this method is called to unselect the
     * previous file/folder and select the new one
     */
    public void changeFileSelection(Event e) {
        ISelectable actualSelect = (ISelectable) e.getSource();

        if (prevSelected != null && prevSelected != actualSelect)
            prevSelected.unselect();

        prevSelected = actualSelect;
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
        try {

            Pair<String, List<Object>> r1 = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles()
                    .fromMyDrive().myOwnershipOnly().notOrdered().build();

            try{
                for (Object obj : r1.getValue())
                    fileList.getChildren().add(new FileFx((FileDTO) obj, this));
            }catch (Exception e){
                System.out.println(e);
            }


            Pair<String, List<Object>> r2 = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFolders()
                    .fromMyDrive().myOwnershipOnly().notOrdered().build();

            for (Object obj : r2.getValue())
                folderList.getChildren().add(new FolderFx((FolderDTO) obj, this));

            updateSpace();
            loadImageUser();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    private Button userBtn;

    @FXML
    private Tooltip toolUser;

    public void loadImageUser() throws IOException {
        Map<String, Map<String,String>> info = AboutDAO.newQuery().getUserInfo().build();

        picture  = new Image(info.get("user").get("photoLink"));



        Circle circ = new Circle(90,60,30);
        circ.setFill(new ImagePattern(picture));



        userBtn.setGraphic(circ);

        toolUser.setText("Cuenta de Google Drive\n"+info.get("user").get("displayName")+"\n"+info.get("user").get("emailAddress"));


    }


    @FXML
    private VBox contentView = new VBox();

    private VBox contentViewDetails = new VBox();

    private FlowPane folderLbls = new FlowPane();


    public void toggleView(){


    }

    public void normalView(){

        try{
            Label carpeta = new Label("Carpetas");

            folderList.setPrefHeight(500);


            folderLbls.getChildren().addAll(carpeta);

            contentView.getChildren().addAll(folderLbls,folderList,folderLbls,fileList);

        }catch (Exception e){
            System.out.println(e);
        }



    }

    public void detailView(){
        contentView.getChildren().removeAll(contentView.getChildren());
        contentView.getChildren().addAll(new Label("lbl"));
    }




}
