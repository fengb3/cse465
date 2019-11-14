using System;
using System.Collections.Generic;
using System.Collections;

namespace TriDiagonalMatrix 
{
	// This enumerator iterates over the whole tridiagonal matrix.
	// This is done in a row major fashion, starting with element at row 0 and column 0,
	// and includes all N x N elements of the matrix (i.e., includes the zeros
	// below and above the band).
	// For example, for the tridiagonal matrix
	//		1  2  0  0  0  0
	//		3  4  5  0  0  0
	//		0  6  7  8  0  0
	//		0  0  9 10 11  0
	//		0  0  0 12 13 14 
	//		0  0  0  0 15 16
	// stored as:
	//		d: 1 4 7 10 13 16
	//		a: 3 6 9 12 15
	//		c: 2 5 8 11 14
	//	the enumerator is supposed to iterate over the matrix as follows:
	//		1 2 0 0 0 0 3 4 5 0 0 0 0 6 7 8 0 0	0 0 9 10 11 0 0 0 0 12 13 14 0 0 0 0 15 16
	public class TriDiagonalMatrixEnumerator : IEnumerator 
	{
		// TODO
		// add your private data members here	
		private TriDiagonalMatrix matrix;
		private int row;
		private int col;

		public TriDiagonalMatrixEnumerator(TriDiagonalMatrix matrix) 
		{
			this.matrix = matrix;
			Reset();
		}
		
		public void Reset() 
		{
			row = -1;
			col = -1;
		}
		
		public bool MoveNext() 
		{
			if(row == -1){		
				row++;
			} 
			if (col == this.matrix.N -1) {
				col = 0;
				row++;
			}
			else {
				col++;
			}

			return row < this.matrix.N;
		}
		
		object IEnumerator.Current 
		{
			get {
				return Current;
			}
		}
		
		public float Current 
		{
			get {
				try {
					if (Math.Abs(row - col) > 1) {
						return 0;
					}
					return matrix.get(row, col);
				}
				catch (IndexOutOfRangeException) {
					throw new InvalidOperationException();
				}
			}
		}
	}
	
	public class TriDiagonalMatrix : IEnumerable 
	{
		/* 
		Adapted from: http://mathfaculty.fullerton.edu/mathews/n2003/Tri-DiagonalMod.html
		
		A tridiagonal matrix is a sparse matrix, more specifically a band matrix.
		An N x N matrix A is called a tridiagonal matrix if a[i,j] = 0 whenever i + 1 <= j or j + 1 <= i.
		
		Example:
			The following is a 6 x 6 tridiagonal matrix.
			1  2  0  0  0  0
			3  4  5  0  0  0
			0  6  7  8  0  0
			0  0  9 10 11  0
			0  0  0 12 13 14 
			0  0  0  0 15 16
			
		Since tridiagonal matrices are sparse, it is important to devise a compact way to store them.
		The idea is to only store:
			- the elements on the main diagonal in an array d;
			- the elements directly below the main diagonal in an array a; and	
			- the elements directly above the main diagonal in an array c.
		With this representation, assuming array indices start at 0, an N x N tridiagonal matrix would
		have the format:
			0,0		1,0		2,0
			a[0] 	c[0] 	0 		0		...		0		0		0

			1,0 	1,1		1,2		1,3
			d[0]	a[1]	c[1]	0		...		0		0		0

			2,0		2,1		2,2		2,3
			0 		d[1]	a[2]	c[2]	...		0		0		0
			...								...		...
			0 		0		0		0		...		a[N-3]	d[N-2]	c[N-2]
			0		0		0		0		...		0		a[N-2]	d[N-1]
			
		Instead of storing N * N elements, we only need to store 3*N - 2 elements. For instance, for a
		31 x 31 matrix, there are 961 elements, 870 of which are zeros. With this approach, we only need 
		to store the 91 elements on the main diagonal and directly above and below it.
				
		Tridiagonal matrices are useful in specifying tridiagonal linear systems of equations,
		which have many applications, especially in physics (e.g., multistage countercurrent extractor).
		*/
		
		
		public float [] d; // main diagonal
		public float [] a; // subdiagonal: directly below the main diagonal
		public float [] c; // superdiagonal: directly above the main diagonal
		// Note: DO NOT add extra data members!

