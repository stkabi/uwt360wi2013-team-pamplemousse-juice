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
    private boolean boolData[][];
    private Color[][] colorData;
    public int cellWidth = 20, cellHeight = 20;
    private boolean showColors;
    
    private Color light = new Color(200, 200, 200);
    private Color dark = new Color(140, 140, 140);
    
    public GridRenderer(int columns, int rows, boolean data[][]) {
        this.rows = rows;
        this.columns = columns;
        this.boolData = data;
        this.showColors = false;
    }
    
    public GridRenderer(int columns, int rows, Color data[][]) {
        this.rows = rows;
        this.columns = columns;
        this.colorData = data;
        this.showColors = true;
    }
    
    //get a column based on a mouse event
    public int eventToCellX(MouseEvent e) {
        int x = (int)Math.floor((float)(e.getX() - this.getInsets().right) / (float)cellWidth);
        if (x > columns || x < 0) {
            x = -1;
        }
        return x;
    }
    
    //get a row based on a mouse event
    public int eventToCellY(MouseEvent e) {
        int y = (int)Math.floor((float)(e.getY() - this.getInsets().top) / (float)cellHeight);
        if (y > rows || y < 0) {
            y = -1;
        }
        return y;
    }
    
    //paint grid
    public void paintComponent(Graphics g2) {

        int offsetY = this.getInsets().top;
        int offsetX = this.getInsets().right;
        
        Graphics2D g = (Graphics2D) g2;
        
        for (int i = 0; i <= columns; i += 1) {
            for (int j = 0; j <= rows; j += 1) {
                int x = i * cellWidth - 1 + offsetX;
                int y = j * cellHeight - 1 + offsetY;
                
                g.setColor(light);
                g.drawRect(x, y, cellWidth, cellHeight);
                
                if (i % 4 == 0 && i != 0) {
                    g.setColor(dark);
                    g.drawLine(x, y, x, y + cellHeight);
                }
                
                if (j % 4 == 0 && j != 0) {
                    g.setColor(dark);
                    g.drawLine(x, y, x + cellWidth, y);
                }
                
                if (!showColors) {
                    if (boolData[i][j]) {
                        g.setPaint(Color.black);
                    } else {
                        g.setPaint(Color.white);
                    }
                } else {
                    g.setPaint(colorData[i][j]);
                }

                g.fillRect(x + 1, y + 1, cellWidth - 1, cellHeight - 1);
            }
        }
        
     
        
    }

}
