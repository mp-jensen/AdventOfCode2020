import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day14_2 {
    public static void main (String[] argv) {
        HashMap<String, Integer> memory = new HashMap<>();
        char[] bitMask = new char[36];
        ArrayList<Integer> floatingBits = new ArrayList<>();

        try {
            File inputFile = new File("Day14_input.txt");
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String[] instruction = inputReader.nextLine().trim().split(" ");
                if(instruction[0].equals("mask")) {
                    floatingBits.clear();
                    bitMask = instruction[2].toCharArray();
                    for(int i=0; i<bitMask.length; i++) {
                        if(bitMask[i] == 'X') {
                            floatingBits.add(i);
                        }
                    }
                } else {
                    String location = Integer.toBinaryString(
                            Integer.parseInt(
                                    instruction[0].substring(4, instruction[0].indexOf(']'))));
                    Integer value = Integer.parseInt(instruction[2]);
                    char[] locationAsChars = new char[36];
                    for (int i = 0; i < location.length(); i++) {
                        locationAsChars[35 - i] = bitMask[35 - i] == 'X' || bitMask[35-i] == '0'
                                ? location.charAt(location.length() - 1 - i)
                                : '1';
                    }
                    for (int i = 35; i >= location.length(); i--) {
                        locationAsChars[35-i] = bitMask[35 - i] == 'X' || bitMask[35-i] == '0'
                                ? '0' : '1';
                    }
                    putAllLocations(memory, floatingBits, 0, value, locationAsChars);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e);
        }

        Long[] memorySum = new Long[] {new Long(0)};
        memory.forEach((location, value) -> {
            memorySum[0] += value.longValue();
        });
        System.out.printf("Sum of all numbers in memory: %d\n", memorySum[0]);
    }

    private static void putAllLocations(HashMap<String, Integer> memory,
                                           ArrayList<Integer> floatingBits,
                                           Integer bitLocation,
                                           Integer value,
                                        char[] locationAsChars) {
        if(bitLocation < floatingBits.size()) {
            char[] locationAsChars0 = Arrays.copyOf(locationAsChars, locationAsChars.length);
            locationAsChars0[floatingBits.get(bitLocation)] = '0';
            putAllLocations(memory, floatingBits, bitLocation+1, value, locationAsChars0);
            char[] locationAsChars1 = Arrays.copyOf(locationAsChars, locationAsChars.length);
            locationAsChars1[floatingBits.get(bitLocation)] = '1';
            putAllLocations(memory, floatingBits, bitLocation+1, value, locationAsChars1);
        } else {
            memory.put(String.valueOf(locationAsChars),value);
        }

    }
}
