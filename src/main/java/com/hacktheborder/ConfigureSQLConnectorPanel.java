package com.hacktheborder;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigureSQLConnectorPanel extends JPanel {
    private JTextField ipAddressTextField;
    private JButton submitIPAddressButton;


    public ConfigureSQLConnectorPanel() {
        ipAddressTextField = new JTextField();
        submitIPAddressButton = new JButton();
    }

}