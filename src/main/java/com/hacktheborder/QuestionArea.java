package com.hacktheborder;

import javax.swing.*;
import java.awt.*;



class QuestionArea extends JPanel {
    private JTextArea questionArea;
    private JTextArea nonEditableTextArea1, nonEditableTextArea2;
    private  JTextArea editableTextArea;
    private Question question;
    private ButtonArea buttonArea;
    private ConsolePart consolePart;
    private User user;
    int points;

    public void updateAll() {
        buttonArea = ClassManager.getButtonArea();
        consolePart = ClassManager.getConsolePart();
        user = ClassManager.getUser();
        question = ClassManager.getCurrentQuestion();
        nonEditableTextArea1.setText(question.getNonEditableCode1());
        editableTextArea.setText(question.getEditableCode());
        nonEditableTextArea2.setText(question.getNonEditableCode2());
        multipleChoice();

    }

    public QuestionArea() {
        //setBackground(new Color(77, 88, 101));
        setBackground(Color.RED);
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);



        questionArea = LayoutCreator.getQuestion("What type of error is this?");
        nonEditableTextArea1 = LayoutCreator.getTextArea("");
        editableTextArea = LayoutCreator.getTextArea("");
        nonEditableTextArea2 = LayoutCreator.getTextArea("");

        add(questionArea); 
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(nonEditableTextArea1);
        add(editableTextArea);
        add(nonEditableTextArea2);
        add(Box.createRigidArea(new Dimension(0, 10)));
    }


    public void nextQuestion() {
        question = ClassManager.getCurrentQuestion();
        remove(consolePart);
        questionArea.setText("What type of error is this?");
        nonEditableTextArea1.setText(question.getNonEditableCode1());
        editableTextArea.setText(question.getEditableCode());
        nonEditableTextArea2.setText(question.getNonEditableCode2());

        multipleChoice();
    }

    
    private void multipleChoice() {
        editableTextArea.setEditable(false);
        editableTextArea.setBackground(new Color(30, 59, 82));
        add(buttonArea);
        revalidate();
        repaint();
    }


    public void resetEditableTextArea() {
        editableTextArea.setText(question.getEditableCode());
    }

    public String getEditableTextAreaText() {
        return editableTextArea.getText();
    }

 
    public void textPart() {
        remove(buttonArea);
        String str = "Fix the code below so that the output is: \n" + question.getExpectedOutput();

        questionArea.setText(str);
        editableTextArea.setBackground(new Color(30, 70, 110));
        editableTextArea.setEditable(true);

        add(consolePart);

        revalidate();
        repaint();
    }
}