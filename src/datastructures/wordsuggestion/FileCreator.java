package datastructures.wordsuggestion;

import java.io.*;
import java.util.Random;

/**
 * The FileCreator class provides methods to generate text files containing random words 
 * and analyze the distribution of word lengths. The class supports creating files with random 
 * words or predetermined word length distributions, analyzing the frequency of words with different 
 * lengths, and generating files with combinations of word lengths and word quantities for experimental purposes.
 * 
 * @since 28/11/2024
 * @version 2.0
 * @author Neophytos Kaikitis
 */
public class FileCreator implements UsefulNumbers {
	
	/**
	 * Calculates the percentage of words based on their length from a given file.
	 * This method reads the file, counts the occurrence of words of different lengths,
	 * and then calculates the percentage of each word length.
	 *
	 * @param file The file path to the text file containing the words.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static void percentage(String file) {
		
		/* Ensure user provided the file path as argument */
		String filePath = file;
		
		/* Array to store the count of words for each length */
		int[] sizes = new int[LARGER_SIZE_WORDS];
		
		/* Total count of words in the file */
		int totalWords = ZERO;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			
			String line;
			
			/* Read file line by line */
			while ((line = br.readLine()) != null) {
				
				/* Split the line into words, removing any non-alphabetical characters */
				String[] words = line.split("\\W+");
				
				/* Process each word in the line */
				for (String word : words) {
					
					if (!word.isEmpty()) {
						
						/* Count word length and increment the respective count in the sizes array */
						int length = word.length();
						
						sizes[length]++;
						totalWords++;
						
					}
					
				}
				
			}

			/* Print word length percentages */
			if (totalWords == ZERO) {
				
				System.out.println("No words found in the file.");
				
			} 
			
