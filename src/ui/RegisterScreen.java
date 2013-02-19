package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import entities.User;

public class RegisterScreen extends BaseScreen {
    private static final long serialVersionUID = -4847569599721799776L;

    public LiteTextField pass, pass2, email, name, address, general, number;
    public LiteButton back, submit;

    public RegisterScreen(final App application) {
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
        number = new LiteTextField("Phone Number");

        back = new LiteButton("Back");
        submit = new LiteButton("Create");

        email.addKeyListener(inputChangeListener);
        pass.addKeyListener(inputChangeListener);
        pass2.addKeyListener(inputChangeListener);
        address.addKeyListener(inputChangeListener);
        name.addKeyListener(inputChangeListener);
        number.addKeyListener(inputChangeListener);

        submit.setBackground(LiteButton.GREEN);
        back.setBackground(Color.white);
        back.setForeground(LiteButton.BLUE);

        submit.setEnabled(false);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(98, 201, 110));
        title.setFont(title.getFont().deriveFont(Font.PLAIN, 20));

        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.add(Box.createVerticalStrut(15));
        this.add(title);
        this.add(Box.createVerticalStrut(15));
        this.add(email);
        this.add(Box.createVerticalStrut(10));
        this.add(pass);
        this.add(Box.createVerticalStrut(10));
        this.add(pass2);
        this.add(Box.createVerticalStrut(10));
        this.add(name);
        this.add(Box.createVerticalStrut(10));
        this.add(number);
        this.add(Box.createVerticalStrut(10));
        this.add(address);
        this.add(Box.createVerticalStrut(10));
        this.add(general);        
        this.add(Box.createVerticalStrut(10));
        this.add(buttonContainer);
        
        submit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0)
          {
            //change ID generation to data provider?
            int id = (int) Math.random() * 1000;
            application.getDataProvider().saveItem(new User("" + id, User.Role.CONTESTANT,
                            name.getText(),address.getText(), number.getText(),email.getText(),
                            User.hashPassword(pass.getText())));
            application.showLogin();
          }});

        buttonContainer.add(back);
        buttonContainer.add(new Box.Filler(null, null, null));
        buttonContainer.add(submit);
    }

    private void performValidation() {
      boolean valid = true;
      //validate email
      if (email.getText().length() == 0 || email.getText().indexOf('@') == -1 || email.getText() == "email") {
        valid = false;
        email.setToolTipText("Please enter a valid email address");
        } else if (application.getDataProvider().getUserByEmail(email.getText()) != null) {
        valid = false;
        email.setToolTipText("Email already in use");
      } else {
        email.setToolTipText("");
      }
      //validate password match
      if (!pass.getText().equals(pass2.getText())) {
        pass.setToolTipText("Passwords do not match");
        pass2.setToolTipText("Passwords do not match");
        valid = false;
      } else if (pass.getText() == "Password") {
        valid = false;
        pass.setToolTipText("Please enter a password");
        pass2.setToolTipText("Please enter a password");
      } else {
        pass.setToolTipText("");
        pass2.setToolTipText("");
      }
      //validate name
      if (name.getText().length() == 0 || name.getText().equals("Name")) {
        name.setToolTipText("Please enter a name");
        valid = false;
      } else {
        name.setToolTipText("");
      }
      //validate number, simply checking it's not empty atm
      if (number.getText().length() == 0 || number.getText().equals("Phone Number")) {
        valid = false;
        number.setToolTipText("Please enter a phone number");
      } else {
        number.setToolTipText("");
      }
      //validate address
      if (address.getText().length() == 0 || address.getText().equals("Address")) { //presumably we needn't do extensive validation
        address.setToolTipText("Invalid Address"); 
        valid = false;
      } else {
        address.setToolTipText("");
      }
      submit.setEnabled(valid);
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
