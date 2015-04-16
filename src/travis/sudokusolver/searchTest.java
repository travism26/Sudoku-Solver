package travis.sudokusolver;

import travis.sudokusolver.search;
import static org.junit.Assert.*;

import org.junit.Test;

public class searchTest {
	
	/*
	 * NOTES:
	 * valid numbers for the first position are: {3, 5}
	 * Invalid Nums for the fisrt pos are: { 1, 2, 4, 6, 7, 8, 9}
	 * 
	 * Create test cases on the below data
	 * Valid pos for top row are: {1, 3, 5, 7}
	 * Valid pos for first col are: {2, 3, 4, 5, 6, 9}
	 * Valid pos for first box are: {3, 4, 5, 6, 9}
	 */
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
	search Search = new search();

//	@Test
//	public void test()
//	{
//		fail("Not yet implemented");
//	}

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
		
		int[][] pos = { { 1, 0 },{2, 0 }, { 3, 0 }, { 4, 0}, {6, 0}, {7, 0} };
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
		/* TEST DESCRIPTION */
		//we need to get the num empty pos and loop through em :)
		//checking if the rules are passing true.
		//I will use an online sudoku solver to get the ans to few spots
		//and use them as test points.

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

}
