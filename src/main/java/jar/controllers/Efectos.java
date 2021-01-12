package jar.controllers;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Efectos {

    public static DropShadow newElementBtnOn() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLUE);
        dropShadow.setHeight(25.83);
        dropShadow.setOffsetY(2.0);
        dropShadow.setRadius(10.7075);
        return dropShadow;
    }

    public static DropShadow newElementBtnOf() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.TRANSPARENT);
        dropShadow.setHeight(0);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(0);
        return dropShadow;
    }

}
