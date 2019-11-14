using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			try
			{
				TriDiagonalMatrix m1 = new TriDiagonalMatrix(2);  
				TriDiagonalMatrix m2 = new TriDiagonalMatrix(3);
				TriDiagonalMatrix m3 = m1 + m2;
				m3.CompactPrint();
			}
			catch (ArgumentException e) 
			{
				Console.WriteLine(e);
			}
		}
	}
}