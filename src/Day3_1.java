import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day3_1 {
    public static void main(String[] args) {
        char TREE = '#';
        int trees = 0;
        int index = 0;
        int line = 1;
        try {
            File inputData = new File("Day3_input.txt");
            Scanner inputReader = new Scanner(inputData);
            String firstLine = inputReader.nextLine();
            int length = firstLine.length();
            if(firstLine.charAt(index%length) == TREE) {
                trees++;
            }
            while(inputReader.hasNextLine()) {
                String inputLine = inputReader.nextLine();
                if(line%2 == 0) {
                    index += 1;
                    if(inputLine.charAt(index%length) == TREE) {
                        trees++;
                    }
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }

        System.out.println(trees);
    }
}
