package com.hacktheborder;

import javax.swing.*;
import java.awt.*;



class QuestionArea extends JPanel {
    private JTextArea questionArea;
    private JTextArea nonEditableTextArea1, nonEditableTextArea2;
    private JTextArea editableTextArea;

    public void updateTextAreaFields() {
        Question question = ApplicationManager.getCurrentQuestion();
        questionArea.setText("What type of error is this?");
        nonEditableTextArea1.setText(question.getNonEditableCode1());
        editableTextArea.setText(question.getEditableCode());
        editableTextArea.setEditable(false);
        editableTextArea.setBackground(new Color(30, 59, 82));
        nonEditableTextArea2.setText(question.getNonEditableCode2());

    }

    public QuestionArea() {
        setBackground(new Color(77, 88, 101));
        //setBackground(Color.RED);
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);


        questionArea = LayoutCreator.getQuestion("What type of error is this?");
        questionArea.putClientProperty("JComponent.roundRect", true);
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
        updateTextAreaFields();
        remove(ApplicationManager.getConsolePart());
        displayMultipleChoiceSection();
    }

    
    private void displayMultipleChoiceSection() {
        add(ApplicationManager.getButtonArea());
        revalidate();
        repaint();
    }


    public void resetEditableTextArea() {
        editableTextArea.setText(ApplicationManager.getCurrentQuestion().getEditableCode());
    }


    public String getEditableTextAreaText() {
        return editableTextArea.getText();
    }

 
    public void displayDebugginSection() {
        remove(ApplicationManager.getButtonArea());
        String str = "Fix the code below so that the output is: \n" + ApplicationManager.getCurrentQuestion().getExpectedOutput();

        questionArea.setText(str);
        editableTextArea.setBackground(new Color(30, 70, 110));
        editableTextArea.setEditable(true);

        add(ApplicationManager.getConsolePart());

        revalidate();
        repaint();
    }
}