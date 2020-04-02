package lzwhashmap;
public class Node {
	
	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	/** create a new node with letter c */
	public Node(char c){
		letter = c;
		isWord = false;
		sibling = null;
		child = null;
	}

	public char getLetter() {
		return letter;
	}

	public boolean isWord() {
		return isWord;
	}

	public Node getSibling() {
		return sibling;
	}

	public Node getChild() {
		return child;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	public void setChild(Node child) {
		this.child = child;
	}
	
	// include accessors and mutators for
	// the various components of the class
	
}
