package com.hacktheborder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame  {
    private final int WIDTH = 1936, HEIGHT = 1048;
    private QuestionArea questionArea;
    private MainMenu mainMenu;
    private TimerGUI timer;



    public void updateAll() {
        mainMenu = ClassManager.getMainMenu();
        questionArea = ClassManager.getQuestionArea();
        timer = ClassManager.getTimerGUI();
    }

    
    public Main() {
        updateAll();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        add(timer, BorderLayout.NORTH);
        add(mainMenu, BorderLayout.CENTER);

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


    public void updateScreen() {
        remove(mainMenu);
        add(questionArea, BorderLayout.CENTER);
        timer.startTimer();
        revalidate();
        repaint();
    }   
}
