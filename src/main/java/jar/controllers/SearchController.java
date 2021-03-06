package jar.controllers;

import jar.graphic.SearchbarFx;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SearchController {

    @FXML
    private Button closeBtn;

    @FXML
    private void close(Event e) {

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
