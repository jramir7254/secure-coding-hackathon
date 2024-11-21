package com.hacktheborder.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import org.fife.ui.rtextarea.RTextArea;

import com.hacktheborder.custom.classes.RoundedJPanel;
import com.hacktheborder.custom.classes.RoundedJTextArea;
import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;
import com.hacktheborder.managers.ApplicationManager.QuestionManager;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.utilities.Question;



public class QuestionContainerPanel extends RoundedJPanel {
    private RoundedJTextArea questionArea;
    private RTextArea nonEditableTextArea1, nonEditableTextArea2;
    private RTextArea editableTextArea;
    private final int THIS_PANEL_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.9); //0.85
    private final int THIS_PANEL_HEIGHT = (int)(GUIManager.PANEL_HEIGHT * 0.4);
    private final int THIS_BUTTON_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.1); //0.1

   
    public void updateTextAreaFields() {
        Question question = QuestionManager.getCurrentQuestion();
        questionArea.setText("What type of error is this?");
        nonEditableTextArea1.setText(question.getNonEditableCode1());
        editableTextArea.setText(question.getEditableCode());
        editableTextArea.setEditable(false);
        editableTextArea.setBackground(new Color(40, 44, 52));
        nonEditableTextArea2.setText(question.getNonEditableCode2());
        

    }






    public QuestionContainerPanel() {
        super(75, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR); 

        setOpaque(false);
        setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);


        questionArea = GUIManager.getQuestion(new Color(80, 67, 123));
      

        nonEditableTextArea1 = GUIManager.gSyntaxTextArea();
        editableTextArea = GUIManager.gSyntaxTextArea();
        nonEditableTextArea2 = GUIManager.gSyntaxTextArea();

        

        add(Box.createRigidArea(new Dimension(0,(int)(GUIManager.PANEL_HEIGHT * 0.02))));
        add(questionArea); 
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));

        add(nonEditableTextArea1);
        add(editableTextArea);
        add(nonEditableTextArea2);

        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));
    
    }


    public void nextQuestion() {
        updateTextAreaFields();
        remove(QuestionAreaManager.getDebuggingPanel());
        displayMultipleChoiceSection();
    }

    
    private void displayMultipleChoiceSection() {
        add(QuestionAreaManager.getMultipleChoicePanel());
        revalidate();
        repaint();
    }


    public void resetEditableTextArea() {
        editableTextArea.setText(QuestionManager.getCurrentQuestion().getEditableCode());
    }


    public String getEditableTextAreaText() {
        return editableTextArea.getText();
    }

 
    public void displayDebugginSection() {
        remove(QuestionAreaManager.getMultipleChoicePanel());
        String str = "Fix the code below so that the output is: \n" + QuestionManager.getCurrentQuestion().getExpectedOutput();

        questionArea.setText(str);
        editableTextArea.setBackground(new Color(69, 76, 90));
        editableTextArea.setEditable(true);

        add(QuestionAreaManager.getDebuggingPanel());

        ApplicationManager.refresh();
    }
}