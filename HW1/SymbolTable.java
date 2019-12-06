
public class SymbolTable {
	
	private static Variable[] vars;
	private static String[] linearr;

	private static int varSize = 0;
	private int lineNum;
	
	private final String[] operator = {"=","+=","*=","&="};// no need for this one, but i what to keep this array to remind what are operators
	private final static String[] keyWords = {"PRINT","FOR"};
	/*
	 * constructor
	 */
	SymbolTable(){
		varSize = 0; 
		lineNum = 0;
		vars = new Variable[8];
	}
	
	/*
	 * add the line to content
	 */
	void add(String line) {
		if(!line.equals("")) {
			if(line.contains("\"")){
				linearr = splitStringLine(line);
			}else{
				linearr = splitOtherLine(line);
			}
			lineNum++;
			read();
		}
	}
	
	/**
		helper method that split a line into a array when a 
	 */
	String[] splitStringLine(String line){
		int q1 = line.indexOf("\""); // index of first quotation mark
		int q2 = line.indexOf("\"",q1+1); // index of second quotation mark
		int len = spaceCountOutsideQuota(line,q1,q2);
		String[] re = new String[len+1];
		
		int index = 0;
		
		String[] arrBegin = line.substring(0, q1-1).split(" ");
		String[] arrEnd = line.substring(q2+2).split("");
		
		for(String s : arrBegin) {
			re[index] = s;
			index++;
		}
		re[index] = line.substring(q1,q2+1);
		index++;
		for(String s : arrEnd) {

			re[index] = s;
			index++;
		}

		return re;

	}
	/**
	 * helper method that tells how may space out side the quotation mark mark of a string
	 * @param line
	 * @param q1
	 * @param q2
	 * @return
	 */
	static int spaceCountOutsideQuota(String line, int q1, int q2) {
		int count = 0;	
		char[] arr1 = line.substring(0, q1).toCharArray();
		char[] arr2 = line.substring(q2).toCharArray();
		for(char c : arr1) {
			if(c==' ')
				count++;
		} 
		for(char c : arr2) {
			if(c==' ')
				count++;
		}
		return count;
	}

	String[] splitOtherLine(String line){
		return line.split(" ");
	}
	
	/*
	 * print some of the content
	 */
	void debugger() {
		for(int i = 0; i< varSize ; i++) {
			System.out.println(vars[i].getName()+" "+ vars[i].getVal()+" "+vars[i].getType());
		}
	}
	
