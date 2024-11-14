package com.hacktheborder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame  {
    private final int WIDTH = 1936, HEIGHT = 1048;




    
    public MainWindow() {
        //updateAll();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addBorders();

        add(ApplicationManager.getMainMenu(), BorderLayout.CENTER);

        setVisible(true);
    }


    public void addBorders() {
        add(new JPanel() {{
            setPreferredSize(new Dimension(WIDTH, 100));
            setBackground(new Color(77, 88, 115));
        }}, BorderLayout.NORTH);
        add(new JPanel() {{
            setPreferredSize(new Dimension(500, 100));
            setBackground(new Color(77, 88, 115));
        }}, BorderLayout.EAST);
        add(new JPanel() {{
            setPreferredSize(new Dimension(WIDTH, 100));
            setBackground(new Color(77, 88, 115));
        }}, BorderLayout.SOUTH);
        add(new JPanel() {{
            setPreferredSize(new Dimension(500, 100));
            setBackground(new Color(77, 88, 115));
        }}, BorderLayout.WEST);
    }
}
