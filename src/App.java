import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;


public class App {
    
    public static void main (String[] args) {
        //force consistent L&F cross platform
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception e) { }
        
        JFrame frame = new JFrame("Weaving App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Login c = new Login();
        
        frame.setLayout(new FlowLayout());
        frame.add(c);
        
//        frame.setSize(305, 255);
        frame.pack();
        frame.setLocationRelativeTo(null); //center
        frame.setVisible(true);
    }
    
}
