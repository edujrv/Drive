package jar.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;


public class LoginController {
    @FXML
    private Label lblCrack;

    @FXML
    private void cambiarLabel(){
        lblCrack.setText("Sandia");
    }


    @FXML
    public void pressEnter(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            lblCrack.setText("hola");
        }
    }
}
