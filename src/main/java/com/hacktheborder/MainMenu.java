package com.hacktheborder;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainMenu extends JPanel {
    private JLabel title;
    private JTextField teamNameTextField, numMembersTextField, idNumTextField;
    private JButton submitFieldsButton, newTeamSubmitFieldsButton;




    public MainMenu() {
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());



        teamNameTextField = LayoutCreator.getForms("team name");
        numMembersTextField = LayoutCreator.getForms("lastname");
        idNumTextField = LayoutCreator.getForms("id num");

        title = new JLabel("Secure Coding") {{
            setFont(new Font("Ariel", Font.PLAIN, 50));
        }};



        submitFieldsButton = new JButton("Submit") {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(600, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                validateLogin();
            });
        }};

        newTeamSubmitFieldsButton = new JButton("Submit New") {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(600, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                createTeam();
                validateLogin();
            });
        }};


        //add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(teamNameTextField);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(submitFieldsButton);
    }


    private void createTeam() {
        try {
            System.out.println("Inside MainMenu start of creating new team");
            String teamName = teamNameTextField.getText();

            System.out.println("Inside MainMenu after getting teamName");
            int numMembers = Integer.valueOf(numMembersTextField.getText());
            if(numMembers < 1 || numMembers > 4)
                throw new Exception("Please enter a number between 2 and 4!");

            System.out.println("Inside MainMenu after getting numMembers");


            int idNumOfPersonPlaying = Integer.valueOf(idNumTextField.getText());
            if(String.valueOf(idNumOfPersonPlaying).length() != 8)
                throw new Exception("Please enter your 8 digit EPCC ID Number!");


            System.out.println("Inside MainMenu before creating new team");
            ApplicationManager.createNewTeam(teamName, idNumOfPersonPlaying, idNumOfPersonPlaying);
            System.out.println("Inside MainMenu after creating new team");


        } catch (NumberFormatException e) {

        } catch (InvalidNumberOfMembersException e) {

        } catch (Exception e) {

        }
    }

    public void resetTextFields() {
        teamNameTextField.setText("");
        numMembersTextField.setText("");
        idNumTextField.setText("");
    }



    private void validateLogin() {
        if(userExist()) {
            ApplicationManager.updateTeam(teamNameTextField.getText());
            resetTextFields();
            ApplicationManager.displayQuestionArea();
        } else {
            createNewUserPage();
        }
    }


    private void createNewUserPage() {
        removeAll();
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(teamNameTextField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(numMembersTextField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(idNumTextField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(newTeamSubmitFieldsButton);
        revalidate();
        repaint();
    }


    private boolean userExist() {
        return ApplicationManager.doesTeamExist(teamNameTextField.getText().toUpperCase());
    }
}
