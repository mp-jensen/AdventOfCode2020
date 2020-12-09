import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4_1 {
    public static void main(String[] args) {
        int validPassports = 0;
        try {
            File inputData = new File("Day4_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while (inputReader.hasNextLine()) {
                String allData = "";
                String nextLine = inputReader.nextLine();
                while(nextLine.trim().length() > 0) {
                    allData += " " + nextLine;
                    if(inputReader.hasNextLine()) {
                        nextLine = inputReader.nextLine();
                    } else {
                        nextLine = "\n";
                    }
                }
                System.out.println(allData);
                validPassports += parseLine(allData) ? 1 : 0;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }

        System.out.println(validPassports);
    }

    static boolean parseLine(String inputLine){
        String[] inputFields = inputLine.trim().split("\\s+");
        if(inputFields.length < 7) {
//            System.out.println("fewer than 7 input fields");
            return false;
        } else if (inputFields.length > 7) {
//            System.out.println("8 input fields");
            return true;
        }

        for(String field : inputFields) {
            String[] keyValue = field.trim().split(":");
            System.out.println(keyValue[0]);
            if(keyValue[0].equals("cid")) {
//                System.out.println("found field cid in 7 input fields, most not have all data");
                return false;
            }
        }
//        System.out.println("found no cid field in 7 input fields, must be good");
        return true;
    }
}
