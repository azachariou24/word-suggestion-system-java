package datastructures.wordsuggestion;

/**
 * The Element class represents an element with a key, probe length, and a RobinTrieNode.
 * It is used for storing and retrieving data in structures like hash tables or Trie trees.
 * It includes constructors and getter/setter methods for setting and retrieving these fields.
 * Suitable for applications such as autocomplete systems or dictionary lookups.
 * 
 * @since 18/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public class Element implements UsefulNumbers {

	private char key;	// The character key of the element
	private int probeLength;	// The probe length from the initial hash position
	private RobinTrieNode trieNode;	// The RobinTrieNode that this Element points to

	/**
	 * Default constructor to initialize an Element with default values.
	 * The key is set to a space character, probeLength to 0, and trieNode to null.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public Element() {
	
		this.key = ' ';	// Set key to empty space
	
		this.probeLength = ZERO;	// Set probeLength to 0
	
		this.trieNode = null;	// Set trieNode to null
	
	}

	/**
	 * Parameterized constructor to initialize an Element with specific values.
	 * 
	 * @param aKey 			The character key to be assigned.
	 * @param aProbeLength 	The probe length for the key's insertion.
	 * @param aTrieNode 	The RobinTrieNode that the key points to.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public Element(char aKey, int aProbeLength, RobinTrieNode aTrieNode) {
	
		this.key = aKey;	// Set the key
	
		this.probeLength = aProbeLength;	// Set the probe length
	
		this.trieNode = aTrieNode;	// Set the associated aTrieNode
	
	}

	/**
	 * Sets the key for the element.
	 * 
	 * @param newKey The new key to set.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setKey(char newKey) {
	
		this.key = newKey;	// Update the key with new value
	
	}

	/**
	 * Retrieves the key of the element.
	 * 
	 * @return The key of the element.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public char getKey() {
	
		return (this.key);	// Return the key
	
	}

	/**
	 * Sets the probe length for the element.
	 * 
	 * @param newProbeLength The new probe length to set.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setProbeLength(int newProbeLength) {
	
		this.probeLength = newProbeLength;	// Update the probe length
	
	}

	/**
	 * Retrieves the probe length of the element.
	 * 
	 * @return The probe length of the element.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getProbeLength() {
	
		return (this.probeLength);	// Return the probe length
	
	}

	/**
	 * Sets the RobinTrieNode that the element points to.
	 * 
	 * @param newTrieNode The new RobinTrieNode to set.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setTrieNode(RobinTrieNode newTrieNode) {
	
		this.trieNode = newTrieNode;	// Set the TrieNode
	
	}

	/**
	 * Retrieves the RobinTrieNode that the element points to.
	 * 
	 * @return The RobinTrieNode associated with the element.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public RobinTrieNode getTrieNode() {
	
		return (this.trieNode);	// Return the TrieNode
	
	}

}