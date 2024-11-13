package com.hacktheborder;


import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.components.button.MaterialButtonUI;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import java.nio.file.Path;
import java.nio.file.Paths;





public class Main extends JFrame {
    final int WIDTH = 800, HEIGHT = 800;

    public static void main(String[] args) {

        try {
            File file = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\");
            System.out.println(file.exists());

            ProcessBuilder pb = new ProcessBuilder("javac", "Test.java");
            pb.directory(file);
            pb.redirectErrorStream(true);
            Process pc = pb.start();
            pc.waitFor();    

            System.out.println("finished compile");

            ProcessBuilder pbr = new ProcessBuilder("java", "-cp", ".", "Test.java");
            pbr.directory(file);
            pbr.redirectErrorStream(true);
            Process pr = pbr.start();
            StringBuilder ouput = new StringBuilder();
            

            try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(pr.getInputStream()))) {
                            
                    String line;
                    while ((line = reader.readLine()) != null) {
                       
                        
                        ouput.append(line).append("\n");
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                pr.waitFor();   
                System.out.println(ouput.toString());
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        //new Main();
    }

    @SuppressWarnings("deprecation")
    public Main() {
        setPreferredSize(new Dimension(800, 800));
        JButton roundedButton = new JButton("Rounded Button");
        roundedButton.setUI(new MaterialButtonUI());
        //MaterialUIMovement.add(roundedButton, MaterialColors.LIGHT_BLUE_400);
        //roundedButton.putClientProperty("JButton.buttonType", "roundRect");
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        //add(roundedButton);
    
        
        TimerGUI timer = new TimerGUI();

        setLayout(new BorderLayout());
        add(timer, BorderLayout.NORTH);
        timer.startTimer();

        JPanel q = new JPanel() {{
            setBackground(new Color(77, 88, 115));
            add(new QuestionArea());
        }};

        add(q, BorderLayout.CENTER);

        JPanel p1 = new JPanel() {{
            setPreferredSize(new Dimension(100, HEIGHT));
            setBackground(new Color(77, 88, 115));
        }};
 
        add(p1, BorderLayout.EAST);

        JPanel p2 = new JPanel() {{
            setBackground(new Color(77, 88, 101));
            setPreferredSize(new Dimension(100, HEIGHT));
        }};
        add(p2, BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}