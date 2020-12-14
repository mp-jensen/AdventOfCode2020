import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11_2 {
    public static void main (String[] argv) {
        ArrayList<ArrayList<Character>> originalSeating = new ArrayList<>();
        ArrayList<ArrayList<Integer>> occupiedCount = new ArrayList<>();
        boolean stateChanged = true;
        try {
            File inputFile = new File("Day11_input.txt");
            Scanner fileReader = new Scanner(inputFile);
            while(fileReader.hasNextLine()) {
                originalSeating.add(new ArrayList<>(fileReader.nextLine().trim()
                        .chars()
                        .mapToObj((i) -> Character.valueOf((char)i))
                .collect(Collectors.toList())));
            }
            int colCount = originalSeating.get(0).size();
            int rowCount = originalSeating.size();
//            System.out.printf("rowCount: %d colCount: %d\n", rowCount, colCount);
            for(int row = 0; row<rowCount; row++) {
                ArrayList<Integer> rowArray = new ArrayList<>();
                for(int col = 0; col<colCount; col++) {
                    rowArray.add(0);
                }
                occupiedCount.add(rowArray);
            }
            int iterationCount = 0;
            while(stateChanged) {
//                System.out.printf("Iteration #%d Seating State:\n", iterationCount);
                stateChanged = false;
                for(int row = 0; row<rowCount; row++) {
                    for(int col = 0; col<colCount; col++) {
//                        System.out.printf("Iteration #%d row #%d col #%d", iterationCount, row, col);
//                        System.out.print(originalSeating.get(row).get(col));
//                        System.out.print(occupiedCount.get(row).get(col));
                        if(originalSeating.get(row).get(col) != '.') {
                            int occupiedNeighbors = 0;
                            if(row>0 && col>0) {
                                if(originalSeating.get(row-1).get(col-1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row-1).get(col-1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row-offset >= 0 && col - offset >= 0) {
                                        switch(originalSeating.get(row-offset).get(col-offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(row>0) {
                                if(originalSeating.get(row-1).get(col) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row-1).get(col) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row-offset >= 0) {
                                        switch(originalSeating.get(row-offset).get(col)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(row>0 && col<colCount-1) {
                                if(originalSeating.get(row-1).get(col+1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row-1).get(col+1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row-offset >= 0 && col+offset < colCount) {
                                        switch(originalSeating.get(row-offset).get(col+offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(col > 0) {
                                if(originalSeating.get(row).get(col-1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row).get(col-1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && col-offset >= 0) {
                                        switch(originalSeating.get(row).get(col-offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(col<colCount-1) {
                                if(originalSeating.get(row).get(col+1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row).get(col+1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && col+offset < colCount) {
                                        switch(originalSeating.get(row).get(col+offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(row<rowCount-1 && col>0) {
                                if(originalSeating.get(row+1).get(col-1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row+1).get(col-1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row+offset < rowCount && col-offset >= 0) {
                                        switch(originalSeating.get(row+offset).get(col-offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(row<rowCount-1) {
                                if(originalSeating.get(row+1).get(col) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row+1).get(col) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row+offset < rowCount ) {
                                        switch(originalSeating.get(row+offset).get(col)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            if(row<rowCount-1 && col<colCount-1) {
                                if(originalSeating.get(row+1).get(col+1) == '#') {
                                    occupiedNeighbors++;
                                } else if(originalSeating.get(row+1).get(col+1) == '.') {
                                    int offset = 2;
                                    boolean foundNeighbor = false;
                                    while(!foundNeighbor && row+offset < rowCount && col+offset < colCount) {
                                        switch(originalSeating.get(row+offset).get(col+offset)) {
                                            case '#':
                                                occupiedNeighbors++;
                                            case 'L':
                                                foundNeighbor = true;
                                                break;
                                            case '.':
                                                offset++;
                                        }
                                    }
                                }
                            }
                            occupiedCount.get(row).set(col, occupiedNeighbors);
                        }
                    }
//                    System.out.print("\n");
                }
                for(int row = 0; row<rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (originalSeating.get(row).get(col) == 'L' && occupiedCount.get(row).get(col) == 0) {
                            originalSeating.get(row).set(col, '#');
                            stateChanged = true;
                        } else if (originalSeating.get(row).get(col) == '#' && occupiedCount.get(row).get(col) >= 5) {
                            originalSeating.get(row).set(col, 'L');
                            stateChanged = true;
                        }
                    }
                }
//                System.out.printf("stateChanged = %b\n\n", stateChanged);
                iterationCount++;
            }
            int occupiedSeatCount = 0;
            for(ArrayList<Character> row : originalSeating) {
                for(Character seat : row) {
                    if(seat == '#') { occupiedSeatCount++; }
                }
            }
            System.out.printf("Occupied seats at steady state: %d\n", occupiedSeatCount);

        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n",e);
        }
    }
}
