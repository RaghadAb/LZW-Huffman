import java.io.*;
import java.util.*;
import java.util.HashMap;



class Main {
public static void main(String[] args) {
  		long start = System.currentTimeMillis();
		String inputFileName = "/Users/raghadabdulrab/Downloads/algi_set_up 3/large.txt";   //args[0];
  HashMap<Character, Integer> characters = new HashMap<Character, Integer>(); //* character is the map that takes in a relative frequency
  ArrayList<Node> leaves = new ArrayList<>(); 
   try {
		  FileReader reader = new FileReader(inputFileName);
		  Scanner in = new Scanner(reader);

     while (in.hasNextLine()) {
       String line = in.nextLine();

       for (int i = 0; i < line.length(); i++) {
         Character ch = line.charAt(i);
         if (characters.containsKey(ch)) {
           characters.put(ch, characters.get(ch) + 1); //keep adding as long as you find the same character 
         } else { //.put you alter the hashmap values 
           characters.put(ch, 1); //otherwise leave it as 1, occurred once
         }
       }
     }

     ArrayList<Node> nodes = new ArrayList<>();

    for (Character key: characters.keySet()) { //key of the dict is the character
      nodes.add(new Node(key.toString(), characters.get(key), null, null)); //creating a new huffman leaf node and adding it to a list
    } //the frequency is the Integer field within the map 
    
    	while (nodes.size() != 1) {
    		ArrayList<Node> n = Main.getTwoSmallest(nodes); //new node is the combined nodes of the two smallest nodes
    		  Node newNod = new Node(n.get(0).name + n.get(1).name, n.get(0).freq + n.get(1).freq, n.get(0), n.get(1));
    			nodes.add(newNod); //new node created 
    			if (n.get(0).isLeafNode()) {
    				leaves.add(n.get(0)); // add the children to the node 
    			}
    			
    			if (n.get(1).isLeafNode()) {
    				leaves.add(n.get(1));
    			}
    			
    			n.get(0).parent = newNod; //make sure that the parent node is n 
    			n.get(1).parent = newNod;
        		nodes.remove(n.get(0)); 
        		nodes.remove(n.get(1));	
        	  }
  

		   // read in the data and do the work here
		  // read a line at a time to enable newlines to be detected    and included in the compression

		  reader.close();
   } catch(IOException e) {
	   e.printStackTrace();
   }
		
		// output the results here
   	int numCompressedBits = Main.getNumCompressedBits(leaves, characters);
   	int numOriginalBits =  Main.getNumOriginalBits(characters);
   	System.out.println("Original file length in bits = " + numOriginalBits);
   	System.out.println("Compressed file length in bits = " + numCompressedBits);
   	System.out.println("Compression ratio = " + (float)numCompressedBits/numOriginalBits);
   		

	// end timer and print elapsed time as last line of output
	long end = System.currentTimeMillis();
	System.out.println("Elapsed time: " + (end - start) + " milliseconds");
}

public static ArrayList<Node> getTwoSmallest(ArrayList<Node> nodes) {
  if (nodes.size() <= 2) return nodes; 
  Node min =  nodes.get(0); 
  Node min2 = nodes.get(1);
  ArrayList<Node> smallestNodes = new ArrayList<>();
  for (int i = 2; i < nodes.size(); i++) {
    if (nodes.get(i).freq < min.freq) {
      if (min.freq < min2.freq) {
        min2 = min;
      }
      min = nodes.get(i);
    } else if (nodes.get(i).freq < min2.freq) {
      if (min2.freq < min.freq) {
        min = min2;
      }
      min2 = nodes.get(i);
    }
  }
  smallestNodes.add(min); // add the nodes to the array list 
  smallestNodes.add(min2);

  return smallestNodes;

}

public static int getNumCompressedBits(ArrayList<Node> leaves,
		HashMap<Character, Integer> characters) {
	int numOfbits = 0;
	
    //for (Character key: characters.keySet()) {
        for (int k = 0; k < leaves.size(); k++) {
        	Node n = leaves.get(k);
        	Node node = leaves.get(k).parent;
        	int counter = 0;
        	while (node != null) {
        		node = node.parent;
        		counter+=1; //increase the counter by counting the parent node
        	}
        	numOfbits += characters.get(n.name.toCharArray()[0]) * counter; //given the key gives the value 
        } //counter * value 
   // }
			
	return numOfbits;
}

	public static int getNumOriginalBits(HashMap<Character, Integer> characters) {
		int numOfbits = 0;
		
		for (Integer value: characters.values()) {
			numOfbits+=value*8; //ASCII cost
		}
		
		return numOfbits;
	}

}