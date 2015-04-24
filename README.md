# Sudoku-Solver

## Getting Started

1. Clone this repository
2. Create a Sudoku object `Sudoku obj = new Sudoku(new solvable());`
3. input your puzzle NOTE: 0 represents empty space in puzzle.
4. call the solvePuzzle(int[][] puz) method.

#### Example
```
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
	
	obj.printPuzzle(puzzle);
```