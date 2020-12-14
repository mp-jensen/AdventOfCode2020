import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12_1 {
    public static void main (String[] argv) {
        int facing = 1;
        int eastWest = 0;
        int northSouth = 0;
        try {
            File inputFile = new File("Day12_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                String instruction = inputReader.nextLine().trim();
                Integer units = Integer.parseInt(instruction.substring(1));
                switch(instruction.charAt(0)) {
                    case 'F':
                        if(facing%2 != 0) {
                            eastWest += facing*units;
                        } else {
                            northSouth += facing*units/2;
                        }
                        break;
                    case 'L':
                        if(units == 90) {
                            facing = facing == 1 ? 2 : facing == 2 ? -1 : facing == -1 ? -2 : 1;
                        } else if (units == 180) {
                            facing = -1 * facing;
                        } else if (units == 270) {
                            facing = facing == 1 ? -2 : facing == -2 ? -1 : facing == -1 ? 2 : 1;
                        }
                        break;
                    case 'R':
                        if (units == 90) {
                            facing = facing == 1 ? -2 : facing == -2 ? -1 : facing == -1 ? 2 : 1;
                        } else if (units == 180) {
                            facing = -1 * facing;
                        } else if (units == 270) {
                            facing = facing == 1 ? 2 : facing == 2 ? -1 : facing == -1 ? -2 : 1;
                        }
                        break;
                    case 'N':
                        northSouth += units;
                        break;
                    case 'S':
                        northSouth -= units;
                        break;
                    case 'E':
                        eastWest += units;
                        break;
                    case 'W':
                        eastWest -= units;
                        break;
                }
//                System.out.printf("Instruction: %s Units: %d East/West: %d North/South: %d\n",
//                        instruction, units, eastWest, northSouth);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e);
        }
        System.out.printf("East/West: %d North/South: %d Manhattan: %d", eastWest, northSouth, Math.abs(eastWest) + Math.abs(northSouth));
    }
}
