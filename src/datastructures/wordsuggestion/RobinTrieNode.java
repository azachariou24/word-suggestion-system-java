package datastructures.wordsuggestion;

/**
 * The "RobinTrieNode" class represents a node in a trie (tree data structure) that uses the Robin Hood Hashing
 * method for efficient storage and lookup of data. Each node contains a reference to a `RobinHoodHashing` object,
 * which is used for efficient word management. It also stores the length of the word if it ends at this node, 
 * and an "importance" value associated with the word.
 * 
 * This class is used in cases where optimized word management is needed using the Robin Hood Hashing algorithm
 * for better performance in scenarios with high hash collisions.
 * 
 * @since 27/11/2024
 * @version 2.0
 * @author Anastasis Zachariou
 */
public class RobinTrieNode implements UsefulNumbers {
	
	/* A reference to the RobinHoodHashing object associated with this TrieNode */
	RobinHoodHashing robinHood;
	int wordLength;	// Stores the length of the word if it ends at this node
	int importance;	// The importance value associated with the word
	
	/**
     * Constructor to initialize a RobinTrieNode.
     * The node starts with no children, and wordLength is zero (meaning no word ends here).
     * This constructor also initializes the associated RobinHoodHashing instance.
     * 
     * @since 27/11/2024
     * @version 1.7
     * @author Anastasis Zachariou
     */
	public RobinTrieNode() {
		
		this.robinHood = new RobinHoodHashing();	// Initialize the RobinHoodHashing instance
		
		this.wordLength = ZERO;	// No word ends at this node initially
		
		this.importance = ZERO;	// Set importance to 0
		
	}
	
}
