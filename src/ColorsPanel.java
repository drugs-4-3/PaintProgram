/**
 * Created by Michał on 19.08.2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;


public class ColorsPanel extends JPanel {


    private Color currentColor;
    private final Color DEFAULT_COLOR = Color.BLACK;
    private HashMap <String, Color> colors = new HashMap<String, Color>();


    public ColorsPanel() {
        currentColor = DEFAULT_COLOR;
        setLayout(new GridLayout(2, 8));
        setColorsMap();
        for (String name : colors.keySet()) {
            ColorButton button = new ColorButton(colors.get(name));
            add(button);
        }
    }

    private void setColorsMap(){
        colors.put("black", Color.black);
        colors.put("blue", Color.BLUE);
        colors.put("brown", new Color(126, 46, 31)); // apparently there is no const for brown color
        colors.put("cyan", Color.CYAN);
        colors.put("dark_gray", Color.DARK_GRAY);
        colors.put("dark_green", new Color(0, 119, 51 ));
        colors.put("gray", Color.GRAY);
        colors.put("green", Color.GREEN);
        colors.put("light_gray", Color.LIGHT_GRAY);
        colors.put("magenta", Color.MAGENTA);
        colors.put("orange", new Color(255, 105, 0)); // const ORANGE looks more like yellow to me
        colors.put("pink", Color.PINK);
        colors.put("purple", new Color(100, 50, 110));
        colors.put("red", Color.RED);
        colors.put("white", Color.WHITE);
        colors.put("yellow", Color.YELLOW);
    }


    public Color getCurrentColor() {
        return currentColor;
    }


    private class ColorButton extends JPanel implements MouseListener {

        private Color color;

        public ColorButton(Color c) {
            color = c;
            setBackground(color);
            setPreferredSize(new Dimension(50,50));
            addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {
            currentColor = color;
        }

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {
            currentColor = color;
        }
    }

}
