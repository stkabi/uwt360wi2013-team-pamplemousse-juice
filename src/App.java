import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class App {
    
    public static void main (String[] args) {
        JFrame frame = new JFrame("Weaving App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("Hello, world!", null, JLabel.CENTER);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        
        frame.setSize(512, 512);
        frame.setLocationRelativeTo(null); //center
        
        frame.setVisible(true);
    }
    
}
