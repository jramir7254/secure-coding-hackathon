package com.hacktheborder.managers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.nio.file.Path;
import com.hacktheborder.*;
import com.hacktheborder.managers.ApplicationManager.MainFrameManager;

import java.nio.file.Paths;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.hacktheborder.utilities.InfoPanel;
import com.hacktheborder.utilities.LeaderBoardPanel;
import com.hacktheborder.utilities.Question;
import com.hacktheborder.utilities.QuestionManager;
import com.hacktheborder.utilities.QuizScoreCalculator;
import com.hacktheborder.utilities.SQLConnector;
import com.hacktheborder.utilities.Team;
import com.hacktheborder.utilities.ThreadRunner;

public class ApplicationManager {
    private static SQLConnector sqlConnector = new SQLConnector();
    

    // static {
    //     SwingUtilities.invokeLater(() -> {
    //          final   ConfigSQLConPanel       CONFIG_SQL_CON_PANEL     = new ConfigSQLConPanel();
    //          final   DebuggingPanel          DEBUGGING_PANEL          = new DebuggingPanel();
    //          final   EndGamePanel            END_GAME_PANEL           = new EndGamePanel();
    //          final   HeaderPanel             HEADER_PANEL             = new HeaderPanel();
    //          final   MainMenuPanel           MAIN_MENU_PANEL          = new MainMenuPanel();
    //          final   MainFrame               MAIN_FRAME               = new MainFrame();
    //          final   MultipleChoicePanel     MULTIPLE_CHOICE_PANEL    = new MultipleChoicePanel();
    //          final   QuestionManager         QUESTION_MANAGER         = new QuestionManager();
    //          final   QuestionContainerPanel  QUESTION_CONTAINER_PANEL = new QuestionContainerPanel();
    //     });
    // };


    private final static  ConfigSQLConPanel       CONFIG_SQL_CON_PANEL     = new ConfigSQLConPanel();
    private final static  DebuggingPanel          DEBUGGING_PANEL          = new DebuggingPanel();
    private final static  EndGamePanel            END_GAME_PANEL           = new EndGamePanel();
    private final static  HeaderPanel             HEADER_PANEL             = new HeaderPanel();
    private final static  MainMenuPanel           MAIN_MENU_PANEL          = new MainMenuPanel();
    private final static  MainFrame               MAIN_FRAME               = new MainFrame();
    private final static  MultipleChoicePanel     MULTIPLE_CHOICE_PANEL    = new MultipleChoicePanel();
    private final static  QuestionManager         QUESTION_MANAGER         = new QuestionManager();
    private final static  QuestionContainerPanel  QUESTION_CONTAINER_PANEL = new QuestionContainerPanel();

   
    private static JPanel componentContainer = new JPanel();
    private static JPanel infoContainer = new JPanel();
    private static JPanel leaderB = new JPanel();
    private static Team currentTeam;
    private static Question currentQuestion;


    public static void refresh() {

        MAIN_FRAME.revalidate();
        MAIN_FRAME.repaint();

    }

    public static void resetAll() {
        currentTeam = null;
        currentQuestion = null;
        QUESTION_MANAGER.regenerateQuestions();
        HEADER_PANEL.resetAll();
        componentContainer.remove(END_GAME_PANEL);
        MainFrameManager.displayMainMenu();
    }



    public static void start() {
        MAIN_FRAME.add(HEADER_PANEL, BorderLayout.NORTH);
        MAIN_FRAME.add(componentContainer, BorderLayout.CENTER);

        leaderB.add(new LeaderBoardPanel());
        leaderB.setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);

        MAIN_FRAME.add(infoContainer, BorderLayout.EAST);
        infoContainer.add(new InfoPanel());
        infoContainer.setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
        MAIN_FRAME.add(infoContainer, BorderLayout.EAST);

