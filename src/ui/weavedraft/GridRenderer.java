package ui.weavedraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class GridRenderer extends JComponent {
    private static final long serialVersionUID = 6277669978587345503L;
    
    private int rows;
    private int columns;
    private boolean data[][];
    public int cellWidth = 20, cellHeight = 20;
    
    private Color light = new Color(200, 200, 200);
    private Color dark = new Color(140, 140, 140);
    
    public GridRenderer(int rows, int columns, boolean data[][]) {
        this.rows = rows;
        this.columns = columns;
        this.data = data;
    }
    
    //get a column based on a mouse event
    public int eventToCellX(MouseEvent e) {
        return (int)Math.floor((float)e.getX() / (float)cellWidth);   
    }
    
    //get a row based on a mouse event
    public int eventToCellY(MouseEvent e) {
        return (int)Math.floor((float)e.getY() / (float)cellHeight);
    }
    
    //paint grid
    public void paintComponent(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        
        for (int i = 0; i <= columns; i += 1) {
            for (int j = 0; j <= rows; j += 1) {
                int x = i * cellWidth - 1;
                int y = j * cellHeight - 1;
                
                g.setColor(light);
                g.drawRect(x, y, cellWidth, cellHeight);
                
                if (i % 4 == 0) {
                    g.setColor(dark);
                    g.drawLine(x, y, x, y + cellHeight);
                }
                
                if (j % 4 == 0) {
                    g.setColor(dark);
                    g.drawLine(x, y, x + cellWidth, y);
                }
                
                if (data[i][j]) {
                    g.setPaint(Color.black);
                } else {
                    g.setPaint(Color.white);
                }
                    g.fillRect(x + 1, y + 1, cellWidth - 1, cellHeight - 1);
            }
        }
        
     
        
    }

}
