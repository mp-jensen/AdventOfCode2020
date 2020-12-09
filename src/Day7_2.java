import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Day7_2 {
    static String TARGET_BAG = "shiny gold";
    public static void main (String[] argv) {
        HashMap<String, String[]> ruleMap = new HashMap<>();
        try {
            File inputFile = new File("Day7_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                String[] rule = inputReader.nextLine().split("contain", 2);
                String[] contains = rule[1].split(",");
                for(int i=0; i< contains.length; i++) {
                    contains[i] = contains[i]
                            .replace("bags", "")
                            .replace("bag", "")
                            .replace(".","")
                            .trim();
                }
                ruleMap.put(rule[0].replace("bags", "").trim(), contains);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
        ruleMap.put("no other", new String[0]);
        System.out.printf("total number of rules: %d\n", ruleMap.size());

        ArrayList<Integer> totalBags = new ArrayList<Integer>();
        totalBags.add(0);
        LinkedList<ArrayList<String>> checkNext = new LinkedList<>();
        String[] targetContains = ruleMap.get(TARGET_BAG);
        ArrayList<ArrayList<String>> bagCount = new ArrayList<>();
        for(String bag : targetContains) {
            if(!bag.trim().equals("no other")) {
                ArrayList<String> bagList = new ArrayList<>();
                bagList.add(bag.replaceAll("[0-9]", "").trim());
                bagList.add("1");
                bagList.add(bag.trim().split(" ")[0].trim());
                checkNext.add(bagList);
                bagCount.add(bagList);
            }
        }
        while(!checkNext.isEmpty()) {
            ArrayList<String> nextBag = checkNext.removeFirst();
            String[] nextBagContains = ruleMap.get(nextBag.get(0));
            for(String bag : nextBagContains) {
                if(!bag.trim().equals("no other")) {
                    ArrayList<String> bagList = new ArrayList<>();
                    bagList.add(bag.replaceAll("[0-9]", "").trim());
                    for (int i = 1; i < nextBag.size(); i++) {
                        bagList.add(nextBag.get(i));
                    }
                    bagList.add(bag.trim().split(" ")[0].trim());
                    checkNext.add(bagList);
                    bagCount.add(bagList);
                }
            }
        }

        bagCount.forEach((bagList) -> {
            Integer bags = 1;
            for(int i=1; i<bagList.size(); i++) {
                bags *= Integer.parseInt(bagList.get(i));
            }
            totalBags.set(0, totalBags.get(0) + bags);
        });
        System.out.println(totalBags.get(0));

    }
}
