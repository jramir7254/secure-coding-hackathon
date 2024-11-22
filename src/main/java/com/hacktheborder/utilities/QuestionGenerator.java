package com.hacktheborder.utilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionGenerator {
    static private File windowsFile, appleFile;

    public static void main(String[] args) {
        M();
    }
    public static void M() {
        appleFile = new File("src/main/java/com/hacktheborder/Text.txt");
            windowsFile = new File("secure-coding\\src\\main\\java\\com\\hacktheborder\\Text.txt");

        ArrayList <Question> questions = new ArrayList<>();
        try {
            Scanner scan = new Scanner(windowsFile);
            StringBuilder sb = new StringBuilder();
            String[] strs = new String[3];
            String questionType = "";
            String secondQ = "";
            for (int j = 0; j < 29; j++) {
                
                int i = 0;
                    while(scan.hasNextLine() && i < 3) {
                        String s = scan.nextLine();
                        if(s.startsWith("STOP")) {
                            strs[i] = sb.toString();
                            sb.setLength(0);
                            i++;
                            //break;
                        } else if(s.startsWith("QUESTION_TYPE:")) {
                            questionType = s.substring(15);
                        }  else if(s.startsWith("EXPECTED_OUTPUT:")) {
                            secondQ += s.substring(17) + "\n";
                        } else {
                            sb.append(s).append("\n");
                        }
                    }
                //System.out.println(secondQ);
 
                sb.setLength(0);
                System.out.println("Question Type: " + questionType);
                System.out.println("Expected Out: " + secondQ);
                System.out.println("str[0]: \n" + strs[0]);
                System.out.println("str[1]: \n" + strs[1]);
                System.out.println("str[2]: \n" + strs[2]);

                questions.add(new Question(strs[0], strs[2], strs[1], questionType, secondQ));
                secondQ = "";
            }
            scan.close();

            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("secure-coding\\src\\main\\resources\\Question.ser")))) {
                for(Question q : questions) {
                    oos.writeObject(q);
                }
                System.out.println("done");
            } catch (Exception e) {
                System.out.println("Problems serializing object");
                e.printStackTrace();
            }

            
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

