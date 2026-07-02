package datastructures.wordsuggestion;

/**
 * Implements Robin Hood hashing algorithm for efficient insertion and search operations.
 * The hash table is resized dynamically based on the load factor.
 * 
 * @since 26/11/2024
 * @version 1.7
 * @author Anastasis Zachariou
 */
public class RobinHoodHashing implements UsefulNumbers {
	
	private Element[] table;	// The hash table holding the elements
	private int capacity;	// The capacity of the table
	private int size;	// The number of elements currently in the table
	private int maxProbeLength;	// The maximum probe length encountered
	
    /**
     * Default constructor to initialize the hash table with a capacity of 5.
     * The table starts empty, with size set to 0 and max probe length set to 0.
     * 
     * @since 18/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public RobinHoodHashing() {
		
		this.table = new Element[REHASH_CAPACITY_5];	// Initialize the table with 5 slots
		
		this.capacity = REHASH_CAPACITY_5;	// Set initial capacity to 5
		
		this.size = (this.maxProbeLength = ZERO);	// Set initial size and max probe length to 0
		
	}
	
    /**
     * Inserts a new key-value pair into the hash table using Robin Hood hashing.
     * If the table's load factor exceeds 90%, the table will be rehashed.
     * 
     * @param aKey 			The key to be inserted.
     * @param nextTrieNode 	The RobinTrieNode associated with the key.
     * 
     * @return The index where the element was inserted.
     * 
     * @since 26/11/2024
     * @version 1.9
     * @author Anastasis Zachariou
     */
	public int insert(char aKey, RobinTrieNode nextTrieNode) {
		
		int index = this.hash(aKey);	// Calculate the hash index

		int newProbeLength = ZERO;	// Initialize new probe length
		
		/* Check if the key is already contained in the table, return if true */
		int hashPosition = this.isContained(aKey, index);
		
		if(hashPosition != (MINUS_ONE)) {
			
			/* If key is found, return the index it is located at */
			return (hashPosition);
			
		}
		
		/* Check if resizing (rehashing) is needed */
		if((this.size + ONE) >= (this.capacity * ZERO_POINT_NINETY) ) {
			
			this.rehash();	// Rehash if the table's size exceeds 90% of the capacity
			
			index = this.hash(aKey);	// Calculate the hash index with a new capacity
			
		} 
		
		/* Create new object element that I want to insert into object RobinHoodHashing */
		Element currentElement = new Element(aKey, newProbeLength, nextTrieNode);
		
		/* Probe to find the correct position for the new element */
		while(this.table[index] != null) {
			
			if(this.table[index].getProbeLength() > newProbeLength) {
				
				/* Swap the current element with the one in the table if needed (Robin Hood rule) */
				Element temp = this.table[index];
				
				/* this.table[index] = new Element(aKey, newProbeLength, nextTrieNode);
				 * 
				 * aKey = temp.getKey();
				 * nextTrieNode = temp.getTrieNode();
				 * newProbeLength = temp.getProbeLength();
				 */
				
				this.table[index] = currentElement;
				
				currentElement = temp;

			}
			
			index = ( (index + ONE) % this.capacity );	// Move to the next index
			
			newProbeLength++;	// Increment the new probe length
			
			/* Update the maximum probe length encountered */
			this.maxProbeLength = Math.max(this.maxProbeLength, newProbeLength);
			
		}
		
		/* this.table[index] = new Element(aKey, newProbeLength, nextTrieNode); */
		
		this.table[index] = currentElement;	// Insert the element at the correct position
		this.size++;	// Increment the size of the table
		
		return (index);	// Return the index where the element was inserted
		
	}
	
