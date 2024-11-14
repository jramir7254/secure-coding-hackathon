package com.hacktheborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class TimerGUI extends JPanel {
    private Timer timer;
    private JTextField timeTextContainer;
    private JTextField userInfoSection;
    private JTextField userLivesSection;
    private int totalTimeInSeconds;

    public TimerGUI() {
        totalTimeInSeconds = 0; 
        timeTextContainer = LayoutCreator.getTimeArea("");
 

        userInfoSection = new JTextField();
        userLivesSection = new JTextField("lives: ");


        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(WIDTH, 100));
        setBackground(new Color(30, 34, 39));
        timeTextContainer.setBackground(new Color(35, 34, 39));
        timeTextContainer.setForeground(Color.WHITE);
     


        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeTextContainer.setText("Total Time: " + formatTime(totalTimeInSeconds));
                revalidate();
                repaint();
                totalTimeInSeconds++;
            }
        });

        
        add(userLivesSection);
        add(timeTextContainer, CENTER_ALIGNMENT);
        add(userInfoSection);
    }


    private String formatTime(int totalTimeInSeconds) {
        int hours = totalTimeInSeconds / 3600;                  
        int minutes = (totalTimeInSeconds % 3600) / 60;        
        int seconds = totalTimeInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void updateAll() {
        Team currentTeam = ApplicationManager.getCurrentTeam();
        String teamInfo = currentTeam.getTeamName() + currentTeam.getNumMembers() + currentTeam.getEPCCIDNumber();
        userInfoSection.setText(teamInfo);
        userLivesSection.setText("Lives: ");

    }

    public void resetAll() {
        totalTimeInSeconds = 0;
        timeTextContainer.setText("Total Time: " + formatTime(totalTimeInSeconds));
        userInfoSection.setText("");
        userLivesSection.setText("");
        repaint();
        revalidate();
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
