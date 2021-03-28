package jar.graphic;

import java.io.IOException;

import jar.controllers.HomeController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SpaceButtonFx extends Button implements ISelectable {
    private ImageView icon = new ImageView();

    private void create(String id, String text, HomeController hController) {
        setId(id);
        setMnemonicParsing(false);

        unselect();

        icon.getStyleClass().setAll("space-button__icon");

        icon.setFitHeight(30);
        icon.setFitWidth(30);

        setText(text);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                select();
                try {
                    hController.changeSpace((SpaceButtonFx) event.getSource());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                hController.changeSpaceButtonSelection(event);
            }
        });
    }

    public SpaceButtonFx(String id, String text, HomeController hController) {
        create(id, text, hController);
        VBox.setMargin(this, new Insets(4, 0, 0, 0));
    }

    public SpaceButtonFx(String id, String text, HomeController hController, Insets margin) {
        create(id, text, hController);
        VBox.setMargin(this, margin);
    }

    @Override
    public void select() {
        getStyleClass().setAll("left-pane__space-button--selected");

        icon.setImage(new Image("jar/images/" + getId() + "Blue.png"));
        setGraphic(icon);
    }

    @Override
    public void unselect() {
        getStyleClass().setAll("left-pane__space-button--unselected");

        icon.setImage(new Image("jar/images/" + getId() + "Black.png"));
        setGraphic(icon);
    }
}
