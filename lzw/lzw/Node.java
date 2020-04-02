package lzw;

public class Node {
	
	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	private int code; 
	
	/** create a new node with letter c */
	public Node(char c){
		letter = c;
		isWord = false;
		sibling = null;
		child = null;  
		//dont take in code as a parameter
	}
	
	// include accessors and mutators for
	// the various components of the class
	public Node getChild() {
		return child;
	}
	public Node getSibling() {
		return sibling;
	}
	public char getLetter() {
		return letter;
	}
	public boolean getIsWord() {
		return isWord;
	}
	public void setChild(Node child) {
		this.child = child;
	}
	public void setSibling(Node sibling) {
		this.sibling = sibling ;
	}
	public void setIsWord(boolean isWord) {
		this.isWord = isWord;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
