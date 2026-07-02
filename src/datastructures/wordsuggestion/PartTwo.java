package datastructures.wordsuggestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The class processes multiple input files and compares the performance of
 * StaticTrie and RobinHoodTrie data structures. It calculates the memory usage
 * and number of nodes for both tries structures and writes the results to a file.
 * 
 * The class collects statistics for two scenarios: same length-based search and 
 * percentage-based search, calculating sums, averages, and displaying the results.
 * It processes 25 files and computes data for each file, then outputs the results 
 * for comparison.
 *
 * @param args Command-line arguments for input files.
 * 
 * @since 30/11/2024
 * @version 1.0
 * @author Neophytos Kaikitis
 */
public class PartTwo implements UsefulNumbers {

	public static void main(String[] args) {
		
		/* Initialize variables to store data for different calculations */
		
		String FileInput = args[ZERO];	// The input file name from the command line argument
		String FileOutputMemory = "FileOutputMemory.txt";	// The output file for storing memory stats
		
		/* Variables to store cumulative sums of node and memory data for Static and Robin Hood Tries */
		
		int sumSameLengthNodesStatic = ZERO;
		long sumSameLengthMemoryStatic = ZERO;
		
		int sumSameLengthNodesRobin = ZERO;
		int sumSameLengthMemoryRobin = ZERO;
		
		int sumPercentageNodesStatic = ZERO;
		int sumPercentageMemoryStatic = ZERO;
		
		int sumPercentageNodesRobin = ZERO;
		int sumPercentageMemoryRobin = ZERO;
		
		double averageSameLengthRobin = ZERO;
		double averageSameLengthStatic = ZERO;
		
		double averagePercentageRobin = ZERO;
		double averagePercentageStatic = ZERO;
		
		/* Call to percentage method to process input files (details are not provided in this snippet) */
		FileCreator.percentage(FileInput);
		
		/* Retrieve the list of all files to be processed */
		String[] InputFiles = FileCreator.allFiles();
		
		/* Loop through the first 25 files to gather data about the Static and Robin Hood Tries */
		try (PrintWriter out = new PrintWriter(new FileWriter(FileOutputMemory))) {

			for (int i = ZERO; i < TWENTY_FIVE; i++) {
				
				/* Create Static and Robin Hood Trie instances */
				StaticTrie staticTrie = new StaticTrie();
				RobinHoodTrie robinHoodTrie = new RobinHoodTrie();
				
				/* Insert data into the Robin Hood Trie and log its node and memory details */
				TrieApplications.insertDictionaryRobinHood(InputFiles[i], robinHoodTrie);
				out.print("\n\nInput File: " + InputFiles[i] + "\nRobin Hood Trie:");
				out.print("\nNumber of Nodes: " + robinHoodTrie.getNodesNumber() + "\nMemory allocated: " + robinHoodTrie.getMemoryBytes());
				
				/* Insert data into the Static Trie and log its node and memory details */
				TrieApplications.insertDictionaryStatic(InputFiles[i], staticTrie);
				out.print("\n\nInput File: " + InputFiles[i] + "\nStatic Trie: ");
				out.print("\nNumber of Nodes: " + staticTrie.getNodesNumber() + "\nMemory allocated: " + staticTrie.getMemoryBytes());
				
				/* Accumulate the node and memory statistics for further calculations */
				sumSameLengthNodesStatic  +=  staticTrie.getNodesNumber();
				sumSameLengthMemoryStatic += staticTrie.getMemoryBytes();
				sumSameLengthNodesRobin += robinHoodTrie.getNodesNumber();
				sumSameLengthMemoryRobin += robinHoodTrie.getMemoryBytes();
				
			}
			
			/* Get word amounts from file creator for percentage-based calculations */
			int[] wordAmount = FileCreator.getAmount();
			
			/* Create files for each percentage-based scenario */
			String[] precentageFiles = new String[wordAmount.length];

			for (int i = ZERO; i < wordAmount.length; i++) {

				precentageFiles[i] = FileCreator.createFile(MINUS_ONE, wordAmount[i]);
				
			}
			
			/* Process the files related to percentage-based data collection */
			for (int i = ZERO; i < precentageFiles.length; i++) {
				
				/* Create Static and Robin Hood Trie instances for percentage-based files */
				StaticTrie staticTrie = new StaticTrie();
				RobinHoodTrie robinHoodTrie = new RobinHoodTrie();
				
				/* Insert data into the Robin Hood Trie and log its node and memory details */
				TrieApplications.insertDictionaryRobinHood(precentageFiles[i], robinHoodTrie);
				out.print("\n\nInput File: " + precentageFiles[i] + "\nRobin Hood Trie:");
				out.print("\nNumber of Nodes: " + robinHoodTrie.getNodesNumber() + "\nMemory allocated: " + robinHoodTrie.getMemoryBytes());
				
				/* Insert data into the Static Trie and log its node and memory details */
				TrieApplications.insertDictionaryStatic(precentageFiles[i], staticTrie);
				out.print("\n\nInput File: " + precentageFiles[i] + "\nStatic Trie:");
				out.print("\nNumber of Nodes: " + staticTrie.getNodesNumber() + "\nMemory allocated: " + staticTrie.getMemoryBytes());
				
				/* Accumulate statistics for percentage-based data as well */
				sumPercentageNodesStatic  +=  staticTrie.getNodesNumber();
				sumPercentageMemoryStatic += staticTrie.getMemoryBytes();
				sumPercentageNodesRobin += robinHoodTrie.getNodesNumber();
				sumPercentageMemoryRobin += robinHoodTrie.getMemoryBytes();
				
			}
			
			/* Output the cumulative statistics and calculate averages */
			
			out.print("\n\nSums of Same Length:\n" + "Robin Hood: \n" + "Nodes: " + sumSameLengthNodesRobin +"\nMemory: " + sumSameLengthMemoryRobin);
			averageSameLengthRobin = (sumSameLengthMemoryRobin / sumSameLengthNodesRobin);
			out.print("\nAverage Same Length Robin Hood: " + averageSameLengthRobin);
			
			out.print("\nStatic Trie: \n" + "Nodes: " + sumSameLengthNodesStatic +"\nMemory: " + sumSameLengthMemoryStatic);
			averageSameLengthStatic = (sumSameLengthMemoryStatic / sumSameLengthNodesStatic);
			out.print("\nAverage Same Length Static Trie: " + averageSameLengthStatic);
			
			out.print("\nSums of Percentages:\n" + "Robin Hood: \n" + "Nodes: " + sumPercentageNodesRobin +"\nMemory: " + sumPercentageMemoryRobin);
			averagePercentageRobin = (sumPercentageMemoryRobin / sumPercentageNodesRobin);
			out.print("\nAverage Percentage Robin Hood: " + averagePercentageRobin);
			
			out.print("\nStatic Trie: \n" + "Nodes: " + sumPercentageNodesStatic +"\nMemory: " + sumPercentageMemoryStatic);
			averagePercentageStatic = (sumPercentageMemoryStatic / sumPercentageNodesStatic);
			out.print("\nAverage Percentage Static Trie: " + averagePercentageStatic);
			
		} 
		
		catch (IOException e) {

			/* Handle potential IOExceptions when writing to the file */
			System.err.println("Error reading the file: " + e.getMessage());

		}
	
	}
	
}
