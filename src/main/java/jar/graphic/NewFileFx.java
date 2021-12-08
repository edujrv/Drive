package jar.graphic;

import java.io.IOException;

import jar.controllers.NewFileController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Popup;

public class NewFileFx extends Popup {

    // GridPane gridpane = new GridPane();
    // TranslateTransition openNav = new TranslateTransition(new Duration(50),
    // gridpane);
    // TranslateTransition closeNav = new TranslateTransition(new Duration(50),
    // gridpane);
    // Timeline openResize = new Timeline();
    // Timeline closeResize = new Timeline();
    // private double height = 914.0;

    public NewFileFx(Button btn, String actualFolderId) {
        Bounds pos = btn.localToScreen(btn.getBoundsInLocal());

        this.hideOnEscapeProperty().set(true);
        this.autoHideProperty().set(true);
        this.show(btn, pos.getMaxX(), pos.getCenterY());
        this.openClose();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/newFile.fxml"));
            this.getContent().add((Parent) loader.load());
            NewFileController controller = loader.getController();
            controller.setActualFolderId(actualFolderId);
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
}