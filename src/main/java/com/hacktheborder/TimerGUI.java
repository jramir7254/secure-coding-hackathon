package com.hacktheborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
 

public class TimerGUI extends JPanel {
    private Timer timer;
    private int totalTimeInSeconds;
    private JTextArea label;
    private JButton button;

    public TimerGUI() {
        totalTimeInSeconds = 0; 
        label = LayoutCreator.getTimeArea(formatTime(totalTimeInSeconds));
        button = new JButton("stop");

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(WIDTH, 100));
        setBackground(new Color(30, 34, 39));


        
        button.addActionListener(e -> stopTimer());

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(formatTime(totalTimeInSeconds));
                //add(label, CENTER_ALIGNMENT);
                totalTimeInSeconds++;
            }
        });

        add(label, CENTER_ALIGNMENT);
        add(button);
    }

    public String formatTime(int totalTimeInSeconds) {
        int hours = totalTimeInSeconds / 3600;                  
        int minutes = (totalTimeInSeconds % 3600) / 60;        
        int seconds = totalTimeInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

}
