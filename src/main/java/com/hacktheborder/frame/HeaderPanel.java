package com.hacktheborder.frame;

import javax.swing.*;

import com.hacktheborder.custom.classes.RoundedJLabel;
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
    private JLabel currentTeamScoreJLabel, currentTeamHighScoreJLabel;
    private JPanel jLabelContainerPanel;
    JPanel teamP, timeP, scoreP;
    private int totalTimeInSeconds, questionTimeInSeconds;
    private int wi = (GUIManager.PANEL_WIDTH - 250);
    private int w = (GUIManager.SCREEN_WIDTH - 50 - wi) / 2;

    public HeaderPanel() {
        // Sets up the layout for this Header Panel that displays Team Score, Team Name, and Time on Question.
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 75));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        totalTimeInSeconds = 0; 
        questionTimeInSeconds = 0;


        // Initializes Score, Team Name, and Time JLabels
        questionTimerTextJLabel = new JLabel("Question Time: " + formatTime(questionTimeInSeconds));
        questionTimerTextJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        questionTimerTextJLabel.setText("Question Time: 0:00:00");


        totalTimerTextJLabel = new JLabel("Total Time: " + formatTime(totalTimeInSeconds));
        totalTimerTextJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        totalTimerTextJLabel.setText("Total Time: 0:00:00");


        currentTeamNameJLabel = new JLabel();
        currentTeamNameJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        currentTeamNameJLabel.setText("Team: ");

        currentTeamScoreJLabel = new JLabel();
        currentTeamScoreJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        currentTeamScoreJLabel.setText("Team Current Score: ");

        currentTeamHighScoreJLabel = new JLabel();
        currentTeamHighScoreJLabel.setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
        currentTeamHighScoreJLabel.setText("Team High Score: ");

        
    
     
 

        // Container Panel that holds JLabels up top
        jLabelContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)) {{
            setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(new Color(40, 44, 52));
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);

        }};
      

         teamP = new JPanel() {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH - 250, 50));
            add(currentTeamNameJLabel, BorderLayout.CENTER);
            currentTeamNameJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }};

         scoreP = new JPanel() {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(w, 50));
            add(currentTeamHighScoreJLabel, BorderLayout.WEST);
            add(currentTeamScoreJLabel, BorderLayout.EAST);
            //setAlignmentX(Component.LEFT_ALIGNMENT);
        }};

         timeP = new JPanel() {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(w, 50));
            add(questionTimerTextJLabel, BorderLayout.WEST);
            add(totalTimerTextJLabel, BorderLayout.EAST);
            //etAlignmentX(Component.RIGHT_ALIGNMENT);
        }};

        jLabelContainerPanel.add(scoreP);
        jLabelContainerPanel.add(teamP);
        jLabelContainerPanel.add(timeP);

        

        // Timer object that updates the time in JLabel
        questiontimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                questionTimerTextJLabel.setText("Question Time: " + formatTime(questionTimeInSeconds));
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

        return String.format("%01d:%02d:%02d", hours, minutes, seconds);
    }

    public void updateAll() {
        Team currentTeam = TeamManager.getCurrentTeam();
        String teamInfo = currentTeam.getTeamName();
        currentTeamNameJLabel.setText("Team: " + teamInfo);
        currentTeamScoreJLabel.setText("Team Current Score: " + currentTeam.getTeamScore());
        currentTeamHighScoreJLabel.setText("Team High Score: " + currentTeam.getTeamHighScore());

        // jLabelContainerPanel.add(scoreP);
        // jLabelContainerPanel.add(teamP);
        // jLabelContainerPanel.add(timeP);
    }


    public void updateScore() {
        Team currentTeam = TeamManager.getCurrentTeam();
        currentTeamScoreJLabel.setText("Current Team Score: " + currentTeam.getTeamScore());
    }

    public void updateAllScores() {
        updateScore();
        Team currentTeam = TeamManager.getCurrentTeam();
        currentTeamScoreJLabel.setText("Current Team Score: " + currentTeam.getTeamScore());
        currentTeamHighScoreJLabel.setText("Team High Score: " + currentTeam.getTeamScore());
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
        resetAllTimers();
        questionTimerTextJLabel.setText("Question Time: 0:00:00");
        totalTimerTextJLabel.setText("Total Time: 0:00:00");
        currentTeamNameJLabel.setText("Team: ");
        currentTeamScoreJLabel.setText("Team Current Score: ");
        currentTeamHighScoreJLabel.setText("Team High Score: ");
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
