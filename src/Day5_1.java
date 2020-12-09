import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5_1 {
    public static void main(String[] args) {
        int maxSeatId = 0;
        try {
            File inputData = new File("Day5_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(inputReader.hasNextLine()) {
                String seatDesc = inputReader.nextLine().trim();
                int seatId = getSeatID(seatDesc.substring(0, 7), seatDesc.substring(7));
                maxSeatId = seatId > maxSeatId ? seatId : maxSeatId;
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }
        System.out.println(maxSeatId);
    }

    public static int getSeatID(String rowDesc, String colDesc) {
        int minRow = 0;
        int maxRow = 127;
        int rowIdx = 0;
        int minCol = 0;
        int maxCol = 7;
        int colIdx = 0;
        while (minRow != maxRow) {
            if(rowDesc.charAt(rowIdx) == 'F') {
                maxRow = (minRow + maxRow)/2;
            } else {
                minRow = (minRow + maxRow + 1)/2;
            }
            rowIdx++;
        }
        while (minCol != maxCol) {
            if(colDesc.charAt(colIdx) == 'L') {
                maxCol = (minCol + maxCol)/2;
            } else {
                minCol = (minCol + maxCol + 1)/2;
            }
            colIdx++;
        }
//        System.out.printf("rowDesc: %s colDesc: %s\n", rowDesc, colDesc);
//        System.out.printf("minRow: %d minCol: %d seatId: %d\n",minRow, minCol, minRow*8+minCol);

        return minRow * 8 + minCol;

    }
}
