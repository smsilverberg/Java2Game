/*
 * source: https://zetcode.com/javagames/ 
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Missile extends Sprite {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int BOARD_WIDTH = (int) screenSize.getWidth();
    private final int BOARD_HEIGHT = (int) screenSize.getHeight();
    
    private final int MISSILE_SPEED = 5;

    private final int destinationX;
    private final int destinationY;
    private final int scaledHeight = 100;
    private final int scaledWidth = 100;

    // Graphics2D g2;

    public Missile(int x, int y) {
        super(x, y);
        //missile destination is set on creation only to avoid homing missiles
        destinationX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        destinationY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        // System.out.println(x);
        // System.out.println(y);
        initMissile();
    }
    
    private void initMissile() {
        
        
        loadImage("src/pics/missile.png", scaledWidth, scaledHeight);
        // AffineTransform af = AffineTransform.getTranslateInstance(100, 100);
        // af.rotate(Math.toRadians(90), 50, 50);
        
        getImageDimensions();
    }

    // public void move() {
        
    //     x += MISSILE_SPEED;
        
    //     if (x > BOARD_WIDTH || y > BOARD_HEIGHT) {
    //         visible = false;
    //     }
    // }

    
    public void move() {
        //TODO: fix angle
        double x_start = (double) this.x;
        double y_start = (double) this.y;
        // double destAngle = -Math.toDegrees(Math.atan2(x_start - destinationX, y_start - destinationY))+180;
        double destAngle = Math.atan2(x_start - destinationX, y_start - destinationY);
        // double destAngle = Math.atan2(destinationX - x_start, destinationY - y_start);
        // double destAngle = Math.atan2(x_start - destinationX, y_start - destinationY);
        // double destAngle = -Math.toDegrees(Math.atan2(x_start - destinationX, y_start - destinationY)) +180;
        
        // y_start - destinationY
        // x_start - destinationX


        double dx = (double) (MISSILE_SPEED)*(Math.cos(destAngle));
        double dy = (double) (MISSILE_SPEED)*(Math.sin(destAngle));
        this.x += dx;
        this.y += dy;

        if (x > BOARD_WIDTH || y > BOARD_HEIGHT) {
            visible = false;
        }
    }

    public Rectangle getScaledBounds() {
        return new Rectangle(x, y, scaledWidth, scaledHeight);
    }
}