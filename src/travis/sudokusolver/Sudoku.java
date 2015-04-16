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
	public int[][] getopenspot(int[][] puzzle)
	{
		int totalNumOpen = 0;
		int[][] numOpenSpots;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (puzzle[i][j] == 0)
					totalNumOpen++;

		numOpenSpots = new int[totalNumOpen][2];
		int count = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (puzzle[i][j] == 0)
				{
					numOpenSpots[count][0] = i;
					numOpenSpots[count++][1] = j;
				}

		return numOpenSpots;
	}
	
	

	public static void main(String[] args) throws IOException
	{
		Sudoku obj = new Sudoku(new search());
		int[][] puzzle = 
			{ 
				{ 0, 2, 8, 0, 9, 0, 6, 4, 0 },
				{ 1, 0, 0, 0, 2, 6, 3, 5, 0 },
				{ 7, 0, 6, 0, 0, 0, 0, 0, 0 },
				{ 0, 7, 2, 8, 0, 0, 0, 0, 0 },
				{ 8, 9, 5, 2, 0, 4, 7, 1, 3 },
				{ 0, 0, 0, 0, 0, 5, 8, 9, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 8, 1, 4, 5, 0, 0, 0, 9 },
				{ 0, 3, 4, 0, 8, 0, 5, 7, 0 }
			};
		int[][] emptyLocals = obj.getopenspot(puzzle);
		println(emptyLocals.length);
		for (int i = 0; i < 4; i++)
		{
			int row = emptyLocals[i][0];
			int col = emptyLocals[i][1];
//			println("ROW: "+ row);
//			println("COL: "+ col);
			
			if(puzzle[row][col] == 0){
				puzzle[row][col] = 1;
			}
			
			
		}

	}
}
