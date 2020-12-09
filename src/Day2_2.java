import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day2_2 {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> policies = new HashMap<>();
        ArrayList<String> currentList;
        final Integer[] validCount = {0};
        try {
            File inputData = new File("Day2_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(inputReader.hasNextLine()) {
                String[] inputLine = inputReader.nextLine().split(": ", 2);
                if(policies.containsKey(inputLine[0])) {
                    currentList = policies.get(inputLine[0]);
                    currentList.add(inputLine[1]);
                } else {
                    policies.put(inputLine[0], new ArrayList<String>(Arrays.asList(inputLine[1])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }

        policies.forEach((policy, passwords) -> {
            String[] policyLine = policy.split("[\\s-]", 3);
            int idx1 = Integer.parseInt(policyLine[0])-1;
            int idx2 = Integer.parseInt(policyLine[1])-1;
            char requiredChar = policyLine[2].charAt(0);
            for(String password : passwords) {
                if((password.charAt(idx1) == requiredChar && password.charAt(idx2) != requiredChar)
                || (password.charAt(idx1) != requiredChar && password.charAt(idx2) == requiredChar)) {
                    validCount[0]++;
                    System.out.printf("%d: %c: %c %c: \"%s\"\n", validCount[0],requiredChar, password.charAt(idx1), password.charAt(idx2), password);
                }

            }
        });
        System.out.println(validCount[0]);
    }
}
