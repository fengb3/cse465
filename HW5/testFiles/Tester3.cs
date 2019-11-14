using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			try
			{
				TriDiagonalMatrix m = new TriDiagonalMatrix(-2);  
				m.CompactPrint();
			}
			catch (ArgumentException e) 
			{
				Console.WriteLine(e);
			}
		}
	}
}