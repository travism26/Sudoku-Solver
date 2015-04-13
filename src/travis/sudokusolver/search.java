package travis.sudokusolver;
public class search implements ISearch {

    public void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
    }

    public int[][] backtrack(int[][] puzzle, int[][] emptySpot) {
        int[][] numOpenSpots = emptySpot;
        int k = 0;
        boolean isFound = false;

        while (!isFound) {
            int i = numOpenSpots[k][0];
            int j = numOpenSpots[k][1];

            if (puzzle[i][j] == 0) {
                puzzle[i][j] = 1;
            }

            //found solution
            else if ((rules(i, j, puzzle))) {
                if (k + 1 == numOpenSpots.length) {
                    isFound = true;
                } else {
                    k++;
                }
            }

            else if (puzzle[i][j] < 9) {
                puzzle[i][j] = puzzle[i][j] + 1;
            }

            else {
                while (puzzle[i][j] == 9) {
                    puzzle[i][j] = 0;
                    if (k == 0) {
                        //PUZZLE CANT BE SOLVED!
                    	return puzzle;
                    }
                    k--;
                    i = numOpenSpots[k][0];
                    j = numOpenSpots[k][1];
                }
                puzzle[i][j] = puzzle[i][j] + 1;
            }
        }
        return puzzle;
    }
    
    public boolean rules(int i, int j, int[][] puzzle)
	{
    	int[] pos = {i,j};
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
			System.out.println();
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
			{
				System.out.println("R value: " + r);
				System.out.println("I value: " + i);
				System.out.println("col value: " + col);
				System.out.println("J value: " + j);
				System.out.println("puzzle[r][col] value: " + puzzle[r][col]);
				System.out.println("puzzle[i][j] value: " + puzzle[i][j]);
				//R and col are used to remove unneed tests
				if (r != i && col != j && puzzle[r][col] == puzzle[i][j])
				{
					System.out.println("value: FALSE");
					return false;
				}
			}
		}
		System.out.println("value: TRUE");
		return true;
	}


    public static void main(String[] args) {
    	search obj = new search();
    	int[][] puzzle = 
			{ 
				{ 1, 2, 8, 0, 9, 0, 6, 4, 0 },
				{ 1, 0, 0, 0, 2, 6, 3, 5, 0 }, 
				{ 7, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 7, 2, 8, 0, 0, 0, 0, 0 }, 
				{ 8, 9, 5, 2, 0, 4, 7, 1, 3 },
				{ 0, 0, 0, 0, 0, 5, 8, 9, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 8, 1, 4, 5, 0, 0, 0, 9 }, 
				{ 0, 3, 4, 0, 8, 0, 5, 7, 0 } 
			};
    	int[][] pos = { {1,0},{ 2, 1 }, { 1, 2 } };
    	//int[] testable = {9, 7, 2};
    	for (int i = 0; i < pos.length; i++)
		{
    		//puzzle[pos[i][0]][pos[i][1]] = testable[i];
    		obj.boxCheck(pos[i], puzzle);
			System.out.println("---------------------");
		}
    	
//		for (int i = 0; i < 9; i++)
//		{
//			System.out.println();
//			for (int j = 0; j < 9; j++)
//			{
//				System.out.print(puzzle[i][j]);
//			}
//		}
    }
}