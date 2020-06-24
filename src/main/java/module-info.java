module jar {
    requires javafx.controls;
    requires javafx.fxml;

    opens jar to javafx.fxml;
    exports jar;
}