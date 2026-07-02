package datastructures.wordsuggestion;

/**
 * This class contains various algorithmic methods that interact with a RobinHoodTrie and MinHeap to perform 
 * advanced string operations. It includes functionalities for creating MinHeap objects, searching for words in 
 * the RobinHoodTrie, and filtering words based on certain length conditions. The class also handles the collection 
 * of words of various lengths, including those with the same length as a given word or with one or two characters 
 * different from the given word.
 * 
 * Key functionalities of the class include:
 * - Creating a MinHeap of a given size via the "creator" method.
 * - Searching for words starting with a given prefix in a RobinHoodTrie and populating the MinHeap with those 
 *   words using the "prefix" method.
 * - Collecting words of the same length as a given word and adding them to the MinHeap with the "sameLength" method.
 * - Collecting words of different lengths that match specified conditions with the "differentLength" method.
 * - Checking if a word meets the necessary conditions to be considered valid based on its similarity to the precursor 
 *   word using methods like "isCorrectWord" and "isSubsequence".
 * 
 * The class also provides recursive methods for collecting words in a RobinHoodTrie structure based on specific conditions 
 * (e.g., matching length and character difference) and inserting them into the MinHeap. These methods leverage a 
 * combination of RobinHoodTrie traversal (DFs) and word comparisons to efficiently gather relevant words.
 * 
 * This class is particularly useful in applications requiring dynamic word search and filtering, such as 
 * autocomplete or spelling suggestion systems.
 * 
 * @since 30/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 * @author Neophytos Kaikitis
 */
public class Algorithms implements UsefulNumbers {

	private static String precursor;	// A static variable to store the prefix word
	
	/**
     * Creates and returns a new MinHeap object with a given size.
     *
     * @param k The size of the heap.
     * 
     * @return A new MinHeap object.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public static MinHeap creator(int k) {
		
		MinHeap heap = new MinHeap(k);	// Creates a new MinHeap with a maximum size k
		
		return (heap);	// Returns the created MinHeap
		
	}
	
/****************************************************************************************************************************************/
	
	/**
     * Searches for a given prefix in the RobinHoodTrie and adds words starting with this prefix to the MinHeap.
     * 
     * @param trie The RobinHoodTrie where the search is performed.
     * @param heap The MinHeap that will be populated with words.
     * @param word The prefix to search for.
     * 
     * @return The MinHeap containing words starting with the prefix.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	public static MinHeap prefix(RobinHoodTrie trie, MinHeap heap, String word) {

		Algorithms.precursor = word;	// Stores the word as the prefix to be used
		
		RobinTrieNode node = Algorithms.search(word, trie);	// Searches for the node in the trie
		
		/* If no matching node is found, return the empty heap */
		if (node == null) {
			
			return (heap);
			
		}
		
		/* Collects words starting from the current node and adds them to the heap */
		Algorithms.collectPrefixWords(heap, node, word);

