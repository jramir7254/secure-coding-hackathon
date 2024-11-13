package com.hacktheborder;

import javax.swing.UIManager;

public class ClassManager {

    

    private final static  QuestionsManager  questionsManager = new QuestionsManager();
    private final static  QuestionArea      questionArea     = new QuestionArea();
    private final static  ButtonArea        buttonArea       = new ButtonArea( );
    private final static  ConsolePart       consolePart      = new ConsolePart( );
    private final static  SQLConnector      sqlConnector     = new SQLConnector();
    private final static  MainMenu          mainMenu         = new MainMenu();
    private final static  TimerGUI          timerGUI         = new TimerGUI();
    private final static  ThreadRunner      ThreadRunner     = new ThreadRunner();

    private static User user;
    private static Question currentQuestion;
    private static Main main1;

    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            main1 = new Main();
            updateAll();

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    

    public static void updateAll() {
        mainMenu.updateAll();
        buttonArea.updateAll();
        questionArea.updateAll();
        consolePart.updateAll();
        timerGUI.updateAll();

    }

    public static void update() {
        timerGUI.update();
    }

    public static void updateUser(int num) {
        user = sqlConnector.getUser(num);
    }

    public static void updateQuestion() {
        if(!questionsManager.isEmpty()) {
            currentQuestion = questionsManager.getNextQuestion();
            questionArea.nextQuestion();
        }
    }

   

    

    public static QuestionsManager getQuestionsManager()    { return questionsManager; }

    public static ConsolePart getConsolePart()              { return consolePart; }

    public static ButtonArea getButtonArea()                { return buttonArea; }

    public static SQLConnector getSqlConnector()            { return sqlConnector; }

    public static MainMenu getMainMenu()                    { return mainMenu; }

    public static QuestionArea getQuestionArea()            { return questionArea; }

    public static TimerGUI getTimerGUI()                    { return timerGUI; }

    public static User getUser()                            { return user; }

    public static Question getCurrentQuestion()             { return currentQuestion; }

    public static ThreadRunner getThreadRunner()            { return ThreadRunner; }

    public static Main getMain()                            { return main1; }
}
