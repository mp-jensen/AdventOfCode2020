import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day15_1 {
    public static void main(String[] argv) {
        HashMap<Integer, Integer> numbersSaid = new HashMap<>();
        Integer mostRecentNumber = 0;
        Integer nextNumber = 0;
        Integer numberCount = 1;
        try {
            Scanner inputReader = new Scanner(new File("Day15_input.txt"));
            String[] numbers = inputReader.nextLine().trim().split(",");
            for(int i=0; i<numbers.length-1; i++){
                mostRecentNumber = Integer.parseInt(numbers[i]);
                numbersSaid.put(mostRecentNumber, numberCount);
                numberCount++;
            }
            mostRecentNumber = Integer.parseInt(numbers[numbers.length-1]);
            while(numberCount < 2020) {
                if(numbersSaid.containsKey(mostRecentNumber)) {
                    nextNumber = numberCount - numbersSaid.get(mostRecentNumber);
                } else {
                    nextNumber = 0;
                }
                numbersSaid.put(mostRecentNumber, numberCount);
                mostRecentNumber = nextNumber;
                numberCount++;
            }
            System.out.printf("The 2020th number spoken is: %d\n", nextNumber);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
    }
}
