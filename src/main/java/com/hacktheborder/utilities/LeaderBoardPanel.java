package com.hacktheborder.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.hacktheborder.custom.classes.RoundedJLabel;
import com.hacktheborder.managers.GUIManager;

public final class LeaderBoardPanel extends JPanel {
    private Timer timer;
    private SQLConnector sqlConnector;


    public LeaderBoardPanel() {

        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH / 4, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(new Color(0, 0, 0, 0));


        sqlConnector = new SQLConnector();


        JPanel leaderBoardContainerJPanel = new JPanel(true) {{
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 2.25), 800));
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};
     

        JLabel leaderBoardTitleJLabel = getRoundedJLabel("Leaderboard");

        timer = new Timer(10_000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaderBoardContainerJPanel.removeAll();
                        
                sqlConnector.startConnection();
                List<String> topTeams = sqlConnector.getTopFive();
                         
                for (String team : topTeams) {
                    leaderBoardContainerJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    leaderBoardContainerJPanel.add(getRoundedJLabel(team));
                }

                leaderBoardContainerJPanel.revalidate();
                leaderBoardContainerJPanel.repaint();
            }
        });
 
        add(leaderBoardTitleJLabel);
        add(leaderBoardContainerJPanel);
        SwingUtilities.invokeLater(() -> timer.start());
    }







    private RoundedJLabel getRoundedJLabel(String text) {
        return new RoundedJLabel(20, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR) {{
            setText(text);
            setFont(new Font("Calibri", Font.BOLD, 20));
            setForeground(Color.WHITE);
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 2.25), 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setHorizontalAlignment(SwingConstants.CENTER);
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }}; 
    }
}
