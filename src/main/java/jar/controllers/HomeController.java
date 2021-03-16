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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
/*    =========================================
    =========================================
     BOTON +NUEVO*/

    @FXML
    public void blurNewBtn() {

//        newElementBtn.setEffect(Efectos.newElementBtnOn());
        newElementBtn.setStyle("-fx-border-color: #e3e1e1; "+
                                "-fx-background-color: #eae6e6;"+
                                "-fx-border-radius: 63;"+
                                "-fx-background-radius: 63;");

    }

    @FXML
    public void blurOfNewBtn() {

//        newElementBtn.setEffect(Efectos.newElementBtnOf());
        newElementBtn.setStyle("-fx-border-color: #bababa; "+
                               "-fx-background-color: #F4F4F4;"+
                               "-fx-border-radius: 63;"+
                               "-fx-background-radius: 63;");
    }

//    ===========================================
//    ===========================================

    @FXML
    public void buttonBlue(Event e) {

        Button btn = (Button) e.getSource();
        String btnName = btn.getId();

        if (prevButton != null) {

//          prevButton.setEffect(Efectos.grayOf());
            prevButton.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent; " +
                                "-fx-pref-height: 60;" +
                                "-fx-border-radius: 63;" +
                                "-fx-background-radius: 63;" +
                                "-fx-text-fill: #3e3e3e;");

            ImageView icon = new ImageView(new Image("jar/images/" + prevButton.getId() + "Black.png"));
            icon.setFitHeight(30);
            icon.setFitWidth(30);
            icon.setTranslateX(-35.0);
            prevButton.setGraphic(icon);
        } else {
            prevButton = miUnidadBtn;
        }

        prevButton = btn;
        prevButton.setId(btnName);

     //  btn.setEffect(Efectos.blueOn());
        btn.setStyle("-fx-background-color:#c4d5ff ;" +
                "-fx-border-color: #c4d5ff; " +
                "-fx-pref-height: 60;" +
                "-fx-border-radius: 63;" +
                "-fx-background-radius: 63;" +
                "-fx-text-fill: #0b7bf3;");

        ImageView ejemplo = new ImageView(new Image("jar/images/" + btnName + "Blue.png"));
        ejemplo.setFitHeight(30);
        ejemplo.setFitWidth(30);
        ejemplo.setTranslateX(-35.0);
        prevButton.setGraphic(ejemplo);



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
          //  btn.setEffect(Efectos.grayOn(btn.getId()));
            btn.setStyle("-fx-background-color: #e3e1e1;" +
                    "-fx-border-color: #e3e1e1; "+
                    "-fx-border-radius: 63;" +
                    "-fx-background-radius: 63;" );
        }
    }

    @FXML
    public void buttonNormal(Event e) {

        Button btn = (Button) e.getSource();

        if (prevButton == null)
            prevButton = miUnidadBtn;

        if (prevButton != btn) {
          //  btn.setEffect(Efectos.grayOf());
            btn.setStyle("-fx-background-color: transparent;" +
                    "-fx-border-color: transparent; " +
                    "-fx-pref-height: 60;" +
                    "-fx-border-radius: 63;" +
                    "-fx-background-radius: 63;" +
                    "-fx-text-fill: #3e3e3e;");
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
//        b.setEffect(Efectos.grayOn(b.getId()));
        b.setStyle("-fx-background-color: #e3e1e1;" + "-fx-background-radius: 40;");

    }

    @FXML
    public void menuNormal(Event e) {

        MenuBar b = (MenuBar) e.getSource();
        System.out.println(b.getId());
//        b.setEffect(Efectos.grayOf());
        b.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 40;");
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
    private Pane popupPane;
    @FXML
    public void toggleSearchSidebar() throws IOException {
        SearchbarFx pop = new SearchbarFx();
        pop.hideOnEscapeProperty().set(true);
        pop.autoHideProperty().set(true);
        pop.show(popupPane, 0, 0);
        pop.openClose();
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
            setLabels();
            normalView();
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
    private FlowPane fileLbls = new FlowPane();

    Label carpeta = new Label("Carpetas");
    Label archivos = new Label("Archivos");
    Event e;

    public void setLabels(){

        try {

            //onMouseEntered="#borderBlue" onMouseExited="#borderNormal"

            archivos.setStyle("-fx-text-fill: #3e3e3e; -fx-font: Normal 18 'Agency FB'; -fx-border-width: 3; -fx-border-color: transparent;");
            fileLbls.setPrefHeight(500);
            fileLbls.setMaxWidth(archivos.getWidth() + 75);
            fileLbls.setId("fileLbls");
        //    fileLbls.setOnMouseExited(this::borderNormal);
         //   fileLbls.setOnMouseEntered(this::borderBlue);


            fileLbls.getChildren().add(archivos);



            carpeta.setStyle("-fx-text-fill: #3e3e3e; -fx-font: Normal 18 'Agency FB'; -fx-border-width: 3; -fx-border-color: transparent;");
            folderLbls.setPrefHeight(500);
            folderLbls.setMaxWidth(carpeta.getWidth() + 75);
            folderLbls.setId("folderLbls");
            folderLbls.setOnMouseExited(this::borderNormal);
            folderLbls.setOnMouseEntered(this::borderBlue);
            folderLbls.getChildren().add(carpeta);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void normalView(){






        try{
            contentView.getChildren().removeAll(contentView.getChildren());
            contentView.getChildren().addAll(folderLbls,folderList,fileLbls,fileList);

        }catch (Exception e){
            System.out.println(e);
        }



    }

    public void detailView(){
        try {
            Label det = new Label("DETALLES");
            det.setStyle("-fx-font-size: 40");
            contentView.getChildren().removeAll(contentView.getChildren());
            contentView.getChildren().addAll(det);
            //contentView.getChildren().addAll(folderList,folderLbls,fileList);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    /*@FXML
    public EventHandler<? super MouseEvent> borderRed(String id){

        if (id.equals(fileLbls.getId())){

            fileLbls.setStyle("-fx-border-color: transparent transparent red transparent; " +
                    "-fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");
                    System.out.println("ARCHIVOS ROJO");
        }else{

            folderLbls.setStyle("-fx-border-color: transparent transparent red transparent; " +
                    "-fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");

        }

        return null;
    }

    @FXML
    public EventHandler<? super MouseEvent> borderNormal(String id){

        if (id.equals(fileLbls.getId())){

            fileLbls.setStyle("-fx-border-color: transparent;" +
                    " -fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");
            System.out.println("ARCHIVOS NORMAL");
        }else{

            folderLbls.setStyle("-fx-border-color: transparent;" +
                    " -fx-border-width: 3;" +
                    "-fx-background-color: transparent;" +
                    "-fx-font: Normal 18 'Agency FB'");

        }

        return null;
    }*/


}
