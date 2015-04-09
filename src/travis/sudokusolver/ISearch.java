package travis.sudokusolver;
public interface ISearch {
	int[][] backtrack(int[][] puzzle, int[][] empty);
}