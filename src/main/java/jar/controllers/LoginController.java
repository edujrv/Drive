package jar.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label lblCrack;

    @FXML
    private void cambiarLabel(){
        lblCrack.setText("Sandia");
    }
}
