package com.hacktheborder.custom.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hacktheborder.managers.GUIManager;

public class RoundedJPanel extends RoundedComponent {
    private int cornerRadius;

    public RoundedJPanel(int radius) {
        super(radius);
        repaint();
    }

    public RoundedJPanel(int radius, Color color) {
        super(radius, color);
        repaint();

    }

    public RoundedJPanel(int radius, Color color, Color parent) {
        super(radius, color, parent);
        repaint();
    }
}
