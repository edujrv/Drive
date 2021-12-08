package jar;

//Imports
import java.io.IOException;
import java.security.GeneralSecurityException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// JavaFX App  
public class App extends Application {
    private static Scene scene;

    // Launcher
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        DriveConnection.initialize(); // Logging and credentials
        // At this point the user has already logged in
        launch();
    }

    // Loading home scene and setting window's configutarion
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("views/home"), 1000, 700); // Resolution
        stage.setTitle("Drive"); // App name
        stage.getIcons().add(new Image("https://ssl.gstatic.com/images/branding/product/2x/hh_drive_96dp.png")); // App
                                                                                                                 // icon
        stage.setScene(scene);
        stage.setMaximized(true); // Fullscreen
        stage.show();
    }

    // Setting scene root
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Loading Fxml file
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}