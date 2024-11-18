package com.hacktheborder.custom.classes;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import com.hacktheborder.managers.GUIManager;

public class RoundedJTextArea extends RoundedComponent {
    private JTextArea jTextArea;

    public void setThis() {
        setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        jTextArea.setOpaque(false); // Ensures transparency for custom painting
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setFocusable(false);
        jTextArea.setCaretColor(Color.WHITE);
        jTextArea.setEditable(false);
        jTextArea.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));
        jTextArea.setFont(new Font("Calibri", Font.BOLD, 20));
        add(jTextArea, BorderLayout.CENTER);
    }


    public RoundedJTextArea(int radius, Color color) {
        super(radius, color);
        setThis();
    }


    public RoundedJTextArea(int radius, Color color, Color parent) {
        super(radius, color, parent);
        setThis();
    }


    public void setText(String newText) {
        jTextArea.setText(newText);
        repaint();
    }


    public JTextArea getRoundedJTextArea() {
        return this.jTextArea;
    }
}