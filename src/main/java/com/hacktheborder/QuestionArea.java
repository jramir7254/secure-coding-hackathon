package com.hacktheborder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


enum QuestionType {
    COMPILE_TIME_ERROR      ("Compile Time Error"),
    RUNTIME_ERROR           ("Runtime Error"),
    LOGIC_ERROR             ("Logic Error"),
    VULNERABILITY           ("Vulnerability");

    private final String questionType;

    private QuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionType() {
        return questionType;
    }
}

class QuestionArea extends JPanel {
    private JButton runButton, submitButton, resetButton;
    private JToggleButton[] buttons;
    private String[] options;
    private ButtonGroup group;
    private JTextField questionArea;
    private JTextArea nonEditableTextArea1, editableTextArea, nonEditableTextArea2, consoleArea;
    private File questionFile;
    private JPanel panel;
    private Question q;
    private String code, resetEditableArea;
    private JScrollPane jsp;

    public QuestionArea() {
        
        q = readObject("secure-coding\\src\\main\\java\\com\\hacktheborder\\Question.ser");

        code = q.getNonEditableCode1() + q.getEditableCode() + q.getNonEditableCode2();

        resetEditableArea = q.getEditableCode();

        setBackground(new Color(77, 88, 101));
        setPreferredSize(new Dimension(500, 785));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setBorder(BorderFactory.createBevelBorder(2));

        buttons = new JToggleButton[4];
        options = new String[] {"Compile Time Error", "Runtime Error", "Logic Error", "Vulnerability"};
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        group = new ButtonGroup();
        questionFile = new File("Question.ser");

        resetButton.addActionListener(e -> {
            editableTextArea.setText(resetEditableArea);
        });
        

        questionArea = LayoutCreator.getQuestion();


        consoleArea = new JTextArea();
        consoleArea.setBackground(Color.BLACK);
        consoleArea.setForeground(Color.WHITE);
        consoleArea.setPreferredSize(new Dimension(500, 100));
        consoleArea.setMaximumSize(consoleArea.getPreferredSize());

        jsp = new JScrollPane(consoleArea);
        jsp.setPreferredSize(new Dimension(500, 100));
        jsp.setMaximumSize(consoleArea.getPreferredSize());
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        nonEditableTextArea1 = LayoutCreator.getTextArea(q.getNonEditableCode1(), false);
        editableTextArea = LayoutCreator.getTextArea(q.getEditableCode(), false);
        nonEditableTextArea2 = LayoutCreator.getTextArea(q.getNonEditableCode2(), false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 20));
        panel.setPreferredSize(new Dimension(500, 421));
        panel.setBackground(getBackground());
   

        add(questionArea);
        add(nonEditableTextArea1);
        add(editableTextArea);
        add(nonEditableTextArea2);
        addButtons();
    
        showButtons();
        
        
        runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            write();
            consoleArea.setText(run());
            // remove(consoleArea);
            // remove(runButton);
            // add(panel);
            // revalidate();
            // repaint();
            if(checkOutput()) {
                
            }
        });

        submitButton.addActionListener(e -> {
            shuffleButtons();
            System.out.println(q.getQuestionType());
            if(group.getSelection().getActionCommand().equals(q.getQuestionType())) {
                System.out.println("facts");
                textPart();
            }
            System.out.println(panel.getHeight());
            System.out.println(getHeight());
            group.clearSelection();
            
        });

       
    }


    private boolean checkOutput() {
        if(consoleArea.getText().contains("yes")) {
            return true;
        }
        return false;
    }


    private void textPart() {
        remove(panel);
        remove(submitButton);

        editableTextArea.setBackground(new Color(30, 70, 110));
        editableTextArea.setEditable(true);
        questionArea.setText("Fix the code so that it compiles and runs.");

        revalidate();
        repaint();
        add(jsp);
        add(runButton);
        add(resetButton);
    }

    public void showButtons() {
        for(JToggleButton b : buttons) {
            panel.add(b);
        }
        panel.add(submitButton);
        panel.revalidate();
        panel.repaint();
        add(panel);
    }


    public void addButtons() {
        int i = 0;
        for (QuestionType questionType : QuestionType.values()) { 
            buttons[i] = LayoutCreator.getToggleButton(questionType.getQuestionType());
            group.add(buttons[i++]);
        }
    }


    public void shuffleButtons() {
        Random rand = new Random();
        for (int i = buttons.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            
            JToggleButton temp = buttons[i];
            buttons[i] = buttons[j]; 
            buttons[j] = temp;
        }
        showButtons();
    }



    private void write() {
        code = "package com.hacktheborder;\n" + q.getNonEditableCode1() + editableTextArea.getText() + q.getNonEditableCode2();
        try(FileWriter fw = new FileWriter(new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Test.java"))) {
            fw.append(code);
        } catch (Exception e) {

        }
    }




    public Question readObject(String fileName) {
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            return (Question)oos.readObject();
        } catch (Exception e) {
            System.out.println("Problems deserializing object");
            e.printStackTrace();
            return null;
        }
    }


    public String run() {
        String returnValue = "";

        try {
           File file = new File("secure-coding/src/main/java/com/hacktheborder/");
            //File file = new File("\\hacktheborder\\Test.java");
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);
      

            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", ".", "Test.java");
            processBuilder.directory(new File(absolutePath));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder ouput = new StringBuilder();


           


            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                            
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Output: " + line);
                        if (line.startsWith("ReturnValue: ")) {  // Parse return value
                            if(line.contains("Bob")) {
                                System.out.println("yes");
                                //break;
                            }
                        } 
                        ouput.append(line).append("\n");
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            

            outputThread.start();

            // Wait for the process to complete
            if(process.waitFor(5000, TimeUnit.SECONDS)) {
                process.destroy();
                outputThread.interrupt();
            }
            outputThread.join();

            System.out.println(returnValue);
     
            return ouput.toString();

        } catch (Exception ex) {
            System.out.println(ex);
            return ex.getMessage();
        }
    }
}