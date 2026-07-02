package datastructures.wordsuggestion;

/**
 * This class implements a trie data structure using the Robin Hood hashing technique 
 * for efficient storage and retrieval of words. The RobinHoodTrie allows for fast insertion,
 * searching, and traversal of words, with special focus on minimizing memory usage through 
 * Robin Hood hashing. The structure supports both iterative and recursive methods for inserting 
 * and searching words, providing flexibility based on the specific needs of the application.
 * Additionally, the RobinHoodTrie tracks the importance and length of each word, allowing for advanced 
 * operations such as collecting all word nodes and calculating memory usage. 
 * The root of the RobinHoodTrie is represented by a `RobinTrieNode`, and words are stored across 
 * multiple nodes with each node holding a part of the word and the associated metadata.
 * 
 * Features include:
 * - Insertion of words with automatic handling of duplicate entries.
 * - Support for both recursive and iterative word insertion and search.
 * - Recursive methods for traversing and collecting word nodes.
 * - Memory calculations based on node and element counts.
 * 
 * 
 * @since 30/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public class RobinHoodTrie implements UsefulNumbers {
	
	private RobinTrieNode root;	// Root node of the RobinHoodTrie, starts with an empty node
	
    /**
     * Constructor to initialize the RobinHoodTrie.
     * Creates an empty root node.
     * 
     * @since 11/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public RobinHoodTrie() {
		
		this.root = new RobinTrieNode();	// The root of the RobinHoodTrie is an empty node
		
	}
	
    /**
     * Inserts a word into the RobinHoodTrie.
     * For each character in the word, we either traverse existing nodes or create new ones if necessary.
     * At the end, we store the length of the word at the last node.
     * 
     * @param aWord The word to be inserted into the RobinHoodTrie.
     * 
     * @since 27/11/2024
     * @version 2.5
     * @author Anastasis Zachariou
     */
	public void insert(String aWord) {
		
		/* If the word already exists, don't insert it again */
		if(this.search(aWord)) {
			
			return ;
			
		}
		
		RobinTrieNode currentNode = this.root;	// Start from the root of the RobinHoodTrie
		
		/* Iterate through each character in the word */
		for(char ch : aWord.toCharArray()) {
			
			RobinTrieNode nextTrieNode = new RobinTrieNode();
			
			int position = currentNode.robinHood.insert(ch, nextTrieNode);
			
			/* Move to the next node in the RobinHoodTrie */
			currentNode = currentNode.robinHood.getIndexTable(position).getTrieNode();
			
		}
		
		currentNode.importance = (ONE);
		
		/* Set the wordLength to the length of the word at the last node */
		currentNode.wordLength = aWord.length();
		
	}
	
    /**
     * Inserts a word into the RobinHoodTrie using recursion.
     * For each character in the word, we either traverse existing nodes or create new ones if necessary.
     * At the end, we store the length of the word at the last node.
     *
     * @param aWord The word to be inserted into the RobinHoodTrie.
     * 
     * @since 27/11/2024
     * @version 2.5
     * @author Anastasis Zachariou
     */
	public void insertRecursive(String aWord) {
		
		/* If the word already exists, don't insert it again */
		if(this.searchRecursive(aWord)) {
			
			return ;
			
		}
		
		helperInsertRecursive(this.root, aWord, ZERO);
		
	}
	
    /**
     * Helper method to insert a word recursively.
     *
     * @param aCurrentNode The current RobinTrieNode we're working with.
     * @param aWord        The word to insert.
     * @param anIndex      The current index in the word.
     * 
     * @since 27/11/2024
     * @version 2.5
     * @author Anastasis Zachariou
     */
	private void helperInsertRecursive(RobinTrieNode aCurrentNode, String aWord, int anIndex) {
		
		if(anIndex == aWord.length()) {
			
			aCurrentNode.wordLength = aWord.length();	// Mark the end of the word
			
			aCurrentNode.importance = (ONE);
			
			return ;	// Base case: we've inserted the entire word
			
		}
		
		/* Get the index for the current character */
		char ch = aWord.charAt(anIndex);
		
		RobinTrieNode nextTrieNode = new RobinTrieNode();
		
		int position = aCurrentNode.robinHood.insert(ch, nextTrieNode);
		
		aCurrentNode = aCurrentNode.robinHood.getIndexTable(position).getTrieNode();
		
		
		/* Recursively insert the next character in the word */
		helperInsertRecursive(aCurrentNode, aWord, (anIndex + ONE));
		
	}
	
    /**
     * Searches for a word in the RobinHoodTrie.
     * We traverse through each character's corresponding node.
     * If we find the word, we return true, otherwise false.
     * 
     * @param aWord The word to search for in the RobinHoodTrie.
     * 
     * @return True if the word exists, false otherwise.
     * 
     * @since 27/11/2024
     * @version 2.5
     * @author Anastasis Zachariou
     */
	public boolean search(String aWord) {
		
		RobinTrieNode currentNode = this.root;	// Start from the root node
		
		/* Iterate through each character in the word */
		for(char ch : aWord.toCharArray()) {
			
			int nextPosition = currentNode.robinHood.search(ch);
			
			/* If the character's node does not exist, the word is not in the RobinHoodTrie */
			if(nextPosition == MINUS_ONE) {
				
				return (false);
				
			}
			
			/* Move to the next node */
			currentNode = currentNode.robinHood.getIndexTable(nextPosition).getTrieNode();
			
		}
		
		boolean flag = (currentNode.wordLength > ZERO);
		
		/* Increment the importance of the word if it exists */
		if(flag) {
			
			currentNode.importance++;
			
		}
		
		/* Return true if the wordLength of the last node is greater than zero (meaning a word ends here) */
		return (flag);
		
	}
	
    /**
     * Searches for a word in the RobinHoodTrie using recursion.
     * We traverse through each character's corresponding node.
     * If we find the word, we return true, otherwise false.
     *
     * @param aWord The word to search for in the RobinHoodTrie.
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
     * @param aCurrentNode The current RobinTrieNode we're working with.
     * @param aWord        The word to search.
     * @param anIndex      The current index in the word.
     * 
     * @return True if the word exists in the RobinHoodTrie, false otherwise.
     * 
     * @since 27/11/2024
     * @version 2.5
     * @author Anastasis Zachariou
     */
	private boolean helperSearchRecursive(RobinTrieNode aCurrentNode, String aWord, int anIndex) {
		
		/* We have checked all characters; return whether wordLength > 0 (meaning a word ends here) */
		if(anIndex == aWord.length()) {
			
			boolean flag = (aCurrentNode.wordLength > ZERO);
			
			/* Increment the importance if the word exists */
			if(flag) {
				
				aCurrentNode.importance++;
				
			}
			
			return (flag);
			
		}
		
		/* Get the index for the current character */
		char ch = aWord.charAt(anIndex);
		
		int nextPosition = aCurrentNode.robinHood.search(ch);
		
		/* If the character's node doesn't exist, the word isn't in the RobinHoodTrie */
		if(nextPosition == MINUS_ONE) {
			
			return (false);
			
		}
		
		aCurrentNode = aCurrentNode.robinHood.getIndexTable(nextPosition).getTrieNode();
		
		/* Recursively search for the next character in the word */
		return (this.helperSearchRecursive(aCurrentNode, aWord, (anIndex + ONE)));
		
	}
	
	/**
	 * Collects all word nodes from the RobinHoodTrie.
	 * This method traverses the RobinHoodTrie and accumulates all words and their importance into a SingleList.
	 * It starts the recursive process by invoking the helper method.
	 * 
	 * @return A SingleList containing all the WordNode objects representing the words in the RobinHoodTrie.
	 * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
	 */
	public SingleList amassAllWordNodes() {
		
		/* Create a new SingleList to hold the WordNodes which will be collected */
		SingleList wordNodes = new SingleList();
		
		/* Call the helper method to recursively collect word nodes starting from the root node */
		this.amassAllWordNodesRecursive(this.root, "", wordNodes);
		
		/* Return the SingleList that now contains all the collected WordNodes */
		return (wordNodes);
		
	}
	
	/**
	 * Recursively collects all the word nodes in the RobinHoodTrie.
	 * This helper method traverses each node in the RobinHoodTrie, forms words by appending characters, 
	 * and adds the formed words along with their importance to the given list.
	 * It performs a DFS to visit all nodes.
	 *
	 * @param aCurrentNode The current node being processed in the RobinHoodTrie.
	 * @param aCurrentWord The current word being formed during the traversal.
	 * @param aWordNodes   The list to which the WordNodes (representing words in the RobinHoodTrie) are added.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	private void amassAllWordNodesRecursive(RobinTrieNode aCurrentNode, String aCurrentWord, SingleList aWordNodes) {
		
		/* 
		 * If the current node marks the end of a word (i.e., wordLength > 0), 
		 * create a new WordNode and add it to the list.
		 */
		if(aCurrentNode.wordLength > ZERO) {
			
			/* Create a new WordNode with the current word and the importance of the word at the current node */
			WordNode newWordNode = new WordNode(aCurrentWord, aCurrentNode.importance);
			
			/* Add the created WordNode to the SingleList that is passed into this method */
			aWordNodes.insert(newWordNode);
			
		}
	
		/*
		 * Iterate through the children of the current node to explore the RobinHoodTrie further.
		 * Each child node corresponds to a different character of a potential word.
		 */
		for(int i = ZERO; i < aCurrentNode.robinHood.getTable().length; i++) {
			
			/* Get the child entry from the robinHood hash table */
			Element entry = aCurrentNode.robinHood.getIndexTable(i);
			
			/* If the element exists (i.e., it's not null), continue the process */
			if(entry != null) {
				
				RobinTrieNode childNode = entry.getTrieNode();	// Get the child node
				
				/* Get the character corresponding to the child node */
				char nextChar = entry.getKey();
				
				/*
				 * Append the character to the current word being formed.
				 * Recursively call this method on the child node with the updated current word.
				 * This allows us to traverse deeper into the RobinHoodTrie and form complete words.
				 */
				this.amassAllWordNodesRecursive(childNode, (aCurrentWord + nextChar), aWordNodes);
				
			}
			
		}
		
	}
	
	/**
     * Returns the root node of the RobinHoodTrie.
     * This method provides access to the root of the RobinHoodTrie, which is the entry point for any RobinHoodTrie operations.
     * 
     * @return The root node of the RobinHoodTrie, which is an instance of the RobinTrieNode class.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	public RobinTrieNode getRoot() {
		
		/* Return the root of the RobinHoodTrie */
		return (this.root);
		
	}
	
	/**
     * Returns the total number of nodes in the RobinHoodTrie by performing a DFS traversal.
     * This method initiates the DFS process by calling a helper method that recursively counts the nodes.
     * 
     * @return The total number of nodes in the RobinHoodTrie.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public int getNodesNumber() {
		
		/* 
		 * Start DFS from the root node to count all the nodes in the RobinHoodTrie.
		 * We remove the root of the RobinHoodTrie which is empty!
		 */
		return (this.dfsCounter(this.root) - ONE);
		
	}
	
	/**
     * Helper method for performing DFS traversal of the RobinHoodTrie to count the nodes.
     * 
     * @param currentNode The current node being processed.
     * 
     * @return The number of nodes in the subtree rooted at the current node.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private int dfsCounter(RobinTrieNode aCurrentNode) {
		
		/* Start with 1 to count the current node itself */
		int counter = ONE;
		
		/* Traverse through the children of the current node (using RobinHoodHashing) */
		for(int i = ZERO; i < aCurrentNode.robinHood.getCapacity(); i++) {
			
			/* Get the child entry from the robinHood hash table */
			Element entry = aCurrentNode.robinHood.getIndexTable(i);
			
			/* If the element exists (i.e., it's not null), recursively count the child node */
			if(entry != null) {
				
				RobinTrieNode childNode = entry.getTrieNode();
				
				/* Recursively count nodes in the child subtree */
				counter += (this.dfsCounter(childNode));
				
			}		
				
		}
		
		/* Return the total count of nodes in this subtree */
		return (counter);
		
	}
	
	/**
	 * Returns the total sum of the capacities of all nodes in the RobinHoodTrie.
	 * This method starts the DFS traversal from the root and accumulates the capacity of each node.
	 * 
	 * @return The total sum of the capacities of all nodes in the RobinHoodTrie.
	 * 
	 * @since 30/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getElementsNumber() {
		
		/* 
		 * Start DFS from the root node to sum up the capacities of all nodes 
		 * Subtract 5, this number represents the number of elements at the root of the RobinHooTrie!
		 */
		return (this.dfsCapacityCounter(this.root) - FIVE);
		
	}
	
	/**
	 * Helper method to perform a DFS traversal of the RobinHoodTrie and sum up the capacities of all nodes.
	 * This method is called recursively for each child node in the RobinHoodTrie.
	 *
	 * @param aCurrentNode The current node being processed.
	 * 
	 * @return The sum of capacities for the current node and all its descendants.
	 * 
	 * @since 30/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	private int dfsCapacityCounter(RobinTrieNode aCurrentNode) {
		
		/* 
		 * Start with the capacity of the current node .
		 * Get the capacity of the current node.
		 */
		int capacitySum = aCurrentNode.robinHood.getCapacity();
		
		/* Traverse through the children of the current node using RobinHoodHashing */
		for(int i = ZERO; i < aCurrentNode.robinHood.getCapacity(); i++) {
			
			/* Get the child entry from the RobinHood hash table */
			Element entry = aCurrentNode.robinHood.getIndexTable(i);
			
			/* 
			 * If the element exists (i.e., it's not null), 
			 * recursively sum the capacity of its child node 
			 */
			if(entry != null) {
				
				RobinTrieNode child = entry.getTrieNode();
				
				/* Recursively sum the capacities of the child nodes */
				capacitySum += (this.dfsCapacityCounter(child));
				
			}
			
		}
		
		/* Return the total capacity sum for the subtree rooted at the current node */
		return (capacitySum);
		
	}
	
	/**
	 * Calculates the memory usage of the RobinHoodTrie in bytes.
	 * This method calculates the total memory usage by considering the size of nodes, elements,
	 * and the RobinHood hashing structure.
	 * 
	 * @return The total memory usage in bytes for the RobinHoodTrie.
	 * 
	 * @since 30/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getMemoryBytes() {
		
		/* Calculate the number of nodes in the RobinHoodTrie */
		int nodesNumber = this.getNodesNumber();
		
		/* Get the total number of elements in the trie */
		int elementsNumber = this.getElementsNumber();
		
		int bytesProbeLength = FOUR;	// Size of probe length in bytes
		/* Size of the pointer in bytes (4 bytes per pointer) */
		int bytesPointer = FOUR;
		
		int bytesElement = (bytesPointer + bytesProbeLength);	// Size of each element in the table
		
		int bytesCapacity = FOUR;	// Size of the capacity field in bytes
		int bytesSize = FOUR;	// Size of the size field in bytes
		int bytesMaxProbeLength = FOUR;	// Size of the max probe length field in bytes
		
		/* Calculate the memory used by the RobinHood hashing structure */
		int bytesRobinHoodHashing = ((bytesCapacity + bytesSize + bytesMaxProbeLength) * nodesNumber);
		
		/* Calculate the total memory in bytes */
		int memoryBytes = ((elementsNumber * bytesElement) + (bytesRobinHoodHashing));
		
		return (memoryBytes);	// Return the total memory in bytes
		
	}

}
