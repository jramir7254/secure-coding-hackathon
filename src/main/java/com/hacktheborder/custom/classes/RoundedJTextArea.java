package com.hacktheborder.custom.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextArea;



public class RoundedJTextArea extends JTextArea {
    private int radius;
    private Color componentColor;
    private Color parentColor;



    public RoundedJTextArea(int r, Color c, Color p) {
        this.radius = r;
        this.componentColor = c;
        this.parentColor = p;
        setOpaque(false);
    }





    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(parentColor); 
        g2.fillRect(0, 0, getWidth(), getHeight());
        // Draw the rounded background

        g2.setColor(componentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
 
        g2.dispose();
        super.paintComponent(g);
    }




    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

   
        // Draw the rounded border
        g2.setColor(componentColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        g2.dispose();
    }
}