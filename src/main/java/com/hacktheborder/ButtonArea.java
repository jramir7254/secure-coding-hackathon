package com.hacktheborder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonArea extends JPanel {
    private JToggleButton[] buttons;
    private final String[] OPTIONS;
    private ButtonGroup group;
    private JButton submitButton;
    private Question question;
    private QuestionArea questionArea;

    public void updateAll() {
        question = ClassManager.getCurrentQuestion();
        questionArea = ClassManager.getQuestionArea();
    }

    public ButtonArea() {


        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 400));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(new Color(77, 88, 101));
        setBackground(Color.BLUE);



        OPTIONS = new String[] {"Compile Time Error", "Runtime Error", "Logic Error", "Vulnerability"};
        buttons = new JToggleButton[4];
        group = new ButtonGroup();
 

        submitButton = new JButton("Submit") {{
            setPreferredSize(new Dimension(500, 50));
            setAlignmentX(Component.CENTER_ALIGNMENT);
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
            questionArea.textPart();
        } else {
            shuffleButtons();
            group.clearSelection();
        }
    }


    private void addButtonsToArray() {
        for (int i = 0; i < OPTIONS.length; i++) { 
            buttons[i] = LayoutCreator.getToggleButton(OPTIONS[i]);
            group.add(buttons[i]);
        }
    }


    private void addButtonsToPanel() {
        for(JToggleButton button : buttons) {
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(button);

        }
        add(Box.createRigidArea(new Dimension(0, 25)));
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
        return group.getSelection().getActionCommand().equals(question.getQuestionType());
    }
}
