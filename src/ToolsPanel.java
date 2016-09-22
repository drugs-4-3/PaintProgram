/**
 * Created by Michał on 19.08.2016.
 */


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolsPanel extends JPanel implements ChangeListener {

    private final int DEFAULT_THICKNESS = 5;
    private int thickness = DEFAULT_THICKNESS;
    private JSlider slider = new JSlider(SwingConstants.VERTICAL, 1, 50, DEFAULT_THICKNESS);


    public ToolsPanel() {
        addSlider(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        thickness = slider.getValue();
    }


    public int getThickness() {
        return thickness;
    }


    private void addSlider(JSlider sld) {
        sld.addChangeListener(this);
        add(sld);
    }

}
