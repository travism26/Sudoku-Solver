import java.io.*;
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

	protected int[][] puzzle;

	public Sudoku(int[][] puzzle) {
		this.puzzle = puzzle;
	}

	public int[][] getopenspot(int[][] puzzle) {

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
				if (puzzle[i][j] == 0) {
					numOpenSpots[count][0] = i;
					numOpenSpots[count++][1] = j;
				}

		return numOpenSpots;
	}

	public int[][] remove(int[][] b, int rowNum) {
		int randomnumber = randomWithRange(5, 8);
		int randomPos = 0;
		for (int i = 0; i < randomnumber; i++) {
			randomPos = randomWithRange(0, 8);
			b[rowNum][randomPos] = 0;

		}
		return b;
	}

	public int[][] Rowgenerate() {

		int[][] gen = new int[9][9];
		Vector<Integer> rVals = new Vector();

		// intialize the vector with value
		for (int i = 1; i < 10; i++) {
			rVals.add(i);
		}
		Collections.shuffle(rVals);
		for (int i = 0; i < 9; i++) {
			gen[0][i] = rVals.firstElement();
			rVals.removeElementAt(0);
		}
		search(gen);

		for (int i = 0; i < 9; i++) {
			gen = remove(gen, i);
		}
		return gen;

	}

	public int[][] puzzleGenerator(int[][] a) {
		int num = 0;
		int x, y;
		int counter = 0;
		int[][] array = a;
		while (counter <= 25) {
			x = (int) (Math.random() * 9);
			y = (int) (Math.random() * 9);
			num = (int) (Math.random() * 10);
			while (x == 9) {
				x = (int) (Math.random() * 10);
			}
			while (y == 9) {
				y = (int) (Math.random() * 10);
			}
			while (num == 0) {
				num = (int) (Math.random() * 10);
			}
			array[x][y] = num;
			while (!isValid(array)) {
				num = (int) (Math.random() * 10);
				while (num == 0) {
					num = (int) (Math.random() * 10);
				}
				array[x][y] = num;

			}
			counter++;

		}
		return array;
	}

	public int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	/**
	 * Goal is to reduce the running time in this search method With the use of
	 * vectors that search for the values that already been used up. however i
	 * realized that vectors are
	 * 
	 * @deprecated. so i will try something new and fix the problem like
	 *              arrayList
	 * @param puzzle
	 * @return
	 */
	int counter = 0;

	public boolean isValid(int[][] puzzle) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (puzzle[i][j] != 0 && !isValid(i, j, puzzle))
					return false;
		return true;
	}

	public boolean isValid(int i, int j, int[][] puzzle) {
		if (rowCheck(i, j, puzzle) && columnCheck(i, j, puzzle)
				&& boxCheck(i, j, puzzle))
			return true; // The current value at puzzle[i][j] is valid
	}

	/*
	 * sudoku rules the row, column, and box rules.
	 */

	public boolean rowCheck(int i, int j, int[][] puzzle) {
		for (int c = 0; c < 9; c++) {
			if (c != j && puzzle[i][c] == puzzle[i][j]) {
				numberOfSearchs++;
				return false;
			}
		}

		return true;
	}

	public boolean columnCheck(int i, int j, int[][] puzzle) {
		for (int r = 0; r < 9; r++) {
			if (r != i && puzzle[r][j] == puzzle[i][j]) {
				numberOfSearchs++;
				return false;
			}
		}

		return true;
	}

	public boolean boxCheck(int i, int j, int[][] puzzle) {
		for (int r = (i / 3) * 3; r < (i / 3) * 3 + 3; r++) {
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
				if (r != i && col != j && puzzle[r][col] == puzzle[i][j]) {
					numberOfSearchs++;
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);

		Sudoku s = new Sudoku();
		int[][] array = new int[9][9];
		array = s.Rowgenerate();
		s.printpuzzle(array);
		for (int i = 0; i < tester.size(); i++) {
			System.out.print(tester.elementAt(i));
		}
		System.out.println();
		s.check(array);

	}

}
