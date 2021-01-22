package jar.graphic;

import jar.model.Folder;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import java.util.List;

public class Path extends HBox {

   public Path(){
       CompletedPath();
   }

    public  Path(List<Folder> path){
        for (Folder item:
             path) {
            if (path.listIterator().hasNext()){
                addPath(parseFolder(item.getName(),item.getIdElement()),pacman());
            }else{
                addPath(parseFolder(item.getName(),item.getIdElement()),flechaPabajo());
            }

        }
    }

    public void CompletedPath(){
        addPath(parseFolder("Hola que tal", "534"), pacman());
        addPath(parseFolder("Bien, gracias", "sgdf"), pacman());
        addPath(parseFolder("Quieres ser mi amigo?", "23423"), pacman());
        addPath(parseFolder("SII, genial. Seremos amigos por siempre!!!!","gsgfgg"), flechaPabajo());
    }
    public void addPath(Button folder, Button next){
        this.getChildren().addAll(folder, next);
    }

    public static Button parseFolder(String name, String folderId) {

        Button folder = new Button();
        folder.setText(name);
        folder.setId(folderId);

        folder.setStyle("-fx-text-fill: #3e3e3e;" +
                "    -fx-text-alignment: center;" +
                "    -fx-background-color: TRANSPARENT;" +
                "    -fx-background-radius: 40;" +
                "    -fx-font: Normal 24 'Agency FB';" +
                "    -fx-pref-height: 40;" +
                "    -fx-max-width: 200;" +
                "    -fx-cursor: HAND;"+
                "    -fx-padding: 0 5 0 5  ");

        return folder;
    }

    public Button flechaPabajo(){
        Button flecha = new Button();

        flecha.setText("▼");
        flecha.setStyle("-fx-text-fill: rgb(57,56,56);" +
                "    -fx-text-alignment: center;" +
                "    -fx-background-color: transparent;" +
                "    -fx-background-radius: 40;" +
                "    -fx-font-size: 24;" +
                "    -fx-pref-height: 40;" +
                "    -fx-max-width: 60;"+
                "    -fx-padding: 0;"+
                "    -fx-cursor: HAND;");

        return flecha;
    }

    public Button pacman(){
        Button pacman = new Button();

        pacman.setText(">");
        pacman.setStyle("-fx-text-fill: rgb(156, 156, 156);\n" +
                "    -fx-text-alignment: center;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-background-radius: 40;\n" +
                "    -fx-font-size: 24;\n" +
                "    -fx-pref-height: 40;\n" +
                "    -fx-max-width: 60;"+
                "    -fx-padding: 0;");

        return pacman;
    }
}