		// Construct an NxN TriDiagonal Matrix, initialized to 0
		// Throws an ArgumentException if N is a negative number.
		// The exception message should include the value of N.
		public TriDiagonalMatrix(int N) 
		{
			// check if N is greater than 0
			if (N <= 0) {
				throw new System.ArgumentException("N must be positive number", "N");
			}
            // set up array size for each diagonal
			d = new float[N];
			a = new float[N - 1];
			c = new float[N - 1];
		}
		
		// N represents the size of the N x N matrix.
		// Note that N is a property, not a data member!
		public int N 
		{ 
			get {
				return d.Length;
			}
		}
		
		// Returns an upper tridiagonal matrix that is the summation of tridiagonal 
		//   matrices x and y.
		// Throws an ArgumentException if x and y are incompatible. The exception
		//   message should include the sizes of x and y.
		public static TriDiagonalMatrix operator + (TriDiagonalMatrix x, TriDiagonalMatrix y) 
		{
			// check if 2 martix are compatible
			if (x.N != y.N) {
				throw new System.ArgumentException("Two matrix are incompatible", "x");
			}

			TriDiagonalMatrix re = new TriDiagonalMatrix(x.N);

			for(int i = 0; i < x.N - 1; i++){
				re.d[i] = x.d[i] + y.d[i];
				re.a[i] = x.a[i] + y.a[i];
				re.c[i] = x.c[i] + y.c[i];
			}

			re.d[x.N - 1] = x.d[x.N - 1] + y.d[x.N - 1];

			return re;
		}
		
		// Sets the value at index [row][col] to val.
		// Throws an ArgumentException if [row][col] is an invalid index to alter,
		// 	  i.e., not an element on or directly below/above the main diagonal.
		// Throws an ArgumentException if row or col are out of bound.
		// The exception messages should include the out of bound index (row or col)
		//    or the invalid row and col combination to alter.
		public void set(int row, int col, int val) 
		{
			// check if row and col are valid num
			if (Math.Abs(row - col) > 1){
				throw new System.ArgumentException(String.Format("{1}, {0} are not valid col and row", row, col), "col");
			}
			if(row == col){
				d[row] =  val;
			} else if(row < col){
				a[row] = val;
			} else if (row > col) {
				c[col] = val;
			}		
		}
		
		// Returns the value at index [row][col]
		// Throws an ArgumentException if row or col are out of bound.
		// The exception message should include the out of bound parameter.
		public float get(int row, int col) 
		{
            // check if row and col are valid num
			if (row > this.N - 1){
				throw new System.ArgumentException(String.Format("{0} row is out of bound", row), "row");
			}
			if (col > this.N - 1){
				throw new System.ArgumentException(String.Format("{0} col is out of bound", row), "col");
			}
			if(row == col){
				return d[row];
			} else if(row < col){
				return a[row];
			} else {
				return c[col];
			}	
		}
		
		// Returns a string that prints the d, a, c arrays for the current matrix in the format
		// illustrated by the following example:
		//    d: 1 4 7 10 13 16 19 22 25 28
		//    a: 3 6 9 12 15 18 21 24 27
		// 	  c: 2 5 8 11 14 17 20 23 26	
		public string CompactPrint() 
		{
			string re = "";
			re += "d: ";
			for(int i = 0; i < d.Length; i++){
				re += d[i] + " ";
			}
			re += "\na: ";
			for(int i = 0; i < a.Length; i++){
				re += a[i] + " ";
			}
			re += "\nc: ";
			for(int i = 0; i < c.Length; i++){
				re += c[i] + " ";
			}
			return re;
		}
		
		
		// Returns an enumerator for the tridiagonal matrix
		IEnumerator IEnumerable.GetEnumerator() 
		{
			return GetEnumerator();
		}
		
		public TriDiagonalMatrixEnumerator GetEnumerator() 
		{
			return new TriDiagonalMatrixEnumerator(this);
		}	
	}
}