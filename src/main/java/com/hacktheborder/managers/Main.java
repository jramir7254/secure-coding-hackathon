package com.hacktheborder.managers;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;



public class Main {

    public static void main(String[] args) {
        System.out.println("first main");
        // Sets up the "LookAndFeel" from the Flatlaf package
        FlatDarculaLaf.setup();

        // Sets up rounded JButtons and JTextField components
        UIManager.put("Button.arc", 20); 
        UIManager.put("TextField.arc", 5); 

        // Sets up the main JFrame for the application
    
        SwingUtilities.invokeLater(ApplicationManager::start);
    }
}
