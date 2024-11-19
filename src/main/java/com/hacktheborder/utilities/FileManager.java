package com.hacktheborder.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Scanner;

import com.hacktheborder.managers.ApplicationManager;
import com.hacktheborder.managers.ApplicationManager.QuestionAreaManager;

public class FileManager {
    private File javaFile;
    private String tempDir;

    public FileManager() {
        tempDir = System.getProperty("java.io.tmpdir");
        String javaFilePath = tempDir + "/Test.java";
        String className = "Test";
        javaFile = new File(tempDir, "Test.java");
   
    }

    public String getFile() {
        return this.tempDir;
    }


    public void deleteFile() {
        if (javaFile.exists() && javaFile.delete()) {
            System.out.println("Deleted file: " + javaFile);
        } else {
            System.out.println("Failed to delete file: " + javaFile);
        }
    }

    public void writeToFile() {



        String output = QuestionAreaManager.getWriteOutputText();

        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(javaFile))) {
            fileWriter.append(output);
        } catch (Exception e) {

        }

    }


    
    public static String readFile() {
        StringBuilder sb = new StringBuilder();
      
        try(InputStream inputStream = FileManager.class.getClassLoader().getResourceAsStream("info.txt")) {
            Scanner fileWriter = new Scanner(inputStream);

            while(fileWriter.hasNext()) {
                sb.append(fileWriter.nextLine()).append("\n");
            }
           // System.out.println(sb.toString());
           fileWriter.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    
}