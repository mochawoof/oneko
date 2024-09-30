import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Main {
    private static boolean traySupported = false;
    private static JWindow window;
    private static TrayIcon trayIcon;
    private static JLabel displayLabel;
    private static SpriteImage oneko;
    
    private static void quit() {
        window.dispose();
        System.exit(0);
    }
    public static void main(String[] args) {
        oneko = new SpriteImage(Resources.getAsImage("oneko.gif"), 32, 32);
        
        window = new JWindow();
        window.setSize(oneko.tileWidth, oneko.tileHeight);
        window.setBackground(new Color(255, 255, 255, 0));
        window.setLayout(new BorderLayout());
        window.setAlwaysOnTop(true);
        
        displayLabel = new JLabel();
        displayLabel.setIcon(new ImageIcon(oneko.get(0, 0, 1, 1)));
        displayLabel.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                // Quit on double click if tray not supported
                if (e.getClickCount() > 1 && !traySupported) {
                    quit();
                }
            }
        });
        window.add(displayLabel);
        
        traySupported = SystemTray.isSupported();
        if (traySupported) {
            SystemTray tray = SystemTray.getSystemTray();
            
            // Menu
            PopupMenu trayPopup = new PopupMenu();
          
            // About
            MenuItem aboutItem = new MenuItem("About");
            aboutItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Oneko 2.0\nhttps://github.com/mochawoof/oneko", "About", JOptionPane.PLAIN_MESSAGE);
                }
            });
            trayPopup.add(aboutItem);
            
            // Quit
            MenuItem quitItem = new MenuItem("Quit");
            quitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    quit();
                }
            });
            trayPopup.add(quitItem);
            
            // Set tray icon
            trayIcon = new TrayIcon(oneko.getScaled(oneko.get(3, 3, 1, 1), 16, 16), "Oneko", trayPopup);
            
            try {
                tray.add(trayIcon);
            } catch (Exception e) {
                e.printStackTrace();
                traySupported = false;
                trayIcon = null;
            }
        }
        
        window.setVisible(true);
    }
}