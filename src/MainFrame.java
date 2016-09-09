/**
 * Created by Michał on 19.08.2016.
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
        private JMenu editMenu = new JMenu("Edit");
        private JFileChooser chooser = new JFileChooser();
        private String userDirectory =  System.getProperty("user.home") + "/Desktop";

        public Menu() {
            setFileMenu();
            setEditMenu();
            add(fileMenu);
            add(editMenu);
            //chooser.setCurrentDirectory(new File(userDirectory));
        }

        private void setFileMenu() {
            fileMenu.setMnemonic(KeyEvent.VK_F);
            JMenuItem saveItem = fileMenu.add("Save");
            saveItem.setMnemonic(KeyEvent.VK_S);
            saveItem.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    chooser.setSelectedFile(new File("filename.png"));
                    int result = chooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        mainPanel.saveImage(chooser.getSelectedFile());
                    }
                }
            });

            JMenuItem openItem = fileMenu.add("Open");
            openItem.setMnemonic(KeyEvent.VK_O);
            openItem.addActionListener(new AbstractAction() {
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

        private void setEditMenu() {
            editMenu.setMnemonic(KeyEvent.VK_E);

            JMenuItem removeItem = editMenu.add("Remove last");
            KeyStroke keyStrokeToRemoveLast = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
            removeItem.setAccelerator(keyStrokeToRemoveLast);
            removeItem.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    mainPanel.removeLast();
                }
            });

            JMenuItem clearItem = editMenu.add("Clear");
            KeyStroke keyStrokeToClear = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
            clearItem.setAccelerator(keyStrokeToClear);
            clearItem.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    mainPanel.clearImage();
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

