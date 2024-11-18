package com.hacktheborder.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import com.hacktheborder.custom.classes.RoundedBorder;
import com.hacktheborder.managers.GUIManager;


public class InfoPanel extends JPanel {
    // Timer timer;
    // JScrollPane scrollPane;

    private JScrollPane scrollPane;
    private JPanel panel;
    private Timer timer;
    private JLabel label;


    public InfoPanel() {


        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH / 4, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        //setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH / 5, GUIManager.PANEL_HEIGHT / 1));
        panel.setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        //panel.setBorder(new LineBorder(Color.GREEN));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        

       
        
        JTextField infoLabel = new JTextField("Click for more info") {
            {
                System.out.println("inside vars");
                setOpaque(false);
                setText("asds");
                setForeground(Color.WHITE);
                setPreferredSize(new Dimension(400, 50));
                setMaximumSize(getPreferredSize());
                setMinimumSize(getPreferredSize());
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setAlignmentX(Component.CENTER_ALIGNMENT);
                repaint();
        
            }

            @Override
            protected void paintComponent(Graphics g) {
                System.out.println("inside pain");
                super.paintComponent(g);
            
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(GUIManager.MAIN_FRAME_BACKGROUND_COLOR); 
                g2.fillRect(0, 0, getWidth(), getHeight());
   
                g2.setColor(GUIManager.BUTTON_BACKGROUND_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                g2.dispose();
                
                //repaint();
            }

            @Override
            protected void paintBorder(Graphics g) {
        //SwingUtilities.invokeLater(() -> {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the rounded border
            g2.setColor(GUIManager.BUTTON_BACKGROUND_COLOR);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);

            g2.dispose();
        //);
    }
        };


        JTextArea  txcArea = new JTextArea("compile time errors: These are errors where the program will no \n\t,issing parenthecis, \n\t misspellded words");
        txcArea.setPreferredSize(new Dimension(800, 800));
        txcArea.setMaximumSize(txcArea.getPreferredSize());
        txcArea.setMinimumSize(txcArea.getPreferredSize());
        txcArea.setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
        txcArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        //txcArea.setOpaque(false);
        txcArea.setBorder(null);
        txcArea.setVisible(false);
        txcArea.setEditable(false);



        scrollPane = new JScrollPane(panel);

        timer = new Timer(1000, new ActionListener() {
            int offset = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle viewRect = scrollPane.getViewport().getViewRect();
                viewRect.y += 20; // Adjust the scrolling speed
                scrollPane.getViewport().scrollRectToVisible(viewRect);
                offset += 20;

                // Stop the timer when the end of the panel is reached
                if (offset >= panel.getHeight() - viewRect.height) {
                    timer.stop();
                }
            }
        });

        infoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (txcArea.isVisible()) {
                    txcArea.setVisible(false); 

                    timer.stop();
                } else {
                    txcArea.setVisible(true);
                    timer.start();
                }
            }
        });

        panel.add(infoLabel);
        panel.add(txcArea);


        add(panel);
    }



}
