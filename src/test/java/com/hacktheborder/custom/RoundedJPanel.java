package com.hacktheborder.custom;

import java.awt.Color;


public class RoundedJPanel extends RoundedComponent {


    public RoundedJPanel(int radius) {
        super(radius);
    }

    public RoundedJPanel(int radius, Color color) {
        super(radius, color);
    }

    public RoundedJPanel(int radius, Color color, Color parent) {
        super(radius, color, parent);
    }
}
