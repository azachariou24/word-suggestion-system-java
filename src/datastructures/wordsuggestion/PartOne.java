package datastructures.wordsuggestion;

import java.util.Scanner;

/**
 * This class involves processing words from files, applying algorithms
 * that search and rank words based on their importance, and displaying the results.
 * 
 * It uses the RobinHoodTrie data structure for storing and searching words,
 * and the MinHeap for sorting words by their importance. It supports three algorithms 
 * for processing the words.
 * 
 * @since 30/11/2024
 * @version 2.0
 * @author Anastasis Zachariou
 * @author Neophytos Kaikitis
 */
public class PartOne implements UsefulNumbers {
	
	public static void main(String[] args) {
		
		try (Scanner in = new Scanner(System.in)) {
			
			RobinHoodTrie trie = new RobinHoodTrie();	// Initialize the RobinHoodTrie
			
			String fileNameInput = args[ZERO];	// First command line argument (input file)
			String fileNameSearch = args[ONE];	// Second command line argument (search file)
			
			/* Insert words from the input file into the RobinHoodTrie */
			TrieApplications.insertDictionaryRobinHood(fileNameInput , trie);
			
			/* Search for words in the search file using the RobinHoodTrie */
			TrieApplications.searchDictionaryRobinHood(fileNameSearch , trie);
			
			/*
			 * String word = "plan";	// The word to be searched and used in the following algorithms
			 * int k = THREE;	// The number of top words to return (e.g., top 3 words)
			 */
			System.out.println("Give the prefix word: ");
			String word = in.nextLine();	// The word to be searched and used in the following algorithms
			
			System.out.println("Give the amount of words you want us to sugest: ");
			int k = in.nextInt();
			
/****************************************************************************************************************************************/		
			
			/* Algorithm 1: Prefix-based search */
			MinHeap Algorithm1 = Algorithms.creator(k);	// Create MinHeap for the first algorithm
			
			/* Apply the algorithm to search for words with the same prefix */
			Algorithm1 = Algorithms.prefix(trie, Algorithm1, word);
			
			/* Sort words in the MinHeap */
			MinHeap.heapSort(Algorithm1.getNodes(), k);
			
			System.out.println();
			System.out.println("Algorithm 1:");
			System.out.println();
			
			/* Display the top k words for Algorithm 1 */
			for (int i = ZERO; i < k; i++) {
				
				WordNode remove = Algorithm1.deleteMin();	// Remove the minimum element (word)
				
				System.out.println(remove.getWord() + "," + remove.getImportance());	// Print word and importance
				
			}
			
/****************************************************************************************************************************************/
			
			/* Algorithm 2: Same length-based search */
			MinHeap Algorithm2 = Algorithms.creator(k);	// Create MinHeap for the second algorithm
			
			/* Apply the algorithm to search for words with the same length */
			Algorithm2 = Algorithms.sameLength(trie, Algorithm2, word);
			
			/* Sort words in the MinHeap */
			MinHeap.heapSort(Algorithm2.getNodes(), k);
			
			System.out.println();
			System.out.println("Algorithm 2:");
			System.out.println();
			
			/* Display the top k words for Algorithm 2 */
			for (int i = ZERO; i < k; i++) {
				
				WordNode remove = Algorithm2.deleteMin();	// Remove the minimum element (word)
				
				System.out.println(remove.getWord() + "," + remove.getImportance());	// Print word and importance
				
			}

/****************************************************************************************************************************************/
			
			/* Algorithm 3: Different length-based search */
			MinHeap Algorithm3 = Algorithms.creator(k);	// Create MinHeap for the third algorithm
			
			/* Apply the algorithm to search for words with a different length */
			Algorithm3 = Algorithms.differentLength(trie, Algorithm3, word);
			
			/* Sort words in the MinHeap */
			MinHeap.heapSort(Algorithm3.getNodes(), k);
			
			System.out.println();
			System.out.println("Algorithm 3:");
			System.out.println();
			
			/* Display the top k words for Algorithm 3 */
			for (int i = ZERO; i < k; i++) {
				
				WordNode remove = Algorithm3.deleteMin();	// Remove the minimum element (word)
				
				System.out.println(remove.getWord() + "," + remove.getImportance());	// Print word and importance
				
			}
			
/****************************************************************************************************************************************/
			
			/* Combine all three algorithms to find the top words */
			MinHeap topKWords = Algorithms.creator(k);	/* Create MinHeap for the top words */
			
			/* Apply all algorithms to combine results */
			topKWords = Algorithms.prefix(trie, topKWords, word);
			topKWords = Algorithms.sameLength(trie, topKWords, word);
			topKWords = Algorithms.differentLength(trie, topKWords, word);
			
			/* Sort words in the MinHeap */
			MinHeap.heapSort(topKWords.getNodes(), k);
			
			System.out.println();
			System.out.println("Top words");
			System.out.println();
			
			/* Display the top k words from all three algorithms */
			for (int i = ZERO; i < k; i++) {
				
				WordNode remove = topKWords.deleteMin();	// Remove the minimum element (word)
				
				System.out.println(remove.getWord() + "," + remove.getImportance());	// Print word and importance
				
			}	
		
/****************************************************************************************************************************************/

		}
		
	}

}
