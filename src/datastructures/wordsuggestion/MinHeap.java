package datastructures.wordsuggestion;

/**
 * The MinHeap class represents a minimum heap, which is a data structure 
 * implemented as a binary tree where each parent node has a value smaller than or equal 
 * to the values of its children. This implementation is used to efficiently find the 
 * minimum element and can be used in algorithms like heap sort.
 * 
 * @since 27/11/2024
 * @version 2.0
 * @author Neophytos Kaikitis
 */
public class MinHeap implements UsefulNumbers {
	
	private WordNode[] nodes;	// Array holding the heap's nodes
	private int size;	// Current size of the heap
	private int maxSize;	// Maximum size of the heap
	
	/**
     * Constructor to initialize a MinHeap with the specified size.
     * The heap is initialized with an array of WordNode and the size and maximum size are set.
     * 
     * @param n The size of the heap.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public MinHeap(int aLength) {
		
		/* Create an array of size (aLength + 1) the first position is the current size array */
		this.nodes = new WordNode[aLength + ONE];
		
		this.size = ZERO;	// Initialize the heap size to 0
		
		this.maxSize = aLength;	 // Set the maximum size of the heap
		
	}
	
	 /**
     * Checks if the heap is empty.
     * 
     * @return true if the heap is empty, otherwise false.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public boolean isEmpty() {
		
		/* Returns true if size is 0, indicating the heap is empty */
		return (this.size == ZERO);
		
	}
	
	/**
     * Checks if the heap is full.
     * 
     * @return true if the heap is full, otherwise false.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public boolean isFull() {
		
		/* Returns true if size is equal to maxSize, indicating the heap is full */
		return (this.size == this.maxSize);
		
	}
	
	/**
     * Inserts a new word and its importance into the heap.
     * If the heap is not full, a new WordNode is created and placed at the appropriate position
     * to maintain the heap property.
     * 
     * @param newWord 		The word to insert.
     * @param newImportance The importance of the word.
     * 
     * @since 28/11/2024
     * @version 2.6
     * @author Neophytos Kaikitis
     */
	public void insert(String newWord, int newImportance) {
		
		/* Create a new WordNode */
		WordNode newNode = new WordNode(newWord, newImportance);
		
		/* If the heap is not full */
		if (this.size < this.maxSize) {
			
			int index = (this.size + ONE);	// Set the index for the new node
			
			/* Rearrange the heap elements to maintain the min-heap property */
			while ((index > ONE) && (this.nodes[(index / TWO)].getImportance() > newImportance)) {
				
				this.nodes[index] = this.nodes[index / TWO];	// Shift elements
				
				index = (index / TWO);	// Update the index
				
			}
			
			this.nodes[index] = newNode;	// Place the new node at the correct position
			
			this.size++;	// Increase the heap size
			
			this.nodes[ZERO] = new WordNode(this.size);	// Update the first element with the new size
			
		}
		
		else {
			
			/* Replace the root if the new node has higher importance */
            if (newImportance > this.nodes[ONE].getImportance()) {
            	
                this.nodes[ONE] = newNode; // Replace root
                
                MinHeap.percolateDown(this.nodes, this.size, ONE); // Restore heap property
                
            }
			
		}
		
	}
	
	/**
     * Deletes and returns the node with the minimum importance (the root of the heap).
     * After deletion, the heap is rearranged to maintain the min-heap property.
     * 
     * @return The node with the minimum importance.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public WordNode deleteMin() {
		
		WordNode minNode = null;	// Initialize the variable for the minimum node
		
		/* If the heap is not empty */
		if(!(this.isEmpty())){
			
			minNode = this.nodes[ONE];	// Save the node with the minimum importance
			
			/* Swap the first and last elements */
			WordNode swap = this.nodes[ONE];
			this.nodes[ONE] = this.nodes[this.size];
			this.nodes[this.size] = swap;
			
			this.size--;	// Decrease the heap size
			
			MinHeap.percolateDown(this.nodes, this.size, ONE);	// Rearrange the heap
			
		}
		
		return (minNode);	// Return the node with the minimum importance
		
	}
	
	/**
     * Rearranges the heap from bottom to top to maintain the min-heap property.
     * This is used after deletion or insertion of a new element.
     * 
     * @param aHeap 			The array representing the heap.
     * @param aLength 			The length of the heap.
     * @param currentPosition 	The current position being examined.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public static void percolateDown(WordNode[] aHeap, int aLength, int currentPosition) {
		
		WordNode temp = aHeap[currentPosition];	// Save the element to be moved
		
		int helperPosition;
		
		/* Examine the children of the node */
		while ((TWO * currentPosition) <= aLength) {
			
			/* Find the child with the smaller importance */
			helperPosition = (TWO * currentPosition);
			
			if ((helperPosition < aLength) && 
					(aHeap[helperPosition + ONE].getImportance() < aHeap[helperPosition].getImportance())) {
				
				helperPosition++;
				
			}
			
			/* Move the element down if it violates the min-heap property */
			if (temp.getImportance() > aHeap[helperPosition].getImportance()) {
				
				aHeap[currentPosition] = aHeap[helperPosition];
				
				currentPosition = helperPosition;
				
			} 
			
			else {
				
				break;	// No need to move down further
				
			}
		}
		
		aHeap[currentPosition] = temp;	// Place the element in its correct position
		
	}
	
	/**
     * Builds a heap from an unordered array.
     * This is used to construct the MinHeap from a given array.
     * 
     * @param aHeap 	The array holding the elements.
     * @param aLength 	The length of the array.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public static void buildHeap(WordNode[] aHeap, int aLength) {
		
		/* Start from the middle of the array */
		for (int i = (aLength / TWO); i > ZERO; i--) {
			
			MinHeap.percolateDown(aHeap, aLength, i);	// Rearrange the heap
			
		}
		
	}
	
	/**
     * Performs heap sort on an array using the MinHeap.
     * 
     * @param aHeap 	The array containing the elements to be sorted.
     * @param aLength 	The length of the array.
     * 
     * @since 27/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public static void heapSort(WordNode[] aHeap, int aLength) {
		
		MinHeap.buildHeap(aHeap, aLength);	// Build the heap
		
		/* Perform the sorting by repeatedly extracting the min element */
		for (int i = aLength; i > ONE; i--) {
			
			/* swap (A[1], A[i]); */
			WordNode swap = aHeap[ONE];
			aHeap[ONE] = aHeap[i];
			aHeap[i] = swap;
			
			MinHeap.percolateDown(aHeap, (i - ONE), ONE);	// Rearrange the heap
			
		}
		
	}
	
	/**
	 * Gets the array holding the nodes of the MinHeap.
     * 
     * @return The array of WordNodes in the heap.
     * 
     * @since 30/11/2024
     * @version 2.0
     * @author Neophytos Kaikitis
     */
	public WordNode[] getNodes() {
		
		return (this.nodes);	// Return the array of nodes in the heap
		
	}
	
}
