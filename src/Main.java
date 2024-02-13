/*
 * source: https://zetcode.com/javagames/ 
 */

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());
        
        setResizable(false);
        pack();
    
        setTitle("Game Test");
        

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width, screenSize.height);
        
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}