
public class Main {

	public static void main(String[] args) {
		
		RelationClass rel = new RelationClass();
		rel.insert("NL", "Norse");
		//rel.insert("NL", "Greek");
		//rel.insert("NL", "Macedonian");
		//rel.insert("NL", "Greek");
		//rel.insert("DE", "German");
		// if I insert DE German, it overwrites - both get hashed to the samee
		
		
		//System.out.println(rel.contains("ITS", "Italian"));
		//System.out.println(rel.getValues("IT"));
		//System.out.println(rel.getKeys("English"));
		

		System.out.println(rel);
		rel.remove("NL", "Norse");
		System.out.println(rel);
	}

}
