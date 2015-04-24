package travis.sudokusolver;

import travis.sudokusolver.solvable;
import static org.junit.Assert.*;

import org.junit.Test;

public class searchTest {
	int[][] puzzle = 
		{ 
			{ 7, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 5, 0, 0, 0, 9, 0, 0 },
			{ 0, 0, 3, 7, 2, 1, 0, 8, 0 },
			{ 0, 0, 0, 5, 0, 8, 7, 0, 0 },
			{ 0, 5, 7, 4, 6, 0, 2, 0, 0 },
			{ 9, 0, 1, 0, 7, 2, 4, 0, 0 },
			{ 0, 0, 0, 6, 4, 0, 8, 5, 0 },
			{ 0, 0, 0, 0, 9, 0, 0, 0, 4 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 9 }
		};
	
	solvable Search = new solvable();
	
	@Test
	public void testValidRow()
	{
		int[][] pos = { { 0, 2 },{0, 3 }, { 0, 4 }, { 0, 5} };
		int[] testable = {8, 9, 5, 6};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", true, Search.rowCheck(pos[i], puzzle));
		}
		
	}
	@Test
	public void testValidCol(){
		
		int[][] pos = { { 1, 0 }, {2, 0 }, { 3, 0 }, { 4, 0}, {6, 0}, {7, 0} };
		int[] testable = {2, 4, 6, 3, 1, 8};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", true, Search.columnCheck(pos[i], puzzle));
		}
	}
	
	@Test
	public void testValidBox(){
		
		//testable spots
		int[][] pos = { { 1, 0 }, { 2, 1 }, { 0, 2 }, {0, 3}, { 1, 4 } };
		int[] testable = {2, 9, 8, 9, 3};
		for (int i = 0; i < pos.length; i++)
		{
			int row = pos[i][0];
			int col = pos[i][1];
			puzzle[row][col] = testable[i];
			assertEquals("should return true", true, Search.boxCheck(pos[i], puzzle));
		}
	}
	
	@Test
	public void testSearchRules(){

		int[][] missingNumbersPosition = Search.getOpenSpots(puzzle);
		int[][] solution = Search.search(puzzle);
		for (int i = 0; i < missingNumbersPosition.length; i++)
		{
			int row = missingNumbersPosition[i][0];
			int col = missingNumbersPosition[i][1];
			assertEquals("should return true", true, Search.searchRules(row, col, solution));
		}
	}
}
