package com.hacktheborder.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.hacktheborder.managers.GUIManager;


public class MainFrame extends JFrame  {
    public JLayeredPane jlp;

    public MainFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        add(new JPanel() {{
            setPreferredSize(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }}, BorderLayout.SOUTH);



        setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
    }
}