package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class LoginScreen extends BaseScreen {
    private static final long serialVersionUID = -4847569599721799776L;

    public LiteTextField pass, email;
    public LiteButton login, register;

    public LoginScreen(App application) {
        super(application);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

        this.setPreferredSize(new Dimension(300, 400));
        Border padding = new EmptyBorder(8, 8, 8, 8);
        padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), padding);

        JLabel title = new JLabel("Login to your account", null, JLabel.CENTER);
        email = new LiteTextField("Email");
        pass = new LiteTextField("Password");
        pass.maskText = true;

        login = new LiteButton("Login");
        register = new LiteButton("Register");

        email.addKeyListener(inputChangeListener);
        pass.addKeyListener(inputChangeListener);

        login.setBackground(LiteButton.GREEN);
        register.setBackground(LiteButton.BLUE);

        login.setEnabled(false);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(98, 201, 110));
        title.setFont(title.getFont().deriveFont(Font.PLAIN, 20));

        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        try {
            JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("res/images/logo.png"))));
            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(picLabel);
        } catch (IOException e) {
        }

        this.add(Box.createVerticalStrut(25));
        this.add(title);
        this.add(Box.createVerticalStrut(25));
        this.add(email);
        this.add(Box.createVerticalStrut(10));
        this.add(pass);
        this.add(Box.createVerticalStrut(60));
        this.add(buttonContainer);

        buttonContainer.add(register);
        buttonContainer.add(new Box.Filler(null, null, null));
        buttonContainer.add(login);
    }

    private void performValidation() {
        if (email.getText().trim().length() > 0 && email.getText().compareTo("Email") != 0 && pass.getText().trim().length() > 0 && pass.getText().compareTo("Password") != 0) {
            login.setEnabled(true);
        } else {
            login.setEnabled(false);
        }
    }

    /**
     * Enable/disable the login button unless all fields are valid.
     */
    private KeyListener inputChangeListener = new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
            performValidation();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            performValidation();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                login.doClick();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            performValidation();
        }
    };

}
