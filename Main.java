
public class Main {

	public static void main(String[] args) {
		
		/* The operations' testing order is not the same order as the order in the assignment specification
		 * for a more logical sequential testing order. The operations are numbered as in the specification. */
		
		RelationClass rel = new RelationClass();
		
		System.out.println("The starting point for testing is myfile.txt."
				+ "It currently contains the following entries:\n"
				+ "BE French\n"
				+ "FR French\n"
				+ "IT Italian\n"
				+ "BE Flemish\n");
		
		/* 9. renders the relation’s contents as a string, in a suitable format. */
		System.out.println("9. renders the relation’s contents as a string, in a suitable format");
		System.out.println("Method: toString()");
		System.out.println(rel);
		
		
		/* 1. test whether the relation contains a given pair (k, v)*/
		System.out.println("1. Returns true if the relation contains a given pair (k,v), otherwise prints false.");
		System.out.println("Method: contains()");
		System.out.println("First test: (FR, French)");
		System.out.println(rel.contains("FR", "French"));
		System.out.println("Second test: (PL, Polish)");
		System.out.println(rel.contains("PL", "Polish") + "\n");
		
		
		/* 5. add a given pair (k, v) to the relation */
		System.out.println("5. add a given pair (k, v) to the relation");
		System.out.println("Method: insert(key, value)");
		System.out.println("test: add (FR, Occitan), (BE, German), (KZ, Russian) and (RU, Russian)");
		rel.insert("FR", "Occitan");
		rel.insert("BE", "German");
		rel.insert("RU", "Russian");
		rel.insert("KZ", "Russian");
		System.out.println(rel);
		
		
		
		/* 2. given k, return a set containing all values v such that the relation contains (k,v) */ 
		System.out.println("2. given k, returns a set containing all values v such that the relation contains (k,v)");
		System.out.println("Method: getValues(key)");
		System.out.println("Test: return values for BE");
		System.out.println(rel.getValues("BE") + "\n");
		
		
		/* 3. given v, return a set containing all values k such that the relation contains (k, v) */
		System.out.println("3. given v, return a set containing all values k such that the relation contains (k, v)");
		System.out.println("Method: getKeys(value)");
		System.out.println("Test: return keys for French");
		System.out.println(rel.getKeys("French") + "\n");
		
		
		/* 6. remove a given pair (k, v) from the relation */
		System.out.println("6. remove a given pair (k, v) from the relation");
		System.out.println("Method: remove(key, value)");
		System.out.println("Test: remove (BE, Flemish) and (FR, French)");
		rel.remove("BE", "Flemish");
		rel.remove("FR", "French");
		System.out.println(rel);
		
		
		/* 7. given k, remove all pairs (k, v) from the relation */
		System.out.println("7. given k, remove all pairs (k, v) from the relation");
		System.out.println("Method: removeAllWithKey(key)");
		System.out.println("Test: Removing all BE pairs.");
		rel.removeAllWithKey("BE");
		System.out.println(rel);
		
		
		/* 8. given v, remove all pairs (k, v) from the relation */
		System.out.println("8. given v, remove all pairs (k, v) from the relation");
		System.out.println("Method: removeAllWithValue(value)");
		System.out.println("Test: Removing all Russian pairs.");
		rel.removeAllWithValue("Russian");
		System.out.println(rel);
		
		/* 4. make the relation empty */
		System.out.println("4. makes the relation empty");
		System.out.println("Method: clear()");
		System.out.println("Test: Relation is cleared and print returns 'Relation is empty'");
		rel.clear();
		System.out.println(rel);
	}

}
