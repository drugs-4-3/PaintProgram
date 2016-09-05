/**
 * Created by Michał on 19.08.2016.
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {


    MainPanel mainPanel = new MainPanel();
    Menu menu = new Menu();

    public MainFrame() {
        setJMenuBar(new Menu());
        add(mainPanel);
        pack();
        setResizable(false);
        setTitle("Paint program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
            }
        });
    }


    private class Menu extends JMenuBar {
        private JMenu fileMenu = new JMenu("File");
        private JFileChooser chooser = new JFileChooser();
        private String userDirectory =  System.getProperty("user.home") + "/Desktop";

        public Menu() {
            setFileMenu();
            add(fileMenu);
            //chooser.setCurrentDirectory(new File(userDirectory));
        }

        private void setFileMenu() {

            JMenuItem saveItem = fileMenu.add("Save");
            saveItem.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    chooser.setSelectedFile(new File("filename.png"));
                    int result = chooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        mainPanel.saveImage(chooser.getSelectedFile());
                    }
                }
            });

            JMenuItem loadItem = fileMenu.add("Load");
            loadItem.addActionListener(new AbstractAction() {
               public void actionPerformed(ActionEvent e) {
                   FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "jpg", "jpeg", "png", "gif");
                   chooser.setFileFilter(filter);
                   int result = chooser.showOpenDialog(mainPanel);
                   if (result == JFileChooser.APPROVE_OPTION) {
                       try {
                           mainPanel.loadImage(chooser.getSelectedFile());
                       } catch (IOException ex) {
                           JOptionPane.showMessageDialog(
                                   null,
                                   "Cannot load file",
                                   "Error",
                                   JOptionPane.ERROR_MESSAGE);
                       }
                   }
                   chooser.setFileFilter(null); // don't forget to disable only image filter for chooser
               }
            });

            JMenuItem exitItem = fileMenu.add("Exit");
            exitItem.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
    }

}

/*
TODO:
- size control of the image at boardpanel class (not only 600x400, adjusting loaded image)
- ability to take back drawings with ctrl+z shortcut (probably'll use list of paintcomponents - abstraction
 will find its usage again)
- toolspanel - has to have scrollbars to adjust thickness
- set background color - clear the image
- ???
- end project
 */

