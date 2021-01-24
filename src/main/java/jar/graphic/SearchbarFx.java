package jar.graphic;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


public class SearchbarFx extends GridPane {

    TranslateTransition openNav = new TranslateTransition(new Duration(350), this);
    TranslateTransition closeNav = new TranslateTransition(new Duration(350), this);
    Timeline openResize = new Timeline();
    Timeline closeResize = new Timeline();
    private double height = 914.0;

    public SearchbarFx() {

        this.setStyle("-fx-background-color: white;");
        this.setPrefHeight(0.0);
        this.setPrefWidth(914.0);
        this.toFront();
        //this.setTranslateY(-height);
        //openNav.setToY(0);

        openResize.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(this.prefHeightProperty(), 0)),
                new KeyFrame(new Duration(600), new KeyValue(this.prefHeightProperty(), height)));

        closeResize.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(this.prefHeightProperty(), height)),
                new KeyFrame(new Duration(600), new KeyValue(this.prefHeightProperty(), 0)));

    }

    public void openClose() {
        if (this.getPrefHeight() != 0) {
            closeResize.play();
            //openNav.play();
        } else {
            openResize.play();
           // closeNav.setToY(-height);
            //closeNav.play();
        }
    }
}