			else {
				
				System.out.println("Word Length Percentages:");
				
				for (int i = ONE; i < LARGER_SIZE_WORDS; i++) {
					
					int length = i;
					int count = sizes[i];
					
					double percentage = (count * 100.0) / totalWords;
					
					if (percentage != ZERO) {
						
						/* Display the percentage of each word length */
						System.out.printf("Length %d: %f%%\n", length, percentage);
						
					}
					
				}
				
			}

		} catch (IOException e) {
			
			/* Handle potential IOExceptions when reading the file */
			System.err.println("Error reading the file: " + e.getMessage());
			
			}
		
	}
	
	/**
	 * Generates files with random words based on various word lengths and word amounts.
	 * This method creates several files with different combinations of word lengths and word counts, 
	 * ensuring a wide range of test data for further analysis.
	 * 
	 * @return An array containing the names of the generated files.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static String[] allFiles() {
		
		/* Array to store the names of the files that will be created */
		String[] names = new String[TWENTY_FIVE];
		/* Counter to keep track of the position in the names array */
		int counter = ZERO;
		
		/* Arrays defining the word lengths and word amounts for file generation */
		
		/* The wordLength array defines different word lengths that will be used for file generation */
		int[] wordLength = { EXPERIMENTAL_WORD_SIZE_ONE, EXPERIMENTAL_WORD_SIZE_TWO, 
				EXPERIMENTAL_WORD_SIZE_THREE, EXPERIMENTAL_WORD_SIZE_FOUR, EXPERIMENTAL_WORD_SIZE_FIVE };
		
		/* The wordAmount array defines the word quantities for each file */
		int[] wordAmount = { EXPERIMENTAL_FILE_SIZE_ONE, EXPERIMENTAL_FILE_SIZE_TWO, 
				EXPERIMENTAL_FILE_SIZE_THREE, EXPERIMENTAL_FILE_SIZE_FOUR, EXPERIMENTAL_FILE_SIZE_FIVE };
		
		/* Create files with all combinations of word lengths and word amounts */
		for (int i = ZERO; i < FIVE; i++) {
			
			for (int j = ZERO; j < FIVE; j++) {
				
				/* Create a file with the defined word length and word amount */
				names[counter] = FileCreator.createFile(wordLength[j], wordAmount[i]);
				
				counter++;	// Increment the counter to store the next file's name in the array
				
			}
			
		}
		
		return (names);	// Return the array of generated file names

	}
	
	/**
	 * Creates a file with random words of specified length and amount.
	 * The method either generates words of a given length or based on a predefined distribution 
	 * of word lengths from a statistical analysis.
	 *
	 * @param wordLength The length of the words to generate. If -1, the word lengths are chosen randomly.
	 * @param wordAmount The number of words to generate in the file.
	 * 
	 * @return The name of the output file created.
	 * 
	 * @since 28/11/2024
	 * @version 1.4
	 * @author Neophytos Kaikitis
	 */
	public static String createFile(int wordLength, int wordAmount) {
		
		/* Output file name based on word length and word amount */
		String outputFile = "outputFile_Length_" + wordLength + "_Amount_" + wordAmount + ".txt";
		
		/* Random object for generating random numbers */
		Random random = new Random();
		
		/* ASCII values for lowerCase letters 'a' to 'z' */
		int min = ASCII_NUMBER_a;
		int max = ASCII_NUMBER_z;
		
		/* Flag to indicate whether word length should follow a statistical distribution */
		boolean notRandom = false;
		
		try (PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {
			
			/* Set flag to use statistical distribution of word lengths */
			if (wordLength == MINUS_ONE) {
				
				notRandom = true;
				
			}
			
			/* Generate and write words to the output file */
			for (int i = ZERO; i < wordAmount; i++) {
				
				if (notRandom) {
					
					/* Choose word length randomly based on predefined frequencies */
					wordLength = FileCreator.myRand(FileCreator.getLengths(), 
													FileCreator.getPercentages(), FileCreator.getSizes());
					
				}
				
				/* Generate each word character by character */
				for (int j = ZERO; j < wordLength; j++) {
					
					char ch = (char) (random.nextInt(max - min + ONE) + min);
					
					out.print(ch);
					
				}
				
				out.println();	// Move to the next line after each word
				
			}

		} catch (IOException e) {
			
			/* Handle potential IOExceptions when writing to the file */
			System.err.println("Error reading the file: " + e.getMessage());
			
			}
		
		return (outputFile);
		
	}
	
	/**
	 * Returns a random number from the given array "arr[]" based on the probability distribution 
	 * defined by the "freq[]" array. The probability of selecting an index is determined by the 
	 * percentages in "freq[]", and the method uses a prefix sum technique to efficiently determine 
	 * the index corresponding to the random number.
	 * 
	 * @param arr 	The array from which a random number will be selected.
	 * @param freq 	The frequency array that defines the probability of selecting each element of "arr".
	 * @param n 	The size of the "arr[]" and "freq[]" arrays.
	 * 
	 * @return A randomly selected value from "arr[]" based on the probabilities defined in "freq[]".
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static int myRand(int arr[], double freq[], int n) {
		
		/* Create and fill prefix array */
		double[] prefix = new double[n];
		
		prefix[ZERO] = freq[ZERO];
		
		/* Fill the prefix array with cumulative frequencies */
		for (int i = ONE; i < n; ++i) {
			
			prefix[i] = prefix[i - ONE] + freq[i];
			
		}

		/*
		 *  prefix[n-1] should be the sum of all frequencies (close to 100%)
		 *  Generate a random number between 0 and the sum of percentages (close to 100)
		 */
		double r = (Math.random() * prefix[n - ONE]);

		/* Find index of ceiling of r in prefix array */
		int indexc = FileCreator.findCeil(prefix, r, ZERO, (n - ONE));
		
		return (arr[indexc]);
		
	}
	
	/**
	 * Finds the index of the smallest element in the array "arr[]" that is greater than or equal 
	 * to the given value "r". This method uses a binary search approach to efficiently find the ceiling 
	 * of "r" within the specified range "[l, h]" in the array.
	 * 
	 * @param arr 	The array to search in.
	 * @param r 	The value to find the ceiling for.
	 * @param l 	The left index of the range to search within.
	 * @param h 	The right index of the range to search within.
	 * 
	 * @return The index of the smallest element greater than or equal to "r", or -1 if no such element exists.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static int findCeil(double arr[], double r, int l, int h) {
		
		int mid;
		
		/* Perform binary search to find the ceiling of 'r' in arr[l..h] */
		while (l < h) {
			
			mid = (l + ((h - l) >> ONE));	// Same as mid = (l+h)/2
			
			if (r > arr[mid]) {
				
				l = (mid + ONE);	// Narrow search range to the right half
				
			}
			
			else {
				
				h = mid;	// Narrow search range to the left half
				
			}
			
		}
		
		/* Return the index of the ceiling element if found, otherwise -1 */
		return ((arr[l] >= r) ? l : MINUS_ONE);
		
	}
	
	/**
	 * Returns an array of word lengths that can be used for generating random words. These lengths 
	 * represent possible lengths for the randomly generated words, based on statistical analysis or design.
	 * 
	 * @return An array of integer word lengths.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static int[] getLengths() {
		
		int[] lengths = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27,
				28, 29, 31, 45 };
		
		/* Return a predefined set of word lengths */
		return (lengths);
		
	}
	
	/**
	 * Returns the size (length) of the lengths array. This size represents the number of possible 
	 * word lengths available for random word generation based on the getLengths() method.
	 * 
	 * @return The number of different word lengths available.
	 * 
	 * @since 29/11/2024
	 * @version 1.6
	 * @author Neophytos Kaikitis
	 */
	public static int getSizes() {
		
		/* Return the length of the array returned by the getLengths() method */
		return (FileCreator.getLengths().length);
		
	}
	
	/**
	 * Returns an array of percentages that represent the probability distribution for selecting each 
	 * word length. The percentages define the likelihood of choosing each word length from the getLengths() array.
	 * 
	 * @return An array of double values representing the percentage distribution for word lengths.
	 * 
	 * @since 28/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static double[] getPercentages() {
		
		double[] percentages = { 0.007313, 0.117849, 0.574899, 1.912016, 4.228486, 8.001046, 11.315151, 14.007386,
				14.273459, 12.311660, 10.161416, 7.911324, 5.726484, 3.904472, 2.447538, 1.449341, 0.835628, 0.415142,
				0.214040, 0.100692, 0.047533, 0.020532, 0.008438, 0.003375, 0.002250, 0.000844, 0.000563, 0.000563,
				0.000281, 0.000281 };
		
		/* Return a predefined array of percentages for word length distribution */
		return (percentages);
		
	}
	
	/**
	 * Returns an array of predefined word amounts that represent the number of words 
	 * to be generated in the experimental files. This array defines the sizes of the 
	 * text files that will be created during the experimentation process.
	 * 
	 * @return An array of integers representing the number of words for different file sizes. 
	 * 
	 * @since 30/11/2024
	 * @version 1.0
	 * @author Neophytos Kaikitis
	 */
	public static int[] getAmount() {
		
		/*
		 * Array of word amounts representing different file sizes.
		 * The values in the array define how many words will be in each file for the experiments.
		 */
		int[] wordAmount = { EXPERIMENTAL_FILE_SIZE_ONE, EXPERIMENTAL_FILE_SIZE_TWO, 
				EXPERIMENTAL_FILE_SIZE_THREE, EXPERIMENTAL_FILE_SIZE_FOUR, EXPERIMENTAL_FILE_SIZE_FIVE };
		
		return (wordAmount);	// Return the array with predefined word amounts for different files
		
	}

}
