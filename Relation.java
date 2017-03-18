import java.util.Set;

/** Contract for the Binary Relation ADT supporting homogenous relations.
 * 
 * @author 1103086v
 *
 * @param <k>
 * @param <v>
 */
public interface Relation <k, v> {

	/** 1. test whether the relation contains a given pair (x, y)*/
	public boolean contains(k key, v value);
	
	/** 2. given x, return a set containing all values y such that the relation contains (x,y) */ 
	public Set<v> getValues(k key); 
	
	/** 3. given y, return a set containing all values x such that the relation contains (x, y) */
	public Set<v> getKeys(v value);
	
	/** 4. make the relation empty */
	public void clear();
	
	/** 5. add a given pair (x, y) to the relation */
	public void insert (k key, v val);
	
	/** 6. remove a given pair (x, y) from the relation */
	public void remove (k key, v val);
	
	/** 7. given x, remove all pairs (x, y) from the relation */
	public void removeAllWithKey(k key);
	
	/** 8. given y, remove all pairs (x, y) from the relation */
	public void removeAllWithValue(v val);
	
	/** 9. render the relation’s contents as a string, in a suitable format. */
	@Override
	public String toString();
}
