using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix {
	public class Tester {
		public static void Main(String [] args) {
			int N = 31;
			
			TriDiagonalMatrix m = new TriDiagonalMatrix(N);
			float [] b = new float[N];
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					int diff = Math.Abs(row-col);
					if (diff == 0)
					{						
						m.set(row, col, 2);
					}
					else if (diff == 1)
					{
						m.set(row, col, 1);
					}
				}
				b[row] = (float) 16.0 - (float) Math.Abs((float) (row+1) - (float)16.0);
			}
						
			try
			{
				Solver s = new Solver(m, b);  
				float [] x = s.Solve();
								
				Console.Write("x: ");
				for (int i = 0; i < N; i++)
				{ 
					Console.Write((int)Math.Round(x[i], 0) + " ");
				}
				Console.WriteLine();
			}
			catch (ArgumentException e) 
			{
				Console.WriteLine(e);
			}
		}
	}
}