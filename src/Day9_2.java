import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day9_2 {
    static Long TARGET_SUM = new Long(393911906);

    public static void main (String[] argv) {
        ArrayList<Long> preamble = new ArrayList<>();
        boolean validPair = true;
        try {
            File inputFile = new File("Day9_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                preamble.add(Long.parseLong(inputReader.nextLine().trim()));
            }
            int startIdx = 0;
            int endIdx = 0;
            Long contiguousSum = new Long(0);
            while(!contiguousSum.equals(TARGET_SUM)) {
                while (contiguousSum < TARGET_SUM) {
                    contiguousSum += preamble.get(endIdx);
                    endIdx++;
                }
                if (!contiguousSum.equals(TARGET_SUM)) {
                    System.out.printf("Start index: %d End index: %d ContiguousSum: %d\n",
                            startIdx, endIdx, contiguousSum);
                    while (contiguousSum > TARGET_SUM) {
                        contiguousSum -= preamble.get(startIdx);
                        startIdx++;
                    }
                }
            }
            List<Long> continuousList = preamble.subList(startIdx, endIdx);
            System.out.printf("Sum of smallest and largest numbers in continuous section: %d",
                    Collections.min(continuousList) + Collections.max(continuousList));
        } catch (FileNotFoundException e) {
            System.out.printf("File Not Found: %s\n", e);
        }
    }
}
