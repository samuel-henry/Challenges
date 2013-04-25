import java.io.*;

public class TopFour {
        
    public static void main(String[] args) {
        
       
        
        //initialize array of min values
        int[] winners = {Integer.MIN_VALUE,
                        Integer.MIN_VALUE,
                        Integer.MIN_VALUE,
                        Integer.MIN_VALUE
            };
        
        //initialize integer to store current value for putting in place        
        int tempVal = 0;
        int N = 0;
        try {
            InputStreamReader inp = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(inp);
            
             //first line is # of integers
            N = Integer.parseInt(br.readLine());
            
            //iterate over the input numbers
            for (int i = 0; i < N; i++) {
                //hold onto the next integer
                tempVal = Integer.parseInt(br.readLine());
                
                //move up while it's greater than already stored values, swapping them down
                
                if (tempVal > winners[3]) {
                    winners[3] = tempVal;
                    if (tempVal > winners[2]) {
                        winners[3] = winners[2];
                        winners[2] = tempVal;
                        if (tempVal > winners[1]) {
                            winners[2] = winners[1];
                            winners[1] = tempVal;
                            if (tempVal > winners[0]) {
                                winners[1] = winners[0];
                                winners[0] = tempVal;
                
                            }
                        }
                    }
                }
 
            }
            
        } catch (IOException ex) {
            
        }
        
        //iterate over the number of actual values put in order in the array
        for (int j = 0; j < Math.min(N,4); j++) {
            System.out.println(winners[j]);
        }
    }
}

