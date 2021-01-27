package jar.graphic;

import jar.App;
import jar.controllers.HomeController;
import jar.dao.FileDAO;
import jar.model.File;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;


public class SearchbarFx extends Popup {




    GridPane gridpane = new GridPane();
    TranslateTransition openNav = new TranslateTransition(new Duration(50), gridpane);
    TranslateTransition closeNav = new TranslateTransition(new Duration(50), gridpane);
    Timeline openResize = new Timeline();
    Timeline closeResize = new Timeline();
    private double height = 914.0;


    public SearchbarFx(){



        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/MenuExpBusqueda.fxml"));

            this.getContent().add((Parent)loader.load());
        }catch (Exception e){
            System.out.println(e);
        }



    }

    public void openClose() {

        this.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SearchbarFx.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void close() {
        this.hide();
    }
}