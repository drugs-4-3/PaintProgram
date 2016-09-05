/**
 * Created by Michał on 22.08.2016.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardPanel extends JPanel {


    private final int DEFAULT_WIDTH = 600;
    private final int DEDAULT_HEIGHT = 400;
    private BufferedImage paintImage = new BufferedImage(DEFAULT_WIDTH, DEDAULT_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    private Point2D lastPressedPoint = new Point2D.Double();


    public BoardPanel() {
        setImageBackground(Color.WHITE);
    }


    private void setImageBackground(Color color) {
        Graphics g = paintImage.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, DEFAULT_WIDTH, DEDAULT_HEIGHT);
    }


    public void saveImage(File file) {
        try {
            ImageIO.write(paintImage, "PNG", file);
        } catch (IOException e) {
            // handle the exception
        }
    }


    public void loadImage(File file) throws IOException {
        paintImage = ImageIO.read(file);
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // fixed the bug with displaying copies of buttons
        g.drawImage(paintImage, 0, 0, null);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEDAULT_HEIGHT);
    }


    public void drawPoint(Tool t, Color c, Point p) {
        ToolPoint tp = new ToolPoint(t, c, p);
        Graphics2D g2 = (Graphics2D) paintImage.createGraphics();
        tp.draw(g2);
        g2.dispose();
        repaint();
    }


    public void drawLine(Tool t, Color c, Point p) {
        ToolLine tl = new ToolLine(t, c, p);
        Graphics2D g2 = (Graphics2D) paintImage.createGraphics();
        tl.draw(g2);
        g2.dispose();
        repaint();
    }

    // todo: fucked up abstraction at paintcomponent - use abstract class, don't call every subclass instance independently
    // (as seen above)


    public void setLastPressedPoint(Point2D p) {
        lastPressedPoint.setLocation(p);
    }


    private abstract class PaintingComponent {

        protected Tool tool;
        protected Point2D point;
        protected Color color;

        public PaintingComponent(Tool tool, Color color, Point2D point) {
            this.tool = tool;
            this.color = color;
            this.point = point;
        }

        public abstract void draw(Graphics2D g2);
    }


    private class ToolPoint extends PaintingComponent {

        public ToolPoint(Tool tool, Color color, Point2D point) {
            super(tool, color, point);
        }

        public void draw(Graphics2D g2) {
            double centerX = point.getX() - (tool.getRadius())/2;
            double centerY = point.getY() - (tool.getRadius())/2;
            Ellipse2D circle = new Ellipse2D.Double(centerX, centerY, tool.getRadius(), tool.getRadius());
            g2.setPaint(color);
            g2.fill(circle);
        }
    }


    private class ToolLine extends PaintingComponent {

        private Line2D line;

        public ToolLine(Tool tool, Color color, Point2D point) {
            super(tool, color, point);
            line = new Line2D.Double(lastPressedPoint.getX(), lastPressedPoint.getY(), point.getX(), point.getY());
        }

        public void draw(Graphics2D g2) {
            g2.setPaint(color);
            g2.setStroke(new BasicStroke(tool.getRadius()));
            g2.draw(line);
        }
    }
}


