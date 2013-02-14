package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class RegisterScreen extends BaseScreen {
    private static final long serialVersionUID = -4847569599721799776L;

    public LiteTextField pass, pass2, email, name, address, general;
    public LiteButton back, submit;

    public RegisterScreen(App application) {
        super(application);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

        this.setPreferredSize(new Dimension(300, 400));
        Border padding = new EmptyBorder(8, 8, 8, 8);
        padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), padding);

        JLabel title = new JLabel("Create an account", null, JLabel.CENTER);
        email = new LiteTextField("Email");
        pass = new LiteTextField("Password");
        pass2 = new LiteTextField("Password Confirm");

        pass.maskText = true;
        pass2.maskText = true;

        name = new LiteTextField("Name");
        address = new LiteTextField("Address");
        general = new LiteTextField("General");

        back = new LiteButton("Back");
        submit = new LiteButton("Create");

        email.addKeyListener(inputChangeListener);
        pass.addKeyListener(inputChangeListener);

        submit.setBackground(LiteButton.GREEN);
        back.setBackground(Color.white);
        back.setForeground(LiteButton.BLUE);

        submit.setEnabled(false);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(98, 201, 110));
        title.setFont(title.getFont().deriveFont(Font.PLAIN, 20));

        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.add(Box.createVerticalStrut(17));
        this.add(title);
        this.add(Box.createVerticalStrut(25));
        this.add(email);
        this.add(Box.createVerticalStrut(10));
        this.add(pass);
        this.add(Box.createVerticalStrut(10));
        this.add(pass2);
        this.add(Box.createVerticalStrut(10));
        this.add(name);
        this.add(Box.createVerticalStrut(10));
        this.add(address);
        this.add(Box.createVerticalStrut(10));
        this.add(general);
        this.add(Box.createVerticalStrut(35));
        this.add(buttonContainer);

        buttonContainer.add(back);
        buttonContainer.add(new Box.Filler(null, null, null));
        buttonContainer.add(submit);
    }

    /**
     * TODO: Enable/disable the create button unless all fields are valid.
     */
    private void performValidation() {
//        if (email.getText().trim().length() > 0 && email.getText().compareTo("Email") != 0 && pass.getText().trim().length() > 0 && pass.getText().compareTo("Password") != 0) {
//            login.setEnabled(true);
//        } else {
//            login.setEnabled(false);
//        }
    }
    
    private KeyListener inputChangeListener = new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
            performValidation();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            performValidation();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            performValidation();
        }
    };

}
