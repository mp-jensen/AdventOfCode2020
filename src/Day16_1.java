import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day16_1 {
    public static void main(String[] argv) {
        ArrayList<String[]> rules = new ArrayList<>();
        String[] myTicket;
        ArrayList<String[]> nearbyTickets = new ArrayList<>();
        Integer errorRate = 0;
        try {
            Scanner inputReader = new Scanner(new File("Day16_input.txt"));
            int sectionCount = 0;
            while(sectionCount < 3) {
                if(sectionCount == 0) {
                    String[] ruleLine = inputReader.nextLine().trim().split(":");
                    while(ruleLine.length == 2) {
                        rules.add(ruleLine[1].trim().split(" "));
                        ruleLine = inputReader.nextLine().trim().split(":");
                    }
                    sectionCount++;
                } else if(sectionCount == 1) {
                    inputReader.nextLine();
                    myTicket = inputReader.nextLine().trim().split(".");
                    inputReader.nextLine();
                    sectionCount++;
                } else {
                    inputReader.nextLine();
                    while(inputReader.hasNextLine()) {
                        nearbyTickets.add(inputReader.nextLine().trim().split(","));
                    }
                    sectionCount++;
                }
            }
            int minRange1 = Integer.MAX_VALUE, minRange2 = Integer.MAX_VALUE;
            int maxRange1 = Integer.MIN_VALUE, maxRange2 = Integer.MIN_VALUE;
            for(String[] rule : rules) {
                String[] range1 = rule[0].trim().split("-");
                minRange1 = Math.min(Integer.parseInt(range1[0]), minRange1);
                maxRange1 = Math.max(Integer.parseInt(range1[1]), maxRange1);
                String[] range2 = rule[2].trim().split("-");
                minRange2 = Math.min(Integer.parseInt(range2[0]), minRange2);
                maxRange2 = Math.max(Integer.parseInt(range2[1]), maxRange2);
            }
            System.out.printf("Range 1: %d-%d Range 2: %d-%d\n", minRange1, maxRange1, minRange2, maxRange2);

            Integer invalidTicketCount = 0;
            for(String[] ticket : nearbyTickets) {
                boolean validTicket = true;
                for(String value : ticket) {
                    Integer numValue = Integer.parseInt(value);
                    if(!((numValue >= minRange1 && numValue <= maxRange1)
                            || (numValue >= minRange2 && numValue <= maxRange2))) {
                        errorRate += numValue;
                        validTicket = false;
                    }
                }
                if(!validTicket) {
                    invalidTicketCount++;
                }
            }
            System.out.printf("Ticket scanning error rate: %d\n", errorRate);
            System.out.printf("Total nearby tickets: %d invalid count: %d \n", nearbyTickets.size(), invalidTicketCount);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
    }
}
