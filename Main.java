
public class Main {

	public static void main(String[] args) {
		
		RelationClass rel = new RelationClass();
		rel.insert("NL", "Norse");
		rel.insert("NL", "Greek");
		rel.insert("NL", "Macedonian");
		
		
		System.out.println(rel);
	}

}
