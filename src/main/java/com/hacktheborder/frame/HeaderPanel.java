package com.hacktheborder.frame;

import javax.swing.*;


import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.managers.ApplicationManager.TeamManager;
import com.hacktheborder.utilities.Team;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class HeaderPanel extends JPanel {
    private Timer questiontimer, totalTimer;
    private JLabel questionTimerTextJLabel, totalTimerTextJLabel;
    private JLabel currentTeamNameJLabel;
    private JLabel currentTeamScoreJLabel;
    private JPanel jLabelContainerPanel;
    private int totalTimeInSeconds, questionTimeInSeconds;


    public HeaderPanel() {

        // Sets up the layout for this Header Panel that displays Team Score, Team Name, and Time on Question.
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 75));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        totalTimeInSeconds = 0; 
        questionTimeInSeconds = 0;


        // Initializes Score, Team Name, and Time JLabels
        questionTimerTextJLabel = new JLabel(formatTime(totalTimeInSeconds));
        questionTimerTextJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        totalTimerTextJLabel = new JLabel(formatTime(questionTimeInSeconds));
        totalTimerTextJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        currentTeamNameJLabel = new JLabel();
        currentTeamNameJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        currentTeamScoreJLabel = new JLabel();
        currentTeamScoreJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
 

        // Container Panel that holds JLabels up top
        jLabelContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 20)) {{
            setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(new Color(40, 44, 52));;
        }};
        

        // Timer object that updates the time in JLabel
        questiontimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                questionTimerTextJLabel.setText("Time on Question: " + formatTime(questionTimeInSeconds));
                questionTimeInSeconds++;
            }
        });


        totalTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                totalTimerTextJLabel.setText("Total Time: " + formatTime(totalTimeInSeconds));
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
        jLabelContainerPanel.add(questionTimerTextJLabel);
        jLabelContainerPanel.add(totalTimerTextJLabel);
    }


    public void updateScore() {
        Team currentTeam = TeamManager.getCurrentTeam();
        currentTeamScoreJLabel.setText("Score: " + currentTeam.getTeamScore());
    }


    public int getTotalTimeSeconds() {
        int totalTimeInSecondsForQuestion  = totalTimeInSeconds;
        return totalTimeInSecondsForQuestion;
    }


    public int getQuestionTimeSeconds() {
        int totalTimeInSecondsForQuestion  = questionTimeInSeconds;
        resetQuestionTimer();
        return totalTimeInSecondsForQuestion;
    }


    public void resetAll() {
        totalTimeInSeconds = 0;
        questionTimerTextJLabel.setText("Total Time: " + formatTime(totalTimeInSeconds));
        currentTeamNameJLabel.setText("");
        currentTeamScoreJLabel.setText("");
    }


    public void resetTotalTimer() {
        totalTimeInSeconds = 0;
    }

    public void resetQuestionTimer() {
        questionTimeInSeconds = 0;
    }

    public void resetAllTimers() {
        resetTotalTimer();
        resetQuestionTimer();
    }

    public void startAllTimers() {
        startTotalTimer();
        startQuestionTimer();
    }

    public void stopAllTimers() {
        stopTotalTimer();
        stopQuestionTimer();
    }



    public void startTotalTimer() {
        totalTimer.start();
    }

    public void stopTotalTimer() {
        totalTimer.stop();
    }


    public void startQuestionTimer() {
        questiontimer.start();
    }

    public void stopQuestionTimer() {
        questiontimer.stop();
    }

}
