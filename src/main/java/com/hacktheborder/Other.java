package com.hacktheborder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;


import javax.swing.*;


public class Other extends JFrame {
    JButton b = new JButton("Button");
    public static void main(String[] args) {
        new Other();
    }


    public void ActionPerformed(ActionEvent e) {
        if(e.getSource() == b) {
            System.out.println("yeas");
        }
    }

    

    public Other() {

        setPreferredSize(new Dimension(500, 500));
        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.YELLOW);
        b.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            System.out.println(e.getSource().toString());
        });
        add(b);
        
        setVisible(true);
    }

}

