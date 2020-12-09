import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8_1 {
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

        int instrNum = 0;
        int acc = 0;
        while(instrNum < instructions.size()) {
            String[] instruction = instructions.get(instrNum).split(" ");
            switch(instruction[0].trim()) {
                case "acc":
                    if(instruction[1].trim().charAt(0) == '-') {
                        acc -= Integer.parseInt(instruction[1].trim().substring(1));
                    } else {
                        acc += Integer.parseInt(instruction[1].trim().substring(1));
                    }
                    instructions.set(instrNum, "visited");
                    instrNum++;
                    break;
                case "jmp":
                    instructions.set(instrNum, "visited");
                    if(instruction[1].trim().charAt(0) == '-') {
                        instrNum -= Integer.parseInt(instruction[1].trim().substring(1));
                    } else {
                        instrNum += Integer.parseInt(instruction[1].trim().substring(1));
                    }
                    break;
                case "nop":
                    instructions.set(instrNum, "visited");
                    instrNum++;
                    break;
                default:
                    instrNum = instructions.size();
            }
        }

        System.out.println(acc);

    }
}
