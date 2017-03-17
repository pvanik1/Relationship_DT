import java.util.Set;

public interface Relation<Node, k, v> {
	
	// TODO reduce the overloading methods so they just pass a converted paramater
	// as a parameter to the original (e.g. k key) method

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
}
