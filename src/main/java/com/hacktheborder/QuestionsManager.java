package com.hacktheborder;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class QuestionsManager {
    private Stack <Question> questions;
    private String fileName;


    public QuestionsManager() {
        fileName = "src/main/java/com/hacktheborder/Question.ser";
        questions = new Stack<>() {{
            addAll(getQuestions());
        }};
    }


    public boolean isEmpty() {
        return questions.isEmpty();
    }


    public Question getNextQuestion() {
        if(isEmpty())
            throw new EmptyStackException();
        return questions.pop();
    }


    private ArrayList<Question> getQuestions() {
        ArrayList<Question> temp = new ArrayList<>();

        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            while (true) {
                try {
                    temp.add((Question)oos.readObject());             
                } catch (EOFException e) {
                    break;
                }
            }

            Collections.shuffle(temp);
            return temp;

        } catch (Exception e) {
            System.out.println("Problems deserializing object");
            e.printStackTrace();
            return null;
        }
    }
}
