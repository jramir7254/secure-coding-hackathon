package com.hacktheborder.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;
import com.hacktheborder.managers.ApplicationManager.QuestionManager;
import com.hacktheborder.managers.ApplicationManager.TeamManager;
import com.hacktheborder.managers.GUIManager;
import com.hacktheborder.utilities.FileManager;
import com.hacktheborder.utilities.ThreadRunner;

public class DebuggingPanel extends JPanel {
    private JPanel buttonsJPanel;
    private JButton runButton, resetButton, nextButton;
    private JTextArea consoleArea;
    private JScrollPane jsp;
    private final int THIS_PANEL_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.9); //0.85
    private final int THIS_PANEL_HEIGHT = (int)(GUIManager.PANEL_HEIGHT * 0.4);
    private final int THIS_BUTTON_WIDTH = (int)(GUIManager.PANEL_WIDTH * 0.1); //0.1


    public DebuggingPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(THIS_PANEL_WIDTH, THIS_PANEL_HEIGHT));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBackground(new Color(77, 88, 101));


        consoleArea = new JTextArea() {{
            setBackground(Color.BLACK);
            setEditable(false);
            setForeground(Color.WHITE);
            setFont(new Font("Courier New", Font.BOLD, GUIManager.FONT_SIZE_12));
            setPreferredSize(new Dimension(THIS_PANEL_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.35)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};


        jsp = new JScrollPane(consoleArea) {{
            putClientProperty("JComponent.roundRect", true);
            setPreferredSize(new Dimension(THIS_PANEL_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.35)));
            setMinimumSize(consoleArea.getPreferredSize());
            setMaximumSize(consoleArea.getPreferredSize());
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }};


        runButton = new JButton("Run") {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setPreferredSize(new Dimension(THIS_BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                //write();
                writeAndRun();
                validateOutPut();
            });
        }};


        resetButton = new JButton("Reset") {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setPreferredSize(new Dimension(THIS_BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            addActionListener(e -> {
                QuestionAreaManager.resetEditableTextArea();
            });
        }};


        nextButton = new JButton("Next") {{
            setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setPreferredSize(new Dimension(THIS_BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            addActionListener(e -> {
                
                prepareNextQuestion();
            });
        }};


        buttonsJPanel = new JPanel() {{
            setPreferredSize(new Dimension(THIS_PANEL_WIDTH,(int)(GUIManager.PANEL_HEIGHT * 0.085)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            //setBackground(GUIManager.COMPONENT_CHILD_BACKGROUND_COLOR);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            setBackground(getBackground());
            add(runButton);
            add(resetButton);
        }};


        add(jsp, BorderLayout.CENTER);
        add(buttonsJPanel, BorderLayout.SOUTH);
    }


    public void prepareNextQuestion() {
        consoleArea.setText("");
        buttonsJPanel.remove(nextButton);
        QuestionManager.updateQuestion();
    }


    public void validateOutPut() {
        if(isExpectedOutput()) {
            TeamManager.updateTeamScore();

            buttonsJPanel.add(nextButton);
            revalidate();
            repaint();
        }
    }


    public boolean isExpectedOutput() {
        System.out.println("is expected output: " + consoleArea.getText().equals(QuestionManager.getCurrentQuestion().getExpectedOutput()));
        return consoleArea.getText().equals(QuestionManager.getCurrentQuestion().getExpectedOutput());
    }


    private void write() {
        FileManager fileWriter = new FileManager();
        fileWriter.writeToFile();
    }


    public void setOutPut(ThreadRunner runner) {
        System.out.println("runner out: " + runner.getOutput());
        consoleArea.setText(runner.getOutput());
    }

  
    public void writeAndRun() {
        try {
            FileManager fileWriter = new FileManager();
            fileWriter.writeToFile();
            ThreadRunner runner = new ThreadRunner(fileWriter.getFile());
            System.out.println("Starting Thread\n");
            runner.start();
            System.out.println("Thread Started\n");
            runner.join();
            System.out.println("Waiting on Thread\n");
            setOutPut(runner);
            fileWriter.deleteFile();

            if (!runner.isAlive()) {
                System.out.println("ThreadRunner has terminated successfully.\n");
            } else {
                System.err.println("ThreadRunner is still alive.\n");
            }
        } catch (Exception e) {

        }
    }
}