		return (heap);	// Returns the MinHeap with collected words
		
	}

	/**
     * Collects words recursively from the RobinTrieNode, starting at a given node.
     * 
     * @param heap The MinHeap to add words to.
     * @param node The current node in the RobinTrieNode.
     * @param word The word formed so far.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	private static void collectPrefixWords(MinHeap heap, RobinTrieNode node, String word) {
		
		/* If the current node represents a word (wordLength > 0) and it's not the prefix, add it to the heap */
		if ((node.wordLength != ZERO) && !(Algorithms.isPrecursor(word))) {
			
			/* Inserts the word into the heap with its importance */
			heap.insert(word, node.importance);

		}

		/* Recursively explores the children of the current node */
		for (int i = ZERO; i < node.robinHood.getCapacity(); i++) {
			
			/* Get the child entry from the robinHood hash table */
			Element entry = node.robinHood.getIndexTable(i);
			
			if (entry != null) {
				
				RobinTrieNode childNode = entry.getTrieNode();	// Get the child node
				
				/* Get the character corresponding to the child node */
				char nextChar = entry.getKey();	
				
				/* Append the character to the current word and recursively collect words from the child node */
				Algorithms.collectPrefixWords(heap, childNode, (word + nextChar));
				
			}
			
		}
		
	}
	
	/**
     * Searches for the given word in the RobinHoodTrie and returns the corresponding RobinHoodTrie current node if found.
     * 
     * @param aWord The word to search for.
     * @param trie 	The RobinHoodTrie to search in.
     * 
     * @return The node corresponding to the word, or null if not found.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	private static RobinTrieNode search(String aWord, RobinHoodTrie trie) {

		RobinTrieNode currentNode = trie.getRoot(); // Start from the root node of the trie

		/* Iterate through each character in the word */
		for (char ch : aWord.toCharArray()) {
			
			/* Find the position of the character in the robinHood table */
			int nextPosition = currentNode.robinHood.search(ch);

			/* If no matching node is found, return null */
			if (nextPosition == MINUS_ONE) {

				return (null);

			}

			/* Move to the next node */
			currentNode = currentNode.robinHood.getIndexTable(nextPosition).getTrieNode();

		}
		
		/* Return the node if the word ends here (wordLength >= 0) */
		return ((currentNode.wordLength >= ZERO) ? currentNode : null);

	}
	
	/**
     * Checks if the given word is the stored prefix.
     * 
     * @param word The word to check.
     * 
     * @return True if the word is the stored prefix, otherwise false.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	private static boolean isPrecursor(String word) {
		
		/* Compares the word with the stored prefix */
		return (word.equals(Algorithms.precursor));
		
	}
	
/****************************************************************************************************************************************/
	
	/**
     * Collects words from the RobinHoodTrie that are of the same length as the given word and adds them to the MinHeap.
     * 
     * @param trie The RobinHoodTrie to search in.
     * @param heap The MinHeap to store the words.
     * @param word The word used as the reference for length.
     * 
     * @return The MinHeap containing words of the same length.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public static MinHeap sameLength(RobinHoodTrie trie, MinHeap heap, String word) {
		
		Algorithms.precursor = word; // save the word as prefix
		
		/* Collect words of the same length and adds them to the heap */
		collectSameLengthRecursive(trie.getRoot(), "", heap);
		
		return (heap);	// Returns the MinHeap with collected words

	}
	
	/**
     * Recursively collects words of the same length from the RobinTrieNode and adds them to the MinHeap.
     * This method traverses the Trie-like structure, constructs words, and checks if they meet the specified conditions.
     * (The conditions are: the proposed word has the same length as the input word and differs from it by at most two characters.)
     * If they do, the word is added to the MinHeap based on its importance.
     * 
     * @param currentNode 	The current node in the RobinTrieNode.
     * @param currentWord 	The word being formed so far.
     * @param wordNodes 	The MinHeap to add the words to.
     * 
     * @since 30/11/2024
     * @version 1.9
     * @author Anastasis Zachariou
     */
	private static void collectSameLengthRecursive(RobinTrieNode currentNode, String currentWord, MinHeap wordNodes) {
		
		/* Check if the current node represents a word (wordLength > 0) */
		if(currentNode.wordLength > ZERO) {
			
			/* Check if the word meets the conditions */
			boolean isCorrect = Algorithms.isCorrectWord(currentWord);
			
			if(isCorrect) {
			
				/* Add the word to the heap */
				wordNodes.insert(currentWord, currentNode.importance);
			
			}
			
		}
		
		/* Explore the children of the current node */
		for(int i = ZERO; i < currentNode.robinHood.getCapacity(); i++) {
			
			/* Get the child entry from the robinHood hash table */
			Element entry = currentNode.robinHood.getIndexTable(i);
			
			if(entry != null) {
				
				RobinTrieNode childNode = entry.getTrieNode();	// Get the child node
				
				/* Get the character corresponding to the child node */
				char nextChar = entry.getKey();	
				
				/* Append the character to the word and recursively collect words from the child node */
				Algorithms.collectSameLengthRecursive(childNode, (currentWord + nextChar), wordNodes);
				
			}
			
		}
		
	}
	
	/**
	 * Checks if the current word meets the given conditions by comparing it 
	 * with a precursor word.
	 * 
	 * The method verifies two main conditions:
	 * 1. If the current word is the precursor or has a different length than the precursor. 
	 *    In this case, the method returns false.
	 * 2. If the word has the same length as the precursor, it compares the characters 
	 *    one by one and counts how many positions have differences. It returns true 
	 *    if the differences are at most 2 characters, otherwise it returns false.
	 * 
	 * Essentially, this method ensures that the word meets the conditions to be 
	 * considered valid, meaning it has the same length and differs by no more than 
	 * two characters from the precursor word.
	 * 
	 * @param currentWord The word to be checked against the conditions.
	 * 
	 * @return Returns true if the word meets the conditions (same length and at most 
	 *         2 character differences). Otherwise, returns false.
	 *         The method checks if the word is a precursor, if it has a different length, 
	 *         and if it differs from the precursor in more than two characters.
	 * 
	 * @since 30/11/2024
	 * @version 2.8
	 * @author Anastasis Zachariou
	 */
	private static boolean isCorrectWord(String currentWord) {
		
		/* Check if the word is the precursor or has a different length than the precursor */
		if((Algorithms.isPrecursor(currentWord)) || (currentWord.length() != Algorithms.precursor.length())) {
			
			return (false);	// Return false if the word is the precursor or has a different length
			
		}
		
		int differences = ZERO;
		
		/* Check each character of the word */
		for(int i = ZERO; i < currentWord.length(); i++) {
			
			/* If the characters at position i are different */
			if(Algorithms.precursor.charAt(i) != currentWord.charAt(i)) {
				
				differences++;	// Increment the difference counter
				
			}
			
		}
		
		/* Return true if the differences are at most 2, otherwise return false */
		return ((differences <= TWO) ? true : false);
		
	}
	
