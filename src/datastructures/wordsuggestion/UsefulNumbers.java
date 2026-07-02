package datastructures.wordsuggestion;

/**
 * The UsefulNumbers interface contains a series of constant values that are used in various parts of the program.
 * These constants include:
 * - Numeric values (e.g., 0, 1, 2, 3, etc.) that are used for easy reference to basic numerical constants.
 * - Values that define the capacities for rehashing (rehash) in data structures like hash tables.
 * - Values related to the size of the alphabet and the ASCII encoding of characters.
 * - Constants used for experimental purposes, such as word and file sizes for performance testing.
 * 
 * These constants help maintain the usability and readability of the code, allowing easy modification of values 
 * without the need to change them in multiple places in the program.
 * 
 * @since 01/12/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public interface UsefulNumbers {
	
	static final int MINUS_ONE = -1;
	static final int ZERO = 0;
	static double ZERO_POINT_NINETY = 0.90;
	static final int ONE = 1;
	static final int TWO = 2;
	static final int THREE = 3;
	static final int FOUR =4;
	static final int FIVE = 5;
	static final int TWENTY_FIVE = 25;
	
	static final int REHASH_CAPACITY_5 = 5;
	static final int REHASH_CAPACITY_11 = 11;
	static final int REHASH_CAPACITY_19 = 19;
	static final int REHASH_CAPACITY_29 = 29;
	
	static final int ALPHABET_SIZE = 26;
	
	static final int LARGER_SIZE_WORDS = 46;
	
	static final int ASCII_NUMBER_a = 97;
	static final int ASCII_NUMBER_z = 122;
	
	static final int EXPERIMENTAL_WORD_SIZE_ONE = 2;
	static final int EXPERIMENTAL_WORD_SIZE_TWO = 5;
	static final int EXPERIMENTAL_WORD_SIZE_THREE = 9;
	static final int EXPERIMENTAL_WORD_SIZE_FOUR = 15;
	static final int EXPERIMENTAL_WORD_SIZE_FIVE = 25;
	
	static final int EXPERIMENTAL_FILE_SIZE_ONE = 1000;
	static final int EXPERIMENTAL_FILE_SIZE_TWO = 5000;
	static final int EXPERIMENTAL_FILE_SIZE_THREE = 10000;
	static final int EXPERIMENTAL_FILE_SIZE_FOUR = 100000;
	static final int EXPERIMENTAL_FILE_SIZE_FIVE = 500000;
	
}
