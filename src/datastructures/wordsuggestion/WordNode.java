package datastructures.wordsuggestion;

/**
 * The WordNode class represents a node that holds a word and its associated importance.
 * It is used for managing words in data structures where the importance of each word is tracked.
 * The class provides constructors to initialize the word and importance, as well as getter and setter methods.
 * This can be useful in applications such as ranking or sorting words based on importance.
 * 
 * @since 27/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public class WordNode implements UsefulNumbers {
	
	private String word;	// The word stored in this node
	private int importance;	// The importance value associated with the word
	
	/**
     * Default constructor to initialize a WordNode with default values.
     * The word is set to null and importance to 0.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public WordNode() {
		
		this.word = null;	// Set word to null
		
		this.importance = ZERO;	// Set importance to 0
		
	}
	
	/**
     * Constructor to initialize a WordNode with an importance value.
     * The word is initialized as a string representation of the importance value.
     * 
     * @param anImportance The importance value to be assigned to the word.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public WordNode(int anImportance) {
		
		this.word = Integer.toString(anImportance);	// Set word as the string of importance
		
		this.importance = anImportance;	// Set importance to the passed value
		
	}
	
	/**
     * Constructor to initialize a WordNode with both a word and an importance value.
     * 
     * @param aWord 		The word to be assigned to the node.
     * @param anImportance 	The importance value to be associated with the word.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public WordNode(String aWord, int anImportance) {
		
		this.word = aWord;	// Set the word
		
		this.importance = anImportance;	// Set the importance value
		
	}
	
	/**
     * Sets the word for the WordNode.
     * 
     * @param newWord The new word to set.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void setWord(String newWord) {
		
		this.word = newWord;	// Update the word with the new value
		
	}
	
	/**
     * Adds a character to the current word in the WordNode.
     * This method appends the new character to the end of the word.
     * 
     * @param newCharacter The character to be appended to the word.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void setCharacter(char newCharacter) {
		
		/* Append the new character to the word */
		this.word = (this.word + newCharacter);
		
	}
	
	/**
     * Retrieves the word stored in the WordNode.
     * 
     * @return The word stored in the WordNode.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public String getWord() {
		
		return (this.word);	// Return the word stored in the node
		
	}
	
	/**
     * Sets the importance value for the WordNode.
     * 
     * @param newImportance The new importance value to set.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void setImportance(int newImportance) {
		
		this.importance = newImportance;	// Update the importance with the new value
		
	}
	
	/**
     * Increases the importance value of the WordNode by 1.
     * This method is useful for ranking systems where importance is incremented based on usage.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void addInportance() {
		
		this.importance++;	// Increment the importance by 1
		
	}
	
	/**
     * Retrieves the importance value of the WordNode.
     * 
     * @return The importance value associated with the word.
     * 
     * @since 27/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public int getImportance() {
		
		return (this.importance);	// Return the importance value of the node
		
	}

}
