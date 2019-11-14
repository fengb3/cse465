using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			try
			{
				TriDiagonalMatrix m = new TriDiagonalMatrix(6);  
				m.set(2, 7, 3);
				m.CompactPrint();
			}
			catch (ArgumentException e) 
			{
				Console.WriteLine(e);
			}
		}
	}
}