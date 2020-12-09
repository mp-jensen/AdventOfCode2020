import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4_2 {
    static ArrayList<String> ECL_OPTIONS = new ArrayList<String>(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    public static void main(String[] args) {
        int validPassports = 0;
        try {
            File inputData = new File("Day4_input.txt");
            Scanner inputReader = new Scanner(inputData);
            while (inputReader.hasNextLine()) {
                String allData = "";
                String nextLine = inputReader.nextLine();
                while(nextLine.trim().length() > 0) {
                    allData += " " + nextLine;
                    if(inputReader.hasNextLine()) {
                        nextLine = inputReader.nextLine();
                    } else {
                        nextLine = "\n";
                    }
                }
//                System.out.println(allData);
                validPassports += parseLine(allData) ? 1 : 0;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured, file not found");
            System.out.println(e);
        }

        System.out.println(validPassports);
    }

    static boolean parseLine(String inputLine){
        String[] inputFields = inputLine.trim().split("\\s+");
        int length = inputFields.length;
        if(length < 7) {
//            System.out.println("fewer than 7 input fields");
            return false;
        }

        for(String field : inputFields) {
            String[] keyValue = field.trim().split(":");
//            System.out.println(keyValue[0]);
            switch (keyValue[0]) {
                case "byr":
                    int byrValue = 0;
                    try {
                        byrValue = Integer.parseInt(keyValue[1].trim());
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(byrValue < 1920 || byrValue > 2002) {
                        return false;
                    }
                    break;
                case "iyr":
                    int iyrValue = 0;
                    try {
                        iyrValue = Integer.parseInt(keyValue[1].trim());
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(iyrValue < 2010 || iyrValue > 2020) {
                        return false;
                    }
                    break;
                case "eyr":
                    int eyrValue = 0;
                    try {
                        eyrValue = Integer.parseInt(keyValue[1].trim());
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(eyrValue < 2020 || eyrValue > 2030) {
                        return false;
                    }
                    break;
                case "hgt":
                    int hgtValue = 0;
                    String hgtType = keyValue[1].substring(keyValue[1].length()-2);
                    try {
                        hgtValue = Integer.parseInt(keyValue[1].substring(0, keyValue[1].length() - 2));
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(hgtType.equals("cm")) {
                        if(hgtValue < 150 || hgtValue > 193) {
                            return false;
                        }
                    } else if(hgtType.equals("in")) {
                        if(hgtValue < 59 || hgtValue > 76) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                case "hcl":
                    if(keyValue[1].length() != 7) { return false; }
                    if(keyValue[1].charAt(0) != '#') { return false; }
                    for(int i = 1; i < 7; i++) {
                        char checkChar = keyValue[1].charAt(i);
                        if (checkChar < 48 || (checkChar > 57 && checkChar < 97) || checkChar > 102) {
                            return false;
                        }
                    }
                    break;
                case "ecl":
                    if(!ECL_OPTIONS.contains(keyValue[1])) {
                        return false;
                    }
                    break;
                case "pid":
                    if(keyValue[1].length() != 9) {
                        return false;
                    }
                    try {
                        Integer.parseInt(keyValue[1]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    break;
                case "cid":
                    if(length == 7) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

//        System.out.println("found no cid field in 7 input fields, must be good");
        return true;
    }
}
