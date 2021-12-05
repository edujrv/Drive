package jar.graphic;

import java.io.IOException;
import java.util.List;

import jar.controllers.HomeController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public class PathRootButton extends PathButton {

    public PathRootButton(String name, String id, HomeController hController, Path path) {
        super(name, id, hController, path);
    }

    @Override
    protected void setAction(HomeController hController, Path path) {
        PathRootButton pBtn = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    // hController.reloadFiles(pBtn.getId());
                    // hController.reloadFolders(pBtn.getId());
                    Pair<String, List<Object>> rfi = hController.reloadFiles(pBtn.getId());
                    Pair<String, List<Object>> rfo = hController.reloadFolders(pBtn.getId());
                    hController.loadNewElements(rfi, rfo);

                    path.setRoot(pBtn, hController);
                } catch (IOException e) {
                    System.out.println("No se pudo cambiar la carpeta [PathRootButton]");
                }
            }
        });
    }

}
