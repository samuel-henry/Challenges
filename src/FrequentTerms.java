import java.util.*;

public class FrequentTerms {
        
    public static void main(String[] args) {
        //initial map for storing key, value pairs
        HashMap<String,Integer> wordMap = new HashMap<String,Integer>();
        
        //create TreeMap for sorting
        TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(new ValueComparator(wordMap));
        
        //create a new Scanner object to read the input
        Scanner S = new Scanner(System.in);
        
        //skip N
        S.next();
        
        //initialize number of terms to print to 0
        int K = 0;
        
        //initialize String to hold and check numeric status of S.next()
        String currInpt = "";
        
        //read the input
        while (S.hasNext()) {           
            currInpt = S.next();
            if (isInteger(currInpt)) {
                //if the current input value is a number, set it to K (we've reached the end)
                K = Integer.parseInt(currInpt);
            } else {
                if (wordMap.containsKey(currInpt)) {
                    //if this word is already in wordMap, increment the value 
                    wordMap.put(currInpt, wordMap.get(currInpt) + 1);
                } else {
                    //add it to the map with value 1
                    wordMap.put(currInpt, 1);
                }
            }            
        }
        
        //put wordMap in a sorted TreeMap
        sortedMap.putAll(wordMap);
        
        //print the specified number of words
        printTopKWords(K, sortedMap);
    }
    
    //print the specified number of words in descending order of frequency / alphabetic order
    private static void printTopKWords(int K, TreeMap<String, Integer> map) {
        //keep track of how many we've printed
        int count = 0;
        
        //iterate through each entry
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (count >= K) {
                //exit the loop if we've printed enough
                break;
            } else {
                //print the next entry
                System.out.println(entry.getKey());
                count++;
            }
        }
    }
    
    //helper method to check whether a String is an Integer
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return false;
        }
        
        return true;
    }
    
    //for TreeMap's sorting
    //NOTE: influenced by http://stackoverflow.com/a/3420912
    //see Wasserman's critique of literal answer above https://plus.google.com/102216152814616302326/posts/bEQLDK712MJ
    //see handling of 0 (equal values) in else below. 
    //could be improved
    static class ValueComparator implements Comparator<String> {
        //maintain the map
        private Map<String, Integer> base;
        
        //constructor
        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }
        
        //override compare() to check frequency of occurrence and alphabetic order
        public int compare(String a, String b) {
            if (base.get(a) > base.get(b)) {
                return -1;
            } else if (base.get(a) < base.get(b)) {
                return 1;
            } else {
                //solves problem referenced by Wasserman and gives alphabetical order in case of same frequency
                return a.compareTo(b);
            }
        }
    }   
}
