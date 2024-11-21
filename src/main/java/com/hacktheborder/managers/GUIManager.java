package com.hacktheborder.managers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

import com.hacktheborder.custom.classes.*;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;



public class GUIManager {
    //
    public final static Color MAIN_FRAME_BACKGROUND_COLOR       = new Color(69, 46, 85);
    // public final static Color CENTER_COMPONENT_BACKGROUND_COLOR = new Color(75, 67, 118);
    // public final static Color COMPONENT_CHILD_BACKGROUND_COLOR  = new Color(95, 67, 138);

    public final static Color COMPONENT_CHILD_BACKGROUND_COLOR = new Color(75, 67, 118);
    public final static Color CENTER_COMPONENT_BACKGROUND_COLOR  = new Color(95, 67, 138);

    public final static Color BUTTON_BACKGROUND_COLOR           = new Color(174, 68, 90);

    public final static Color TRANSPARENT_COLOR           = new Color(0, 0, 0, 0);


    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_WIDTH = screenSize.width;
    public static final int SCREEN_HEIGHT = screenSize.height;
    public static final int PANEL_WIDTH = (int)(SCREEN_WIDTH / 2.20);
    public static final int PANEL_HEIGHT = (int)(SCREEN_HEIGHT / 1.40);
    public static final int BUTTON_WIDTH = (int)(PANEL_WIDTH / 1.75);
    public static final int BUTTON_HEIGHT = (int)(SCREEN_HEIGHT / 1.2);
    public static final int THIS_PANEL_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.9); //0.85
    public static final int THIS_PANEL_HEIGHT = (int)(GUIManager.PANEL_HEIGHT * 0.4);
    public static final int THIS_BUTTON_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.1); //0.1

    public static final int FONT_SIZE_8 = (int)((double)SCREEN_HEIGHT * 0.0115);
  
    public static final int FONT_SIZE_12 = (int)((double)SCREEN_HEIGHT * 0.0120);

    public static final int FONT_SIZE_15 = (int)((double)SCREEN_HEIGHT * 0.0150);

    public static final int FONT_SIZE_18 = (int)((double)SCREEN_HEIGHT * 0.0175);






    public static RoundedJTextArea getQuestion(Color color) {
     
        System.out.println((int)((double)SCREEN_HEIGHT * 0.0115));
        System.out.println("size 18: " + FONT_SIZE_18);
        return new RoundedJTextArea(35, color, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR) {
            {
                setPreferredSize(new Dimension(THIS_PANEL_WIDTH, (int)(PANEL_HEIGHT * 0.125)));
                setMaximumSize(getPreferredSize());
                setMinimumSize(getPreferredSize());
                setLineWrap(true);
                setWrapStyleWord(true);
                setFocusable(false);
                setCaretColor(Color.WHITE);
                setForeground(Color.WHITE);
                setEditable(false);
                setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
                setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE_15));
                new JScrollPane(this);
            }
        };
    }



    public static RSyntaxTextArea gSyntaxTextArea() {
        return new RSyntaxTextArea() {{
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

            setFont(new Font("Courier New", Font.BOLD, FONT_SIZE_12));
            setPreferredSize(new Dimension(THIS_PANEL_WIDTH, (int)(PANEL_HEIGHT * 0.175)));
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
                setFont(new Font("Calibri", Font.BOLD, FONT_SIZE_15));
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
            setFont(new Font("Calibri", Font.PLAIN, FONT_SIZE_18));
            setCaretColor(getBackground());
            setPreferredSize(new Dimension((int)(SCREEN_WIDTH * 0.1), (int)(PANEL_HEIGHT * 0.05))); // EDIT THISSSSSSSSSSSSSSSSSSSS
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};
    }

    public static JToggleButton getToggleButton(String buttonType) {
        return new JToggleButton(buttonType) {{
            setFont(new Font("Calibri", Font.BOLD, FONT_SIZE_12));
            setBackground(BUTTON_BACKGROUND_COLOR);
            setActionCommand(buttonType);
            setOpaque(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setForeground(Color.BLACK);
            putClientProperty("JComponent.roundRect", true);
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.5), (int)(PANEL_HEIGHT * 0.06)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            
        }};
    }
}







