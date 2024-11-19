package com.hacktheborder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hacktheborder.managers.ApplicationManager;
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


    public DebuggingPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((int)(GUIManager.PANEL_WIDTH * 0.85), 350));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBackground(new Color(77, 88, 101));


        consoleArea = new JTextArea() {{
            setBackground(Color.BLACK);
            setEditable(false);
            setForeground(Color.WHITE);
            setFont(new Font("Courier New", Font.PLAIN, 13));
            setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, 300));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};


        jsp = new JScrollPane(consoleArea) {{
            putClientProperty("JComponent.roundRect", true);
            setPreferredSize(new Dimension(800, 300));
            setMinimumSize(consoleArea.getPreferredSize());
            setMaximumSize(consoleArea.getPreferredSize());
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }};


        runButton = new JButton("Run") {{
            setPreferredSize(new Dimension(100, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                //write();
                run();
                validateOutPut();
            });
        }};


        resetButton = new JButton("Reset") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                QuestionAreaManager.resetEditableTextArea();
            });
        }};


        nextButton = new JButton("Next") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                
                prepareNextQuestion();
            });
        }};


        buttonsJPanel = new JPanel() {{
            setPreferredSize(new Dimension(600,75));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
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

  
    public void run() {
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
