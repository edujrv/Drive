package jar.graphic;

import java.util.Queue;

import jar.controllers.HomeController;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PathGenerator extends VBox {

    public PathGenerator() {

    }

    public Button getButton(String name) {
        Button btn = new Button();

        btn.setId(name);
        btn.setText(name);
        btn.setTextFill(Color.BLACK);
        btn.getStylesheets().add("jar/Styles/path.css");
        btn.setStyle("pathBtn");

        return btn;
    }

}
