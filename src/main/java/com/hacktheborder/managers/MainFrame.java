package com.hacktheborder.managers;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainFrame extends JFrame  {

    public MainFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
    }
}