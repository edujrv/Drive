package jar.graphic;

import jar.controllers.Efectos;
import jar.controllers.HomeController;
import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SpaceButtonFx extends Button implements ISelectable, IHoverable {
    private ImageView icon = new ImageView();
    private boolean selected = false;

    public SpaceButtonFx(String id, String text, HomeController hController) {
        setMnemonicParsing(false);
        setId(id);
        setPrefHeight(60);
        setPrefWidth(300);
        setStyle("-fx-background-radius: 0 40 40 0; -fx-background-color: #F4F4F4; -fx-padding: 0 0 0 50");
        // setTranslateX(25);
        setTextFill(Color.web("#4cafe8"));
        setFont(Font.font(24));
        setCursor(Cursor.HAND);
        setAlignment(Pos.CENTER_LEFT);
        // getChildren().stream().forEach(n -> n.setStyle("-fx-translate-x: 10;"));
        // setPadding(new Insets(0, 0, 0, 20));

        DropShadow ds = new DropShadow(6.25, 0, 6, Color.web("#4cafe8a6"));
        ds.setInput(new InnerShadow(41.8725, 0, 148.49, Color.web("#4cafe8a6")));
        setEffect(new DropShadow(6.25, 0, 6, Color.web("#4cafe8a6")));

        // Icon
        icon.setImage(new Image("jar/images/" + getId() + "Black.png"));
        icon.setFitHeight(40);
        icon.setFitWidth(30);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        icon.setTranslateX(-35.0);
        icon.setCursor(Cursor.HAND);

        setText(text);
        setController(hController);
    }

    public void setController(HomeController hController) {
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