	/**
     * Searches for a key in the hash table using Robin Hood hashing.
     * 
     * @param aKey The key to search for.
     * 
     * @return The index where the key is located, or -1 if not found.
     * 
     * @since 25/11/2024
     * @version 1.5
     * @author Anastasis Zachariou
     */
	public int search(char aKey) {
		
		int index = this.hash(aKey);	// Calculate the hash index for the key
		
		int newProbeLength = ZERO;	// Initialize new probe length
		
		/* Probe to search for the key */
		while(this.table[index] != null) {
			
			/* Get the current element's key */
			char currentCharacter = this.table[index].getKey();
			
			if(currentCharacter == aKey) {
				
				return (index);	// Return index if the key is found
				
			}
			
			index = ( (index + ONE) % this.capacity );	// Move to the next index
			
			newProbeLength++;	// Increment the new probe length
			
			if(newProbeLength > this.maxProbeLength) {
				
				break;	// Stop searching if the probe length exceeds max
				
			}
			
		}
		
		return (MINUS_ONE);	// Return -1 if the key is not found
		
	}
	
    /**
     * Rehashes the table when the load factor exceeds 90%.
     * It resizes the table to the next prime number in the sequence.
     *  
     * @since 20/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void rehash() {
		
		/* Get the next larger capacity */
		this.capacity = this.getNextCapacity(this.capacity);
		
		/* Create a new table with updated capacity */
		Element[] newTable = new Element[this.capacity];
		
		this.maxProbeLength = ZERO;	// Reset the max probe length
		
		/* Re-insert elements into the new table */
		for(Element element : this.table) {
			
			if(element != null) {
				
				/* Calculate hash index for the element */
				int index = this.hash(element.getKey());
				
				int newProbeLength = ZERO;	// Initialize the new probe length
				
				/* Probe until an empty slot is found */
				while(newTable[index] != null) {
					
					index = ( (index + ONE) % this.capacity );	// Move to the next index
					
					newProbeLength++;	// Increment the new probe length
					
				}
				
				/* Place the element */
				newTable[index] = new Element(element.getKey(), newProbeLength, element.getTrieNode());
				
				/* Update the max probe length */
				this.maxProbeLength = Math.max(this.maxProbeLength, newProbeLength);
				
			}
			
		}
		
