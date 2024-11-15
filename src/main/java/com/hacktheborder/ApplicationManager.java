package com.hacktheborder;

import java.awt.BorderLayout;
import java.sql.SQLException;

public class ApplicationManager {

    
    private final static  SQLConnector      sqlConnector     = new SQLConnector();
    private final static  QuestionArea      questionArea     = new QuestionArea();
    private final static  ButtonArea        buttonArea       = new ButtonArea( );
    private final static  ConsolePart       consolePart      = new ConsolePart( );
    private final static  QuestionsManager  questionsManager = new QuestionsManager();
    private final static  MainMenu          mainMenu         = new MainMenu();
    private final static  TimerGUI          timerGUI         = new TimerGUI();
    private final static EndGameScreen      EndGameScreen    = new EndGameScreen();

    private static Team currentTeam;
    private static Question currentQuestion;
    private static MainWindow mainWindow;



    public static void start() {
        mainWindow = new MainWindow();
    }


    public static String getWriteOutputText() {
        return currentQuestion.getNonEditableCode1() + questionArea.getEditableTextAreaText() + currentQuestion.getNonEditableCode2();
    }


    public static void createNewTeam(String teamName, int numMembers, int idNum) {
        try {
            System.out.println("Inside AppMngr before creating new team");
            sqlConnector.insertNewTeam(teamName, numMembers, idNum);
            System.out.println("Inside AppMngr after creating new team");
        } catch (SQLException s) {

        }
    }


    public static boolean doesTeamExist(String teamName) {
        try {
            return sqlConnector.teamExist(teamName);
        } catch (SQLException s) {
            return false;
        }
    }


    public static void resetAll() {
        currentQuestion = null;
        questionsManager.regenerateQuestions();
        currentTeam = null;
        timerGUI.resetAll();
        resetMainWindow();
    }

    public static void resetMainWindow() {
        mainWindow.remove(EndGameScreen);
        mainWindow.add(mainMenu);
        mainWindow.add(timerGUI);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void setSecSection() {
        questionArea.displayDebugginSection();
    }



    public static void update() {
        timerGUI.update();
    }


    public static void displayQuestionArea() {
        updateQuestion();
        questionArea.nextQuestion();
        mainWindow.remove(mainMenu);
        mainWindow.add(questionArea, BorderLayout.CENTER);
        mainWindow.add(timerGUI, BorderLayout.NORTH);
        timerGUI.updateAll();
        timerGUI.startTimer();
        mainWindow.revalidate();
        mainWindow.repaint();
   
    }


    public static void updateTeam(String teamName) {
        currentTeam = sqlConnector.getTeam(teamName.toUpperCase());
    }


    public static void updateQuestion() {
        if(!questionsManager.isEmpty()) {
            currentQuestion = questionsManager.getNextQuestion();
            questionArea.nextQuestion();
        } else {
            displayEndGameScreen();
        }
    }


    public static void displayEndGameScreen() {
        mainWindow.remove(questionArea);
        mainWindow.add(EndGameScreen);
        timerGUI.stopTimer();
        mainWindow.revalidate();
        mainWindow.repaint();
    }

   

    

    public static QuestionsManager getQuestionsManager()    { return questionsManager; }

    public static EndGameScreen getEndGameScreen()          { return EndGameScreen; }

    public static ConsolePart getConsolePart()              { return consolePart; }

    public static ButtonArea getButtonArea()                { return buttonArea; }

    public static SQLConnector getSqlConnector()            { return sqlConnector; }

    public static MainMenu getMainMenu()                    { return mainMenu; }

    public static QuestionArea getQuestionArea()            { return questionArea; }

    public static TimerGUI getTimerGUI()                    { return timerGUI; }

    public static Team getCurrentTeam()                     { return currentTeam; }

    public static Question getCurrentQuestion()             { return currentQuestion; }

    public static ThreadRunner getThreadRunner()            { return new ThreadRunner(); }

    public static MainWindow getMain()                      { return mainWindow; }
}
