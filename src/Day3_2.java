import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3_2 {
    public static void main(String[] args) {
        char TREE = '#';
        int row = 2;
        int[] trees = new int[]{0,0,0,0,0};
        int[] index = new int[]{0,0,0,0,0};
        int[] rows = new int[]{1,1,1,1,2};
        int[] cols = new int[]{1,3,5,7,1};
        try {
            File inputData = new File("Day3_input.txt");
            Scanner inputReader = new Scanner(inputData);
            String firstLine = inputReader.nextLine();
            int length = firstLine.length();
            for(int i=0; i<trees.length; i++) {
                if(row%rows[i] == 0) {
                    if (firstLine.charAt(index[i] % length) == TREE) {
                        trees[i]++;
                    }
                }
            }
            while(inputReader.hasNextLine()) {
                for(int i=0; i<trees.length; i++) {
                    if(row%rows[i] == 0) {
                        index[i] += cols[i];
                    }
                }
                row++;
                String inputLine = inputReader.nextLine();
                for(int i=0; i<trees.length; i++) {
                    if(row%rows[i] == 0) {
                        if (inputLine.charAt(index[i] % length) == TREE) {
                            trees[i]++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }
        for(int count : trees) {
            System.out.println(count);
        }
        long product = trees[0];
        for(int i=1; i<trees.length; i++) {
            product *= trees[i];
        }
        System.out.printf("Product = %d", product);
    }
}
