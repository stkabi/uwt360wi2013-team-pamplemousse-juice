package ui.weavedraft;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class WeaveDraft extends JPanel {
    private static final long serialVersionUID = -1275575997193739739L;
    private Model model;
    private Color[][] data;
    private GridRenderer threading, tieup, treading, center, threadColor, treadColor;

    public void update() {
        for (int i = 0; i < model.threadWidth; i += 1) {
            for (int j = 0; j < model.treadHeight; j += 1) {
                data[i][j] = model.getValue(i, j);
            }
        }

        threading.invalidate();
        tieup.invalidate();
        treading.invalidate();
        center.invalidate();
        this.revalidate();
        this.repaint();
    }

    /**
     * Save a screenshot of the weavedraft to the specified file path
     * @param path Path to save image to
     * @return Image file
     * @throws IOException
     */
    public File saveScreenshot(String path) throws IOException {
        BufferedImage bufImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        paint(bufImage.createGraphics());
        
        File imageFile = new File(path);
        imageFile.createNewFile();
        ImageIO.write(bufImage, "jpeg", imageFile);
        
        return imageFile;
    }

    public Model getModel() {
        return model;
    }

    public WeaveDraft(int threadWidth, int threadHeight, int treadWidth, int treadHeight) {
        model = new Model(threadWidth, threadHeight, treadWidth, treadHeight);
        init();
    }

    public WeaveDraft(Model model) {
        this.model = model;
        init();
    }

    private void init() {
        data = new Color[model.threadWidth][model.treadHeight];

        for (int i = 0; i < model.threadWidth; i += 1) {
            for (int j = 0; j < model.treadHeight; j += 1) {
                data[i][j] = model.getValue(i, j);
            }
        }

        threading = new GridRenderer(model.threadWidth - 1, model.threadHeight - 1, model.threading);
        tieup = new GridRenderer(model.treadWidth - 1, model.threadHeight - 1, model.tieup);
        treading = new GridRenderer(model.treadWidth - 1, model.treadHeight - 1, model.treading);
        center = new GridRenderer(model.threadWidth - 1, model.treadHeight - 1, data);
        
        threadColor = new GridRenderer(model.threadWidth - 1, 0, model.threadingColor);
        treadColor = new GridRenderer(0, model.treadHeight - 1, model.treadingColor);
        
        threading.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                handleThreadEvent(e, true);
            }
            public void mouseMoved(MouseEvent e) { }
        });

        threading.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                handleThreadEvent(e, false);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
        });
        
        tieup.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                handleTieUpEvent(e, true);
            }
            public void mouseMoved(MouseEvent e) { }
        });

        tieup.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                handleTieUpEvent(e, false);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
        });

        treading.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                handleTreadEvent(e, true);
            }
            public void mouseMoved(MouseEvent e) { }
        });
        
        treading.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                handleTreadEvent(e, false);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
        });
        
        threadColor.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                handleThreadColorEvent(e);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
        });
        
        treadColor.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                handleTreadColorEvent(e);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
        });

        int thw = model.threadWidth * 20, thh = model.threadHeight * 20,
            trw = model.treadWidth * 20, trh = model.treadHeight * 20;

        JSplitPane threadSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, threadColor, threading);
        JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT, threadSplit, center);
        JSplitPane treadSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treading, treadColor);
        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tieup, treadSplit);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        
        threading.setPreferredSize(new Dimension(thw, thh));
        tieup.setPreferredSize(new Dimension(trw + 20 + treadSplit.getDividerSize(), thh + 20 + threadSplit.getDividerSize()));
        tieup.setBorder(BorderFactory.createEmptyBorder(20 + treadSplit.getDividerSize(), 0,  + 20 + treadSplit.getDividerSize(), 0));
        treading.setPreferredSize(new Dimension(trw, trh));
        center.setPreferredSize(new Dimension(trw, trh));
        threadColor.setPreferredSize(new Dimension(thw, 20));
        treadColor.setPreferredSize(new Dimension(20, trh));

        splitPane.setBorder(BorderFactory.createEmptyBorder());
        left.setBorder(BorderFactory.createEmptyBorder());
        right.setBorder(BorderFactory.createEmptyBorder());
        threadSplit.setBorder(BorderFactory.createEmptyBorder());
        treadSplit.setBorder(BorderFactory.createEmptyBorder());

        threadSplit.setEnabled(false);
        treadSplit.setEnabled(false);
        splitPane.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);

        this.add(splitPane);
    }
    
    private void handleThreadEvent(MouseEvent e, boolean set) {
        int cellX = threading.eventToCellX(e);
        int cellY = threading.eventToCellY(e);
        if (cellX > -1 && cellY > -1) {
            if (set) {
                model.setThread(cellX, cellY, true);    
            } else {
                model.toggleThread(cellX, cellY);
            }
            
            update();
        }
    }
    
    private void handleTreadEvent(MouseEvent e, boolean set) {
        int cellX = treading.eventToCellX(e);
        int cellY = treading.eventToCellY(e);
        if (cellX > -1 && cellY > -1) {
            if (set) {
                model.setTread(cellX, cellY, true);
            } else {
                model.toggleTread(cellX, cellY);    
            }
            
            update();
        }
    }
    
    private void handleTreadColorEvent(MouseEvent e) {
        int cellY = treadColor.eventToCellY(e);
        if (cellY > -1) {
            Color newC = JColorChooser.showDialog(this, "Choose Background Color", model.treadingColor[0][cellY]);
            model.treadingColor[0][cellY] = newC != null ? newC : model.treadingColor[0][cellY];
            update();
        }
    }

    private void handleThreadColorEvent(MouseEvent e) {
        int cellX = threadColor.eventToCellX(e);
        if (cellX > -1) {
            Color newC = JColorChooser.showDialog(this, "Choose Background Color", model.threadingColor[cellX][0]);
            model.threadingColor[cellX][0] = newC != null ? newC : model.threadingColor[cellX][0];
            update();
        }
    }
    
    private void handleTieUpEvent(MouseEvent e, boolean set) {
        int cellX = tieup.eventToCellX(e);
        int cellY = tieup.eventToCellY(e);
        if (cellX > -1 && cellY > -1) {
            if (set) {
                model.setTieUp(cellX, cellY, true);    
            } else {
                model.toggleTieUp(cellX, cellY);
            }
            
            update();   
        }
    }
}