package datastructures.wordsuggestion;

/**
 * The SingleList class implements a singly linked list. The list consists of nodes,
 * where each node contains a "WordNode" object and a reference to the next node in the list.
 * The class supports basic operations such as inserting elements at the beginning, at the end, 
 * deleting elements, finding nodes, and other operations for manipulating the list.
 * 
 * @since 28/11/2024
 * @version 1.0
 * @author Anastasis Zachariou
 */
public class SingleList implements UsefulNumbers {
	
	/*
     * The internal ListNode class represents a node in the singly linked list.
     * Each node holds a "WordNode" object and a reference to the next node.
     */
	private class ListNode {
		
		private WordNode obj;	// The object stored in the node
		private ListNode next;	// The reference to the next node in the list
		
		/**
         * Constructor to initialize a node with the given object and the next node.
         * 
         * @param aObj 	The object to be stored in the node.
         * @param aNext The next node in the list.
         * 
         * @since 28/11/2024
         * @version 1.0
         * @author Anastasis Zachariou
         */
		public ListNode(WordNode aObj, ListNode aNext) {
			
			this.obj = aObj;
			
			this.next = aNext;
			
		}
		
		/**
         * Returns the object stored in the node.
         * 
         * @return The object stored in the node.
         * 
         * @since 28/11/2024
         * @version 1.0
         * @author Anastasis Zachariou
         */
		public WordNode getElement() {
			
			return (this.obj);
			
		}
		
		/**
         * Returns a string representation of the node's object.
         * 
         * @return A string representation of the node's object.
         * 
         * @since 28/11/2024
         * @version 1.0
         * @author Anastasis Zachariou
         */
		public String toString() {
			
			return (this.obj.toString());
			
		}

	}

	private ListNode head;	// The first node of the list
	private int size;	// The size of the list
	
	/**
     * Constructor to initialize an empty singly linked list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public SingleList() {
		
		this.head = null;
		
		this.size = ZERO;
		
	}
	
	/**
     * Clears the list, setting the size to 0 and the head node to null.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void makeEmpty() {
		
		this.head = null;
		
		this.size = ZERO;
		
	}
	
	/**
     * Returns true if the list is empty, otherwise false.
     * 
     * @return True if the list is empty, otherwise false.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public boolean isEmpty() {
		
		return (this.size == ZERO);
		
	}
	
	/**
     * Returns the size of the list.
     * 
     * @return The size of the list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public int size() {
		
		return (this.size);
		
	}
	
	/**
     * Inserts a node with the given object at the beginning of the list.
     * 
     * @param aObj The object to be inserted in the new node.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void insert(WordNode aObj) {
		
		/*** ADD YOUR CODE HERE ***/
		/* Create a new node with the given object */
		ListNode newNode = new ListNode(aObj, this.head);
		
		this.head = newNode;	// Update head to the new node
		
		(this.size)++;	// Increase the size of the list
		/*** ADD YOUR CODE HERE ***/
		
	}
	
	/**
     * Inserts a node with the given object at the end of the list.
     * 
     * @param aObj The object to be inserted in the new node.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void insertLast(WordNode aObj) {
		
		/*** ADD YOUR CODE HERE ***/
		ListNode newNode = new ListNode(aObj, null);
		
		/* If the list is empty, the new node becomes the head */
		if(this.isEmpty()) {
			
			this.head = newNode;
			
		}
		
		else {
			
			ListNode current = this.head;
			
			/* Traverse to the last node */
			while(current.next != null) {
				
				current = current.next;	
				
			}
			
			current.next = newNode;	// Insert the new node at the end
			
		}
		
		(this.size)++;	// Increase the size of the list
		/*** ADD YOUR CODE HERE ***/
		
	}
	
	/**
     * Deletes a node containing the given object from the list.
     * 
     * @param aObj The object to be deleted from the list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void delete(WordNode aObj) {
		
		if (!(this.isEmpty())) {
			
			ListNode tmp = this.head;
			ListNode prev = tmp;
			
			/* If first node is to be deleted */
			if (tmp.getElement().equals(aObj)) {
				
				this.head = this.head.next;	// Move the head to the next node
				
				this.size -= ONE;	// Decrease the size of the list
				
			} else {
				
				while (tmp != null) {
					
					if (tmp.getElement().equals(aObj)) {
						
						prev.next = tmp.next;	// Bypass the node to be deleted
						
						this.size -= ONE;	// Decrease the size of the list
						
						break;
						
					}
					
					prev = tmp;
					tmp = tmp.next;
					
				}
				
			}
			
		}
		
	}
	
	/**
     * Finds and returns the node containing the given object, if it exists in the list.
     * 
     * @param aObj The object to be searched for in the list.
     * 
     * @return The node containing the object, or null if not found.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public ListNode findNode(WordNode aObj) {
		
		/*** ADD YOUR CODE HERE ***/
		ListNode current = this.head;
		
		while(current != null) {
			
			if(current.getElement().equals(aObj)) {
				
				return (current);	// Return the node if the object is found
				
			}
			
			current = current.next;
			
		}
		
		return (null);	// Return null if the object is not found
		/*** ADD YOUR CODE HERE ***/
		
	}
	
	/**
     * Returns the front node of the list (head node).
     * 
     * @return The head node of the list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public ListNode getFrontNode() {
		
		/* Return the front node (head) of the list */
		return (this.head);
		
	}
	
	/**
     * Checks if a node containing the given object exists in the list.
     * 
     * @param aObj The object to be checked for existence in the list.
     * 
     * @return True if the object exists in the list, false otherwise.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public boolean existsNode(WordNode aObj) {
		
		/*** ADD YOUR CODE HERE ***/
		return (this.findNode(aObj) != null);	// Return true if the object is found, false otherwise
		/*** ADD YOUR CODE HERE ***/
		
	}
	
	/**
     * Prints all elements of the list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public void print() {
		
		ListNode tmp = this.head;
		
		for (int i = this.size - ONE; i >= ZERO; i--) {
			
			System.out.print(tmp.getElement() + " ");	// Print each element in the list
			
			tmp = tmp.next;
			
		}
		
		System.out.println("");	// Print a newline after the list
		
	}
	
	/**
     * Returns a string representation of the list.
     * 
     * @return A string representing all elements in the list.
     * 
     * @since 28/11/2024
     * @version 1.0
     * @author Anastasis Zachariou
     */
	public String toString() {
		
		String str = new String();
		
		ListNode tmp = this.head;
		
		for (int i = this.size - ONE; i >= ZERO; i--) {
			
			/* Add each element to the string representation */
			str += tmp.getElement() + " ";	
			
			tmp = tmp.next;
			
		}
		
		/* Return the string representation of the list */
		return (str);
		
	}
	
}
