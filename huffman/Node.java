
public class Node {
	
	public String name; // ch value of the node
	  public int freq;
	  public Node left;
	  public Node right;
	  public Node parent;

	  Node(String name, int freq, Node left, Node right) {
	    this.name = name;
	    this.freq = freq;
	    this.left = left;
	    this.right = right;
	    this.parent = null; //initialise parent as null 
	  }

	  public boolean isLeafNode() {
	    if (this.left == null && this.right == null) {
	      return true;
	    } else {
	      return false;
	    }
	  }

	  public String toString() {
	    return this.name + "=>>>>" + Integer.toString(this.freq);
	  }
	  
	  // compare, based on frequency
	  public int compareTo(Node that) {
	      return this.freq - that.freq;
	  }
	
	

}
