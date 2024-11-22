package com.hacktheborder.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hacktheborder.custom.classes.RoundedJLabel;
import com.hacktheborder.custom.classes.RoundedJPanel;
import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.managers.ApplicationManager.QuestionManager;
import com.hacktheborder.managers.ApplicationManager.TeamManager;

public class EndGamePanel extends RoundedJPanel {
    private JButton newGame, mainMenu;
    private JLabel teamStatsJLabel, teamStatsJLabel2, teamStatsJLabe3;

    public EndGamePanel() {
        super(125, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        newGame = new JButton("New Game") {{
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
        }};

        mainMenu = new JButton("Main Menu") {{
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH,  (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
            setBackground(GUIManager.BUTTON_BACKGROUND_COLOR);
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_15));
            setAlignmentX(Container.CENTER_ALIGNMENT);
            addActionListener(e -> {
                ApplicationManager.resetAll();
            });
        }};

        teamStatsJLabel = new JLabel() {{
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};
        teamStatsJLabel2 = new JLabel() {{
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};
        teamStatsJLabe3 = new JLabel() {{
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};


        JPanel p = new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
            add(teamStatsJLabel);
            add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.025))));
            add(teamStatsJLabel2);
            add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.025))));
            add(teamStatsJLabe3);
            add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.035))));
            add(mainMenu);
        }};


        add(new JPanel() {{
            setBackground(GUIManager.TRANSPARENT_COLOR);
            setSize(new Dimension(1, (int)(GUIManager.PANEL_HEIGHT * 0.35)));
            setPreferredSize(getSize());
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }}, BorderLayout.NORTH);
        
        add(new JPanel() {{
            setBackground(GUIManager.TRANSPARENT_COLOR);
            setSize(new Dimension(50, 1));
            setPreferredSize(getSize());
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }}, BorderLayout.EAST);
        add(new JPanel() {{
            setBackground(GUIManager.TRANSPARENT_COLOR);
            setSize(new Dimension(1, 50));
            setPreferredSize(getSize());
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }}, BorderLayout.SOUTH);
        add(new JPanel() {{
            setBackground(GUIManager.TRANSPARENT_COLOR);
            setSize(new Dimension(50, 1));
            setPreferredSize(getSize());
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }}, BorderLayout.WEST);

        add(p, BorderLayout.CENTER);
    }

    public void update() {
        teamStatsJLabel.setText(TeamManager.getCurrentTeam().getTeamName());
        teamStatsJLabel2.setText("Score: " + TeamManager.getCurrentTeam().getTeamScore());
        teamStatsJLabe3.setText("Team High Score: " + TeamManager.getCurrentTeam().getTeamScore());
    }


}
