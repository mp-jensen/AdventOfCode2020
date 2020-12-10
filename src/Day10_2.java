import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10_2 {
    public static void main(String[] argv) {
        int[] voltageDifference = new int[] {0, 0, 1};
        ArrayList<Integer> adapters = new ArrayList<>();
        ArrayList<Long> options = new ArrayList<>();
        try {
            File inputFile = new File("Day10_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                adapters.add(Integer.parseInt(inputReader.nextLine().trim()));
            }
            Collections.sort(adapters);
            Collections.reverse(adapters);
            options.add((long) 1);
            for(int i=1; i<adapters.size(); i++) {
                options.add((long) 0);
//                System.out.printf("Options array is now: %d", options.get(0));
//                for(int j=1; j<options.size(); j++) {
//                    System.out.printf(", %d", options.get(j));
//                }
//                System.out.print("\n");
                int offset = 1;
                while((i-offset >= 0) && adapters.get(i) + 3 >= (adapters.get(i-offset))) {
                    options.set(i, options.get(i) + options.get(i-offset));
//                    System.out.printf("Evaluating %d vs %d option count is now %d + %d\n",adapters.get(i), adapters.get(i-offset), options.get(i), options.get(i-offset));
                    offset++;
                }
            }
            Long finalCount = options.get(options.size()-1);
            int offset = 2;
            while(adapters.get(adapters.size()-offset) <= 3) {
                finalCount += options.get(options.size()-offset);
                offset++;
            }
            System.out.printf("Count of adapter arrangements: %d", finalCount);

        } catch(FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
    }
}
