import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
	private String fileName;
	
	public Parser (String fileName) {
		this.fileName = fileName;
	}
	
	public int getNumOfLines() {
	
	int lines = 0;
	try {
		FileReader r = new FileReader(fileName);
		Scanner inFile = new Scanner(r);
		
		while (inFile.hasNextLine()) {
			lines++;
			inFile.nextLine();
		}
		
		r.close();
		inFile.close();
	}
	  catch (FileNotFoundException e) {
		System.out.println("FNF Exception");
	} catch (IOException e) {
		System.out.println("IO Exception");
	}
	return lines;
	}
	
	/*
	 * Processing method is tailored to the input file and assumes that
	 * keys and values are strings entered in the following format:
	 * 		key <space> value <newline>
	 * 
	 * Other file types, such as integers, could be handled by 
	 * checking whether the next string can be parsed as an integer (.parseToInt()) and
	 * catching the exception. 
	 */	
	@SuppressWarnings("unchecked")
	public void process(RelationClass relation) {
		String key = "";
		String value = "";
		
		try {
			FileReader r = new FileReader(fileName);
			Scanner inFile = new Scanner(r);
			
			while (inFile.hasNextLine()) {
				
				String parsedLine = inFile.nextLine();
				Scanner inLine = new Scanner(parsedLine);
				
				while (inLine.hasNext()) {
					key = inLine.next();
					value = inLine.next();
					relation.insert(key, value);
				}
				inLine.close();
			}
			r.close();
			inFile.close();
		}
		  catch (FileNotFoundException e) {
			System.out.println("FNF Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}
}
