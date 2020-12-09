import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7_1 {
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
                            .replaceAll("[0-9]", "")
                            .trim();
                }
                ruleMap.put(rule[0].replace("bags", "").trim(), contains);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
        ruleMap.put("no other", new String[0]);
        System.out.printf("total number of rules: %d\n", ruleMap.size());
//        ruleMap.forEach((k,v) -> {
//            System.out.printf("%s : ", k);
//            for(String bag : v) {
//                System.out.printf("%s, ", bag);
//            }
//            System.out.println();
//        });
        HashMap<String, Integer> discovered = new HashMap<>();
        discovered.put(TARGET_BAG, 1);
        discovered.put("no other", 0);
        Iterator<Map.Entry<String, String[]>> ruleMapIt = ruleMap.entrySet().iterator();
        LinkedList<String> checkNext = new LinkedList<>();
        while(ruleMapIt.hasNext()) {
            checkNext.clear();
            Map.Entry<String, String[]> rule = ruleMapIt.next();
            if(!discovered.containsKey(rule.getKey())){
                String[] contains = rule.getValue();
                for(String bagType : contains) {
                    if(bagType == TARGET_BAG){
                        discovered.put(rule.getKey(), 1);
                    } else {
                        checkNext.add(bagType);
                    }
                }
            }
            while(!discovered.containsKey(rule.getKey()) && !checkNext.isEmpty()){
                String checkItem = checkNext.removeFirst();
                String[] contains = ruleMap.get(checkItem);
                for(String bagType : contains) {
//                    System.out.printf("Checking bag type: %s\n", bagType);
                    if(!discovered.containsKey(bagType)) {
                            checkNext.add(bagType);
                    } else {
                        Integer hasTarget = discovered.get(bagType);
                        if(hasTarget.equals(1)) {
                            discovered.put(checkItem, 1);
                            discovered.put(rule.getKey(),1);
                        }
                    }
                }
            }
            if(!discovered.containsKey(rule.getKey())) {
                discovered.put(rule.getKey(),0);
            }
        }
        Integer totalBags = 0;
        Iterator<Map.Entry<String, Integer>> discoveredIt = discovered.entrySet().iterator();
        while(discoveredIt.hasNext()) {
            totalBags += discoveredIt.next().getValue();
        }
        System.out.printf("Number of discovered: %d\n", discovered.size());

        System.out.println(totalBags);

    }
}
