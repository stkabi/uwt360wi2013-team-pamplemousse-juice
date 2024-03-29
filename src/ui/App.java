package ui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;

import data.DataProvider;
import entities.User;

public class App implements ActionListener {

    public JFrame frame;

    private User loggedInUser;
    private DataProvider dataProvider;

    public LoginScreen loginScreen;
    public RegisterScreen registerScreen;
    public EntriesScreen entriesScreen;

    public Component currentScreen; // currently shown screen
    public Component nextScreen; // the next screen to be shown, used by the
    // animation
    public Timer animationTimer; // timer for animations

    /** Initial startup of application. */
    public App() {

        this.dataProvider = new DataProvider();

        frame = new JFrame("Weaving App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        animationTimer = new Timer(10, this);
        animationTimer.setInitialDelay(0);

        showLogin(); // show login screen intially

        frame.pack();
        frame.setLocationRelativeTo(null); // center
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    private void handleLogin() {
        switch (loggedInUser.getRole()) {
            case CONTESTANT:
                showEntries();
                break;
            case ORGANIZER:
                showUserList();
                break;
            case JUDGE:
                showJudgeList();
                break;
        }
    }

    /** Show login screen. */
    public void showLogin() {
        if (loginScreen == null) {
            loginScreen = new LoginScreen(this);
            loginScreen.register.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    showRegister();
                }
            });
            loginScreen.login.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    User user = dataProvider.getUserByEmail(loginScreen.email.getText());
                    if (user != null) {
                        if (user.authenticate(loginScreen.pass.getText())) {
                            loggedInUser = user;
                            handleLogin();
                        } else {
                            loginScreen.pass.wiggle();    
                        }
                    } else {
                        loginScreen.email.wiggle();    
                    }
                    
                }
            });
        }
        
        changeScreen(loginScreen);
        loginScreen.email.requestFocus();
    }

    /** Show entries screen. */
    public void showEntries() {
        changeScreen(new EntriesScreen(this));
    }
    
    /** Show judge entry list screen */
    public void showJudgeList() {
        changeScreen(new JudgeListScreen(this));
    }
    
    /** Show organizer's user list screen */
    public void showUserList() {
        //TODO: Implement user screen
        changeScreen(new UserListScreen(this));
    }

    /** Show register screen. */
    public void showRegister() {
        if (registerScreen == null) {
            registerScreen = new RegisterScreen(this);
            registerScreen.back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    showLogin(); // when back is pressed, show login
                    registerScreen.clearFields();
                }
            });
        }
        changeScreen(registerScreen);
        registerScreen.email.requestFocus();
    }

    /**
     * Show a new screen.
     * 
     * @param newScreen
     *            Screen to show.
     */
    public void changeScreen(Component newScreen) {
        if (!animationTimer.isRunning()) { // prevents a changeScreen while
            // animating
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

    /** Called from animation timer. Incrementally moves the screens. */
    public void actionPerformed(ActionEvent arg0) {
        Rectangle r = currentScreen.getBounds();
        r.x -= 15;
        currentScreen.setBounds(r);
        nextScreen.setBounds(r.x + r.width, currentScreen.getY(), nextScreen.getWidth(), nextScreen.getHeight());
        if (r.x + r.width < 15) {
            animationTimer.stop();
            frame.remove(currentScreen);
            currentScreen = nextScreen;
            nextScreen = null;
            frame.validate();
            frame.pack();
        }
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public DataProvider getDataProvider() {
        return this.dataProvider;
    }

    /**
     * Main method. Sets look and feel and starts app.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // force consistent L&F cross platform
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        new App();
    }
}
