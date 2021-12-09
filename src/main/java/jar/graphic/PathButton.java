package jar.graphic;

import java.io.IOException;

import jar.controllers.HomeController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PathButton extends Button {
    public PathButton(String name, String id, HomeController hController, Path path) {
        this.setStyle("-fx-text-fill: #3e3e3e;" +
                "    -fx-text-alignment: center;" +
                "    -fx-background-color: TRANSPARENT;" +
                "    -fx-background-radius: 40;" +
                "    -fx-font: Normal 24 'Agency FB';" +
                "    -fx-pref-height: 40;" +
                "    -fx-max-width: 200;" +
                "    -fx-cursor: HAND;" +
                "    -fx-padding: 0 5 0 5  ");

        this.setText(name);
        this.setId(id);
        setAction(hController, path);
    }

    protected void setAction(HomeController hController, Path path) {
        PathButton pBtn = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    hController.changeFolder(pBtn.getId(), pBtn.getText(), false);
                    path.updatePath(pBtn, hController);
                } catch (IOException e) {
                    System.out.println("No se pudo cambiar la carpeta [PathButton]");
                }
            }
        });
    }
}
