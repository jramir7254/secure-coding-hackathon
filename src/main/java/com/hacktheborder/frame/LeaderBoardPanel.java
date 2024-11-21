package com.hacktheborder.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.hacktheborder.custom.classes.RoundedJLabel;
import com.hacktheborder.custom.classes.RoundedJPanel;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.utilities.SQLConnector;

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
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 2.25), GUIManager.PANEL_HEIGHT));
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};
     

        JLabel leaderBoardTitleJLabel = getRoundedJLabel("Leaderboard Top 5", GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);

        timer = new Timer(10_000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaderBoardContainerJPanel.removeAll();
                        
                sqlConnector.startConnection();
                List<String[]> topTeams = sqlConnector.getTopFive();
                         
                for (String[] teamInfo : topTeams) {
                    leaderBoardContainerJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    leaderBoardContainerJPanel.add(getRoundedJPanel(teamInfo[0], teamInfo[1], GUIManager.BUTTON_BACKGROUND_COLOR));
                }

                leaderBoardContainerJPanel.revalidate();
                leaderBoardContainerJPanel.repaint();
            }
        });
 
        add(leaderBoardTitleJLabel);
        add(leaderBoardContainerJPanel);
        SwingUtilities.invokeLater(() -> timer.start());
    }

    private RoundedJPanel getRoundedJPanel(String teamName, String teamScore, Color color) {
        return new RoundedJPanel(20, color, GUIManager.MAIN_FRAME_BACKGROUND_COLOR) {{
            //setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            //setForeground(Color.WHITE);
            setLayout(new BorderLayout());

            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 3), (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
     
            add(getRoundedJLabel2(teamName, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR), BorderLayout.WEST);
            add(getRoundedJLabel2(teamScore, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR), BorderLayout.EAST);
        }}; 
    }


    
    private JLabel getRoundedJLabel2(String text, Color color) {
        return new JLabel() {{
            setText(text);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            setForeground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }}; 
    }







    private RoundedJLabel getRoundedJLabel(String text, Color color) {
        return new RoundedJLabel(20, color, GUIManager.MAIN_FRAME_BACKGROUND_COLOR) {{
            setText(text);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_18));
            setForeground(Color.WHITE);
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH / 3), (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setHorizontalAlignment(SwingConstants.CENTER);
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }}; 
    }
}
