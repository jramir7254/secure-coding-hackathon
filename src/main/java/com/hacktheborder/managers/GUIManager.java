package com.hacktheborder.managers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.StyleConstants;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.modes.JavaScriptTokenMaker;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jdesktop.swingx.JXTextField;

import com.hacktheborder.custom.classes.*;
import java.awt.Toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatClientProperties;


public class GUIManager {
    //
    public final static Color MAIN_FRAME_BACKGROUND_COLOR       = new Color(67, 46, 84);
    // public final static Color CENTER_COMPONENT_BACKGROUND_COLOR = new Color(75, 67, 118);
    // public final static Color COMPONENT_CHILD_BACKGROUND_COLOR  = new Color(95, 67, 138);

    public final static Color COMPONENT_CHILD_BACKGROUND_COLOR = new Color(75, 67, 118);
    public final static Color CENTER_COMPONENT_BACKGROUND_COLOR  = new Color(95, 67, 138);

    public final static Color BUTTON_BACKGROUND_COLOR           = new Color(174, 68, 90);


    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_WIDTH = screenSize.width;
    public static final int SCREEN_HEIGHT = screenSize.height;
    public static final int PANEL_WIDTH = (int)(SCREEN_WIDTH / 2);
    public static final int PANEL_HEIGHT = (int)(SCREEN_HEIGHT / 1.2);
    public static final int BUTTON_WIDTH = (int)(PANEL_WIDTH / 1.75);
    public static final int BUTTON_HEIGHT = (int)(SCREEN_HEIGHT / 1.2);






    public static RoundedJTextArea getQuestion(Color color) {
        return new RoundedJTextArea(35, color, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR) {
            {
                setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.85), (int)(PANEL_HEIGHT * 0.1)));
                setMaximumSize(getPreferredSize());
                setMinimumSize(getPreferredSize());
                setLineWrap(true);
                setWrapStyleWord(true);
                setFocusable(false);
                setCaretColor(Color.WHITE);
                setEditable(false);
                setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));
                setFont(new Font("Calibri", Font.BOLD, 20));
                new JScrollPane(this);
            }
        };
    }



    // public static JTextArea getTextArea(String s) {
    //     return new JTextArea(s) {
    //         {
    //             setOpaque(true);
    //             setFont(new Font("Courier New", Font.PLAIN, 13));
    //             setEditable(false);
    //             setForeground(Color.WHITE);
    //             setLineWrap(true);
    //             setWrapStyleWord(true);
    //             setBackground(new Color(35, 39, 46));
    //             setCaretColor(Color.WHITE);
    //             setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.85), 150));
    //             setMaximumSize(getPreferredSize());
    //             setMinimumSize(getPreferredSize());
    //         }
    //     };
    // }

    public static RSyntaxTextArea gSyntaxTextArea() {
        return new RSyntaxTextArea(5, 30) {{
            InputStream themeStream = null;
            try {
                themeStream = GUIManager.class.getResourceAsStream("/dark.xml");
                if (themeStream != null) {
                    Theme theme = Theme.load(themeStream);
                    theme.apply(this);
                } else {
                    System.err.println("Theme file not found!");
                } 
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }  finally {
                if (themeStream != null) {
                try {
                    themeStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
     }
         

            setSyntaxEditingStyle(RSyntaxTextArea.SYNTAX_STYLE_JAVA);


            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.85), (int)(PANEL_HEIGHT * 0.15)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setLineWrap(true);
            setWrapStyleWord(true);

            setOpaque(true);
            setBackground(new Color(40, 44, 52)); // Dark background
            setForeground(new Color(171, 178, 191)); // Default text color
            setEditable(false);
            setSelectionColor(getBackground());
            
            setCodeFoldingEnabled(true);
            setHighlightCurrentLine(false);
            //CustomSyntaxStyling.applyCustomColors(this);
            RTextScrollPane sp = new RTextScrollPane(this);
     
            sp.setLineNumbersEnabled(true);
    
      
        }};
    }


    
    public static RoundedJXTextField getRoundedJXTextField(int r, Color c, Color p) {
        return new RoundedJXTextField(r, c, p) {
            {
                setFont(new Font("Calibri", Font.BOLD, 15));
                setForeground(Color.WHITE);
                setPromptFontStyle(Font.BOLD);
                setPromptForeground(new Color(255, 255, 255, 122));
                setBackground(COMPONENT_CHILD_BACKGROUND_COLOR);
                setAlignmentX(Component.CENTER_ALIGNMENT);
                setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.65), (int)(PANEL_HEIGHT * 0.05)));
                setMaximumSize(getPreferredSize());
                setMinimumSize(getPreferredSize());
            }
        };
    }







    public static JTextField getTimeArea(String text) {
        return new JTextField(){{
            setText(text);
            setHorizontalAlignment(JTextField.CENTER);
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setBackground(new Color(35, 34, 39));
            setForeground(Color.WHITE);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN, 18));
            setCaretColor(getBackground());
            setPreferredSize(new Dimension((int)(SCREEN_WIDTH * 0.1), 50)); // EDIT THISSSSSSSSSSSSSSSSSSSS
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};
    }

    public static JToggleButton getToggleButton(String buttonType) {
        return new JToggleButton(buttonType) {{
            setFont(new Font("Calibri", Font.BOLD, 12));
            setBackground(BUTTON_BACKGROUND_COLOR);
            setActionCommand(buttonType);
            setOpaque(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setForeground(Color.BLACK);
            putClientProperty("JComponent.roundRect", true);
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.5), 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            
        }};
    }
}








// private static void applyAtomDarkTheme(RSyntaxTextArea textArea) {
//     // Set background and foreground colors for Atom Dark
//     textArea.setBackground(new Color(40, 44, 52)); // Dark background
//     //textArea.setForeground(new Color(171, 178, 191)); // Default text color

//     // Keyword colors
//     textArea.getSyntaxScheme().getStyle(Token.RESERVED_WORD).foreground = new Color(198, 120, 221); // Purple
//     textArea.getSyntaxScheme().getStyle(Token.RESERVED_WORD_2).foreground = new Color(198, 120, 221); // Purple
//     textArea.getSyntaxScheme().getStyle(Token.DATA_TYPE).foreground = new Color(86, 182, 194); // Cyan

//     // Comments
//     textArea.getSyntaxScheme().getStyle(Token.COMMENT_EOL).foreground = new Color(92, 99, 112); // Gray
//     textArea.getSyntaxScheme().getStyle(Token.COMMENT_MULTILINE).foreground = new Color(92, 99, 112); // Gray

//     // String literals
//     textArea.getSyntaxScheme().getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = new Color(152, 195, 121); // Green

//     // Operators
//     textArea.getSyntaxScheme().getStyle(Token.OPERATOR).foreground = new Color(171, 178, 191); // Default text color
// }
