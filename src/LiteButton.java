import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


public class LiteButton extends JButton {
    private static final long serialVersionUID = -3721160745364262167L;
    
    public static Color GREEN = new Color(98, 201, 110);
    public static Color BLUE = new Color(98, 146, 201);
    
    private Color focusColor;
    private Color normalColor;
    private boolean enabled = true;

    public LiteButton() {
    }

    public LiteButton(Icon arg0) {
        super(arg0);
    }

    public LiteButton(String arg0) {
        super(arg0);
    }

    public LiteButton(Action arg0) {
        super(arg0);
    }

    public LiteButton(String arg0, Icon arg1) {
        super(arg0, arg1);
    }
    
    public void setEnabled(boolean enable) {
        this.enabled = enable;
        if (enable) {
            this.setForeground(Color.WHITE);
        } else {
            this.setForeground(new Color(145, 215, 153));
        }
    }
    
    public void setBackground(Color c) {
        super.setBackground(c);
        focusColor = c.darker();
        normalColor = c;
    }
    
    public void setBackgroundState(Color c) {
        super.setBackground(c);
    }
    
    protected void init(String text, Icon icon) {
        super.init(text, icon);
        final LiteButton button = this;
        
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if (!enabled) {
                    button.transferFocus();
                } else {
                    button.setBackgroundState(focusColor);    
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                if (enabled) {
                    button.setBackgroundState(normalColor);
                }
            }
            
        });
        
        //set mouse event for hover effect
        this.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                if (enabled) {
                    button.setBackgroundState(normalColor);
                }
            }
         
            public void mouseEntered(MouseEvent e) {
                if (enabled) {
                    button.setBackgroundState(focusColor);
                }
            }
        });
        
        //set style
        this.setBorder(new EmptyBorder(6,12,6,12));
        this.setOpaque(true);
        this.setBackground(GREEN);
        this.setFocusPainted(false);
        this.setForeground(Color.white);
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
    }
    
    

}
