package com.hacktheborder.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.hacktheborder.custom.classes.RoundedJPanel;
import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;
import com.hacktheborder.managers.ApplicationManager.QuestionManager;
import com.hacktheborder.managers.GUIManager;

public class MultipleChoicePanel extends RoundedJPanel {
    private final String[] OPTIONS = new String[] {"Compile Time Error", "Runtime Error", "Logic Error", "Vulnerability"};
    private JToggleButton[] buttons;
    private ButtonGroup group;
    private JButton submitButton;
    private int wrongAttempts;
    private final int THIS_PANEL_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.9); //0.85
    private final int THIS_PANEL_HEIGHT = (int)(GUIManager.PANEL_HEIGHT * 0.4);
    private final int THIS_BUTTON_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.1); //0.1



    
    public MultipleChoicePanel() {

        super(35, GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        //setBackground(ComponentGUIManager.backColor3);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(THIS_PANEL_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.4)));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        wrongAttempts = 0;

        //setBackground(ComponentGUIManager.backColor3);


        buttons = new JToggleButton[4];
        group = new ButtonGroup();
 

        submitButton = new JButton("Submit") {{
            setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.5), (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setFont(new Font("Calibri", Font.BOLD, GUIManager.FONT_SIZE_12));
            setBackground(new Color(232, 188, 185));
            setForeground(Color.BLACK);
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                validateQuestion();
            });
        }};

        addButtonsToArray();
        addButtonsToPanel();
    }


    public void validateQuestion(){
        if(isResponseCorrect()) {
            group.clearSelection();
            QuestionAreaManager.setSecSection();
        } else {
            wrongAttempts++;
            shuffleButtons();
            group.clearSelection();
        }
    }


    public int getNumWrongAttempts() {
        int wrongAttempts = this.wrongAttempts;
        resetNumWrongAttempts();
        return wrongAttempts;
    }

    public void resetNumWrongAttempts() {
        this.wrongAttempts = 0;
    }


    private void addButtonsToArray() {
        for (int i = 0; i < OPTIONS.length; i++) { 
            buttons[i] = GUIManager.getToggleButton(OPTIONS[i]);
            group.add(buttons[i]);
        }
    }


    private void addButtonsToPanel() {
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.02))));
        for(JToggleButton button : buttons) {
            add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));
            add(button);

        }
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.02))));
        add(submitButton);
        revalidate();
        repaint();
    }


    private void shuffleButtons() {
        Random rand = new Random();
        for (int i = buttons.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            
            JToggleButton temp = buttons[i];
            buttons[i] = buttons[j]; 
            buttons[j] = temp;
        }
        removeAll();
        addButtonsToPanel();
    }


    public boolean isResponseCorrect() {
        return group.getSelection().getActionCommand().equals(QuestionManager.getCurrentQuestion().getQuestionType());
    }
}
