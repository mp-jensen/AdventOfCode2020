import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Day2_1 {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> policies = new HashMap<>();
        ArrayList<String> currentList;
        final Integer[] validCount = {0};
        try {
            File inputData = new File("Day2_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(inputReader.hasNextLine()) {
                String[] inputLine = inputReader.nextLine().split(":", 2);
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
            int minChar = Integer.parseInt(policyLine[0]);
            int maxChar = Integer.parseInt(policyLine[1]);
            char requiredChar = policyLine[2].charAt(0);
            int charCount = 0;
            for(String password : passwords) {
                CharacterIterator it = new StringCharacterIterator(password);
                while(it.current() != CharacterIterator.DONE) {
                    if(it.current() == requiredChar) {
                        charCount++;
                    }
                    it.next();
                }
                if(charCount >= minChar && charCount <= maxChar) {
                    validCount[0]++;
                }
                charCount = 0;
            }
        });
        System.out.println(validCount[0]);
    }
}
