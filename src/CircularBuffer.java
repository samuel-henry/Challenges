import java.io.*;

public class CircularBuffer {
        
    public static void main(String[] args) {
        
        //size of buffer
        int N = 0;
        
        try {
            //get the input
            InputStreamReader inp = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(inp);
            
             //first line is # of integers
            N = Integer.parseInt(br.readLine());
            
            //create the buffer
            CircularBuffer circBuff = new CircularBuffer();
            Buffer buf = circBuff.new Buffer(N);
            
            //will store the String tokens in the current line
            String[] currLine;
            
            //iterate over the input numbers
            while (true) {
                currLine = br.readLine().split(" ");
                
                //determine what to do
                if (currLine.length == 1) {    
                    if (currLine[0].equals("L")) {
                        //print the current state of the buffer
                        buf.listBuffer();
                    } else if (currLine[0].equals("Q")) {
                        //quit
                        System.exit(0);
                        
                    } else {
                        //append a node with the current string
                        buf.append(currLine[0]);
                    }
                } else if (currLine.length == 2 && currLine[0].equals("R")) {
                    //remove first currLine[1] nodes
                    buf.removeFirst(Integer.valueOf(currLine[1])); 
                }
            }
            
        } catch (IOException ex) {
            System.out.println("IOEx");
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            System.out.println("NFEx");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public class Buffer {
    	//# of elements currently in Buffer
    	int size = 0;
    	
    	//# of elements the buffer can hold at a time
    	int length;
    	
        //store first and last nodes
    	Node first;
    	Node last;
    	
        //create a buffer of length N
    	public Buffer(int N) {
            this.length = N;
    	}
        
        //get the length of the buffer
        public int getLength() {
            return length;
        }
    	
        //get the current size of the buffer
    	public int getSize() {
    		return this.size;
    	}
    	
        //set the current size of the buffer
    	public void setSize(int size) {
    		this.size = size;
    	}
    	
        //get the first node in the buffer
    	public Node getFirst() {
    		return this.first;
    	}
    	
        //set the first node to the given node
    	public void setFirst(Node first) {
    		this.first = first;
    	}
    	
        //remove the first n nodes
    	public void removeFirst(int n) {
            //walk forward
            Node currNode = first;
            
            for (int i = 0; i < n; i++) {
                currNode = currNode.getNext();
            }
            
            //set the first node 
            setFirst(currNode);
            
            //reduce size by n
            setSize(getSize() - n);
    	}
    	
        //append a node to the end of the buffer
    	public void append(String val) {
            //create the new node
            Node addNode = new Node(val);
            
            if (size == 0) {
                //adding the node - set first and last to it and increment size
                first = addNode;
                last = addNode;
                size++;
            } else if (size == length) {
                //buffer is full. move first forward one node and add the new node
                //after the last. size remains the same
                first = first.getNext();
                last.setNext(addNode);
    		last = addNode;
            } else {
                //last becomes the new node. increment size.
    		last.setNext(addNode);
    		last = addNode;
                size++;
            }           
    	}
    	
        //print the buffer from oldest node to newest node
    	public void listBuffer() {
            Node curr = first;
            for (int i = 0; i < size; i++) {
                System.out.println(curr.getValue());
                curr = curr.getNext();
            }
    	}    	
    }
    
    //stores a value and links to the next node in the buffer
    public class Node {
    	Node next;
        
    	String value;
    	
        //construct a node with the given string value
    	public Node(String value) {
            this.value = value;
    	}
    	
        //get the node after this one
    	public Node getNext() {
            return this.next;
    	}
    	
        //set the node after this one
    	public void setNext(Node next) {
    		this.next = next;
    	}
    	
        //get the value stored in this node
    	public String getValue() {
    		return this.value;
    	}
    }
}

