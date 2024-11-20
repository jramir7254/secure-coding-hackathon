package com.hacktheborder.managers;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.hacktheborder.utilities.SQLConnector;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



public class Main {
    public static void main(String[] args) {

        FlatDarculaLaf.setup();

        UIManager.put("Button.arc", 35);
    
        setupSQLConnection();
       
        SwingUtilities.invokeLater(ApplicationManager::start);
    }

    
    private static void setupSQLConnection() {
        String value = JOptionPane.showInputDialog(new JLabel(), "Enter IP Address", "Start SQL Connection", JOptionPane.QUESTION_MESSAGE);

        try {
            SQLConnector.setURL(value);     
            SQLConnector.trySQLConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JPanel(), "Could not connect to database", "Database err", JOptionPane.WARNING_MESSAGE);

            int option = JOptionPane.showOptionDialog(new JLabel(), "exit or retry", "retry", JOptionPane.YES_NO_OPTION, 
                                                      JOptionPane.DEFAULT_OPTION, null, null, null);
            if(option == 0) 
                setupSQLConnection();
             else 
                System.exit(0);
            
        }
    }
}
