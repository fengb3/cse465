using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			const int N = 10;
			TriDiagonalMatrix m1 = new TriDiagonalMatrix(N);         
			TriDiagonalMatrix m2 = new TriDiagonalMatrix(N);
			
			int x = 1;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (Math.Abs(row-col) <= 1) {
						m1.set(row, col, 1);
						m2.set(row, col, x);
						x++;
					}
				}
			}
			
			TriDiagonalMatrix m3 = m1 + m2;
						
			Console.WriteLine("m1:");
			foreach (int v in m1) {
				Console.Write(v + " ");
			}
			Console.WriteLine();

			Console.WriteLine("m2:");
			foreach (int v in m2) {
				Console.Write(v + " ");
			}
			Console.WriteLine();		
            
			Console.WriteLine("m3:");		
			foreach (int v in m3) {
				Console.Write(v + " ");
			}
			Console.WriteLine();
			
			Console.WriteLine("m3:");	
			TriDiagonalMatrixEnumerator ie = m3.GetEnumerator();
			while (ie.MoveNext()) {
				Console.Write(ie.Current + " ");
			}
			Console.WriteLine(); 
		}
	}
}