import java.io.*;
import java.util.*;

public class RelationClass<k,v> implements Relation {

	private Node<k,v>[] buckets;
	private final String FILE_NAME = "myfile.txt";
	
	/** Arbitrary number co-determining the number of buckets. Seeks to achieve a compromise
	 *  of hash table collision and occupancy. Based on a common hash table load factor of 0.5-0.75.*/
	private final double BUCKET_MULTIPLIER = 1.50;
	
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
		String lineResult = "";
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
			if (key.equals(curr.key)) {
				if (curr.value == val)
					return;
				else 
					buckets[b].setNext(key, val);
			}
			curr = curr.next;
		}
		buckets[b] = new Node<k,v>(key, val, buckets[b]);
		System.out.println("inserted");
	}
	
	
	
	
	/* default insert for HT
	 * 
	public void insert (k key, v val) {
		int b = hash(key);
		Node<k,v> curr = buckets[b];
		while (curr != null) {
			if (key.equals(curr.key)) {
				curr.value = val;
				return;
			}
			curr = curr.next;
		}
		buckets[b] = new Node<k,v>(key, val, buckets[b]);
	} */
	
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
		
		private Node<k,v> getNext() {
			return next;
		}
		
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

