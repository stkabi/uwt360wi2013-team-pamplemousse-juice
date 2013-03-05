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
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class WeaveDraft extends JPanel {
    private static final long serialVersionUID = -1275575997193739739L;
    private Model model;
    private boolean[][] data;
    private GridRenderer threading, tieup, treading, center;

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
        data = new boolean[model.threadWidth][model.treadHeight];

        for (int i = 0; i < model.threadWidth; i += 1) {
            for (int j = 0; j < model.treadHeight; j += 1) {
                data[i][j] = model.getValue(i, j);
            }
        }

        threading = new GridRenderer(model.threadWidth - 1, model.threadHeight - 1, model.threading);
        tieup = new GridRenderer(model.treadWidth - 1, model.threadHeight - 1, model.tieup);
        treading = new GridRenderer(model.treadWidth - 1, model.treadHeight - 1, model.treading);
        center = new GridRenderer(model.threadWidth - 1, model.treadHeight - 1, data);
        
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

        int thw = model.threadWidth * 20, thh = model.threadHeight * 20,
            trw = model.treadWidth * 20, trh = model.treadHeight * 20;
        
        threading.setPreferredSize(new Dimension(thw, thh));
        tieup.setPreferredSize(new Dimension(trw, thh));
        treading.setPreferredSize(new Dimension(trw, trh));
        center.setPreferredSize(new Dimension(trw, trh));

        JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT, threading, center);
        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tieup, treading);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);

        splitPane.setBorder(BorderFactory.createEmptyBorder());
        left.setBorder(BorderFactory.createEmptyBorder());
        right.setBorder(BorderFactory.createEmptyBorder());

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