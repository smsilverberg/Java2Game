/*
 * source: https://zetcode.com/javagames/ 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Board extends JPanel implements ActionListener {

    // private static final MouseEvent mAdapter;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private SpaceShip spaceShip;

   






    private List<Zombie> zombies;
    private boolean ingame;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // setSize(screenSize.width, screenSize.height);

    private final int[][] pos = {
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239},
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addMouseListener(new MAdapter());

        addKeyListener(new TAdapter());
        setBackground(Color.GRAY);
        setFocusable(true);

        ingame = true;

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
        
        //maybe something like for 100, {spawn baddies} in here, using a constructor like above
        initZombies();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initZombies() {
        
        zombies = new ArrayList<>();

        for (int[] p : pos) {
            zombies.add(new Zombie(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {drawObjects(g);}
        else {drawGameOver(g);}

        // doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        if (spaceShip.isVisible()) {
            g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
        }

        List<Missile> ms = spaceShip.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), 
                        missile.getY(), this);
            }
        }

        for (Zombie zombie : zombies) {
            if (zombie.isVisible()) {
                g.drawImage(zombie.getImage(), zombie.getX(), zombie.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + zombies.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
    
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, ( (int) screenSize.getWidth() - fm.stringWidth(msg)) / 2, (int) screenSize.getWidth() / 2);
    }

    // private void drawObjects(Graphics g) {

    //     Graphics2D g2d = (Graphics2D) g;
        
    //     g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
    //             spaceShip.getY(), this);

    //     List<Missile> missiles = spaceShip.getMissiles();

    //     for (Missile missile : missiles) {
            
    //         g2d.drawImage(missile.getImage(), missile.getX(),
    //                 missile.getY(), this);
    //     }
    // }

    @Override
    public void actionPerformed(ActionEvent e) {

        // int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        // int y = (int) MouseInfo.getPointerInfo().getLocation().getY();

        updateMissiles();
        updateSpaceShip();
        updateZombies();

        checkCollisions();

        repaint();
    }

    private void updateMissiles() {

        List<Missile> missiles = spaceShip.getMissiles();

        // MAdapter mad = new MAdapter();

        // int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        // int y = (int) MouseInfo.getPointerInfo().getLocation().getY();

        for (int i = 0; i < missiles.size(); i++) {
            // spaceShip.mousePressed(MouseEvent.BUTTON1);

            Missile missile = missiles.get(i);


            if (missile.isVisible()) {
                

                missile.move();
            } else {

                missiles.remove(i);
            }
        }
    }

    private void updateSpaceShip() {

        spaceShip.move();
    }

    private void updateZombies() {

        if (zombies.isEmpty()) {
    
            ingame = false;
            return;
        }
    
        for (int i = 0; i < zombies.size(); i++) {
    
            Zombie z = zombies.get(i);
            
            if (z.isVisible()) {
                // z.move();
                //moving towards player
                z.move(spaceShip.getX(), spaceShip.getY());
            } else {
                zombies.remove(i);
            }
        }
    }  

    public void checkCollisions() {

        Rectangle r3 = spaceShip.getScaledBounds();
    
        for (Zombie zombie : zombies) {
            
            Rectangle r2 = zombie.getScaledBounds();
            
            //this is checking player colliding with zombie
            if (r3.intersects(r2)) {
                
                // spaceShip.setVisible(false);
                zombie.setVisible(false);
                // ingame = false; // if this is false, the game ends

                spaceShip.setHealth(spaceShip.getHealth() - 1);
                if (spaceShip.getHealth() == 0) {
                    ingame = false;
                }
            }
        }
        List<Missile> ms = spaceShip.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getScaledBounds();

            for (Zombie zombie : zombies) {

                Rectangle r2 = zombie.getScaledBounds();

                if (r1.intersects(r2)) {
                    
                    m.setVisible(false);
                    zombie.setVisible(false);
                }
            }
        }

    // ...
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            spaceShip.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            spaceShip.mouseReleased(e);
        }
    }

}