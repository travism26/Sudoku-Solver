public class search {

    public void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
    }


    public boolean search(int[][] puzzle) {
        boolean check;
        int[][] numOpenSpots = getopenspot(puzzle);
        int k = 0;
        boolean isFound = false;

        while (!isFound) {
            int i = numOpenSpots[k][0];
            int j = numOpenSpots[k][1];

            if (puzzle[i][j] == 0) {
                puzzle[i][j] = 1;
            }

            check = isUsed(puzzle, i, j);

            if (check) {
                puzzle[i][j] = puzzle[i][j] + 1;
            }

            else if ((isValid(i, j, puzzle))) {
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
                        return false;
                    }
                    k--;
                    i = numOpenSpots[k][0];
                    j = numOpenSpots[k][1];
                }
                puzzle[i][j] = puzzle[i][j] + 1;
            }
        }
        return true;
    }
    public static void main(String[] args) {

    }
}