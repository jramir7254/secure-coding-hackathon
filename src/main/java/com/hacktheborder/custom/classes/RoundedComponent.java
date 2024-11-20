package com.hacktheborder.custom.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public abstract class RoundedComponent extends JComponent {
    private int cornerRadius;
    private Color backgroundColor;
    private Color borderColor;
    private Color parentColor;


    public RoundedComponent(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Allow custom painting

    }

    public RoundedComponent(int cornerRadius, Color color) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = color;
        this.borderColor = color;
        setOpaque(false); // Allow custom painting

    }

    public RoundedComponent(int cornerRadius, Color color, Color parent) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = color;
        this.borderColor = color;
        this.parentColor = parent;
        setOpaque(false); // Allow custom painting

    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
       
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;

    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {

        
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(parentColor); 
            g2.fillRect(0, 0, getWidth(), getHeight());
            // Draw the rounded background
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            g2.dispose();
    
     
    }

    @Override
    protected void paintBorder(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

           
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            g2.dispose();
   
    }
}
