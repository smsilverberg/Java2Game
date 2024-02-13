/*
 * source: https://zetcode.com/javagames/ 
 */
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite {

    private int dx;
    private int dy;
    private List<Missile> missiles;
    private final int SPEED = 5;
    private final int scaledHeight = 100;
    private final int scaledWidth = 100;
    private int HP = 5;



    public SpaceShip(int x, int y) {
        super(x, y);

        // super.image = super.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        initSpaceShip();
    }

    private void initSpaceShip() {

        missiles = new ArrayList<>();
        
        loadImage("src/pics/player.png", scaledWidth, scaledHeight); 
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void mousePressed(MouseEvent e) {
        fire();
        //TODO: mouse event code
        // int[] coords = new int[2];
        // int key = e.getButton();
        // if (key == MouseEvent.BUTTON1) {
        //     coords[0] = e.getX();
        //     coords[1] = e.getY();
            
        // }
        // System.out.println(coords[0]);
        // System.out.println(coords[1]);
        // return coords;
        
    }

    public void mouseReleased(MouseEvent e) {
        //TODO: mouse event code
        
    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_A) {
            dx = -SPEED;
        }

        if (key == KeyEvent.VK_D) {
            dx = SPEED;
        }

        if (key == KeyEvent.VK_W) {
            dy = -SPEED;
        }

        if (key == KeyEvent.VK_S) {
            dy = SPEED;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }

    public Rectangle getScaledBounds() {
        return new Rectangle(x, y, scaledWidth, scaledHeight);
    }

    public int getHealth() {
        return HP; //because HP is private
    }

    public void setHealth(int health) {
        HP = health;
    }
    
}