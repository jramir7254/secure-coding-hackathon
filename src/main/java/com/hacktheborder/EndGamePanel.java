package com.hacktheborder;

import java.awt.Container;
import java.awt.Dimension;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.GUIManager;

public class EndGamePanel extends JPanel {
    private JButton newGame, mainMenu;
    private JLabel teamStatsJLabel;

    public EndGamePanel() {
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

        teamStatsJLabel = new JLabel("Team: Huzz\nScore: 420");

        add(teamStatsJLabel);
        add(mainMenu);
    }


}
