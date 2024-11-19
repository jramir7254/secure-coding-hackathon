package com.hacktheborder.managers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame  {

    public MainFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(300,300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
    }
}