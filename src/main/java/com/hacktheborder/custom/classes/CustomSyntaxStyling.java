package com.hacktheborder.custom.classes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;

import java.awt.*;

public class CustomSyntaxStyling {

    public static void applyCustomColors(RSyntaxTextArea textArea) {
        SyntaxScheme scheme = textArea.getSyntaxScheme();
        
        // Set class names (RESERVED_WORD) to Pink HEX: #c678dd
        scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(198, 120, 221);

        // Set class names (VARIABLE) to Red HEX: #e06c75
        scheme.getStyle(Token.VARIABLE).foreground = new Color(224, 108, 117);

        // Set class names (DATA_TYPE) to Blue HEX: #61afef
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(97, 175, 239); 

        // Set method names (FUNCTION) to Yellow HEX: #e5c07b
        scheme.getStyle(Token.FUNCTION).foreground = new Color(229, 192, 123);

        // Set variables (IDENTIFIER) to Yellow HEX: #e5c07b
        scheme.getStyle(Token.IDENTIFIER).foreground = new Color(229, 192, 123);

        textArea.revalidate();
        textArea.repaint();
    }
}