package com.hacktheborder.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadRunner extends Thread {
    private ProcessBuilder processBuilder;
    private Process process;
    private File windowsFile, appleFile;
    private String output;

    public ThreadRunner() {
        try {
            appleFile = new File("src/main/java/com/hacktheborder/");
            windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\");
            processBuilder = new ProcessBuilder("java", "-cp", ".", "Test.java");
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(windowsFile);
        } catch (Exception e) {

        }
    }


    public String getOutput() {

        return output;
    }


    @Override
    public void run() {
        try {
            process = processBuilder.start();
   
            if (!process.waitFor(3, java.util.concurrent.TimeUnit.SECONDS)) {
                // Timeout exceeded: terminate the process and interrupt this thread
                process.destroy();
                this.interrupt();
                System.out.println("Process took too long and was terminated.\n");
            } else {

            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println("Output: " + line);
                sb.append(line).append("\n");     
            }

            
            output = sb.toString();


            
            
        } catch (Exception e) {

        } finally {
            // Ensure the process is terminated and resources are freed
            if (process != null && process.isAlive()) {
                process.destroy();
            }

            // Close any lingering streams to prevent resource leaks
            try {
                
                process.getInputStream().close();
                process.getErrorStream().close();
                process.getOutputStream().close();
            } catch (IOException e) {
                System.err.println("Failed to close streams: " + e.getMessage());
            }
  
        }
    }
}