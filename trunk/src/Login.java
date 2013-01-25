import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class Login extends JPanel {
    private static final long serialVersionUID = -4847569599721799776L;

    public Login() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Container buttonContainer  = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
        
        this.setPreferredSize(new Dimension(300, 225));
        Border padding = new EmptyBorder(8, 8, 8, 8);
        padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), padding);
        
        JLabel title = new JLabel("Login to your account", null, JLabel.CENTER);
        LiteTextField email = new LiteTextField("Email");
        LiteTextField pass = new LiteTextField("Password");
        pass.maskText = true;
        
        LiteButton login = new LiteButton("Login");
        LiteButton register = new LiteButton("Register");
        
        login.setBackground(LiteButton.GREEN);
        register.setBackground(LiteButton.BLUE);
        
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
        this.add(Box.createVerticalStrut(35));
        this.add(buttonContainer);
        
        buttonContainer.add(register);
        buttonContainer.add(new Box.Filler(null, null, null));
        buttonContainer.add(login);
    }

}
