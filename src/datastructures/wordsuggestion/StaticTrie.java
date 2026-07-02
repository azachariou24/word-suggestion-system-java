package datastructures.wordsuggestion;

/**
 * The StaticTrie class implements a trie (prefix tree) data structure where words are stored in a way 
 * that allows for efficient insertion, search, and retrieval of words. 
 * A StaticTrie is specifically optimized for use cases where the size of the alphabet is fixed 
 * (e.g., lowerCase English letters from 'a' to 'z'). Each node in the StaticTrie contains an array to hold 
 * references to its children, and the length of the word is stored at the node where the word ends. 
 * The class provides both iterative and recursive methods for inserting and searching words.
 * 
 * This class also includes methods for calculating the number of nodes and memory consumption, 
 * which are useful for analyzing the efficiency of the StaticTrie structure.
 * 
 * Features:
 * - Insertion of words, both iteratively and recursively.
 * - Search for words, both iteratively and recursively.
 * - Counting the number of nodes in the StaticTrie.
 * - Calculating the memory consumed by the StaticTrie.
 * 
 * @since 11/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public class StaticTrie implements UsefulNumbers {
	
	/* Inner class StaticTrieNode represents a node in the StaticTrie */
	private class StaticTrieNode{
		
		/* Array to hold children nodes for each letter of the alphabet */
		StaticTrieNode[] letters = new StaticTrieNode[ALPHABET_SIZE];
		int wordLength;	// Stores the length of the word if it ends at this node
		
        /**
         * Constructor to initialize a StaticTrieNode.
         * The node starts with no children, and wordLength is zero (meaning no word ends here).
         * 
         * @since 11/11/2024
         * @version 1.0
         * @author Anastasis Zachariou
         */
		public StaticTrieNode() {
			
			this.wordLength = ZERO;	// No word ends at this node initially
			
			/* Initialize the letters array with null values, as no children exist yet */
			for(int i = ZERO; i < ALPHABET_SIZE; i++) {
				
				this.letters[i] = null;	// Each position corresponds to a letter of the alphabet
				
			}
			
		}
		
	}
	
	private StaticTrieNode root;	// Root node of the StaticTrie, starts with an empty node
	
    /**
     * Constructor to initialize the StaticTrie.
     * Creates an empty root node.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public StaticTrie() {
		
		this.root = new StaticTrieNode();	// The root of the StaticTrie is an empty node
		
	}
	
    /**
     * Inserts a word into the StaticTrie.
     * For each character in the word, we either traverse existing nodes or create new ones if necessary.
     * At the end, we store the length of the word at the last node.
     * 
     * @param aWord The word to be inserted into the StaticTrie.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void insert(String aWord) {
		
		StaticTrieNode currentNode = this.root;	// Start from the root of the StaticTrie
		
		/* Iterate through each character in the word */
		for(char ch : aWord.toCharArray()) {
			
			/* Calculate the index of the character in the letters array (0 for 'a', 1 for 'b', ..., 25 for 'z') */
			int index = (ch - 'a');
			
			/* If there's no existing node for this character, create a new StaticTrieNode */
			if(currentNode.letters[index] == null) {
				
				currentNode.letters[index] = new StaticTrieNode();
				
			}
			
			/* Move to the next node in the StaticTrie */
			currentNode = currentNode.letters[index];
			
		}
		
		/* Set the wordLength to the length of the word at the last node */
		currentNode.wordLength = aWord.length();
		
	}
	
    /**
     * Inserts a word into the StaticTrie using recursion.
     * For each character in the word, we either traverse existing nodes or create new ones if necessary.
     * At the end, we store the length of the word at the last node.
     *
     * @param aWord The word to be inserted into the StaticTrie.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void insertRecursive(String aWord) {
		
		this.helperInsertRecursive(this.root, aWord, ZERO);
		
	}
	
    /**
     * Helper method to insert a word recursively.
     *
     * @param aCurrentNode The current StaticTrieNode we're working with.
     * @param aWord        The word to insert.
     * @param anIndex      The current index in the word.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private void helperInsertRecursive(StaticTrieNode aCurrentNode, String aWord, int anIndex) {
		
		if(anIndex == aWord.length()) {
			
			aCurrentNode.wordLength = aWord.length();	// Mark the end of the word
			
			return ;	// Base case: we've inserted the entire word
			
		}
		
		/* Get the index for the current character */
		char ch = aWord.charAt(anIndex);
		int letterIndex = (ch - 'a');
		
		/* If the child node for the character doesn't exist, create it */
		if(aCurrentNode.letters[letterIndex] == null) {
			
			aCurrentNode.letters[letterIndex] = new StaticTrieNode();
			
		}
		
		/* Recursively insert the next character in the word */
		this.helperInsertRecursive(aCurrentNode.letters[letterIndex], aWord, (anIndex + ONE));
		
	}
	
    /**
     * Searches for a word in the StaticTrie.
     * We traverse through each character's corresponding node.
     * If we find the word, we return true, otherwise false.
     * 
     * @param aWord The word to search for in the StaticTrie.
     * 
     * @return True if the word exists, false otherwise.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public boolean search(String aWord) {
		
		StaticTrieNode currentNode = this.root;	// Start from the root node
		
		/* Iterate through each character in the word */
		for(char ch : aWord.toCharArray()) {
			
			/* Calculate the index of the character in the letters array */
			int index = (ch - 'a');
			
			/* If the character's node does not exist, the word is not in the StaticTrie */
			if(currentNode.letters[index] == null) {
				
				return (false);
				
			}
			
			/* Move to the next node */
			currentNode = currentNode.letters[index];
			
		}
		
		/* Return true if the wordLength of the last node is greater than zero (meaning a word ends here) */
		return (currentNode.wordLength > ZERO);
		
	}
	
    /**
     * Searches for a word in the StaticTrie using recursion.
     * We traverse through each character's corresponding node.
     * If we find the word, we return true, otherwise false.
     *
     * @param aWord The word to search for in the StaticTrie.
     * 
     * @return True if the word exists, false otherwise.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public boolean searchRecursive(String aWord) {
		
		return (this.helperSearchRecursive(this.root, aWord, ZERO));
		
	}
	
    /**
     * Helper method to search for a word recursively.
     *
     * @param aCurrentNode The current StaticTrieNode we're working with.
     * @param aWord        The word to search.
     * @param anIndex      The current index in the word.
     * 
     * @return true if the word exists in the StaticTrie, false otherwise.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private boolean helperSearchRecursive(StaticTrieNode aCurrentNode, String aWord, int anIndex) {
		
		/* We have checked all characters; return whether wordLength > 0 (meaning a word ends here) */
		if(anIndex == aWord.length()) {
			
			return (aCurrentNode.wordLength > ZERO);
			
		}
		
		/* Get the index for the current character */
		char ch = aWord.charAt(anIndex);
		int letterIndex = (ch - 'a');
		
		/* If the character's node doesn't exist, the word isn't in the StaticTrie */
		if(aCurrentNode.letters[letterIndex] == null) {
			
			return (false);
			
		}
		
		/* Recursively search for the next character in the word */
		return (this.helperSearchRecursive(aCurrentNode.letters[letterIndex], aWord, (anIndex + ONE)));
		
	}
	
	/**
	 * Calculates the number of nodes in the StaticTrie.
	 * It starts from the root and performs a DFS to count all the nodes.
	 * The recursive method "dfsCounter" traverses the entire StaticTrie tree and counts the nodes.
	 * 
	 * @return The number of nodes in the StaticTrie.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
	 */
	public int getNodesNumber() {
		
		/* 
		 * Start DFs from the root node.
		 * We remove the root of the StaticTrie which is empty! 
		 */
		return (this.dfsCounter(this.root) - ONE);
		
	}
	
	/**
	 * Helper method to recursively count the number of nodes.
	 * This method calls itself for each child node and adds up the number of nodes in the tree.
	 * If the node is null, it returns 0.
	 * If it's not null, it counts the current node and recursively continues for its children.
	 * 
	 * @param aCurrentNode The current StaticTrieNode being processed.
	 * 
	 * @return The number of nodes from the current node onward.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
	 */
	private int dfsCounter(StaticTrieNode aCurrentNode) {
		
		/* If the current node is null, return 0 because there is no node here */
		if(aCurrentNode == null) {
			
			return (ZERO);
			
		}
		
		/* Initialize the counter to 1, because the current node counts */
		int counter = ONE;
		
		/* Traverse through each child of the current node */
		for(StaticTrieNode child : aCurrentNode.letters) {
			
			/* If the child is not null, recursively call the method for the child */
			if(child != null) {
				
				counter += (this.dfsCounter(child));
				
			}
			
		}
		
		/* Return the total count of nodes (current node + all its children) */
		return (counter);
		
	}
	
	/**
	 * Calculates the memory consumed by the StaticTrie.
	 * The calculation is done by multiplying the number of nodes by the number of pointers and the size of the array.
	 * Each node contains an array of 26 elements (for each letter of the alphabet) and a pointer to each element of the array.
	 * The total memory is calculated as: (number of nodes) * (size of the array) * (size of a pointer).
	 * 
	 * @return The total memory in bytes consumed by the StaticTrie.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
	 */
	public int getMemoryBytes() {
		
		/* Calculate the number of nodes in the StaticTrie */
		int nodesNumber = this.getNodesNumber();
		
		/* Size of the pointer in bytes (4 bytes per pointer) */
		int bytesPointer = FOUR;
		
		/* Size of the letters array for each node (26 elements for the alphabet) */
		int sizeTable = ALPHABET_SIZE;
		
		/* Calculate the total memory in bytes */
		int memoryBytes = (nodesNumber * bytesPointer * sizeTable);
		
		return (memoryBytes);	// Return the total memory in bytes
		
	}

}
