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
	search Search = new search();

//	@Test
//	public void test()
//	{
//		fail("Not yet implemented");
//	}

	@Test
	public void testValidRow()
	{
		int[][] pos = { { 0, 0 },{0, 3 }, { 0, 5 }, { 0, 8} };
		int[] testable = {1, 3, 5, 7};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", true, Search.rowCheck(pos[i], puzzle));
		}
		
	}
	@Test
	public void testValidCol(){
		
		int[][] pos = { { 0, 0 },{3, 0 }, { 5, 0 }, { 6, 0}, {7, 0}, {8, 0} };
		int[] testable = {2, 3, 4, 5, 6, 9};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", true, Search.columnCheck(pos[i], puzzle));
		}
	}
	
	@Test
	public void testValidBox(){
		
		//testable spots
		int[][] pos = { { 0, 0 }, { 1, 1 }, { 1, 2 }, {2, 1 }, { 2, 2 } };
		int[] testable = {2, 3, 4, 5, 6, 9};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", true, Search.boxCheck(pos[i], puzzle));
		}
	}
	
	@Test
	public void testInvalidRow()
	{
		int[][] pos = { { 0, 0 },{0, 3 }, { 0, 5 }, { 0, 8} };
		int[] testable = {2, 4, 6, 8, 9}; //2, 4, 6, 8
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return false", false, Search.rowCheck(pos[i], puzzle));
		}	
	}
	
	@Test
	public void testInvalidCol(){
		
		int[][] pos = { { 0, 0 },{3, 0 }, { 5, 0 }};
		int[] testable = {1, 7, 8};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", false, Search.columnCheck(pos[i], puzzle));
		}
	}
	
	@Test
	public void testInvalidBox(){
		
		//testable spots
		int[][] pos = { { 0, 0 }, { 1, 1 }, { 1, 2 } };
		int[] testable = {6, 7, 2};
		for (int i = 0; i < pos.length; i++)
		{
			puzzle[pos[i][0]][pos[i][1]] = testable[i];
			assertEquals("should return true", false, Search.boxCheck(pos[i], puzzle));
		}
	}

}
