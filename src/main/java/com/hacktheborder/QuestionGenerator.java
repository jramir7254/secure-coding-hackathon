package com.hacktheborder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class QuestionGenerator {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Text.txt"));
            StringBuilder sb = new StringBuilder();
            String[] strs = new String[3];
            for (int j = 0; j < 2; j++) {
                
                int i = 0;
                    while(scan.hasNextLine() && i < 3) {
                        String s = scan.nextLine();
                        if(s.equals("STOP")) {
                            strs[i] = sb.toString();
                            sb.setLength(0);
                            i++;
                            //break;
                        } else {
                            sb.append(s).append("\n");
                        }
                    }
                
                sb.setLength(0);
                System.out.println("j = " + j + "\ti = " + 0 + strs[0]);
                System.out.println("j = " + j + "\ti = " + 1 + strs[1]);
                System.out.println("j = " + j + "\ti = " + 2 + strs[2]);

                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Question.ser")))) {
                    oos.writeObject(new Question(strs[0], strs[2], strs[1], "Compile Time Error"));
                    System.out.println("done");
                } catch (Exception e) {
                    System.out.println("Problems serializing object");
                }
            }
            scan.close();

            
        } catch (Exception e) {

        }

    }

    public static String getCode() {
            try(Scanner scan = new Scanner(new File("Text.txt"))) {
                StringBuilder sb = new StringBuilder();
                while (scan.hasNextLine()) {
                    sb.append(scan.nextLine()).append("\n");
                }
                return sb.toString();
              
            } catch (Exception e) {
               System.out.println(e);
               return "";
            }
        }
}

