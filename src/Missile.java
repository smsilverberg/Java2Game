import java.awt.Dimension;
import java.awt.Toolkit;

public class Missile extends Sprite {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int BOARD_WIDTH = (int) screenSize.getWidth();
    private final int MISSILE_SPEED = 5;

    public Missile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("src/pics/missile.png", 100, 100);  
        getImageDimensions();
    }

    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}