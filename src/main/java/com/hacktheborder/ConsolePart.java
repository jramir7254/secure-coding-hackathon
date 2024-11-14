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

public class ConsolePart extends JPanel {
    private JPanel buttonsJPanel;
    private JButton runButton, resetButton, nextButton;
    private JTextArea consoleArea;
    private JScrollPane jsp;
    private QuestionArea questionArea;
    private ThreadRunner runner;


    public ConsolePart() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 400));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBackground(new Color(77, 88, 101));

        //windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Test.java");

        consoleArea = new JTextArea() {{
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            setFont(new Font("Calibri", Font.PLAIN, 15));
            setPreferredSize(new Dimension(800, 300));
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
                write();
                run();
                validateOutPut();
            });
        }};


        resetButton = new JButton("Reset") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                ApplicationManager.getQuestionArea().resetEditableTextArea();
            });
        }};


        nextButton = new JButton("Next") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                prepareNextQuestion();
            });
        }};


        buttonsJPanel = new JPanel() {{
            setPreferredSize(new Dimension(600,100));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setLayout(new FlowLayout(FlowLayout.CENTER));
            setBackground(Color.GREEN);
            add(runButton);
            add(resetButton);
        }};


        add(jsp, BorderLayout.CENTER);
        add(buttonsJPanel, BorderLayout.SOUTH);
    }

    
    public void prepareNextQuestion() {
        consoleArea.setText("");
        ApplicationManager.updateQuestion();     
        buttonsJPanel.remove(nextButton);
        revalidate();
        repaint();
    }


    public void validateOutPut() {
        if(isExpectedOutput()) {
            System.out.println("Expected Output");
            buttonsJPanel.add(nextButton);
            revalidate();
            repaint();
        }
    }


    public boolean isExpectedOutput() {
        return consoleArea.getText().equals(ApplicationManager.getCurrentQuestion().getExpectedOutput());
    }


    private void write() {
        MyFileWriter fileWriter = new MyFileWriter();
        fileWriter.writeToFile();
    }


    public void setOutPut() {
        consoleArea.setText(runner.getOutput());
    }

  
    public void run() {
        try {
            runner = ApplicationManager.getThreadRunner();
            System.out.println("Starting Thread\n");
            runner.start();
            System.out.println("Thread Started\n");
            runner.join();
            System.out.println("Waiting on Thread\n");
            setOutPut();

            if (!runner.isAlive()) {
                System.out.println("ThreadRunner has terminated successfully.\n");
            } else {
                System.err.println("ThreadRunner is still alive.\n");
            }
        } catch (Exception e) {

        }
    }
}
