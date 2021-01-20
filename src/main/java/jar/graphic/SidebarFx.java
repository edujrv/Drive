package jar.graphic;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SidebarFx extends VBox {

    TranslateTransition openNav = new TranslateTransition(new Duration(350), this);
    TranslateTransition closeNav = new TranslateTransition(new Duration(350), this);
    Timeline openResize = new Timeline();
    Timeline closeResize = new Timeline();
    private double width = 300.0;

    public SidebarFx() {

        this.setStyle("-fx-background-color: white;");
        this.setTranslateX(width);
        openNav.setToX(0);

        openResize.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(this.prefWidthProperty(), 0)),
                new KeyFrame(new Duration(600), new KeyValue(this.prefWidthProperty(), width)));

        closeResize.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(this.prefWidthProperty(), width)),
                new KeyFrame(new Duration(600), new KeyValue(this.prefWidthProperty(), 0)));

    }

    public void openClose() {
        if (this.getTranslateX() != 0) {
            openResize.play();
            openNav.play();
        } else {
            closeResize.play();
            closeNav.setToX(width);
            closeNav.play();
        }
    }
}
