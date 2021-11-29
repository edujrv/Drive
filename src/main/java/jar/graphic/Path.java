package jar.graphic;

import jar.DriveConnection;
import jar.controllers.HomeController;
import jar.model.Folder;
import javafx.scene.layout.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import com.google.api.services.drive.model.File;

public class Path extends HBox {
    Button root;

    public Path() {
        // this.getChildren().addAll(root, flechaPabajo());
        setRoot("Mi unidad");
    }

    public void setRoot(String name) {
        // Un boton recibe:
        // 1. Nombre
        // 2. Accion en caso de hacerse click
        // TODO
        root = new Button(name);
        this.getChildren().clear();
        this.getChildren().addAll(root, flechaPabajo());
    }

    public void updatePath(PathButton pBtn, HomeController hController) {
        this.getChildren().clear();
        this.getChildren().addAll(root, pacman());
        Stack<Button> buttons = new Stack<Button>();

        List<String> actualFolder = List.of(pBtn.getId());

        // Recorre todas las carpetas padres
        try {
            do {
                File f = DriveConnection.service.files().get(actualFolder.get(0))
                        .setFields("id, name, parents")
                        .execute();
                actualFolder = f.getParents();
                buttons.add(flechaPabajo());
                buttons.add(new PathButton(f.getName(), f.getId(), hController, this));
            } while (actualFolder != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // buttons.add(next);
        // buttons.add(folder);

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

        addPathButton(folder, next);
    }

    public String getActualFolderID() {
        return this.getChildren().get(this.getChildren().size() - 2).getId();
    }

    public String getActualFolderName() {
        return ((Button) this.getChildren().get(this.getChildren().size() - 2)).getText();
    }

    private void addPathButton(Button folder, Button next) {
        this.getChildren().addAll(folder, next);
    }

    public static Button parseFolder(String name, String folderId) {

        Button folder = new Button();
        folder.setText(name);
        folder.setId(folderId);

        folder.setStyle("-fx-text-fill: #3e3e3e;" +
                "    -fx-text-alignment: center;" +
                "    -fx-background-color: TRANSPARENT;" +
                "    -fx-background-radius: 40;" +
                "    -fx-font: Normal 24 'Agency FB';" +
                "    -fx-pref-height: 40;" +
                "    -fx-max-width: 200;" +
                "    -fx-cursor: HAND;" +
                "    -fx-padding: 0 5 0 5  ");

        return folder;
    }

    public Button flechaPabajo() {
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

    public Button pacman() {
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
