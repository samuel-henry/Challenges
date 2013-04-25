import java.util.Scanner; 

public class MultiplyExceptSelf {
    public static void main(String[] args) {
        //create a new Scanner object to read the input
        Scanner S = new Scanner(System.in);
        
        //first int is the number of ints in input array
        int len = Integer.parseInt(S.nextLine());
        
        //create an array of integers of length len
        int[] inputArray = new int[len];
        
        //initialize an index for filling inputArray
        int i = 0;
        
        //initialize product of all numbers in inputArray to 1
        long product = 1;
        
        //initialize number of occurrences of 0 value to 0
        int zeroCnt = 0;
        
        //fill inputArray with the values read in by S
        while (S.hasNext()) {
            //store this value
            inputArray[i] = Integer.parseInt(S.nextLine());
            
            if (inputArray[i] == 0) {
                //increment zeroCnt if this number is a 0
                zeroCnt++;
            } else {
                //multiply product times this number otherwise
                product *= inputArray[i];
            }
            
            //increment i
            i++;
        }
        
        //print answers
        for (int k = 0; k < inputArray.length; k++) {
            if (zeroCnt == 0) {
                //print product without this value factored in if no values are zero
                System.out.println(product / inputArray[k]);
            } else if ((zeroCnt == 1 && inputArray[k] != 0) ||(zeroCnt > 1)) {
                //print 0 if there's only one 0 value and it's not this one or if there's more than one 0 value
                System.out.println(0);
            } else if (zeroCnt == 1 && inputArray[k] == 0) {
                //only other case is that this is the only 0 value, so just print product
                System.out.println(product);
            }
        }
        
        //close the Scanner S
        S.close();
    }
        
}

