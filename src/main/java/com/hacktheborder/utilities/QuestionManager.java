package com.hacktheborder.utilities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class QuestionManager {
    private Stack <Question> questions;


    public QuestionManager() {
        questions = new Stack<>() {{
            addAll(getQuestions());
        }};
    }


    public void regenerateQuestions() {
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
        ArrayList<Question> temp = new ArrayList<>(10);

        try(ObjectInputStream oos = new ObjectInputStream(ClassLoader.getSystemResourceAsStream("Question.ser"))) {
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
