package com.hacktheborder;

import javax.swing.*;


import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.managers.ApplicationManager.TeamManager;
import com.hacktheborder.utilities.Team;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class HeaderPanel extends JPanel {
    private Timer timer;
    private JLabel timerTextJLabel;
    private JLabel currentTeamNameJLabel;
    private JLabel currentTeamScoreJLabel;
    private JPanel jLabelContainerPanel;
    private int totalTimeInSeconds;


    public HeaderPanel() {

        // Sets up the layout for this Header Panel that displays Team Score, Team Name, and Time on Question.
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 75));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        totalTimeInSeconds = 0; 


        // Initializes Score, Team Name, and Time JLabels
        timerTextJLabel = new JLabel(formatTime(totalTimeInSeconds));
        timerTextJLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        currentTeamNameJLabel = new JLabel();
        currentTeamNameJLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        currentTeamScoreJLabel = new JLabel();
        currentTeamScoreJLabel.setFont(new Font("Calibri", Font.BOLD, 20));
 

        // Container Panel that holds JLabels up top
        jLabelContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 20)) {{
            setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(new Color(40, 44, 52));;
        }};
        

        // Timer object that updates the time in JLabel
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerTextJLabel.setText("Total Time: " + formatTime(totalTimeInSeconds));
                totalTimeInSeconds++;
            }
        });


        add(jLabelContainerPanel);

        
        // Adds a new JPanel below the JLabelContainerPanel to create padding for the center comp
        add(new JPanel() {{
            setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 25));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        }});
    }


    private String formatTime(int totalTimeInSeconds) {

        int hours = totalTimeInSeconds / 3600;                  
        int minutes = (totalTimeInSeconds % 3600) / 60;        
        int seconds = totalTimeInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void updateAll() {
        Team currentTeam = TeamManager.getCurrentTeam();
        String teamInfo = currentTeam.getTeamName();
        currentTeamNameJLabel.setText("Team Name: " + teamInfo);
        currentTeamScoreJLabel.setText("Score: " + currentTeam.getTeamScore());
        jLabelContainerPanel.add(currentTeamScoreJLabel);
        jLabelContainerPanel.add(currentTeamNameJLabel);
        jLabelContainerPanel.add(timerTextJLabel);
    }


    public int getTotalTimeSeconds() {
        int totalTimeInSecondsForQuestion  = totalTimeInSeconds;
        resetTimer();
        return totalTimeInSecondsForQuestion;
    }


    public void resetAll() {
        totalTimeInSeconds = 0;
        timerTextJLabel.setText("Total Time: " + formatTime(totalTimeInSeconds));
        currentTeamNameJLabel.setText("");
        currentTeamScoreJLabel.setText("");
    }


    public void resetTimer() {
        totalTimeInSeconds = 0;
    }


    public void startTimer() {
        timer.start();

    }

    public void stopTimer() {
        timer.stop();
    }

}
