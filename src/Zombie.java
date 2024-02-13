import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Zombie extends Sprite {

    private final int INIT_X = 400;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int BOARD_WIDTH = (int) screenSize.getWidth();
    private final int BOARD_HEIGHT = (int) screenSize.getHeight();
    private final int scaledHeight = 100;
    private final int scaledWidth = 100;

    public Zombie(int x, int y) {
        super(x, y);

        initZombie();
    }

    private void initZombie() {
        loadImage("src/pics/zombie.png", scaledWidth, scaledHeight);

        getImageDimensions();
    }

    public void move() {
        if (x<0) {
            x = INIT_X;
        }
        x-=1;

        if (x > BOARD_WIDTH || y > BOARD_HEIGHT) {
            visible = false;
        }
    }

    //x, y are the player postition
    public void move(int playerX, int playerY) {
        if (super.x < playerX) {super.x += 1;}
        else if (super.x > playerX) {super.x -= 1;}

        if (super.y < playerY) {super.y += 1;}
        else if (super.y > playerY) {super.y -= 1;}
        

    }

    public Rectangle getScaledBounds() {
        return new Rectangle(x, y, scaledWidth, scaledHeight);
    }







    
}
