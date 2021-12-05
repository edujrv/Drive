package jar.graphic;

import java.io.IOException;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.util.Duration;

public class NewMenuFx extends Popup {

    // GridPane gridpane = new GridPane();
    // TranslateTransition openNav = new TranslateTransition(new Duration(50),
    // gridpane);
    // TranslateTransition closeNav = new TranslateTransition(new Duration(50),
    // gridpane);
    // Timeline openResize = new Timeline();
    // Timeline closeResize = new Timeline();
    // private double height = 914.0;
    public Pane popupPane;

    public NewMenuFx(Pane popupPane) {

        this.popupPane = popupPane;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/newMenu.fxml"));

            this.getContent().add((Parent) loader.load());
        } catch (Exception e) {
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

    public void createNewFolder() {
        NewFolderFx pop = new NewFolderFx();
        pop.hideOnEscapeProperty().set(true);
        pop.autoHideProperty().set(true);
        pop.show(popupPane, 500, 350);
        pop.openClose();
    }

}