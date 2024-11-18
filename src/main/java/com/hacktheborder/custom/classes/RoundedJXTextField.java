package com.hacktheborder.custom.classes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;

import org.jdesktop.swingx.JXTextField;

public class RoundedJXTextField extends RoundedComponent {
    private JXTextField jxTextField;

    public JXTextField getJXTextField() {
        return this.jxTextField;
    }

    

    public RoundedJXTextField(int radius) {
        super(radius);
        doThis();
    }


    public String getText() {
        return jxTextField.getText();
    }

    public RoundedJXTextField(int radius, Color componentColor) {
        super(radius, componentColor);
        doThis();
    }

    public RoundedJXTextField(int radius, Color componentColor, Color parentColor) {
        super(radius, componentColor, parentColor);
        doThis();
    }

    public void doThis() {
        setLayout(new BorderLayout());
        jxTextField = new JXTextField();
        jxTextField.setOpaque(false);
        jxTextField.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));
        add(jxTextField, BorderLayout.CENTER);
        repaint();
    }

    public void setPrompt(String prompt) {
        jxTextField.setPrompt(prompt);
    }
}
