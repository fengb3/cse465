using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix 
{
	public class Solver 
	{
		private TriDiagonalMatrix m;
		private float [] b;
		
		// Constructor
		// Initialize m with value matrix and b with value v
		// Throw an ArgumentException if the size of the matrix is not equal to
		//   the length of vector v. The exception message should have the format:
		//   "Mismatched sizes: The size of the matrix is {0} and the length of the vector is {1}"
		public Solver(TriDiagonalMatrix matrix, float [] v) 
		{	
			if (matrix.N != v.Length) {
				throw new System.ArgumentException(String.Format("Mismatched sizes: The size of the matrix is {0} and the length of the vector is {1}", matrix.N, v.Length), "matrix");
			}
			this.m = matrix;
			this.b = v;
		}
		
		// Returns an array representing the solution to the system of linear equations 
		//   defined by m and b.
		// Implement the algorithm specified here:
		//   http://mathfaculty.fullerton.edu/mathews/n2003/Tri-DiagonalMod.html
		//   in the "Mathematica Subroutine" section.
		// The implementation can be tested using Example 1 provided on the same web page.
		public float [] Solve() 
		{
			// TODO
		}
	}
}