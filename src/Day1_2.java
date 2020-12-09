import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1_2 {
    public static void main(String[] args) {
        Integer TARGET_SUM = new Integer(2020);
        Integer nextNumber = new Integer(0);
        ArrayList<Integer> coins = new ArrayList<>();
        HashMap<Integer, Integer> twoCoins = new HashMap<>();
        try {
            File inputData = new File("Day1_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while(inputReader.hasNextLine()) {
                coins.add(new Integer(inputReader.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }
        for (int i = 0; i < coins.size(); i++) {
            for (int j = i+1; j < coins.size(); j++) {
                twoCoins.put(coins.get(i) + coins.get(j), coins.get(i));
            }
        }
        for (Integer coin : coins) {
            if(twoCoins.containsKey(TARGET_SUM-coin)) {
                System.out.println(coin
                        * twoCoins.get(TARGET_SUM-coin)
                        * (TARGET_SUM - coin - twoCoins.get(TARGET_SUM-coin)));
                return;
            }
        }
    }
}