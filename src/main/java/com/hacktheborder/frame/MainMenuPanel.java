package com.hacktheborder.frame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.hacktheborder.custom.classes.RoundedJPanel;
import com.hacktheborder.custom.classes.RoundedJXTextField;
import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;
import com.hacktheborder.managers.ApplicationManager.SQLManager;
import com.hacktheborder.managers.GUIManager;



public class MainMenuPanel extends RoundedJPanel {
    private JLabel coverImageLabel;
    private RoundedJXTextField teamNameTextField, numMembersTextField, idNumTextField;

    private JTextField invalidNumMembersTextField, invalidIdNumTextField;
    private JButton submitFieldsButton, newTeamSubmitFieldsButton;
    


    public MainMenuPanel() {
        super(175, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR, GUIManager.MAIN_FRAME_BACKGROUND_COLOR);


        setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH, GUIManager.PANEL_HEIGHT));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());


        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        

        ImageIcon icon = new ImageIcon(getClass().getResource("/Cover.png"));
        Image image = icon.getImage(); // Get the original image
        Image resizedImage = image.getScaledInstance(GUIManager.PANEL_WIDTH / 2,  300, Image.SCALE_SMOOTH); // Resize the image CHANGE SIZING
        ImageIcon resizedIcon = new ImageIcon(resizedImage);


        teamNameTextField = GUIManager.getRoundedJXTextField
        (
            10, 
            GUIManager.BUTTON_BACKGROUND_COLOR, 
            GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR
        );
        
        teamNameTextField.setPrompt("Team Name");
        numMembersTextField = GUIManager.getRoundedJXTextField(10, GUIManager.BUTTON_BACKGROUND_COLOR, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        numMembersTextField.setPrompt("Number of Members");
        idNumTextField = GUIManager.getRoundedJXTextField(10, GUIManager.BUTTON_BACKGROUND_COLOR, GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
        idNumTextField.setPrompt("ID# of Person Playing");


        invalidNumMembersTextField = getT();
        invalidIdNumTextField = getT();

        coverImageLabel = new JLabel(resizedIcon) {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
        }};





        submitFieldsButton = new JButton("Submit") {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                if(teamExist()) {
                    String teamName = teamNameTextField.getText();
                    resetTextFields(); 
                    QuestionAreaManager.setupQuestionAreaAndTeam(teamName.toUpperCase());
                } else {
                    createNewUserPage(); 
                }
            });
        }};


        
        newTeamSubmitFieldsButton = new JButton("Submit New") {{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(GUIManager.BUTTON_WIDTH, (int)(GUIManager.PANEL_HEIGHT * 0.05)));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            addActionListener(e -> {
                clearErrMessages(); 
                createTeam();
                validateLogin();
            });
        }};

        
        add(coverImageLabel);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.1))));
        add(teamNameTextField);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.05))));
        add(submitFieldsButton);

  
    }


    public JTextField getT() {
        return new JTextField() {{
            setPreferredSize(new Dimension(GUIManager.PANEL_WIDTH / 2, 25));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            setFocusable(false);
            setBackground(GUIManager.CENTER_COMPONENT_BACKGROUND_COLOR);
            setBorder(null);
            setForeground(Color.RED);
            setFont(new Font("Calibri", Font.PLAIN, GUIManager.FONT_SIZE_12));
        }};
    }



    private void createTeam() {
        String teamName = teamNameTextField.getText();
        int numMembers = 0;
        int idNumOfPersonPlaying = 0;
    
        
        try {
            numMembers = Integer.valueOf(numMembersTextField.getText());
            if(numMembers < 1 || numMembers > 4)
                throw new Exception();

        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> invalidNumMembersTextField.setText("Please enter a valid number"));
            return;

        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> invalidNumMembersTextField.setText("Please enter a valid number between 1 and 4"));
            return;
        }


        try {
            idNumOfPersonPlaying = Integer.valueOf(idNumTextField.getText());
            if(String.valueOf(idNumOfPersonPlaying).length() != 8)
                throw new Exception();

        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> invalidIdNumTextField.setText("Please enter a valid number"));
            return;

        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> invalidIdNumTextField.setText("Please enter a valid ID Number"));
            return;
        }

        SQLManager.createNewTeam(teamName, numMembers, idNumOfPersonPlaying);

    }



    public void resetTextFields() {
        teamNameTextField.setText("");
        numMembersTextField.setText("");
        idNumTextField.setText("");
    }





    private void validateLogin() {
        if(teamExist()) {
            String name = teamNameTextField.getText();
            resetTextFields();
            QuestionAreaManager.setupQuestionAreaAndTeam(name.toUpperCase());
        } 
    }


    private void clearErrMessages() {
        invalidNumMembersTextField.setText("");
        invalidIdNumTextField.setText("");
    }




    private void createNewUserPage() {
        removeAll();
        add(coverImageLabel);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.1))));
        add(teamNameTextField);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));
        add(invalidNumMembersTextField, Component.LEFT_ALIGNMENT);
        add(numMembersTextField);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));
        add(invalidIdNumTextField);
        add(idNumTextField);
        add(Box.createRigidArea(new Dimension(0, (int)(GUIManager.PANEL_HEIGHT * 0.01))));
        add(newTeamSubmitFieldsButton);
        revalidate();
        repaint();
    }


    private boolean teamExist() {
        return SQLManager.doesTeamExist(teamNameTextField.getText().toUpperCase());
    }
}
