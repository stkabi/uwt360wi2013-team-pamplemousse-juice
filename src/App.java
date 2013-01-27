import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;


public class App implements ActionListener {
    
    public JFrame frame;
    
    public LoginScreen loginScreen;
    public RegisterScreen registerScreen; 
    
    public Component currentScreen; //currently shown screen 
    public Component nextScreen; //the next screen to be shown, used by the animation
    public Timer animationTimer;  //timer for animations
    
    /**
     * Intial startup of application.
     */
    public App() {
        frame = new JFrame("Weaving App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        animationTimer = new Timer(10, this);
        animationTimer.setInitialDelay(0);
        
        showLogin(); //show login screen intially
        
        frame.pack();
        frame.setLocationRelativeTo(null); //center
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    /**
     * Show login screen.
     */
    public void showLogin() {
        if (loginScreen == null) {
            loginScreen = new LoginScreen();
            loginScreen.register.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    showRegister();
                }
            });
        }
        changeScreen(loginScreen);
        loginScreen.email.requestFocus();
    }
    
    /**
     * Show register screen.
     */
    public void showRegister() {
        if (registerScreen == null) {
            registerScreen = new RegisterScreen();
            registerScreen.back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    showLogin(); //when back is pressed, show login
                }
            });
        }
        changeScreen(registerScreen);
        registerScreen.email.requestFocus();
    }
    
    /**
     * Show a new screen.
     * @param newScreen Screen to show.
     */
    public void changeScreen(Component newScreen) {
        if (!animationTimer.isRunning()) { //prevents a changeScreen while animating
            frame.add(newScreen);
            frame.validate();
            if (currentScreen != null) {
                nextScreen = newScreen;
                animationTimer.start();
            } else {
                currentScreen = newScreen;
            }    
        }
    }
    
    /**
     * Called from animation timer. Incrementally moves the screens.
     */
    public void actionPerformed(ActionEvent arg0) {
        currentScreen.setBounds(currentScreen.getX() - 15, currentScreen.getY(), currentScreen.getWidth(), currentScreen.getHeight());
        nextScreen.setBounds(currentScreen.getX() + nextScreen.getWidth() , currentScreen.getY(), nextScreen.getWidth(), nextScreen.getHeight());
        if (currentScreen.getWidth() + currentScreen.getX() < 15) {
            animationTimer.stop();
            frame.remove(currentScreen);
            currentScreen = nextScreen;
            nextScreen = null;
            frame.validate();
        }
    }
    
    /**
     * Main method. Sets look and feel and starts app.
     * @param args
     */
    public static void main (String[] args) {
        //force consistent L&F cross platform
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception e) { }
        new App();
    }
}
