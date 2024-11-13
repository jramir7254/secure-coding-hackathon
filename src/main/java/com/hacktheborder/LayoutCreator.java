package com.hacktheborder;

import java.awt.Color;
import java.awt.Component;
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


    public static JTextArea getQuestion(String s) {
        return new JTextArea(s) {{
            setFont(new Font("Monaco", Font.PLAIN, 15));
            setEditable(false);
            //setBorder(new RoundedBorder(75));
            setPreferredSize(new Dimension(800, 100));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setBackground(new Color(19, 22, 25));
            setForeground(Color.WHITE);
            //setHorizontalAlignment(JTextArea.CENTER);
        }};
    }


    public static JTextArea getTextArea(String s) {
        return new JTextArea(s){{
            setFont(new Font("Courier New", Font.PLAIN, 12));
            setEditable(false);
            setForeground(Color.WHITE);
            setBackground(new Color(30, 59, 82));
            setCaretColor(Color.WHITE);
            setPreferredSize(new Dimension(800, 100));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};
    }

    public static JTextField getForms(String s) {
        return new JTextField(s) {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(800, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setFont(new Font("Ariel", Font.PLAIN, 18));
        }};
    }

    public static JTextField getTimeArea(String text) {
        return new JTextField(){{
            setText(text);
            setHorizontalAlignment(JTextField.CENTER);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setBackground(new Color(35, 34, 39));
            setForeground(Color.WHITE);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN, 25));
            setCaretColor(getBackground());
        }};
    }

    public static JToggleButton getToggleButton(String buttonType) {
        return new JToggleButton(buttonType) {{
            setActionCommand(buttonType);
            setOpaque(true);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setForeground(Color.BLACK);
            setPreferredSize(new Dimension(500, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};
    }
}
