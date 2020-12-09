import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Day1_1 {
    public static void main(String[] args) {
        boolean matchFound = false;
        Integer TARGET_SUM = new Integer(2020);
        Integer nextNumber = new Integer(0);
        HashMap<Integer, Integer> numbersFound = new HashMap<>();
        try {
            File inputData = new File("Day1_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(!matchFound && inputReader.hasNextLine()) {
                nextNumber = new Integer(inputReader.nextLine());
                if(numbersFound.containsKey(TARGET_SUM-nextNumber)) {
                    System.out.println(nextNumber * (TARGET_SUM-nextNumber));
                    matchFound = true;
                } else {
                    numbersFound.put(nextNumber, nextNumber);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }
    }
}