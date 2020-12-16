import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day16_2 {
    public static void main(String[] argv) {
        ArrayList<Integer[]> rules = new ArrayList<>();
        ArrayList<Integer> myTicket = new ArrayList<>();
        ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();
        ArrayList<Integer> departureRules = new ArrayList<>();
        try {
            Scanner inputReader = new Scanner(new File("Day16_input.txt"));
            int sectionCount = 0;
            while(sectionCount < 3) {
                if(sectionCount == 0) {
                    String[] ruleLine = inputReader.nextLine().trim().split(":");
                    while(ruleLine.length > 1) {
                        String[] ruleValues = ruleLine[1].trim().split(" ");
                        String[] lowRange = ruleValues[0].trim().split("-");
                        String[] highRange = ruleValues[2].trim().split("-");
                        rules.add(new Integer[]{
                                Integer.parseInt(lowRange[0]),
                                Integer.parseInt(lowRange[1]),
                                Integer.parseInt(highRange[0]),
                                Integer.parseInt(highRange[1])});
                        if(ruleLine[0].contains("departure")){
                            departureRules.add(rules.size()-1);
                        }
                        ruleLine = inputReader.nextLine().trim().split(":");
                    }
                    sectionCount++;
                } else if(sectionCount == 1) {
                    inputReader.nextLine();
                    String[] myTicketString = inputReader.nextLine().trim().split(",");
                    for(String value : myTicketString) {
                        myTicket.add(Integer.parseInt(value));
                    }
                    inputReader.nextLine();
                    sectionCount++;
                } else {
                    inputReader.nextLine();
                    while(inputReader.hasNextLine()) {
                        String[] nearbyTicketString = inputReader.nextLine().trim().split(",");
                        ArrayList<Integer> thisTicket = new ArrayList<>();
                        for(String value : nearbyTicketString) {
                            thisTicket.add(Integer.parseInt(value));
                        }
                        nearbyTickets.add(thisTicket);
                    }
                    sectionCount++;
                }
            }
            int minRange1 = Integer.MAX_VALUE, minRange2 = Integer.MAX_VALUE;
            int maxRange1 = Integer.MIN_VALUE, maxRange2 = Integer.MIN_VALUE;
            for(Integer[] rule : rules) {
                minRange1 = Math.min(rule[0], minRange1);
                maxRange1 = Math.max(rule[1], maxRange1);
                minRange2 = Math.min(rule[2], minRange2);
                maxRange2 = Math.max(rule[3], maxRange2);
            }

            ArrayList<ArrayList<Integer>> invalidTickets = new ArrayList<>();
            for(ArrayList<Integer> ticket : nearbyTickets) {
                boolean validTicket = true;
                for(Integer value : ticket) {
                    if(!((value >= minRange1 && value <= maxRange1)
                            || (value >= minRange2 && value <= maxRange2))) {
                        validTicket = false;
                    }
                }
                if(!validTicket) {
                    invalidTickets.add(ticket);
                }
            }
            for(ArrayList<Integer> ticket : invalidTickets) {
                nearbyTickets.remove(ticket);
            }
            ArrayList<Integer> possibleOptions = new ArrayList<>();
            ArrayList<Integer> notPossibleOptions = new ArrayList<>();
            ArrayList<ArrayList<Integer>> confirmedLocations = new ArrayList<>();
            Integer ticketLocation = 0;
            while(confirmedLocations.size() < rules.size()) {
                possibleOptions.clear();
                for(int i=0; i<rules.size(); i++) {
                    boolean isOption = true;
                    for(ArrayList<Integer> confirmedList : confirmedLocations) {
                        if(confirmedList.size() == 1 && confirmedList.contains(i)) {
                            isOption = false;
                        }
                    }
                    if(isOption) {
                        possibleOptions.add(i);
                    }
                }
                Integer checkingTicket = 0;
                while(possibleOptions.size() > 1 && checkingTicket < nearbyTickets.size()){
                    notPossibleOptions.clear();
                    for(Integer option : possibleOptions) {
                        Integer checkNum = nearbyTickets.get(checkingTicket).get(ticketLocation);
                        if(!((checkNum >= rules.get(option)[0] && checkNum <= rules.get(option)[1])
                                || (checkNum >= rules.get(option)[2] && checkNum <= rules.get(option)[3]))) {
                            notPossibleOptions.add(option);
                        }
                    }
                    for(Integer nonOption : notPossibleOptions) {
                        possibleOptions.remove(nonOption);
                    }
                    checkingTicket++;
                }
                confirmedLocations.add((ArrayList<Integer>) possibleOptions.clone());
                ticketLocation++;
            }
            notPossibleOptions.clear();
            while(notPossibleOptions.size() < rules.size()) {
                notPossibleOptions.clear();
                for (ArrayList<Integer> locations : confirmedLocations) {
                    if (locations.size() == 1) {
                        notPossibleOptions.add(locations.get(0));
                    }
                }
                for (ArrayList<Integer> locations : confirmedLocations) {
                    if (locations.size() > 1) {
                        for(Integer nonOption : notPossibleOptions) {
                            locations.remove(nonOption);
                        }
                    }
                }
            }

            Long departureProduct = new Long(1);
            for(int i=0; i< myTicket.size(); i++) {
                if(departureRules.contains(confirmedLocations.get(i).get(0))) {
                    departureProduct = departureProduct * myTicket.get(i);
                }
            }

            System.out.printf("Product of ticket values in departure fields : %d\n", departureProduct);

        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
    }
}