/****************************************************************************************************************************************/
	
	/**
     * Collects words of a different length from the RobinHoodTrie based on certain conditions
     * related to the given word and returns them in a MinHeap.
     * 
     * @param trie The RobinHoodTrie to search in.
     * @param heap The MinHeap that will be populated with the words.
     * @param word The word used to define the length-related conditions.
     * 
     * @return The MinHeap containing words that meet the length conditions.
     * 
     * @since 30/11/2024
     * @version 1.9
     * @author Anastasis Zachariou
     */
	public static MinHeap differentLength(RobinHoodTrie trie, MinHeap heap, String word){
		
		Algorithms.precursor = word; // save the word as prefix
		
		String[] conditions = Algorithms.conditionsAlgorith3(word);	// Create the conditions for comparison
		
		/* Collect words of different lengths and adds them to the heap */
		Algorithms.collectDifferentLengthRecursive(trie.getRoot(), "", heap, conditions);
		
		return (heap);	// Returns the MinHeap with collected words
		
	}
	
	/**
     * Recursively collects words from the RobinHoodTrie, starting at the given node, that
     * satisfy specific length-related conditions compared to the given word.
     * 
     * @param currentNode 	The current node in the RobinTrieNode.
     * @param currentWord 	The word being constructed from the RobinTrieNode traversal.
     * @param wordNodes 	The MinHeap to add matching words to.
     * @param conditions 	The conditions derived from the input word that the word lengths should meet.
     * 
     * @since 29/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private static void collectDifferentLengthRecursive(RobinTrieNode currentNode, String currentWord, 
								MinHeap wordNodes, String[] conditions) {
		
		/* Check if the current node represents a word (wordLength > 0) and it's not the prefix */
		if((currentNode.wordLength > ZERO) && !(Algorithms.isPrecursor(currentWord))) {
			
			 /* Check if the current word length matches the first condition (one less than the precursor length) */
			if(currentWord.length() == (Algorithms.precursor.length() - ONE)) {
				
				boolean flag1 = conditions[ZERO].equals(currentWord);	// First condition check
				boolean flag2 = conditions[ONE].equals(currentWord);	// Second condition check
				
				/* If the word matches either condition, add it to the heap */
				if((flag1 == true) || (flag2 == true)) {
					
					wordNodes.insert(currentWord, currentNode.importance);
					
				}
				
			}
			
			/* Check if the current word length matches conditions for one or two more characters than the precursor */
			if((currentWord.length() == (Algorithms.precursor.length() + ONE)) || 
					(currentWord.length() == (Algorithms.precursor.length() + TWO))) {
				
				/* If the current word is a valid subsequence, add it to the heap */
				if(Algorithms.isSubsequence(currentWord)) {
					
					wordNodes.insert(currentWord, currentNode.importance);
					
				}
				
			}
			
		}
			
		/* Explore the child nodes recursively */
		for(int i = ZERO; i < currentNode.robinHood.getCapacity(); i++) {
			
			/* Get the child entry from the robinHood hash table */
			Element entry = currentNode.robinHood.getIndexTable(i);
			
			if(entry != null) {
				
				RobinTrieNode childNode = entry.getTrieNode();	// Get the child node
				
				/* Get the character corresponding to the child node */
				char nextChar = entry.getKey();	
				
				/* Append the character to the word and recursively collect words from the child node */
				Algorithms.collectDifferentLengthRecursive(childNode, (currentWord + nextChar), wordNodes, conditions);
				
			}
			
		}
		
	}
	
	/**
     * Creates conditions based on the length of the given word. 
     * These conditions are used to filter words of different lengths.
     * 
     * @param word The reference word used to generate length-based conditions.
     * 
     * @return An array of conditions to match words with certain lengths.
     * 
     * @since 29/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private static String[] conditionsAlgorith3(String word) {
		
		String[] conditions = new String[TWO];	// Array to hold two conditions
		
		String helper1 = "";	// Initialize helper variable for the first condition
		
		helper1 = (helper1 + word.charAt(ZERO));	// Add the first character of the word to the first condition
		
		String helper2 = "";	// Initialize helper variable for the second condition
		
		for(int i = ONE; i < word.length(); i++) {
			
			/* Add all characters except the last one to the first condition */
			if(i != (word.length() - ONE)) {
				
				helper1 = (helper1 + word.charAt(i));
				
			}
			
			/* Add every character to the second condition */
			helper2 = (helper2 + word.charAt(i));
			
		}
		
		conditions[ZERO] = helper1;	// Set the first condition
		conditions[ONE] = helper2;	// Set the second condition
		
		return (conditions);	// Return the conditions array
		
	}
	
	/**
     * Checks if the given word is a subsequence of the precursor word (prefix).
     * 
     * @param word The word to check if it's a subsequence of the precursor.
     * 
     * @return True if the word is a subsequence of the precursor, otherwise false.
     * 
     * @throws IllegalArgumentException if the word or precursor is null.
     * 
     * @since 29/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	private static boolean isSubsequence(String word) {
		
		if((word == null) || (Algorithms.precursor == null)) {
			
			throw new IllegalArgumentException("The word or precursor cannot be null!");
			
		}
		
		int i = ZERO;	// Pointer for the precursor word
		int j = ZERO;	// Pointer for the given word
		
		/* Traverse through both the precursor and the given word */
		while((i < Algorithms.precursor.length()) && (j < word.length())) {
			
			 /* If characters match, move the pointer for the precursor */
			if(Algorithms.precursor.charAt(i) == word.charAt(j)) {
				
				i++;
				
			}
			
			/* Move the pointer for the given word */
			j++;
			
		}
		
		/* If all characters in the precursor were matched, it is a subsequence */
		return (i == Algorithms.precursor.length());
		
	}

/****************************************************************************************************************************************/
	
}
