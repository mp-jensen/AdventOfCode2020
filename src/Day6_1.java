import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6_1 {
    public static void main(String[] args) {
        int totalQuestions = 0;
        try {
            File inputData = new File("Day6_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(inputReader.hasNextLine()) {
                int[] questionAns = new int[26];
                for(int i=0; i<26; i++) { questionAns[i] = 0; }
                String answers = inputReader.nextLine().trim();
                while(answers.length() != 0) {
                    for(int i=0; i<answers.length(); i++) {
                        questionAns[answers.charAt(i)-97] = 1;
                    }
                    if(inputReader.hasNextLine()){
                        answers = inputReader.nextLine().trim();
                    } else {
                        answers = "";
                    }
                }
                for(int i=0; i<26; i++) { totalQuestions += questionAns[i]; }
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }
        System.out.println(totalQuestions);
    }
}
