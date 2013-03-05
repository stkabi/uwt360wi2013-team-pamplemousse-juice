package ui.weavedraft;

import java.awt.Color;
import java.io.Serializable;

public class Model implements Serializable {
    private static final long serialVersionUID = -7309399360699214636L;

    public int threadWidth, threadHeight, treadWidth, treadHeight;
               
    public boolean threading[][], tieup[][], treading[][];
    public Color threadingColor[], treadingColor[];

    public Model(int threadWidth, int threadHeight, int treadWidth, int treadHeight) {
        this.threadWidth = threadWidth;
        this.threadHeight = threadHeight;
        this.treadWidth = treadWidth;
        this.treadHeight = treadHeight;
        
        // [columns][rows]
        threading = new boolean[threadWidth][threadHeight];
        tieup = new boolean[treadWidth][threadHeight];
        treading = new boolean[treadWidth][treadHeight];
    }
    
    public void toggleTieUp(int x, int y) {
        setTieUp(x, y, !tieup[x][y]);
    }
    
    public void toggleThread(int x, int y) {
        setThread(x, y, !threading[x][y]);
    }
    
    public void toggleTread(int x, int y) {
        setTread(x, y, !treading[x][y]);
    }

    public void setTieUp(int x, int y, boolean val) {
        tieup[x][y] = val;
    }

    public void setThread(int x, int y, boolean val) {
        for (int i = 0; i < threadHeight; i += 1) {
            threading[x][i] = false; 
         }
        threading[x][y] = val;
    }

    public void setTread(int x, int y, boolean val) {
        for (int i = 0; i < treadWidth; i += 1) {
           treading[i][y] = false; 
        }
        treading[x][y] = val;
    }

    private int isInThread(int col) {
        for (int i = 0; i < threadHeight; i += 1) {
            if (threading[col][i]) {
                return i;
            }
        }
        return -1;
    }

    private int isInTread(int row) {
        for (int i = 0; i < treadWidth; i += 1) {
            if (treading[i][row]) {
                return i;
            }
        }
        return -1;
    }

    public boolean getValue(int col, int row) {
        int warp = isInThread(col), pedal = isInTread(row);
        if (warp > -1 && pedal > -1) {
            if (tieup[pedal][warp]) {
                return true;
            }
        }
        return false;
    }
}
