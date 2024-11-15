package com.hacktheborder;

import java.io.File;
import java.io.FileWriter;

public class MyFileWriter {
    private File windowsFile, appleFile;

    public MyFileWriter() {
        appleFile = new File("src/main/java/com/hacktheborder/");
        windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Test.java");
    }

    public void writeToFile() {
        String output = ApplicationManager.getWriteOutputText();

        try(FileWriter fileWriter = new FileWriter(appleFile)) {
            fileWriter.append(output);
        } catch (Exception e) {

        }

    }

    
    
}
