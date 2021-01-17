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

        // Pair<String, List<Object>> result =
        // FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles()
        // .fromStarred().myOwnershipOnly().notOrdered().build();
        Pair<String, List<Object>> result = FileDAO.newQuery().startFromBeginning().defaultPageSize().getFiles()
                .fromMyDrive().myOwnershipOnly().notOrdered().build();

        for (Object obj : result.getValue()) {
            File file = (File) obj;
            System.out.println(file.getName() + " - " + file.getPath() + " || " + file.getIdElement() + " || "
                    + file.getContent().getContentType().getType() + " || " + (file.getFileSize() / 1048576.0) + "Mb");
        }

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