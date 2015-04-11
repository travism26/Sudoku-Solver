package travis.sudokusolver;

import java.io.*;
import java.lang.reflect.Constructor;
import java.math.*;
import java.util.Collections;
import java.util.Scanner;

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

	// might now be needed.
	protected int[][] emptyGrids;

	public Sudoku(search solver)
	{
		this.solver = solver;
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

	}
}
