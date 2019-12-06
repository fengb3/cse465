public class hw6 {

	public static int adder(int ...args)
	{
		int sum = 0;
		
		for (int i : args){
			sum += i;
		}
		
		return sum;
	}
	public static void main(String [] args)
	{
		System.out.println("Sum 1 is " + adder(1,2,3,4,5,6));		
		System.out.println("Sum 2 is " + adder(1,5));		
		System.out.println("Sum 3 is " + adder());
	}
}
