package com.hacktheborder;

import java.awt.BorderLayout;
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
    private JTextField firstName, lastName, idNum;
    private JButton bttn;
    private SQLConnector connector;
    private Main main;


 

    public void updateAll() {
        main = ClassManager.getMain();
        connector = ClassManager.getSqlConnector();
    }


    public MainMenu() {
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(800, 800));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());



        firstName = LayoutCreator.getForms("firstname");
        lastName = LayoutCreator.getForms("lastname");
        idNum = LayoutCreator.getForms("id num");
        title = new JLabel("Secure Coding") {{
            setFont(new Font("Ariel", Font.PLAIN, 50));
        }};



        bttn = new JButton("Submit") {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(600, 50));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                validateLogin();
            });
        }};


        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(idNum);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(bttn);
    }


    private void validateLogin() {
        if(userExist()) {
            main.updateScreen();
            int id = Integer.valueOf(idNum.getText());
            ClassManager.updateUser(id);
            ClassManager.updateQuestion();
            ClassManager.updateAll();
        } else {
            createNewUserPage();
        }
    }


    private void createNewUserPage() {
        removeAll();
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(firstName);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(lastName);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(idNum);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(bttn);
    }


    private boolean userExist() {
        int id = Integer.valueOf(idNum.getText());
        return connector.userExist(id);
    }
}
