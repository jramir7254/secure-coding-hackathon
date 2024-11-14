package com.hacktheborder;

import java.awt.Container;
import java.awt.Dimension;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EndGameScreen extends JPanel {
    private JButton newGame, mainMenu;

    public EndGameScreen() {
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        newGame = new JButton("New Game") {{
            setPreferredSize(new Dimension(100, 50));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
        }};

        mainMenu = new JButton("Main Menu") {{
            setPreferredSize(new Dimension(100, 50));
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
            setAlignmentX(Container.CENTER_ALIGNMENT);
            addActionListener(e -> {
                ApplicationManager.resetAll();
            });
        }};

        //add(newGame);
        add(mainMenu);
    }


}
