package travis.sudokusolver;

import java.io.*;


/*
 * @author Travis Martin
 * UNB Computer Science project "Introduction to Artificial intelligence"
 *
 *   Quick note: you can ignore some of my comments I use them to help solve the problem at hand.
 *                 QUICK NOTE!!!!!!
 * THIS CODE WILL BE REDONE TO MAKE USE OF OOP ALSO MAKE IT EASIER TO READ!!
 */

public class Sudoku {

	protected search solver;
	protected int[][] puzzle;

	// might now be needed.
	protected int[][] emptyGrids;

	public Sudoku(search solver)
	{
		this.solver = solver;
	}

	public static void println(Object input){
		System.out.println(input.toString());
	}
	
	public search getSearch(){
		return this.solver;
	}
	

	public static void main(String[] args) throws IOException
	{
		Sudoku obj = new Sudoku(new search());
		int[][] puzzle = 
			{ 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 2, 6, 3, 5, 0 },
				{ 7, 0, 6, 0, 0, 0, 0, 0, 0 },
				{ 0, 7, 2, 8, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 4, 7, 1, 3 },
				{ 0, 0, 0, 0, 0, 5, 8, 9, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 8, 1, 4, 5, 0, 0, 0, 9 },
				{ 0, 3, 4, 0, 8, 0, 5, 7, 0 }
			};
		puzzle = obj.getSearch().backtrack(puzzle);
		
		obj.getSearch().printPuzzle(puzzle);
	}
}
