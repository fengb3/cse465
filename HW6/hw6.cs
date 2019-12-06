using System;

namespace hw6 {
	class hw6 {
		public static int Adder(params int[] args)
		{
			int sum = 0;
			
			for (int i = 0; i < args.Length; i++){
				sum += args[i];
			}
			
			return sum;
		}
		
		public static int Main(String [] args)
		{
			Console.WriteLine("Sum 1 is {0}", Adder(1,2,3,4,5,6));
			Console.WriteLine("Sum 2 is {0}", Adder(1,5));
			Console.WriteLine("Sum 3 is {0}", Adder());
			
			return 0;
		}
	}
}
