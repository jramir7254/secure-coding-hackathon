package com.hacktheborder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Other extends JFrame {
    public static void main(String[] args) {
        for(LookAndFeelInfo lfi : UIManager.getInstalledLookAndFeels()) {
            System.out.println(lfi);
        }
    }

    public Other() {
        setPreferredSize(new Dimension(500, 500));
        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.YELLOW);


        JTextArea jta = new JTextArea() {{
            setPreferredSize(new Dimension(100, 100));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(Color.RED);
        }};

        JTextArea jta2 = new JTextArea() {{
            setPreferredSize(new Dimension(100, 100));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(Color.BLUE);
        }};

        JPanel panel = new JPanel() {{
            setPreferredSize(new Dimension(100, 200));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setSize(getPreferredSize());
            setBackground(Color.GREEN);
            add(new JButton("Button") {{
                setPreferredSize(new Dimension(100, 100));
            }});
        }};

        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(jta);
        add(jta2);
        
        add(panel);
        setVisible(true);
    }

}

