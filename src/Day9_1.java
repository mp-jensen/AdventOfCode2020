import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9_1 {
    public static void main (String[] argv) {
        ArrayList<Integer> preamble = new ArrayList<>();
        boolean validPair = true;
        try {
            File inputFile = new File("Day9_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            int idx = 0;
            for(idx = 0; idx<25; idx++) {
                if(inputReader.hasNextLine()) {
                    preamble.add(Integer.parseInt(inputReader.nextLine().trim()));
                }
            }
            while(validPair && inputReader.hasNextLine()) {
                Integer nextNum = Integer.parseInt(inputReader.nextLine().trim());
                validPair = false;
                int checkIdx = 0;
                while(!validPair && checkIdx<25) {
                    Integer num1 = preamble.get(checkIdx);
                    if(num1 < nextNum && num1*2 != nextNum) {
                        if(preamble.contains(nextNum-num1)) {
                            validPair = true;
                        }
                    }
                    checkIdx++;
                }
                if(!validPair) {
                    System.out.printf("Number with no valid sums: %d is at index: %d\n", nextNum, idx);
                    break;
                } else {
                    preamble.set(idx%25, nextNum);
                    idx++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File Not Found: %s\n", e);
        }
    }
}
