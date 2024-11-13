package com.hacktheborder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

/**
 * LayoutCreator
 */
public class LayoutCreator {

    public static JButton getButtons() {
        return new JButton();
    }


    public static JTextField getQuestion() {
        return new JTextField() {{
            setText("What type of error is this?");
            setEditable(false);
            setOpaque(true);
            setPreferredSize(new Dimension(500, 50));
            setMaximumSize(getPreferredSize());
            //setBackground(new Color(19, 22, 25));
            setForeground(Color.BLACK);
            setHorizontalAlignment(JTextField.CENTER);
        }};
    }


    public static JTextArea getTextArea(String text, boolean editable) {
        return new JTextArea(){{
            setText(text);
            setFont(new Font("Courier New", Font.PLAIN, 12));
            setEditable(editable);
            setForeground(Color.WHITE);
            setBackground(new Color(30, 59, 82));
            setCaretColor(Color.WHITE);
            setPreferredSize(new Dimension(500, 100));
            setMaximumSize(getPreferredSize());
        }};
    }

    public static JTextArea getTimeArea(String text) {
        return new JTextArea(){{
            setText(text);
            setBackground(new Color(35, 34, 39));
            setForeground(Color.WHITE);
            setEditable(false);
            setFont(new Font("Calibri", -1, 25));
            setCaretColor(getBackground());
        }};
    }

    public static JToggleButton getToggleButton(String buttonType) {
        return new JToggleButton(buttonType) {{
            setActionCommand(buttonType);
            // setContentAreaFilled(false); // Disable default button rendering
            // setFocusPainted(false);
            setOpaque(true);
            //setBorder(new RoundedBorder(75, Color.BLACK));
            //setBorderPainted(false);
            setBackground(new Color(30, 24, 39));
            setForeground(Color.BLACK);
            setPreferredSize(new Dimension(300, 100));
            //setFocusPainted(false);
 
        }};
        //return new RoundedButton(buttonType, 75);
    }
}
