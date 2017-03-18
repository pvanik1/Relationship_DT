import java.io.*;
import java.util.*;

/** Binary Relation ADT of homogenous types k, v implemented as a Hash Table. 
 *
 * @author 1103086v
 *
 * @param <k>
 * @param <v>
 */
public class RelationClass<k,v> implements Relation <k,v> {

	private Node<k,v>[] buckets;
	
	/** Testing file containing country symbol and language pairs separated by new lines, 
	 *  e.g. FR French
	 */
	private final String FILE_NAME = "myfile.txt";
	
	/** Arbitrary number for determining the number of buckets. Seeks to achieve a compromise
	 *  of hash table occupancy and collission occurences. Based on a common hash table load factor of 0.5-0.75.*/
	private final double BUCKET_MULTIPLIER = 1.5;
	
	/** Hash Table constructor. Utilises Parser object to process input from testing file.
	 *  Creates an array of Node<k,v> objects which contain references to the next node in the array index (bucket)
	 *  as in a singly linked list ("SLL"). Array length is based on the number of entries in the 
	 *  testing file and the BUCKET_MULTIPLIER. Populates the hash table from the testing file.
	 */
	@SuppressWarnings("unchecked")
	public RelationClass() {
		
		Parser pars = new Parser(FILE_NAME);
		int bucketlength = 0;
		bucketlength = (int) (pars.getNumOfLines()*BUCKET_MULTIPLIER);
		buckets = (Node<k,v>[]) new Node<?,?>[bucketlength];
		pars.process(this);
	}
	
	/** Hash function that returns a bucket index smaller than bucketlength */
	private int hash (k key) {
		return Math.abs(key.hashCode())	% buckets.length;
	}
	
	/*
	 * Hash table insertion algorithm. If a pair already exists on the hashed index,
	 * appends it to the last element as in a SLL.
	 */
	public void insert (k key, v val) {
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		
		while (curr != null) {	
			if (key.equals(curr.getKey())) {

				if (val.equals(curr.getValue())) {		// if both key and value already exist, terminate
					System.err.println("Pair " + key + " " + val + " already exists");
					return;
				}
				else if (curr.getNext() == null) {		// if reached last element, append the new node to the end
						curr.setNext(key, val);
						return;
				}
			}
			else if  (curr.getNext() == null) {			// 
						curr.setNext(key, val);
						return; 
			}
			curr = curr.getNext();	// propagate on the SLL until both key and value matched or end of SLL is found
		}
		buckets[b] = new Node<k,v>(key, val, null);		// if there are no nodes at the hashed index, place the new node there
	}
	
	
	public boolean contains(Node node) {
		k key = (k) node.getKey();
		v value = (v) node.getValue();
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		
		while (curr != null) {
			if (key.equals(curr.getKey()))	// check if keys are the same
				if (value.equals(curr.getValue()))
					return true;
			curr = curr.getNext();
		}
		return false;
	}
	
	// overloaded contains() method so that simple string input can be tested
	public boolean contains(k key, v value) {
		Node n = new Node(key, value, null);
		return contains(n);
	}
	
	// 2. given x, return a set containing all values y such that the relation contains (x, y)
	
	public Set<v> getValues(k key) {
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		Set<v> resultSet = new HashSet<v>();
		
		while (curr != null) {
			if (curr.getKey().equals(key))
				resultSet.add(curr.getValue());			
			curr = curr.getNext();
		}
		return resultSet;
	}
		
	// 3. given y, return a set containing all values x such that the relation contains (x, y)
	
	public Set<v> getKeys(v value) {
		Set<v> resultSet = new HashSet<v>();
		for (int i = 0; i < buckets.length; i++) {
			Node<k,v> curr = buckets[i];
			while (curr != null) {
				if (curr.getValue().equals(value))
					resultSet.add((v) curr.getKey());
				curr = curr.getNext();
			}
		}
		return resultSet;
	}
	
