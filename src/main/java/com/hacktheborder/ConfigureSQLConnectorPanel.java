package com.hacktheborder;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigureSQLConnectorPanel extends JPanel {
    private JTextField ipAddressTextField;
    private JButton submitIPAddressButton;


    public ConfigureSQLConnectorPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        ipAddressTextField = new JTextField("Submit");
        submitIPAddressButton = new JButton() {{
            addActionListener(e -> {
                submitIPAddress();
            });
        }};
    }

    private void submitIPAddress() {
        ApplicationManager.retrySQLConnection(ipAddressTextField.getText());
    }

}