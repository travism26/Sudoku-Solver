package travis.sudokusolver;
public interface ISearch {
	int[][] search(int[][] puzzle);
	void printPuzzle(int[][] puzzle);
}