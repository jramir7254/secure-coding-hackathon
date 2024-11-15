package com.hacktheborder;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
    public static void main(String[] args) {
        try {
            
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //FlatDarkLaf.setup();
            UIManager.put("Button.arc", 20); 
            UIManager.put("TextField.arc", 10); 
            UIManager.put("TextComponent.arc", 20);
            System.out.println(System.getProperty("user.dir"));
    

         
            //ApplicationManager.start();

        } catch (Exception e) {
            System.out.println("yea no");
            System.out.println(e.getMessage());
        }
    }
}
