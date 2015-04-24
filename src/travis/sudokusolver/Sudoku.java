package travis.sudokusolver;


/*
 * @author Travis Martin
 * UNB Computer Science project "Introduction to Artificial intelligence"
 *
 *   Quick note: you can ignore some of my comments I use them to help solve the problem at hand.
 *                 QUICK NOTE!!!!!!
 * THIS CODE WILL BE REDONE TO MAKE USE OF OOP ALSO MAKE IT EASIER TO READ!!
 */

public class Sudoku {

	protected ISearch solver;
	protected int[][] puzzle;

	// might now be needed.
	protected int[][] emptyGrids;

	public Sudoku(ISearch solver)
	{
		this.solver = solver;
	}

	public static void println(Object input){
		System.out.println(input.toString());
	}
	
	public ISearch getSearch(){
		return this.solver;
	}
	
	public int[][] solvePuzzle(int[][] puzzle){
		int[][] solution = getSearch().search(puzzle);
		return solution;
	}
	
	public void printPuzzle(int[][] puzzle){
		getSearch().printPuzzle(puzzle);
	}
	

	public static void main(String[] args)
	{
		Sudoku obj = new Sudoku(new solvable());
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
		puzzle = obj.solvePuzzle(puzzle);
		
		obj.getSearch().printPuzzle(puzzle);
	}
}
