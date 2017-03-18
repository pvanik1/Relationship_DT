import java.util.Set;

public interface Relation<Node, k, v> {

	// 1. test whether the relation contains a given pair (x, y)
	// public boolean contains(Node node);
	
	// 2. given x, return a set containing all values y such that the relation
	// 	  contains (x,y)
	// public getValues(k key); 
	
	// 3. given y, return a set containing all values x such that the relation
	// contains (x, y)
	// public getKeys(v value);
	
	//4. make the relation empty
	// public clear()
	
	//5. add a given pair (x, y) to the relation
	// insert (k key, v val)
	
	//6. remove a given pair (x, y) from the relation
	// remove (k key, v val)
	
	// 7. given x, remove all pairs (x, y) from the relation
	
	// 8. given y, remove all pairs (x, y) from the relation
	
	// 9. render the relation’s contents as a string, in a suitable format.
}
