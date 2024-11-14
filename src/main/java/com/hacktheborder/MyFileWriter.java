package com.hacktheborder;

import java.io.File;
import java.io.FileWriter;

public class MyFileWriter {
    private File windowsFile;

    public MyFileWriter() {
        windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Test.java");
    }

    public void writeToFile() {
        String output = ApplicationManager.getWriteOutputText();

        try(FileWriter fileWriter = new FileWriter(windowsFile)) {
            fileWriter.append(output);
        } catch (Exception e) {

        }

    }

    
    
}
