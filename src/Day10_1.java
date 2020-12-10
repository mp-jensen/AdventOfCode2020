import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10_1 {
    public static void main(String[] argv) {
        int[] voltageDifference = new int[] {0, 0, 1};
        ArrayList<Integer> adapters = new ArrayList<>();
        try {
            File inputFile = new File("Day10_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                adapters.add(Integer.parseInt(inputReader.nextLine().trim()));
            }
            Collections.sort(adapters);
            voltageDifference[adapters.get(0)-1] += 1;
            for(int i=1; i<adapters.size(); i++) {
                voltageDifference[adapters.get(i) - adapters.get(i-1) - 1] += 1;
            }
            System.out.printf("Total number of adapters: %d\n", adapters.size());
            System.out.printf("Number of adapters with 1 voltage difference: %d\n", voltageDifference[0]);
            System.out.printf("Number of adapters with 2 voltage difference: %d\n", voltageDifference[1]);
            System.out.printf("Number of adapters with 3 voltage difference: %d\n", voltageDifference[2]);
            System.out.printf("Product of 1 and 3 voltage difference count: %d\n",
                    voltageDifference[0] * voltageDifference[2]);
        } catch(FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
    }
}
