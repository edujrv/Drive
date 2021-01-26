package jar.graphic;

import jar.controllers.Efectos;
import jar.controllers.HomeController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpaceButtonFx extends Button implements ISelectable, IHoverable {
    private ImageView icon = new ImageView();
    private boolean selected = false;

    private void create(String id, String text, HomeController hController) {

        setMnemonicParsing(false);
        setId(id);
        setPrefHeight(60);
        setPrefWidth(300);
        setFont(Font.font(24));
        setCursor(Cursor.HAND);
        setAlignment(Pos.CENTER_LEFT);

        setStyle("-fx-background-radius: 0 40 40 0; -fx-background-color: #F4F4F4; -fx-padding: 0 0 0 50");
        setEffect(Efectos.grayOf());
        setTextFill(Color.BLACK);

        // Icon
        icon.setImage(new Image("jar/images/" + getId() + "Black.png"));
        icon.setFitHeight(40);
        icon.setFitWidth(30);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        icon.setTranslateX(-35.0);
        icon.setCursor(Cursor.HAND);

        setText(text);
        setGraphic(icon);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                select();
                hController.changeSpaceButtonSelection(event);
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hover();
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unhover();
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
        selected = true;

        setEffect(Efectos.blueOn());
        setTextFill(Color.rgb(76, 175, 232));

        icon.setImage(new Image("jar/images/" + getId() + "Blue.png"));
        setGraphic(icon);
    }

    @Override
    public void unselect() {
        selected = false;

        setEffect(Efectos.grayOf());
        setTextFill(Color.BLACK);

        icon.setImage(new Image("jar/images/" + getId() + "Black.png"));
        setGraphic(icon);
    }

    @Override
    public void hover() {
        if (!selected)
            setEffect(Efectos.grayOn(getId()));
    }

    @Override
    public void unhover() {
        if (!selected)
            setEffect(Efectos.grayOf());
    }
}
