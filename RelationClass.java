import java.io.*;
import java.util.*;

public class RelationClass<k,v> implements Relation {

	private Node<k,v>[] buckets;
	private final String FILE_NAME = "myfile.txt";
	
	/** Arbitrary number co-determining the number of buckets. Seeks to achieve a compromise
	 *  of hash table collision and occupancy. Based on a common hash table load factor of 0.5-0.75.*/
	private final double BUCKET_MULTIPLIER = 1.5;
	
	/** Relation class implemented as a has table.*/
	@SuppressWarnings("unchecked")
	public RelationClass() {
		
		Parser pars = new Parser(FILE_NAME);
		int bucketlength = 0;
		bucketlength = (int) (pars.getNumOfLines()*BUCKET_MULTIPLIER);
		buckets = (Node<k,v>[]) new Node<?,?>[bucketlength];
		pars.process(this);
	}
	
	// Hash function returns an array index smaller than bucketlength for each key
	private int hash (k key) {
		return Math.abs(key.hashCode())	% buckets.length;
	}
	
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				result += "[" + i + "]" + " ";
				result += buckets[i].printBucket() + "\n";
			}
		}
		return result;
	}
	
	public void insert (k key, v val) {
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		
		while (curr != null) {	
			if (key.equals(curr.getKey())) {

				if (val.equals(curr.getValue())) {
					System.err.println("Pair " + key + " " + val + " already exists");
					return;
				}
				else if (curr.getNext() == null) {
						curr.setNext(key, val);
						return;
				}
			}

			else { 
				if (curr.getNext() == null) {
						curr.setNext(key, val);
						break; 
				}	
			}
			curr = curr.getNext();
		}
		buckets[b] = new Node<k,v>(key, val, null);
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
	public boolean contains(String keyString, String valueString) {
		k key = (k) keyString;
		v value = (v) valueString;
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
	
	// overloaded getValues method for testing string inputs
	
	public Set<v> getValues(String keyString) {
		k key = (k) keyString;
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
	
	// 3. given y, return a set containing all values x such that the relation
		// contains (x, y)
	
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
	
	// overloaded getKeys() method
	
	public Set<v> getKeys(String valuestring) {
		v value = (v) valuestring;
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
	
	
	// --- Node code ---
	
	/**
	 * Inner node class for nodes of the hash table
	 * @author 1103086v
	 *
	 * @param <k> key
	 * @param <v> value
	 */
	private static class Node<k,v> {
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
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void setNext(k key, v value) {
			next = new Node (key, value, null);
		}
		
		@Override
		public String toString() {
			return "<"+key.toString()+","+value.toString()+">";
		}
		
		/** Prints SLL starting with a node */
		public String printBucket() {
			String result = this.toString() + " ";
			if (this.getNext() != null)
				result += this.getNext().printBucket();
			return result;
		}
	}

	

	
	
}

