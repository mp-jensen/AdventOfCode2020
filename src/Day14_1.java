import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14_1 {
    public static void main (String[] argv) {
        HashMap<Integer, String> memory = new HashMap<>();
        try {
            File inputFile = new File("Day14_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            char[] bitMask = new char[36];

            while(inputReader.hasNextLine()) {
                String[] instruction = inputReader.nextLine().trim().split(" ");
                if(instruction[0].equals("mask")) {
                    bitMask = instruction[2].toCharArray();
                } else {
                    Integer location = Integer.parseInt(instruction[0].substring(4, instruction[0].indexOf(']')));
                    String value = Integer.toBinaryString(Integer.parseInt(instruction[2]));
                    char[] valueAsChars = new char[36];
                    for (int i = 0; i < value.length(); i++) {
                        valueAsChars[35 - i] = bitMask[35 - i] == 'X' ? value.charAt(value.length() - 1 - i) : bitMask[35 - i];
                    }
                    for (int i = 35; i >= value.length(); i--) {
                        valueAsChars[35-i] = bitMask[35-i] == 'X' ? '0' : bitMask[35-i];
                    }
                    memory.put(location, new String(valueAsChars));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e);
        }

        Long[] memorySum = new Long[] {new Long(0)};
        memory.forEach((location, value) -> {
            memorySum[0] += Long.parseLong(value,2);
        });
        System.out.printf("Sum of all numbers in memory: %d\n", memorySum[0]);
    }
}
