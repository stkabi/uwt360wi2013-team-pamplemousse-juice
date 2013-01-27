import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

public class LiteTextField extends JPasswordField {
    private static final long serialVersionUID = 4343478043238514738L;
    
    public boolean maskText = false;

    public LiteTextField() {
        this.init();
    }

    public LiteTextField(String arg0) {
        super(arg0);
        this.init();
    }

    public LiteTextField(int arg0) {
        super(arg0);
        this.init();
    }

    public LiteTextField(String arg0, int arg1) {
        super(arg0, arg1);
        this.init();
    }

    public LiteTextField(Document arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);
        this.init();
    }
    
    @SuppressWarnings("deprecation")
    public void init() {
        
        this.setEchoChar((char)0);
        
        //set style
        Border padding = new EmptyBorder(8, 8, 8, 8);
        padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), padding);
        
        this.setBorder(padding);
        this.setBackground(Color.white);
        this.setForeground(new Color(170, 170, 170));
        
        final LiteTextField field = this;
        final String placeholderText = this.getText();
        
        //this creates placeholder text, 
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                field.selectAll();
                if (field.getText().compareTo(placeholderText) == 0) {
                    if (field.maskText) {
                        field.setEchoChar('*');
                    }
                    field.setForeground(Color.darkGray);
                }
            }
            @Override
            public void focusLost(FocusEvent arg0) {
                if (field.getText().trim().compareTo("") == 0 || field.getText().compareTo(placeholderText) == 0) {
                    field.setEchoChar((char)0);
                    field.setText(placeholderText);
                    field.setForeground(Color.lightGray);
                }
            }
        });
    }
}
