/**
 * Created by Michał on 19.08.2016.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolsPanel extends JPanel {


    private Tool pencil = new Tool(3);
    private Tool brush = new Tool(10);

    private Tool currentTool;
    private final Tool DEFAULT_TOOL = pencil;


    public ToolsPanel() {
        setLayout(new GridLayout(2,1));

        currentTool = DEFAULT_TOOL;

        ToolAction pencilAction = new ToolAction("pencil", pencil);
        ToolAction brushAction = new ToolAction("brush", brush);

        add(new JButton(pencilAction));
        add(new JButton(brushAction));
    }


    public Tool getCurrentTool() {
        return currentTool;
    }


    private class ToolAction extends AbstractAction {

        public ToolAction(String name, Tool tool) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, "Set tool to " + name);
            putValue("tool", tool);
        }

        public void actionPerformed(ActionEvent event) {
            currentTool = (Tool) getValue("tool");
        }
    }

}
