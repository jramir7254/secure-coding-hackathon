package com.hacktheborder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePart extends JPanel {
    private JPanel buttonsJPanel;
    private JButton runButton, resetButton, nextButton;
    private JTextArea consoleArea;
    private JScrollPane jsp;
    private Question question;
    private QuestionArea questionArea;
    private ThreadRunner runner;


    public void updateAll() {
        question = ClassManager.getCurrentQuestion();
        runner = ClassManager.getThreadRunner();
        questionArea = ClassManager.getQuestionArea();
    }

    public ConsolePart() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 400));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBackground(new Color(77, 88, 101));



        consoleArea = new JTextArea() {{
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            setFont(new Font("Calibri", Font.PLAIN, 15));
            setPreferredSize(new Dimension(800, 300));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
        }};


        jsp = new JScrollPane(consoleArea) {{
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
                if(isExpectedOutput()) {
                    System.out.println("Expected Output");
                    buttonsJPanel.add(nextButton);
                    revalidate();
                    repaint();
                }
            });
        }};


        resetButton = new JButton("Reset") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                questionArea.resetEditableTextArea();
            });
        }};


        nextButton = new JButton("Next") {{
            setPreferredSize(new Dimension(100, 50));
            addActionListener(e -> {
                consoleArea.setText("");
                ClassManager.updateQuestion();
                ClassManager.updateAll();
                buttonsJPanel.remove(nextButton);
                revalidate();
                repaint();
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


    public boolean isExpectedOutput() {
        //return true;
        return consoleArea.getText().equals(question.getExpectedOutput());
    }


    private void write() {
        String code = question.getNonEditableCode1() + questionArea.getEditableTextAreaText() + question.getNonEditableCode2();
        try (FileWriter fw = new FileWriter(new File("src/main/java/com/hacktheborder/Test.java"))) {
            fw.append(code);
        } catch (Exception e) {

        }
    }


    public void run() {
        try {
            
            runner.start();
            //runner.join();
            consoleArea.setText(runner.getOutput());
        } catch (Exception e) {

        }

    }
}
