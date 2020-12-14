import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13_1 {
    public static void main (String[] argv) {
        Integer departTime;
        Integer minWait = Integer.MAX_VALUE;
        Integer minWaitBus = 0;
        ArrayList<Integer> busses = new ArrayList<>();
        try{
            File inputFile = new File("Day13_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            departTime = Integer.parseInt(inputReader.nextLine().trim());
            String[] busList = inputReader.nextLine().trim().split(",");
            for(String bus : busList) {
                if(!bus.trim().equals("x")){
                    Integer busNum = Integer.parseInt(bus.trim());
                    Integer waitTime = departTime%busNum == 0 ? 0 : busNum - departTime%busNum;
                    minWait = minWait < waitTime ? minWait : waitTime;
                    minWaitBus = minWait == waitTime ? busNum : minWaitBus;
                }
            }
            System.out.printf("Bus Number: %d Wait Time: %d Product: %d\n", minWaitBus, minWait, minWait*minWaitBus);
        } catch (FileNotFoundException e ) {
            System.out.printf("File not found: %s\n", e);
        }

    }
}
