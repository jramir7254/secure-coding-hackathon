package com.hacktheborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class TimerGUI extends JPanel {
    private Timer timer;
    private int totalTimeInSeconds;
    private JTextField label;
    JTextField userInfoSection;
    JTextField userLivesSection;
    private User user;

    public TimerGUI() {
        totalTimeInSeconds = 0; 
        label = LayoutCreator.getTimeArea("");
 

        userInfoSection = new JTextField();
        userLivesSection = new JTextField("lives: ");


        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(WIDTH, 100));
        setBackground(new Color(30, 34, 39));
        label.setBackground(new Color(35, 34, 39));
        label.setForeground(Color.WHITE);
     


        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Total Time: " + formatTime(totalTimeInSeconds));
                revalidate();
                repaint();
                totalTimeInSeconds++;
            }
        });

        
        add(userLivesSection);
        add(label, CENTER_ALIGNMENT);
        add(userInfoSection);
    }


    private String formatTime(int totalTimeInSeconds) {
        int hours = totalTimeInSeconds / 3600;                  
        int minutes = (totalTimeInSeconds % 3600) / 60;        
        int seconds = totalTimeInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void updateAll() {
        user = ClassManager.getUser();
        String userInfo = user.getFirstName() + user.getLastName() + user.getEPCCIDNumber();
        userInfoSection.setText(userInfo);
        userLivesSection.setText("Lives: " + user.getNumLives());
        revalidate();
        repaint();
    }


    public void update() {

    }


    public void startTimer() {
        timer.start();

    }


    public void stopTimer() {
        timer.stop();
    }

}
