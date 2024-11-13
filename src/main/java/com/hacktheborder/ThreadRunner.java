package com.hacktheborder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.module.FindException;
import java.util.logging.Logger;

public class ThreadRunner extends Thread {
    private ProcessBuilder processBuilder;
    private Process process;
    private File file;
    private String output;

    public ThreadRunner() {
        try {
            file = new File("src/main/java/com/hacktheborder/");
            processBuilder = new ProcessBuilder("java", "-cp", ".", "Test.java");
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(file);
        } catch (Exception e) {

        }
    }


    public String getOutput() {
        System.out.println("Getting output: " + output);
        return output;
    }


    @Override
    public void run() {
        try {
            process = processBuilder.start();
   
            if (!process.waitFor(5, java.util.concurrent.TimeUnit.SECONDS)) {
                // Timeout exceeded: terminate the process and interrupt this thread
                process.destroy();
                System.out.println("Process took too long and was terminated.");
            } else {
                System.out.println("Process completed successfully.");
            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Output: " + line);
                sb.append(line).append("\n");     
            }
            output = sb.toString();
            
        } catch (Exception e) {

        } finally  {
            process.destroy();
        }
    }
}

