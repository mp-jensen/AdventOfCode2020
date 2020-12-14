import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12_2 {
    public static void main (String[] argv) {
        int facing = 1;
        int shipEastWest = 0;
        int waypointEastWest = 10;
        int shipNorthSouth = 0;
        int waypointNorthSouth = 1;
        try {
            File inputFile = new File("Day12_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                String instruction = inputReader.nextLine().trim();
                Integer units = Integer.parseInt(instruction.substring(1));
                switch(instruction.charAt(0)) {
                    case 'F':
                        shipEastWest += waypointEastWest*units;
                        shipNorthSouth += waypointNorthSouth*units;
                        break;
                    case 'L':
                        if(units == 90) {
                            int hold = waypointEastWest;
                            waypointEastWest = -1 * waypointNorthSouth;
                            waypointNorthSouth = hold;
                        } else if (units == 180) {
                            waypointEastWest = -1 * waypointEastWest;
                            waypointNorthSouth = -1 * waypointNorthSouth;
                        } else if (units == 270) {
                            int hold = waypointEastWest;
                            waypointEastWest = waypointNorthSouth;
                            waypointNorthSouth = -1 * hold;
                        }
                        break;
                    case 'R':
                        if(units == 90) {
                            int hold = waypointEastWest;
                            waypointEastWest = waypointNorthSouth;
                            waypointNorthSouth = -1 * hold;
                        } else if (units == 180) {
                            waypointEastWest = -1 * waypointEastWest;
                            waypointNorthSouth = -1 * waypointNorthSouth;
                        } else if (units == 270) {
                            int hold = waypointEastWest;
                            waypointEastWest = -1 * waypointNorthSouth;
                            waypointNorthSouth = hold;
                        }
                        break;
                    case 'N':
                        waypointNorthSouth += units;
                        break;
                    case 'S':
                        waypointNorthSouth -= units;
                        break;
                    case 'E':
                        waypointEastWest += units;
                        break;
                    case 'W':
                        waypointEastWest -= units;
                        break;
                }
//                System.out.printf("Instruction: %s Units: %d East/West: %d North/South: %d\n",
//                        instruction, units, eastWest, northSouth);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e);
        }
        System.out.printf("East/West: %d North/South: %d Manhattan: %d", shipEastWest, shipNorthSouth, Math.abs(shipEastWest) + Math.abs(shipNorthSouth));
    }
}
