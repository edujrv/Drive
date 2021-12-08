package jar.controllers;

import jar.dao.FileDAO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewFileController {

    @FXML
    private Button closeBtn;

    @FXML
    private TextField folderName;

    private String actualFolderId;

    public void setActualFolderId(String actualFolderId) {
        this.actualFolderId = actualFolderId;
    }

    @FXML
    private void close(Event e) {
        closeBtn = (Button) e.getSource();
        System.out.println(closeBtn.getParent().getParent());
        closeBtn.getParent().getParent().setVisible(false);
        HomeController.closeMenu();
    }

    @FXML
    private void create(Event e) {
        FileDAO.createFile(folderName.getText(), actualFolderId);
        close(e);
    }

}