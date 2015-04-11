package travis.sudokusolver;

import travis.sudokusolver.search;
import static org.junit.Assert.*;

import org.junit.Test;
import 

public class searchTest {
	
	/*
	 * NOTES:
	 * valid numbers for the first position are: {3, 5}
	 * Invalid Nums for the fisrt pos are: { 1, 2, 4, 6, 7, 8, 9}
	 */
	int[][] puzzle = 
		{ 
			{ 0, 2, 8, 0, 9, 0, 6, 4, 0 },
			{ 1, 0, 0, 0, 2, 6, 3, 5, 0 }, 
			{ 7, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 7, 2, 8, 0, 0, 0, 0, 0 }, 
			{ 8, 9, 5, 2, 0, 4, 7, 1, 3 },
			{ 0, 0, 0, 0, 0, 5, 8, 9, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 0, 8, 1, 4, 5, 0, 0, 0, 9 }, 
			{ 0, 3, 4, 0, 8, 0, 5, 7, 0 }
		};
	int[][] pos = { { 0, 0 },{0, 3 }, { 0, 5 }, { 0, 8} };

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testInvalidRow()
	{
		
	}

}
