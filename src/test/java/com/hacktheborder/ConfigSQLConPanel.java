package com.hacktheborder;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.managers.ApplicationManager.SQLManager;

public class ConfigSQLConPanel extends JPanel {
    private JTextField ipAddressTextField;
    private JButton submitIPAddressButton;


    public ConfigSQLConPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, GUIManager.PANEL_WIDTH));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        ipAddressTextField = new JTextField();
        submitIPAddressButton = new JButton() {{
            addActionListener(e -> {
                submitIPAddress();
            });
        }};
    }


    private void submitIPAddress() {
        
    }
}