package com.hacktheborder.utilities;

import java.io.File;
import java.io.FileWriter;

import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;

public class MyFileWriter {
    private File windowsFile, appleFile;

    public MyFileWriter() {
        appleFile = new File("src/main/java/com/hacktheborder/");
        windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Test.java");
    }

    public void writeToFile() {
        String output = QuestionAreaManager.getWriteOutputText();

        try(FileWriter fileWriter = new FileWriter(windowsFile)) {
            fileWriter.append(output);
        } catch (Exception e) {

        }

    }

    
    
}
