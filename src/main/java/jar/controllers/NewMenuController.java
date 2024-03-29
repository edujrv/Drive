package jar.controllers;

import java.io.IOException;

import jar.dao.FileDAO;
import jar.graphic.NewFileFx;
import jar.graphic.NewFolderFx;
import jar.graphic.SearchbarFx;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class NewMenuController {

    @FXML
    private static Button closeBtn;
    @FXML
    private Button newFolder;

    private String actualFolderId;

    public void setActualFolderId(String actualFolderId) {
        this.actualFolderId = actualFolderId;
    }

    @FXML
    public void toggleNewFolderPopUp(Event e) throws IOException {
        new NewFolderFx(newFolder, actualFolderId);
    }

    @FXML
    public void toggleNewFilePopUp() throws IOException {
        new NewFileFx(newFolder, actualFolderId);
    }

    @FXML
    public void uploadFile(Event e) throws IOException {
        FileDAO.uploadFile(actualFolderId);
        close(e);
    }

    @FXML
    static void close(Event e) {

        closeBtn = (Button) e.getSource();
        System.out.println(closeBtn.getParent().getParent());
        closeBtn.getParent().getParent().setVisible(false);

        closeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SearchbarFx j = (SearchbarFx) event.getSource();
                j.close();
            }
        });

    }
}