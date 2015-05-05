package travis.sudokusolver;

public class solvable implements ISearch {

	public void printPuzzle(int[][] puzzle)
	{
		for (int i = 0; i < 9; i++)
		{
			System.out.print("{");
			for (int j = 0; j < 9; j++)
				System.out.print(puzzle[i][j] + ",");
			System.out.print("},\n");
		}
	}

	public int[][] getOpenSpots(int[][] puzzle)
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

	public int[][] search(int[][] puzzle)
	{
		boolean isDone = false;
		int i = 0;
		int[][] emptySpots = getOpenSpots(puzzle);
		while (isDone == false)
		{

			int row = emptySpots[i][0];
			int col = emptySpots[i][1];

			if (puzzle[row][col] == 0)
			{
				puzzle[row][col] = 1;
			}

			else if (searchRules(row, col, puzzle) == true)
			{
				if (i + 1 == emptySpots.length)
				{
					return puzzle;
				} else
				{
					i++;
				}
			} else if (puzzle[row][col] < 9)
			{
				puzzle[row][col] += 1;
			}

			else
			{
				while (puzzle[row][col] == 9)
				{
					puzzle[row][col] = 0;
					if (i == 0)
					{
						// puzzle not solvable.
						return notSolvable();
					}
					i--;
					row = emptySpots[i][0];
					col = emptySpots[i][1];
				}
				puzzle[row][col] += 1;
			}
		}
		return puzzle;
	}

	public int[][] notSolvable()
	{
		int[][] unSolvable = new int[9][9];
		for (int i = 0; i < unSolvable.length; i++)
		{
			for (int j = 0; j < unSolvable.length; j++)
			{
				unSolvable[i][j] = 9;
			}
		}
		return unSolvable;
	}

	public boolean searchRules(int i, int j, int[][] puzzle)
	{
		int[] pos = { i, j };
		boolean row = rowCheck(pos, puzzle);
		boolean col = columnCheck(pos, puzzle);
		boolean box = boxCheck(pos, puzzle);

		if (row && col && box)
		{
			return true;
		}
		return false;
	}

	public boolean rowCheck(int[] pos, int[][] puzzle)
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

	public boolean columnCheck(int[] pos, int[][] puzzle)
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

	public boolean boxCheck(int[] pos, int[][] puzzle)
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
}