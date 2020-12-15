import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Day13_2 {
    public static BigInteger[] getBezout(BigInteger a, BigInteger b) {
        BigInteger x = new BigInteger("0"), y = new BigInteger("1"), lastx = new BigInteger("1"), lasty = new BigInteger("0"), temp;
        while(!b.equals(BigInteger.ZERO)) {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }
        System.out.printf("\nRoots x: %d y: %d\n", lastx, lasty);
        return new BigInteger[] {lastx, lasty};
    }

    public static void main (String[] argv) {
        BigInteger subsequentTime = new BigInteger("0");
        ArrayList<ArrayList<BigInteger>> busOffsets = new ArrayList<>();
        try{
            File inputFile = new File("Day13_input.txt");
            Scanner inputReader = new Scanner(inputFile);
            inputReader.nextLine();
            String[] busList = inputReader.nextLine().trim().split(",");
            for(int i=0; i<busList.length; i++) {
                if(!busList[i].trim().equals("x")){
                    BigInteger busNum = new BigInteger(busList[i].trim());
                    busOffsets.add(new ArrayList<BigInteger>(Arrays.asList(busNum, busNum.subtract(new BigInteger(Integer.toString(i)).mod(busNum)))));
                }
            }
        } catch (FileNotFoundException e ) {
            System.out.printf("File not found: %s\n", e);
        }

        BigInteger coPrimeProduct = new BigInteger("1");

        for(ArrayList<BigInteger> busInfo : busOffsets) {
            System.out.print("\nBus number and offset: ");
            coPrimeProduct = coPrimeProduct.multiply(busInfo.get(0));
            for(BigInteger i : busInfo) {
                System.out.printf("%d ", i);
            }
        }

        BigInteger solution = new BigInteger("0");
        for(ArrayList<BigInteger> busInfo : busOffsets) {
            solution = solution.add(busInfo.get(1)
                    .multiply(getBezout(coPrimeProduct.divide(busInfo.get(0)), busInfo.get(0))[0])
                    .multiply(coPrimeProduct.divide(busInfo.get(0))));
            System.out.printf("Solution as of bus number %d: %d\n", busInfo.get(0), solution);
        }
        while(solution.compareTo(BigInteger.ZERO) < 0 ) {
            solution = solution.add(coPrimeProduct);
        }
        while(solution.compareTo(coPrimeProduct) > 1) {
            solution = solution.subtract(coPrimeProduct);
        }
        System.out.printf("CoPrimeProduct: %d Positive Solution: %d\n", coPrimeProduct, solution);

    }
}
