/*
 * source: https://zetcode.com/javagames/ 
 */
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    //this ones for scaling
    protected void loadImage(String imageName, int scaledWidth, int scaledHeight) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        //scaling the image
        
        image = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        
    }

    //this one doesnt scale
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}