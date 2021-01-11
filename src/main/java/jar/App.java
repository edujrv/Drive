package jar;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import jar.dao.FileDAO;
import jar.model.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        DriveConnection.initialize();

        // Print the names and IDs for up to 10 files.
        // FileList result = DriveConnection.service.files().list().setPageSize(10)
        // .setFields("nextPageToken, files(id, name)").execute();
        // List<File> files = result.getFiles();
        // if (files == null || files.isEmpty()) {
        // System.out.println("No files found.");
        // } else {
        // System.out.println("Files:");
        // for (File file : files) {
        // System.out.printf("%s (%s)\n", file.getName(), file.getId());
        // }
        // }
        FileDAO fdao = new FileDAO();
        List<File> lf = fdao.getAll(0);
        
        System.out.println("----------------------------------------------------------------------------");

        for (File file : lf)
            System.out.println(file.getName() + " - " + file.getIdElement());

        // At this point the user has already logged in
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("views/login"), 1000, 700);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}