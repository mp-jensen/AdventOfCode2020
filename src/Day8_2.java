import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8_2 {
    public static void main(String[] argv) {
        ArrayList<String> instructions = new ArrayList<>();
        try {
            File inputFile = new File("Day8_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()){
                instructions.add(inputReader.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File Not Found: %s", e);
        }

        String exitType = "invalid";
        ArrayList<Integer> changedInstr = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        while(exitType != "valid") {
            exitType = "valid";
            visited.clear();
            Integer instrNum = 0;
            Integer acc = 0;
            boolean instrChanged = false;
            while (instrNum < instructions.size()) {
                String[] instruction = instructions.get(instrNum).split(" ");
                switch (instruction[0].trim()) {
                    case "acc":
                        if (visited.contains(instrNum)) {
                            exitType = "invalid";
                            instrNum = instructions.size();
                            break;
                        }
                        if (instruction[1].trim().charAt(0) == '-') {
                            acc -= Integer.parseInt(instruction[1].trim().substring(1));
                        } else {
                            acc += Integer.parseInt(instruction[1].trim().substring(1));
                        }
                        visited.add(instrNum);
                        instrNum++;
                        break;
                    case "jmp":
                        if(visited.contains(instrNum)) {
                            exitType = "invalid";
                            instrNum = instructions.size();
                            break;
                        }
                        if (!instrChanged && !changedInstr.contains(instrNum)) {
                            changedInstr.add(instrNum);
                            instrChanged = true;
                            visited.add(instrNum);
                            instrNum++;
                            break;
                        }
                        visited.add(instrNum);
                        if (instruction[1].trim().charAt(0) == '-') {
                            instrNum -= Integer.parseInt(instruction[1].trim().substring(1));
                        } else {
                            instrNum += Integer.parseInt(instruction[1].trim().substring(1));
                        }
                        break;
                    case "nop":
                        if(visited.contains(instrNum)) {
                            exitType = "invalid";
                            instrNum = instructions.size();
                            break;
                        }
                        if (!instrChanged && !changedInstr.contains(instrNum)) {
                            changedInstr.add(instrNum);
                            instrChanged = true;
                            visited.add(instrNum);
                            if (instruction[1].trim().charAt(0) == '-') {
                                instrNum -= Integer.parseInt(instruction[1].trim().substring(1));
                            } else {
                                instrNum += Integer.parseInt(instruction[1].trim().substring(1));
                            }
                            break;
                        }
                        visited.add(instrNum);
                        instrNum++;
                        break;
                    default:
                        instrNum = instructions.size();
                }
            }
            if(exitType == "valid") { System.out.println(acc); }
        }

    }
}