        MAIN_FRAME.add(leaderB, BorderLayout.WEST);
        MainFrameManager.displayMainMenu();
        refresh();
        MAIN_FRAME.setVisible(true);
    }




    public static class MainFrameManager {
        public static void displayMainMenu() {
            componentContainer.add(MAIN_MENU_PANEL);
            componentContainer.setBackground(GUIManager.MAIN_FRAME_BACKGROUND_COLOR);
            refresh();
        }

        public static void displayQuestionArea() {
            componentContainer.remove(MAIN_MENU_PANEL);
            componentContainer.add(QUESTION_CONTAINER_PANEL);
            refresh();
        }

        public static void displayEndGameScreen() {
            componentContainer.remove(QUESTION_CONTAINER_PANEL);
            componentContainer.add(END_GAME_PANEL);
            HEADER_PANEL.stopTimer();
            HEADER_PANEL.resetTimer();
            refresh();
        }

        // public static void resetMainWindow() {
        //     componentContainer.remove(END_GAME_PANEL);
        //     componentContainer.add(MAIN_MENU_PANEL);
        //     refresh();
        // }
    }




    public static class SQLManager {
        public static void retrySQLConnection(String ipAddr) {
            sqlConnector = new SQLConnector();
        }

        public static boolean doesTeamExist(String teamName) {
            return sqlConnector.teamExist(teamName.toUpperCase());
        }

        public static void createNewTeam(String teamName, int numMembers, int idNum) {
            sqlConnector.insertNewTeam(teamName, numMembers, idNum);
        }
    }





    public static class TeamManager {
        public static void updateTeam(String teamName) {
            currentTeam = sqlConnector.getTeam(teamName.toUpperCase());
        }

        public static void updateTeamScore() {
            int timeInSeconds = HEADER_PANEL.getTotalTimeSeconds();
            int numWrongAttempts = MULTIPLE_CHOICE_PANEL.getNumWrongAttempts();
            int score = QuizScoreCalculator.calculateScore(timeInSeconds, numWrongAttempts);
            currentTeam.setUpdateTeamScore(score);
            HEADER_PANEL.updateAll();
            //refresh();
        }

        public static Team getCurrentTeam() {
            return currentTeam;
        }   
    }



    public static class QuestionAreaManager {
        public static void setupQuestionAreaAndTeam(String currentTeamName) {
            TeamManager.updateTeam(currentTeamName);
        
            updateQuestion();
            HEADER_PANEL.updateAll();
            HEADER_PANEL.startTimer();
            MainFrameManager.displayQuestionArea();
     
        }

        public static void setSecSection() {
            QUESTION_CONTAINER_PANEL.displayDebugginSection();
        }

        public static String getWriteOutputText() {
            return currentQuestion.getNonEditableCode1() + QUESTION_CONTAINER_PANEL.getEditableTextAreaText() + currentQuestion.getNonEditableCode2();
        }

        public static void updateQuestion() {
            if(!QUESTION_MANAGER.isEmpty()) {
                currentQuestion = QUESTION_MANAGER.getNextQuestion();
                QUESTION_CONTAINER_PANEL.nextQuestion();
            } else {
                MainFrameManager.displayEndGameScreen();
            }
        }
    }








   

    

    public static QuestionManager getQuestionsManager()    { return QUESTION_MANAGER; }

    public static EndGamePanel getEndGameScreen()          { return END_GAME_PANEL; }

    public static DebuggingPanel getConsolePart()              { return DEBUGGING_PANEL; }

    public static MultipleChoicePanel getButtonArea()                { return MULTIPLE_CHOICE_PANEL; }

    public static SQLConnector getSqlConnector()            { return sqlConnector; }

    public static MainMenuPanel getMainMenu()                    { return MAIN_MENU_PANEL; }

    public static QuestionContainerPanel getQuestionArea()            { return QUESTION_CONTAINER_PANEL; }

    public static HeaderPanel getTimerGUI()                    { return HEADER_PANEL; }

    public static Team getCurrentTeam()                     { return currentTeam; }

    public static Question getCurrentQuestion()             { return currentQuestion; }

    public static ThreadRunner getThreadRunner()            { return new ThreadRunner(); }

    public static MainFrame getMain()                      { return MAIN_FRAME; }
}
