package ui.weavedraft;

public class Model {
    
    public int gridSize = 0, 
               tieUpSize = 0;
               

    public boolean warp[][], tieup[][], pedals[][];

    public Model(int gridSize, int tieUpSize) {
        
        this.gridSize = gridSize;
        this.tieUpSize = tieUpSize;
        
        // [columns][rows]
        warp = new boolean[gridSize][tieUpSize];
        tieup = new boolean[tieUpSize][tieUpSize];
        pedals = new boolean[tieUpSize][gridSize];
    }
    
    public void toggleTieUp(int x, int y) {
        setTieUp(x, y, !tieup[x][y]);
    }
    
    public void toggleWarp(int x, int y) {
        setWarp(x, y, !warp[x][y]);
    }
    
    public void togglePedals(int x, int y) {
        setPedals(x, y, !pedals[x][y]);
    }

    public void setTieUp(int x, int y, boolean val) {
        tieup[x][y] = val;
    }

    public void setWarp(int x, int y, boolean val) {
        for (int i = 0; i < tieUpSize; i += 1) {
            warp[x][i] = false; 
         }
        warp[x][y] = val;
    }

    public void setPedals(int x, int y, boolean val) {
        for (int i = 0; i < tieUpSize; i += 1) {
           pedals[i][y] = false; 
        }
        pedals[x][y] = val;
    }

    private int isInWarp(int col) {
        for (int i = 0; i < tieUpSize; i += 1) {
            if (warp[col][i]) {
                return i;
            }
        }
        return -1;
    }

    private int isInPedal(int row) {
        for (int i = 0; i < tieUpSize; i += 1) {
            if (pedals[i][row]) {
                return i;
            }
        }
        return -1;
    }

    public boolean getValue(int col, int row) {
        int warp = isInWarp(col), pedal = isInPedal(row);
        if (warp > -1 && pedal > -1) {
            if (tieup[pedal][warp]) {
                return true;
            }
        }
        return false;
    }
}