		this.table = newTable;	// Set the new table
		
	}
	
	/**
     * Calculates the hash index for a given key.
     * 
     * @param aLetter The character key to hash.
     * 
     * @return The computed hash index.
     *  
     * @since 25/11/2024
     * @version 1.1
     * @author Anastasis Zachariou
     */
	private int hash(char aLetter) {
		
		/* Return the index based on the character's ASCII value */
		return ((int)aLetter % this.capacity);
		
	}
	
	/**
     * Checks if a key is already contained in the table.
     * 
     * @param aKey 			The key to check for.
     * @param hashPosition 	The starting index to check from.
     * 
     * @return Hash Position if the key is found, -1 otherwise.
     *  
     * @since 25/11/2024
     * @version 1.5
     * @author Anastasis Zachariou	
     */
	private int isContained(char aKey, int hashPosition) {
		
		int counter = ONE;	// Start with 1 probe
		
		while((this.table[hashPosition] != null) && (counter <= this.capacity)) {
			
			if(this.table[hashPosition].getKey() == aKey) {
				
				return (hashPosition);	// Return hash position if key is found
				
			}
			
			counter++;
			
			/* Move to the next index */
			hashPosition = ( (hashPosition + ONE) % this.capacity );
			
		}
		
		return (MINUS_ONE);	// Return -1 if key is not found
		
	}
	
	/**
     * Checks if a number is prime.
     * 
     * @param aNumber The number to check.
     * 
     * @return True if the number is prime, false otherwise.
     *  
     * @since 20/11/2024
     * @version 1.0
     * @author Anastasis Zachariou	
     */
	private boolean isPrime(int aNumber) {
		
		if(aNumber <= ONE) {
			
			return (false);	// Return false for numbers <= 1
			
		}
		
		for(int i = TWO; (i * i) <= aNumber; i++) {
			
			if(aNumber % i == ZERO) {
				
				return (false);	// Return false if number is divisible by i
				
			}
			
		}
		
		return (true);	// Return true if the number is prime
		
	}
	
	/**
     * Returns the next prime capacity for the table.
     * 
     * @param newCapacity The current capacity of the table.
     * 
     * @return The next prime capacity.
     *  
     * @since 25/11/2024
     * @version 1.5
     * @author Anastasis Zachariou
     */
	private int getNextCapacity(int newCapacity) {
		
		while(true) {
			
			if(this.isPrime(newCapacity)) {
				
				switch(newCapacity) {
				
				case REHASH_CAPACITY_5 : return (REHASH_CAPACITY_11);
				
				case REHASH_CAPACITY_11 : return (REHASH_CAPACITY_19);
				
				case REHASH_CAPACITY_19 : return (REHASH_CAPACITY_29);
				
				default : return (newCapacity);
				
				}
				
			}
			
		}
		
	}
	
	/**
	 * Sets the table with a new array of elements.
	 * This method creates a new table with the specified size and copies the elements
	 * from the provided newTable into the current table.
	 * 
	 * @param newTable The new table to set for the hash table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setTable(Element[] newTable) {
		
		/* Create a new array with the length of the provided table */
		this.table = new Element[newTable.length];
		
		/* Copy each element from the provided newTable into the current table */
		for(int i = ZERO; i < newTable.length; i++) {
			
			this.table[i] = newTable[i];	// Assign each element
			
		}
		
	}
	
	/**
	 * Sets the element at the specified position in the table.
	 * This method replaces the existing element at the given position with a new one.
	 * 
	 * @param newIndex 	The new element to set in the table.
	 * @param aPosition The position at which the element should be inserted.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setIndexTable(Element newIndex, int aPosition) {
		
		/* Set the new element at the specified position in the table */
		this.table[aPosition] = newIndex;
		
	}
	
	/**
	 * Retrieves the entire hash table.
	 * This method returns the array that holds all the elements of the hash table.
	 * 
	 * @return The hash table array containing all elements.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public Element[] getTable() {
		
		return (this.table);	// Return the entire table
		
	}
	
	/**
	 * Retrieves the element at the specified position in the table.
	 * This method fetches the element stored at the provided index.
	 * 
	 * @param aPosition The position of the element to retrieve.
	 * 
	 * @return The element at the specified position in the table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public Element getIndexTable(int aPosition) {
		
		return (this.table[aPosition]);	// Return the element at the given index
		
	}
	
	/**
	 * Sets the capacity of the hash table.
	 * This method allows you to set the capacity of the table directly, which defines the number of slots available for elements.
	 * 
	 * @param newCapacity The new capacity to set for the table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setCapacity(int newCapacity) {
		
		this.capacity = newCapacity;	// Set the new capacity for the table
		
	}
	
	/**
	 * Retrieves the current capacity of the hash table.
	 * This method returns the number of available slots in the table.
	 * 
	 * @return The current capacity of the hash table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getCapacity() {
		
		return (this.capacity);	// Return the capacity of the table
		
	}
	
	/**
	 * Sets the size of the hash table.
	 * This method updates the number of elements currently stored in the table.
	 * 
	 * @param newSize The new size (number of elements) of the table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setSize(int newSize) {
		
		this.size = newSize;	// Set the new size for the table
		
	}
	
	/**
	 * Retrieves the current size of the hash table.
	 * This method returns the number of elements currently stored in the table.
	 * 
	 * @return The current size (number of elements) of the hash table.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getSize() {
		
		return (this.size);	// Return the size of the table
		
	}
	
	/**
	 * Sets the maximum probe length encountered in the table.
	 * This method updates the maximum probe length value, which keeps track of the longest sequence
	 * of probe attempts during insertions in the table.
	 * 
	 * @param newMaxProbeLength The new maximum probe length.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public void setMaxProbeLength(int newMaxProbeLength) {
		
		this.maxProbeLength = newMaxProbeLength;	// Set the new maximum probe length
		
	}
	
	/**
	 * Retrieves the maximum probe length encountered in the hash table.
	 * This method returns the longest probe length observed during insertion.
	 * 
	 * @return The maximum probe length encountered.
	 * 
	 * @since 18/11/2024
	 * @version 1.0
	 * @author Anastasis Zachariou
	 */
	public int getMaxProbeLength() {
		
		return (this.maxProbeLength);	// Return the maximum probe length
		
	}

}