	public void clear() {
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = null;
		}
	}
	
	public void remove (k key, v val) {
		
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		Node<k,v> previous = null;
		
		while (curr != null) {
			
			
			if (curr.getKey().equals(key) && curr.getValue().equals(val)) { // if pairs match
												
				if (previous == null) {		// if it's the first one , make the following SLL entry first in the bucket
						buckets[b] = curr.getNext();
						return;
				}	
				
				else {						// if it's not the first one then make previous's next point to curr's next
					previous.setNext(curr.getNext()); 
					return;
				}
				
			} else {						// there is not match, keep going through the SLL
				previous = curr;
				curr = curr.getNext();
			}
		}
		System.out.println("Pair not found");
	}
		
	// 7. given x, remove all pairs (x, y) from the relation
	public void removeAllWithKey(k key) {
		
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		Node<k,v> previous = null;
		
		while (curr != null) {
			if (key.equals(curr.getKey())) { // check if keys match
			
				if (previous == null) {
					buckets[b] = curr.getNext(); // if removing first item, set the next as first
					
				} else {
					previous.setNext(curr.getNext()); // else set the previous' next to curr's next
				}
				
			} else {
				previous = curr; // if match wasn't found, set previous to current before moving on
			}
			curr = curr.getNext(); // move in SLL
		}
	}	
	
	
	// 8. given y, remove all pairs (x, y) from the relation
	public void removeAllWithValue(v val) {
		for (int i = 0; i < buckets.length; i++) {
			Node<k,v> curr = buckets[i];
			Node<k,v> previous = null;
			
			while (curr != null) {
				if (curr.getValue().equals(val)) {
					
					if (previous == null) {
						buckets[i] = curr.getNext(); // if removing first item, set the next as first
						
					} else {
						previous.setNext(curr.getNext()); // else set the previous' next to curr's next
					}	
				} else {							// if match wasn't found, set previous to current before moving on
					previous = curr;
				}	
				curr = curr.getNext(); 				// move in SLL
			}	
		}
	}
	
	/*
	 * Returns a string representation of all occupied buckets in the format 
	 * [occupied bucket index] <k, v> <k, v> <k,v> ...
	 *  ... 
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				result += "[" + i + "]" + " ";
				result += buckets[i].printBucket() + "\n";
			}
		}
		if (result == "")
			return "Relation is empty";
		return result;
	}
	
	// --- Node code ---
	
	/**
	 * Inner node class for nodes of the hash table
	 * @author 1103086v
	 *
	 * @param <k> key
	 * @param <v> value
	 */
	public static class Node<k,v> {
		private k key;
		private v value;
		private Node<k,v> next;	
		
		private Node(k nodekey, v nodevalue, Node<k,v> next) {
			this.key = nodekey;
			this.value = nodevalue;
			this.next = next;
		}
		
		public Node<k,v> getNext() {
			return next;
		}
		
		public k getKey() {
			return key;
		}
		
		public v getValue() {
			return value;
		}
		
		// Overloaded setNext method to enable the use and testing of different parameter types
		/** Sets the next attribute of a node to a new node with the provided key and value. 
		 * 
		 * @param key
		 * @param value
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void setNext(k key, v value) {
			next = new Node (key, value, null);
		}
		
		/** Sets the next attribute of a node to the node n specified in the parameter.
		 * 
		 * @param n
		 */
		private void setNext(Node n) {
			next = n;
		}
		
		/** Returns a string representation of a Node<k,v> in the format "<k,v>".*/
		@Override
		public String toString() {
			return "<"+key.toString()+","+value.toString()+">";
		}
		
		/** Returns a string representation of a SLL starting with a Node<k,v> in the format "<k,v> <k,v> ..." */
		public String printBucket() {
			String result = this.toString() + " ";
			if (this.getNext() != null)
				result += this.getNext().printBucket();
			return result;
		}
	}
}

