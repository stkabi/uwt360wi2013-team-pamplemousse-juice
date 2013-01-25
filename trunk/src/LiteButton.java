import java.awt.Color;
import java.awt.Component;
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
    
    protected void init(String text, Icon icon) {
        super.init(text, icon);
        final LiteButton button = this;
        
        //set mouse event for hover effect
        this.addMouseListener(new MouseAdapter() {
            private Color old;
            public void mouseExited(MouseEvent e) {
                button.setBackground(old);
            }
         
            public void mouseEntered(MouseEvent e) {
                old = button.getBackground();
                button.setBackground(old.darker());
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
