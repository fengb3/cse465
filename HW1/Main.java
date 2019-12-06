import java.io.*;

public class Main {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.print("invalid input \n");
		}else{
		System.out.println(args[0]);
		String fileName =  args[0];
		String line = null;
		SymbolTable table = new SymbolTable();
		// file reader
		try {
			FileReader fr = new FileReader(fileName);
			
			BufferedReader br = new BufferedReader(fr);		
			
			while((line = br.readLine()) != null) {
				
				//System.out.println(line);

					table.add(line);
							 
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.print("Error: file opening "+fileName+".");
			
		} catch (IOException e) {
			
			System.out.println("error: file Reading "+fileName+" .");
		}
		}
		
	}
}
