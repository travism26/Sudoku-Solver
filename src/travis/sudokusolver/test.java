package travis.sudokusolver;
public class test {

	public boolean rules(int[] pos, int[][] puzzle)
	{
		boolean row = rowCheck(pos, puzzle);
		boolean col = columnCheck(pos, puzzle);
		boolean box = boxCheck(pos, puzzle);

		if (row && col && box)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private boolean rowCheck(int[] pos, int[][] puzzle)
	{
		int i = pos[0];
		int j = pos[1];
		for (int c = 0; c < 9; c++)
		{
			if (c != j && puzzle[i][c] == puzzle[i][j])
			{
				return false;
			}
		}
		return true;
	}

	private boolean columnCheck(int[] pos, int[][] puzzle)
	{
		int i = pos[0];
		int j = pos[1];
		for (int r = 0; r < 9; r++)
		{
			if (r != i && puzzle[r][j] == puzzle[i][j])
			{
				return false;
			}
		}
		return true;
	}

	private boolean boxCheck(int[] pos, int[][] puzzle)
	{
		int i = pos[0];
		int j = pos[1];
		for (int r = (i / 3) * 3; r < (i / 3) * 3 + 3; r++)
		{
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
			{
				if (r != i && col != j && puzzle[r][col] == puzzle[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

	// shorten up the println statement.
	public static void println(Object input)
	{
		System.out.println(input.toString());
	}

	public static void main(String[] args)
	{
		test obj = new test();
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
		boolean testPostion;

		int[][] testCases = { { 0, 1 }, { 1, 4 }, { 8, 8 }, { 0, 0 }, { 5, 0 } };
		// }
		// for(){

		// }
		//testPostion = obj.rules(pos, puzzle);
		for (int i = 0; i < testCases.length; i++)
		{
			
		}
		println("----- TESTING RULES METHOD -----");
		

	}
}