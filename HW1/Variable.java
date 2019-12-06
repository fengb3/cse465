/*
 * Author 		Bohan Feng
 * Description 	this is HW1 for cse 465
 * 				this class represent 3 types of Variables in L language
 * 
 * vType	JavaType
 * i		integer
 * b		boolean
 * s		string
 * n		null
 */

public class Variable {
	
	private char[] types = {'i','s','b','n'};
	
	private char vType;
	
	private String val;
	
	private String name;
	
	// i don't want to use this
	public Variable() {
		name = null;
		val = null;
		vType = 'n';
	}
	
	public Variable(String name, String var) {
		this.name = name;
		this.val = var;
		this.vType = judgeType(var);	
	}
	
	public char judgeType(String val) {
		if(val.equals("TRUE")||val.equals("FALSE")) {
			return 'b';
		}else if(val.contains("\"")) {
			return 's';
		}else {
			return 'i';
		}
	}
/*
 * getter
 */
	public char getType() {
		return this.vType;
	}
	
	public String getVal() {
		return this.val;
	}
	
	public String getName() {
		return this.name;
	}
/*
 * show and print the content of a variable
 */
	public void print() {
		System.out.println(this.val);
	}
	
	public void show() {
		System.out.println(getName()+ " " +getType() + " " + getVal());
	}
/*
 * do some thing to variables by operators
 */
	/*
	 * add two strings or integers
	 */
	public void addEqual(Variable b) {
		if(this.getType()!=b.getType()) {
			System.out.println("Cannot add different tpyes of variable");
			return;
		}else if(this.getType()=='b'||b.getType()=='b') {
			System.out.println("Cannot add Boolean variable");
			return;
		}else if(this.getType()=='i'&&b.getType()=='i') {
			int num1 = Integer.parseInt(this.getVal());
			int num2 = Integer.parseInt(b.getVal());
			this.setVal(num1+num2+"");
		}else if(this.getType()=='s'&&b.getType()=='s') {
			String str1 = this.getVal().substring(0, this.getVal().length()-1);
			String str2 = b.getVal().substring(1);
			this.setVal(str1+str2);
		}
	}
	
	/*
	 * add a VString and a string or a Vint and a int
	 */
	public void addEqual(String str) {
		if(this.getType() == 'b'||judgeType(str) == 'b') {
			System.out.println("Cant not add boolean");
		}else if(judgeType(str) == 'i'&&this.getType()=='i') {
			int num1 = Integer.parseInt(this.getVal());
			int num2 = Integer.parseInt(str);
			this.setVal(num1+num2+"");
		}else if(judgeType(str) == 's'&&this.getType()=='s') {
			String str1 = this.getVal().substring(0, this.getVal().length()-1);
			String str2 = str.substring(1);
			this.setVal(str1+str2);
		}
	}
	
	/*
	 * times 2 integers
	 */
	public void timeEqual(Variable b) {
		 if(this.vType != 'i' || b.vType != 'i') {
			 System.out.println("Cannot times boolean or string");
			 return;
		 }else {
			 int num1 = Integer.parseInt( this.getVal());
			 int num2 = Integer.parseInt( b.getVal());
			 this.setVal(num1*num2+"");
		 }
	}
	
	/**
	 * this variable times equal a integer string
	 * @param i
	 */
	public void timeEqual(String i) {
		if(this.vType != 'i' || !SymbolTable.isNumeric(i)) {
			 System.out.println("Cannot times boolean or string");
			 return;
		 }else {
			 int num1 = Integer.parseInt( this.getVal());
			 int num2 = Integer.parseInt(i);
			 this.setVal(num1*num2+"");
		 }
	}
	
	/*
	 * and 2 booleans 
	 */
	public void andEqual(Variable b) {
		if(this.vType != 'b' || b.vType != 'b') {
			 System.out.println("Cannot operate with int and string");
			 return;
		 }else {
			 boolean bool1 = (this.getVal().equals("TRUE"))? true : false;
			 boolean bool2 = (b.getVal().equals("TRUE"))? true : false;
			 String output = bool1&bool2 ? "TRUE" : "FALSE" ;
			 this.setVal(output);
		 }
	}
	/**
	 * this variable andequal with a bool string
	 * @param b
	 */
	public void andEqual(String b) {
		if(this.vType != 'b' || !(b.equals("TRUE")||b.equals("FALSE"))) {
			 System.out.println("Cannot operate with int and string");
			 return;
		 }else {
			 boolean bool1 = (this.getVal().equals("TRUE"))? true : false;
			 boolean bool2 = (b.equals("TRUE"))? true : false;
			 String output = bool1&bool2 ? "TRUE" : "FALSE" ;
			 this.setVal(output);
		 }
	}
	
	/*
	 * gives a value to this variable
	 */
	public void equal(Variable b) {
		this.setVal(b.getVal());
	}
	
	public void equal(String newVal) {
		this.val = newVal;
		if(val.equals("TRUE")||val.equals("FALSE")) {
			vType = 'b';
		}else if(val.contains("\"")) {
			vType = 's';
		}else {
			vType = 'i';
		}
	}
	
	/*
	 * set a value for this variable
	 */
	public void setVal(String newVal) {
		this.val = newVal;
		if(val.equals("TRUE")||val.equals("FALSE")) {
			vType = 'b';
		}else if(val.contains("\"")) {
			vType = 's';
		}else {
			vType = 'i';
		}
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	
	public static void main(String args[]) {
		Variable i = new Variable("A","1350");
		Variable s = new Variable("B", "\"teeet\"");
		Variable b = new Variable("C", "30");
		
		i.timeEqual("2");
		s.addEqual("\"booook\"");
		
		i.show();
		s.show();
	}
}
