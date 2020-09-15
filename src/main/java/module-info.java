module jar {
    requires javafx.controls;
    requires javafx.fxml;

    opens jar to javafx.fxml;
    opens jar.controllers to javafx.fxml;

    exports jar;
}