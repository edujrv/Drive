package jar;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import jar.dao.FileDAO;
import jar.model.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

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

        // Asi se usa el get
        Optional<File> of = fdao.get("Ahrex");
        if (of.isEmpty())
            System.out.println("El id no es valido");
        else {
            File file = of.get();
            System.out.println(file.getName() + " - " + file.getPath() + " || " + file.getIdElement() + " || "
                    + file.getContent().getContentType().getType() + " || " + (file.getFileSize() / 1048576.0) + "Mb");
        }

        System.out.println(
                " = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");

        // Asi se puede recorrer por el getAllMyDrive
        Pair<String, List<File>> lf = fdao.getAllMyDrive(null, null);
        // Pair<String, List<File>> lf = fdao.getAllTrashed(null);

        while (lf.getKey() != null) {
            for (File file : lf.getValue())
                // if()
                System.out.println(file.getContent().getDataCreate().getCreatorUser().getName() + " " + file.getName()
                        + " - " + file.getPath() + " || " + file.getIdElement() + " || "
                        + file.getContent().getContentType().getType() + " || " + (file.getFileSize() / 1048576.0)
                        + "Mb");
            System.out.println(
                    "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            lf = fdao.getAllMyDrive(null, lf.getKey());
            // lf = fdao.getAllTrashed(lf.getKey());
        }
        for (File file : lf.getValue())
            System.out.println(file.getName() + " - " + file.getPath() + " || "
                    + file.getContent().getContentType().getType() + " || " + (file.getFileSize() / 1048576.0) + "Mb");
        System.out.println(
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");

        // At this point the user has already logged in
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("views/home"), 1000, 700);
        stage.setTitle("Drive");
        stage.getIcons().add(new Image("https://ssl.gstatic.com/images/branding/product/2x/hh_drive_96dp.png"));
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