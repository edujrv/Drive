package jar.controllers;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class Efectos {

    public static InnerShadow newElementBtnOn2() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(76, 175, 232, 0.19));
        innerShadow.setHeight(234.49);
        innerShadow.setRadius(63.37);
        return innerShadow;
    }

    public static InnerShadow newElementBtnOf2() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(0, 0, 0));
        innerShadow.setHeight(0);
        innerShadow.setRadius(0);
        return innerShadow;
    }

    public static DropShadow newElementBtnOn() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(44, 67, 199, 0.6));
        dropShadow.setHeight(25.83);
        dropShadow.setOffsetY(2.0);
        dropShadow.setRadius(10.7075);
        dropShadow.setInput(newElementBtnOn2());
        return dropShadow;
    }

    public static DropShadow newElementBtnOf() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(114, 114, 114));
        dropShadow.setHeight(21);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(10);
        dropShadow.setInput(newElementBtnOf2());
        return dropShadow;
    }

    public static DropShadow grayOn(String id) {
        InnerShadow innerShadow = new InnerShadow();
        if (id.equals("searchBtn") || id.equals("searchExpBtn") || id.equals("cancelSearchBtn"))
            innerShadow.setColor(Color.rgb(102, 102, 102, 0.2));
        else
            innerShadow.setColor(Color.rgb(210, 222, 221, 1));

        innerShadow.setHeight(234.49);
        innerShadow.setRadius(63.37);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(210, 222, 221, 1));
        dropShadow.setHeight(6);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(10);

        dropShadow.setInput(innerShadow);

        return dropShadow;
    }

    public static DropShadow grayOf() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(210, 222, 221, 0));
        innerShadow.setHeight(234.49);
        innerShadow.setRadius(63.37);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(210, 222, 221, 0));
        dropShadow.setHeight(6);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(10);

        dropShadow.setInput(innerShadow);

        return dropShadow;
    }

    public static DropShadow blueOn() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(76, 175, 232, 0.65));
        innerShadow.setHeight(234.49);
        innerShadow.setRadius(63.37);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(76, 175, 232, 0.65));
        dropShadow.setHeight(6);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(10);

        dropShadow.setInput(innerShadow);

        return dropShadow;
    }

}
