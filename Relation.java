import java.util.Set;

/** Contract for the Binary Relation ADT supporting homogenous relations.
 * 
 * @author 1103086v
 *
 * @param <k>
 * @param <v>
 */
public interface Relation <k, v> {

	/** 1. tests whether the relation contains a given pair (k, v)*/
	public boolean contains(k key, v value);
	
	/** 2. given k, returns a set containing all values v such that the relation contains (k,v) */ 
	public Set<v> getValues(k key); 
	
	/** 3. given v, returns a set containing all values k such that the relation contains (k, v) */
	public Set<v> getKeys(v value);
	
	/** 4. makes the relation empty */
	public void clear();
	
	/** 5. adds a given pair (k, v) to the relation */
	public void insert (k key, v val);
	
	/** 6. removes a given pair (k, v) from the relation */
	public void remove (k key, v val);
	
	/** 7. given k, removes all pairs (k, v) from the relation */
	public void removeAllWithKey(k key);
	
	/** 8. given v, removes all pairs (k, v) from the relation */
	public void removeAllWithValue(v val);
	
	/** 9. renders the relation’s contents as a string, in a suitable format. */
	@Override
	public String toString();
}
