package com.hacktheborder.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
import javax.swing.SwingConstants;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


import com.hacktheborder.custom.classes.RoundedJLabel;
import com.hacktheborder.custom.classes.RoundedJTextArea;
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
        

       
        
        RoundedJLabel infoLabel = new RoundedJLabel(20, GUIManager.BUTTON_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR) {
            {
                setBackground(GUIManager.BUTTON_BACKGROUND_COLOR);
                setFont(new Font("Calibri", Font.BOLD, 20));
                setForeground(Color.WHITE);
                setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 2.25), 50));
                setMaximumSize(getPreferredSize());
                setMinimumSize(getPreferredSize());
                setHorizontalAlignment(SwingConstants.CENTER);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setAlignmentX(Component.CENTER_ALIGNMENT);
   
            }
        };

        


        RoundedJTextArea  txcArea = new RoundedJTextArea(25, GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR) {{
            setText(FileManager.readFile());
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 2.25), 1200));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setEditable(false);
            setCaretPosition(0);
            setVisible(false);
        }};


   



        JScrollPane jsp = new JScrollPane(txcArea) {{
            setPreferredSize(new Dimension(400, 400));
            // setMaximumSize(getPreferredSize());
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
            setVisible(false);
        }};

        infoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (txcArea.isVisible()) {
                    System.out.println("setting not visible");
                    txcArea.setVisible(false); 
                    jsp.setVisible(false);

                    //timer.stop();
                } else {
                    System.out.println("setting visible");
                    txcArea.setVisible(true);
                    jsp.setVisible(true);
                    
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        infoLabel.setText("Click for more info");
 
        panel.add(infoLabel);
        panel.add(jsp);
   

        add(panel);
    }



}
    // timer = new Timer(1000, new ActionListener() {
        //     int offset = 0;

        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Rectangle viewRect = scrollPane.getViewport().getViewRect();
        //         viewRect.y += 1; // Adjust the scrolling speed
        //         scrollPane.getViewport().scrollRectToVisible(viewRect);
        //         offset += 20;

        //         // Stop the timer when the end of the panel is reached
        //         if (offset >= panel.getHeight() - viewRect.height) {
        //             timer.stop();
        //         }
        //     }
        // });