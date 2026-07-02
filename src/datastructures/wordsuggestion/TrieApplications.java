package datastructures.wordsuggestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class provides applications for managing and searching words using two types of trie: 
 * RobinHoodTrie and StaticTrie. It implements methods for inserting words from files, 
 * searching for words in these tries, and updating the importance of words in the RobinHoodTrie 
 * based on their occurrence in search files.
 * 
 * Specifically, the class includes the following main functionalities:
 * - Inserting words from files into the RobinHoodTrie and StaticTrie via the 
 *   "insertDictionaryRobinHood" and "insertDictionaryStatic" methods, respectively.
 * - Searching for words in files using the RobinHoodTrie and updating their importance 
 *   via the "searchDictionaryRobinHood" method.
 * 
 * All methods follow the same logic for reading and parsing files, using regular expressions 
 * to split words and remove special characters from the texts. Words are converted to lowerCase 
 * before insertion or searching to ensure uniformity.
 * 
 * Key features:
 * - Supports inserting words from files for two types of trie: RobinHoodTrie and StaticTrie.
 * - Built-in support for searching and increasing the importance of words in the RobinHoodTrie.
 * - Efficient file handling, splitting words and removing non-alphanumeric characters.
 * 
 * @since 30/11/2024
 * @version 1.0
 * @author Neophytos Kaikitis
 */
public class TrieApplications implements UsefulNumbers {
	
	/**
     * Inserts words from a given file into the RobinHoodTrie.
     * This method reads the file line by line, splits each line into words, 
     * and inserts them into the RobinHoodTrie.
     *
     * @param fileNameInput The name of the file containing the words to insert.
     * @param trie 			The RobinHoodTrie into which the words will be inserted.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	public static void insertDictionaryRobinHood(String fileNameInput , RobinHoodTrie trie) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileNameInput))) {
			
			String lineInput;
			
			/* Read file line by line */
			while ((lineInput = br.readLine()) != null) {
				
				/* 
				 * Splits the line from the search file into words using a regular expression.
				 * The regular expression "\\W+" matches one or more non-word characters 
				 * (i.e., characters that are not letters, digits, or underscores),
				 * which means the line will be split into words wherever such characters are found.
				 * This effectively separates the line into individual words, removing any punctuation or special characters.
				 */
				String[] words = lineInput.split("\\W+");
				
				/* Insert each word into the RobinHoodTrie */
				for(String word : words) {
					
					word = word.toLowerCase();	// Convert the word to lowerCase before inserting
					
					trie.insert(word);	// Insert the word into the RobinHoodTrie
					
				}
				
			}  
			
		} catch (IOException e) {
			
			/* Handle any exceptions that occur while reading the file */
			System.err.println("Error reading the file: " + e.getMessage());
			
			}
		
	}
	
	/**
     * Inserts words from a given file into the StaticTrie.
     * This method reads the file line by line, splits each line into words, 
     * and inserts them into the StaticTrie.
     *
     * @param fileNameInput The name of the file containing the words to insert.
     * @param trie 			The StaticTrie into which the words will be inserted.
     * 
     * @since 30/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	public static void insertDictionaryStatic(String fileNameInput , StaticTrie trie) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileNameInput))) {
			
			String lineInput;
			
			/* Read file line by line */
			while ((lineInput = br.readLine()) != null) {
				
				/* 
				 * Splits the line from the search file into words using a regular expression.
				 * The regular expression "\\W+" matches one or more non-word characters 
				 * (i.e., characters that are not letters, digits, or underscores),
				 * which means the line will be split into words wherever such characters are found.
				 * This effectively separates the line into individual words, removing any punctuation or special characters.
				 */
				String[] words = lineInput.split("\\W+");
				
				/* Insert each word into the StaticTrie */
				for(String word : words) {
					
					word = word.toLowerCase();	// Convert the word to lowerCase before inserting
					
					trie.insert(word);	// Insert the word into the StaticTrie
					
				}
				
			}  
			
		} catch (IOException e) {
			
			/* Handle any exceptions that occur while reading the file */
			System.err.println("Error reading the file: " + e.getMessage());
			
			}
		
	}
	
	/**
     * Searches for words in a given file using the RobinHoodTrie.
     * This method reads the file line by line, splits each line into words, 
     * and searches for each word in the RobinHoodTrie.
     * 
     * Also through this method and having as an argument a file based (fileNameSearch),
     * on the words of this file we increase the importance of each word present in the RobinHoodTrie.
     *
     * @param fileNameSearch The name of the file containing the words to search.
     * @param trie 			 The RobinHoodTrie used for searching.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Neophytos Kaikitis
     */
	public static void searchDictionaryRobinHood(String fileNameSearch , RobinHoodTrie trie) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileNameSearch))) {
			
			String lineSearch;
			
			/* Read file line by line */
			while ((lineSearch = br.readLine()) != null) {
				
				/* 
				 * Splits the line from the search file into words using a regular expression.
				 * The regular expression "\\W+" matches one or more non-word characters 
				 * (i.e., characters that are not letters, digits, or underscores),
				 * which means the line will be split into words wherever such characters are found.
				 * This effectively separates the line into individual words, removing any punctuation or special characters.
				 */
				String[] words = lineSearch.split("\\W+");
				
				/* Search for each word in the RobinHoodTrie */
				for(String word : words) {
					
					word = word.toLowerCase();	// Convert the word to lowerCase before searching
					
					trie.search(word);	// Search the word
					
				}
				
			}
			
		} catch (IOException e) {
			
			/* Handle any exceptions that occur while reading the file */
			System.err.println("Error reading the file: " + e.getMessage());
			
			}
		
	}

}
