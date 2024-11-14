package com.hacktheborder;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            for(LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
                System.out.println(lafi);
            }
            ApplicationManager.start();

        } catch (ClassNotFoundException  | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("yea no");
            System.out.println(e.getMessage());
        }
    }
}
