package com.hacktheborder.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.hacktheborder.managers.GUIManager;

public class LeaderBoardPanel extends JPanel {
    private Timer timer;
    private JTextArea leaderboard;


   public LeaderBoardPanel() {
        setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH / 4, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);


        leaderboard = new JTextArea();
        leaderboard.setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
        leaderboard.setPreferredSize(new Dimension(200, 800));
        leaderboard.setMaximumSize(getPreferredSize());
        leaderboard.setMinimumSize(getMinimumSize());
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GUIManager.SCREEN_WIDTH / 5, GUIManager.PANEL_HEIGHT / 1));
        panel.setBackground(GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        //panel.setBorder(new LineBorder(Color.GREEN));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);



        timer = new Timer(10_000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        // Database connection and query
                        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.213:3306/secure_coding_database", "new_user", "Qpzm()56");
                        String query = "SELECT * FROM TEAMS ORDER BY Team_Score DESC LIMIT 5";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        ResultSet rs = stmt.executeQuery();

                        List<String> topTeams = new ArrayList<>();
                        while (rs.next()) {
                            String teamName = rs.getString("Team_Name");
                            topTeams.add(teamName);
                        }

                        // Update the UI (SwingWorker recommended for safe UI updates)
                        SwingUtilities.invokeLater(() -> {
                            leaderboard.setText("");
                            for (String team : topTeams) {
                                leaderboard.append(team + "\n");
                            }
                        });

                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }

        });
        panel.add(leaderboard);
     
        timer.start();
        add(panel);
    }
}
