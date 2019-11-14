using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			try
			{
				TriDiagonalMatrix m = new TriDiagonalMatrix(6);  
				Console.WriteLine(m.get(8, 3));
			}
			catch (ArgumentException e) 
			{
				Console.WriteLine(e);
			}
		}
	}
}