package jar.model;

//import com.sun.prism.Texture;

//import java.awt.*;

public class Folder extends Element {

    public int accumulated = 0;
    /*
     * public Color colorFolder = Color.BLACK; public Texture textureFolder;
     */

    /*
     * Construct.
     */

    public Folder() {
    }

    /*
     * Getters.
     */

    public int getAccumulated() {
        return accumulated;
    }

    /*
     * public Color getColorFolder() { return colorFolder; }
     * 
     * public Texture getTextureFolder() { return textureFolder; }
     */

    /*
     * Setters.
     */

    public void setAccumulated(int accumulated) {
        this.accumulated = accumulated;
    }

    /*
     * public void setColorFolder(Color colorFolder) { this.colorFolder =
     * colorFolder; }
     * 
     * public void setTextureFolder(Texture textureFolder) { this.textureFolder =
     * textureFolder; }
     */

}
