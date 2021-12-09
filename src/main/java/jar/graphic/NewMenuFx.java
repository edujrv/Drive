package jar.graphic;

import java.io.IOException;

import jar.controllers.NewMenuController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Popup;

public class NewMenuFx extends Popup {

    public Button newElementBtn;

    public NewMenuFx() {
    }

    public NewMenuFx(Button newElementBtn, String folderId) {
        Bounds pos = newElementBtn.localToScreen(newElementBtn.getBoundsInLocal());

        this.hideOnEscapeProperty().set(true);
        this.autoHideProperty().set(true);
        this.show(newElementBtn, pos.getWidth(), pos.getCenterY());
        this.openClose();
        this.newElementBtn = newElementBtn;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/newMenu.fxml"));
            this.getContent().add((Parent) loader.load());
            NewMenuController controller = loader.getController();
            controller.setActualFolderId(folderId);
        } catch (Exception e) {
            e.printStackTrace();
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