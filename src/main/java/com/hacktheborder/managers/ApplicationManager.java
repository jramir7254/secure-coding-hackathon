package com.hacktheborder.managers;

import java.awt.BorderLayout;

import javax.swing.JPanel;


import com.hacktheborder.frame.*;
import com.hacktheborder.panels.*;
import com.hacktheborder.utilities.*;


public class ApplicationManager {


    private final static  DebuggingPanel          DEBUGGING_PANEL          = new DebuggingPanel();
    private final static  EndGamePanel            END_GAME_PANEL           = new EndGamePanel();
    private final static  HeaderPanel             HEADER_PANEL             = new HeaderPanel();
    private final static  MainMenuPanel           MAIN_MENU_PANEL          = new MainMenuPanel();
    private final static  MainFrame               MAIN_FRAME               = new MainFrame();
    private final static  MultipleChoicePanel     MULTIPLE_CHOICE_PANEL    = new MultipleChoicePanel();
    private final static  SQLConnector            SQL_CONNECTOR            = new SQLConnector();
    private final static  QuestionHolder          QUESTION_HOLDER          = new QuestionHolder();
    private final static  QuestionContainerPanel  QUESTION_CONTAINER_PANEL = new QuestionContainerPanel();



    private final static JPanel CENTER_COMP_CONTAINER_JPANEL = new JPanel() {
        {
            setLayout(new BorderLayout());
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        }
    };


    private final static JPanel INFO_CONTAINER_PANEL = new JPanel() {
        {
            add(new InfoPanel());
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        }
    };



    private final static JPanel LEADERBOARD_CONTAINER_PANEL = new JPanel() {
        {
            add(new LeaderBoardPanel());
            setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        }
    };


    private static Team currentTeam;
    private static Question currentQuestion;


    public static void refresh() {

        MAIN_FRAME.revalidate();
        MAIN_FRAME.repaint();

    }



    public static void resetAll() {
        SQL_CONNECTOR.resetTeamCurrentScore(currentTeam.getTeamName());
        currentTeam = null;
        currentQuestion = null;
        QUESTION_HOLDER.regenerateQuestions();
        HEADER_PANEL.resetAll();
        CENTER_COMP_CONTAINER_JPANEL.remove(END_GAME_PANEL);
        MainFrameManager.displayMainMenu();
    }



    public static void start() {

        MAIN_FRAME.add(HEADER_PANEL, BorderLayout.NORTH);

        MAIN_FRAME.add(LEADERBOARD_CONTAINER_PANEL, BorderLayout.WEST);

        MAIN_FRAME.add(INFO_CONTAINER_PANEL, BorderLayout.EAST);

        MAIN_FRAME.add(CENTER_COMP_CONTAINER_JPANEL, BorderLayout.CENTER);

        MainFrameManager.displayMainMenu();

        MAIN_FRAME.setVisible(true);
    }




    public static class MainFrameManager {
        public static void displayMainMenu() {
            CENTER_COMP_CONTAINER_JPANEL.add(MAIN_MENU_PANEL);
            refresh();
        }


        public static void displayQuestionArea() {
            CENTER_COMP_CONTAINER_JPANEL.remove(MAIN_MENU_PANEL);
            CENTER_COMP_CONTAINER_JPANEL.add(QUESTION_CONTAINER_PANEL);
            refresh();
       
        }


        public static void displayEndGameScreen() {
            CENTER_COMP_CONTAINER_JPANEL.remove(QUESTION_CONTAINER_PANEL);
            END_GAME_PANEL.update();
            CENTER_COMP_CONTAINER_JPANEL.add(END_GAME_PANEL);
            HEADER_PANEL.stopAllTimers();
            HEADER_PANEL.resetAllTimers();
            TeamManager.updateTeamHighScore();
            refresh();
        }
    }




    public static class SQLManager {
        public static void startConnection() {
            SQL_CONNECTOR.startConnection();
        }


        public static void updateTeamDBScore() {
            startConnection();
            SQL_CONNECTOR.updateTeamCurrentScore(currentTeam.getTeamScore(), currentTeam.getTeamName().toUpperCase());
        }

        public static void updateTeamDBHighScore() {
            startConnection();
            SQL_CONNECTOR.updateTeamHighScore(currentTeam.getTeamScore(), currentTeam.getTeamName().toUpperCase());
        }


        public static boolean doesTeamExist(String teamName) {
            startConnection();
            return SQL_CONNECTOR.teamExist(teamName.toUpperCase());
        }


        public static void createNewTeam(String teamName, int numMembers, int idNum) {
            startConnection();
            SQL_CONNECTOR.insertNewTeam(teamName, numMembers, idNum);
        }
    }





    public static class TeamManager {
        public static void updateTeam(String teamName) {
            SQLManager.startConnection();
            currentTeam = SQL_CONNECTOR.getTeam(teamName.toUpperCase());
        }

        public static boolean newHighScore() {
            return currentTeam.getTeamScore() > currentTeam.getTeamHighScore();
        }

        public static void updateTeamHighScore() {
            if(newHighScore()) {
                SQLManager.updateTeamDBHighScore();
            }
        }


        public static void updateTeamScore() {
            int timeInSeconds = HEADER_PANEL.getQuestionTimeSeconds();
            HEADER_PANEL.stopQuestionTimer();
            int numWrongAttempts = MULTIPLE_CHOICE_PANEL.getNumWrongAttempts();
            int score = QuizScoreCalculator.calculateScore(timeInSeconds, numWrongAttempts);
            currentTeam.updateTeamScore(score);
            HEADER_PANEL.updateScore();
            if(TeamManager.newHighScore()) {
                HEADER_PANEL.updateAllScores();
                SQLManager.updateTeamDBHighScore();
            }
               
        }


        public static Team getCurrentTeam() {
            return currentTeam;
        }   
    }


    public static class QuestionManager {
        public static void updateQuestion() {
            if(!QUESTION_HOLDER.isEmpty()) {
                HEADER_PANEL.startQuestionTimer();
                HEADER_PANEL.resetQuestionTimer();
                currentQuestion = QUESTION_HOLDER.getNextQuestion();
                QUESTION_CONTAINER_PANEL.nextQuestion();
            } else {
                MainFrameManager.displayEndGameScreen();
            }
        }

        public static Question getCurrentQuestion() {
            return currentQuestion;
        }
    }



    public static class QuestionAreaManager {
        public static void setupQuestionAreaAndTeam(String currentTeamName) {
            TeamManager.updateTeam(currentTeamName);
            QuestionManager.updateQuestion();
            HEADER_PANEL.updateAll();
            HEADER_PANEL.startAllTimers();
            MainFrameManager.displayQuestionArea();
        }


        public static MultipleChoicePanel getMultipleChoicePanel() {
            return MULTIPLE_CHOICE_PANEL;
        }

        public static DebuggingPanel getDebuggingPanel() {
            return DEBUGGING_PANEL;
        }

        public static void resetEditableTextArea() {
            QUESTION_CONTAINER_PANEL.resetEditableTextArea();
        }


        public static void setSecSection() {
            QUESTION_CONTAINER_PANEL.displayDebugginSection();
        }


        public static String getWriteOutputText() {
            return currentQuestion.getNonEditableCode1() + QUESTION_CONTAINER_PANEL.getEditableTextAreaText() + currentQuestion.getNonEditableCode2();
        }
    }
}
