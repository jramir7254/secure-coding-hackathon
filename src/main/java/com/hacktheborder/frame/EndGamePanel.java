package com.hacktheborder.frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hacktheborder.custom.classes.RoundedJLabel;
import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.managers.ApplicationManager.QuestionManager;
import com.hacktheborder.managers.ApplicationManager.TeamManager;

public class EndGamePanel extends RoundedJLabel {
    private JButton newGame, mainMenu;
    private JLabel teamStatsJLabel, teamStatsJLabel2, teamStatsJLabe3;

    public EndGamePanel() {
        super(175, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        newGame = new JButton("New Game") {{
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
        }};

        mainMenu = new JButton("Main Menu") {{
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH,  (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
            setAlignmentX(Container.CENTER_ALIGNMENT);
            addActionListener(e -> {
                ApplicationManager.resetAll();
            });
        }};

        teamStatsJLabel = new JLabel() {{
            setFont(new Font("Calibri", Font.PLAIN, GUIManager.FONT_SIZE_18));
        }};
        teamStatsJLabel2 = new JLabel() {{
            setFont(new Font("Calibri", Font.PLAIN, GUIManager.FONT_SIZE_18));
        }};
        teamStatsJLabe3 = new JLabel() {{
            setFont(new Font("Calibri", Font.PLAIN, GUIManager.FONT_SIZE_18));
        }};

        add(teamStatsJLabel);
        add(teamStatsJLabel2);
        add(teamStatsJLabe3);
        add(mainMenu);
    }

    public void update() {
        teamStatsJLabel.setText(TeamManager.getCurrentTeam().getTeamName());
        teamStatsJLabel2.setText("Score: " + TeamManager.getCurrentTeam().getTeamScore());
        teamStatsJLabe3.setText("Team High Score: " + TeamManager.getCurrentTeam().getTeamScore());
    }


}
