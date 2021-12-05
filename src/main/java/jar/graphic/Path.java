package jar.graphic;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import com.google.api.services.drive.model.File;

import jar.DriveConnection;
import jar.controllers.HomeController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Path extends HBox {
    PathRootButton rootBtn;

    public void setRoot(PathRootButton rootBtn, HomeController hController) {
        this.rootBtn = rootBtn;
        this.getChildren().clear();
        this.getChildren().addAll(rootBtn, flechaPabajo());
    }

    public void setRoot(String name, String unitName, HomeController hController) {
        rootBtn = new PathRootButton(name, unitName, hController, this);
        this.getChildren().clear();
        this.getChildren().addAll(rootBtn, flechaPabajo());
    }

    public void updatePath(PathButton pBtn, HomeController hController) {
        this.getChildren().clear();
        this.getChildren().addAll(rootBtn, pacman());
        Stack<Button> buttons = new Stack<Button>();

        List<String> actualFolder = List.of(pBtn.getId());

        // Recorre todas las carpetas padres
        try {
            do {
                File f = DriveConnection.service.files().get(actualFolder.get(0))
                        .setFields("id, name, parents")
                        .execute();
                actualFolder = f.getParents();
                if (!(f.getName().equals("Mi unidad") && actualFolder == null)) {
                    buttons.add(pacman());
                    buttons.add(new PathButton(f.getName(), f.getId(), hController, this));
                }
            } while (actualFolder != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttons.set(0, flechaPabajo());

        while (!buttons.isEmpty())
            this.getChildren().add(buttons.pop());

    }

    public void addPath(String name, String idFolder, HomeController hController) {
        // reemplaza la flecha abajo con el pacman del ultimo elemento
        if (this.getChildren().size() >= 2) {
            this.getChildren().remove(this.getChildren().size() - 1);
            this.getChildren().add(pacman());
        }

        Button next = flechaPabajo();
        PathButton folder = new PathButton(name, idFolder, hController, this);

        this.getChildren().addAll(folder, next);
    }

    public String getActualFolderID() {
        return this.getChildren().get(this.getChildren().size() - 2).getId();
    }

    public String getActualFolderName() {
        return ((Button) this.getChildren().get(this.getChildren().size() - 2)).getText();
    }

    private Button flechaPabajo() {
        Button flecha = new Button();

        flecha.setText("▼");
        flecha.setStyle("-fx-text-fill: rgb(57,56,56);" +
                "    -fx-text-alignment: center;" +
                "    -fx-background-color: transparent;" +
                "    -fx-background-radius: 40;" +
                "    -fx-font-size: 24;" +
                "    -fx-pref-height: 40;" +
                "    -fx-max-width: 60;" +
                "    -fx-padding: 0;" +
                "    -fx-cursor: HAND;");

        return flecha;
    }

    private Button pacman() {
        Button pacman = new Button();

        pacman.setText(" > ");
        pacman.setStyle("-fx-text-fill: rgb(156, 156, 156);\n" +
                "    -fx-text-alignment: center;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-background-radius: 40;\n" +
                "    -fx-font-size: 24;\n" +
                "    -fx-pref-height: 40;\n" +
                "    -fx-max-width: 60;" +
                "    -fx-padding: 0;");

        return pacman;
    }
}