	/*
	 * read the line
	 */
	void read() {
		if(isKeyWord()) {
			if(linearr[0].equals("PRINT")) {
				if(getVar(linearr[1])==-1) {
					System.out.println("Error: Variable "+linearr[1]+" does not exist [line :"+lineNum+"](t3)");//error when printing a non-exist variable
				}else{
					System.out.println(linearr[1]+"="+vars[getVar(linearr[1])].getVal());
				}
			}else if(linearr[0].equals("FOR")){
				// do something of FOR
				
				int forTimes = Integer.parseInt(linearr[1]);
				int lineNumsInEachFor = countSimiCon(linearr);
				
				String[][] forLines = noForAll(linearr); 
				
				for(int i = 0; i< forLines.length ;i++ ) {
					linearr = forLines[i];
					operate();
				}
				
			}else {
				//other key words
			}
		}else {
			// read the variable and get the value
			if(ifVarName(linearr[2])&&!ifExistVar(linearr[2])) {
				System.out.println("Error: Variable "+linearr[2]+" does not exist [Line: "+lineNum+"](t1)");// error msg with not defined variable
			}else {
				operate();
			}
		}
	}
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static String[][] noForAll(String[] arr) {
		int forCount = 0;
		for(String s : arr) {
			if(s.equals("FOR"))
				forCount ++ ;
		}
		
		for(int i = 0; i< forCount ;i ++) {
			int[] brace = findBrace(arr);
			arr = onForOnce(arr, brace);
		}
		
		int lines = SymbolTable.countSimiCon(arr);
		
		String[][] re = new String[lines][4];
		
		int index = 0;
		for(int i = 0; i < lines ; i++) {
			for(int j =0; j < 4; j++) {
				re[i][j] = arr[index];
				index++;
			}
		}
		
		return re;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	static int[] findBrace(String[] arr) {
		int[] re = new int[2];		
		int index = 0;
		
		for(String s : arr) {
			if(s.equals("{")) {
				re[0] = index;
			}
			if(s.equals("}")) {
				re[1] = index;
				break;
			}
			index++;
		}
		return re;
	}
	
	/**
	 * 
	 * @param arr
	 * @param brace
	 * @return
	 */
	static String[] onForOnce(String[] arr, int[] brace) {
		String content = "";
		int fortimes = Integer.parseInt(arr[brace[0]-1]);
		int newlen = arr.length - (brace[1]-brace[0]) - 2 +(brace[1]-brace[0]-1)*fortimes;
		for(int i = 0; i < brace[0]-2; i++) {
			content += arr[i]+" ";
		}
		
		
		for(int j=0; j<fortimes; j++) {
			for(int k=brace[0]+1; k<brace[1]; k++) {
				content += arr[k]+" ";
			}
		}
		
		for(int i = brace[1]+1; i<arr.length;i++) {
				content += arr[i]+" ";
			}	

		return content.split(" ");
	}
	
	public static int countSimiCon(String[] arr) {
		int count = 0;
		for(String s : arr) {
			if(s.equals(";"))
			count++;
		}
		return count;
	}
	
	/*
	 * tells if the head of list is one of the key words
	 */
	static boolean isKeyWord() {
		boolean flag = false;
		for(String str : keyWords ) {
			if(str.equals(linearr[0]))
				flag = true;
		}
		return flag;
	}
	
	
	/*
	 * =, +=, *=, &=
	 * 
	 * operate what is in linearr;
	 */
	
	void operate() {
		String op = linearr[1];
		// EQUALS
		if(op.equals("=")) {
			if(ifExistVar(linearr[0])) {
				if(ifVarName(linearr[2])) {
					vars[getVar(linearr[0])] .equal(vars[getVar(linearr[2])]);
				}else if(!ifVarName(linearr[2])) {
					vars[getVar(linearr[0])] .setVal(linearr[2]);
				}
			}else {
				Variable temp = null;
				if(ifVarName(linearr[2])) {
					// add the value of var to var of linearr[0]
					 temp = new Variable(linearr[0], vars[getVar(linearr[2])].getVal());
				}else if(!ifVarName(linearr[2])) {
					// add the the value to var of linarr[0]
					temp = new Variable(linearr[0], linearr[2]);
				}	
				extend();
				vars[varSize] = temp;
				varSize ++;
			}
			// ADD EQUALS
		}else if (op.equals("+=")){
			if(ifExistVar(linearr[0])) {
				if(ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].addEqual(vars[getVar(linearr[2])]);
				}else if(!ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].addEqual(linearr[2]);
				}
			}else {
				System.out.println("Variable "+linearr[0]+" dose not Exist [line: "+lineNum+"](t2)");// error msg with undefined variable to addequale
			}
			// TIME EQUALS
		}else if (op.equals("*=")){
			if(ifExistVar(linearr[0])) {
				if(ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].timeEqual(vars[getVar(linearr[2])]);
				}else if(!ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].timeEqual(linearr[2]);
				}
			}else {
				System.out.println("Variable "+linearr[0]+" dose not Exist [line: "+lineNum+"](t2)");// error msg with undefined variable to timesequale
			}
			// AND EQUAL
		}else if (op.equals("&=")){
			if(ifExistVar(linearr[0])) {
				if(ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].andEqual(vars[getVar(linearr[2])]);
				}else if(!ifVarName(linearr[2])) {
					vars[getVar(linearr[0])].andEqual(linearr[2]);
				}
			}else {
				System.out.println("Variable "+linearr[0]+" dose not Exist [line: "+lineNum+"](t2)");// error msg with undefined variable to andequale
			}
		}
	}
	
	void extend() {
		if(varSize == vars.length) {
			Variable[] temp = new Variable[varSize*2];
			int i =0;
			for(Variable a : vars) {
				temp[i] = a;
				i++;
			}
		}
	}
	
	int getVar(String varName) {
		for(int i = 0; i < varSize; i++) {
			if(vars[i].getName().equals(varName)) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * tells if there are a variable exist in symbol table by var name
	 */
	public static boolean ifExistVar(String varName) {
		boolean flag = false;
		for(int i = 0; i< varSize;i++) {
			if(vars[i].getName().equals(varName)) {
				flag = true;
			}
		}
		return flag;
	}
	
	/*
	 * tells if a stirng can be represent a variable name
	 */
	boolean ifVarName(String m) {
		boolean notBool = !m.equals("TRUE")&&!m.equals("FALSE");
		boolean notInt = !SymbolTable.isNumeric(m);
		boolean notString = !m.contains("\"");
		return notBool&&notInt&&notString;
	}
	
	public static boolean isNumeric(String str)
	  {
	    try
	    {
	      double d = Double.parseDouble(str);
	    }
	    catch(NumberFormatException nfe)
	    {
	      return false;
	    }
	    return true;
	  }
	
	void addVar(String varName,String VarValue) {
		extend();
		vars[varSize] = new Variable(varName, VarValue);
		varSize++;
	}
	
	
	
	// main for test functions 
	public static void main(String[] args) {
		SymbolTable t = new SymbolTable();
		
		//t.add("A = \"AB\" ;");
		//t.add("FOR 4 { A += A ; }");t.debugger();		
		//t.add("PRINT A ;");
		
		//t.add("");
		//t.add("A += \" !\" ;");
		//t.add("PRINT A ;");
		
		//t.add("a = 40 ;");
		//t.add("b *= 2 ;");
		
		//t.add("PRINT b ;");
		//t.showOut();
		
		//System.out.println(isKeyWord());
		

	}
	
	
}
