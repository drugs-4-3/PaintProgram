/**
 * Created by Michał on 19.08.2016.
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {


    private ColorsPanel colorsPanel = new ColorsPanel();
    private ToolsPanel toolsPanel = new ToolsPanel();
    private BoardPanel boardPanel = new BoardPanel();


    public MainPanel() {
        boardPanel.addMouseListener(new MouseHandler());
        boardPanel.addMouseMotionListener(new MouseMotionHandler());
        setLayout(new BorderLayout());
        add(toolsPanel, BorderLayout.WEST);
        add(colorsPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
    }


    public void saveImage(File file) {
        boardPanel.saveImage(file);
    }


    public void loadImage(File file) throws IOException {
        boardPanel.loadImage(file);
    }


    public void clearImage() {
        boardPanel.clearImage();
    }


    public void removeLast() {
        boardPanel.removeLastPaint();
    }


    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            boardPanel.drawPoint(toolsPanel.getCurrentTool(), colorsPanel.getCurrentColor(), e.getPoint());
        }

        public void mousePressed(MouseEvent e) {
            boardPanel.setLastPressedPoint(e.getPoint());
        }
    }


    private class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e){
            boardPanel.drawLine(toolsPanel.getCurrentTool(), colorsPanel.getCurrentColor(), e.getPoint());
            boardPanel.setLastPressedPoint(e.getPoint());
        }
    }
}
