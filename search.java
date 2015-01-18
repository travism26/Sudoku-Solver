public class search {

    public void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
    }


    public boolean search(int[][] puzzle, int[][] numOpenSpots) {
        boolean check;
        int k = 0;
        boolean isFound = false;

        while (!isFound) {
            int i = numOpenSpots[k][0];
            int j = numOpenSpots[k][1];

            if (puzzle[i][j] == 0) {
                puzzle[i][j] = 1;
            }

            //found solution
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

    public boolean searchAlt(int[][] puzzle) {
        Vector<Integer> list = new Vector<Integer>();
        int[][] numOpenSpots = getopenspot(puzzle);
        int k = 0; // Start from the first free cell
        boolean found = false; // Solution found?

        while (!found) {
            int i = numOpenSpots[k][0];
            int j = numOpenSpots[k][1];

            if (isValid(i, j, puzzle)) {
                if (k + 1 == numOpenSpots.length) { // No more free cells
                    found = true; // A solution is found
                } else { // Move to the next free cell
                    k++;
                    counter = 0;
                }
            } else if ((puzzle[i][j] < 9) && list.size() != 0) {
                if (list.size() != 0) {
                    puzzle[i][j] = list.firstElement(); // Check the next possible value in the vector
                }
            }
            /**
             * This is where I create a condition for the backtracking but how???
             */
            if (list.size() == 0) {

            }

        }

        return true; // A solution is found
    }

    public boolean isValid(int[][] puzzle) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (puzzle[i][j] != 0 && !isValid(i, j, puzzle)) return false;
        return true;
    }

      public boolean isValid(int i, int j, int[][] puzzle) {
        if(rowCheck(i, j, puzzle) && columnCheck(i, j, puzzle) && boxCheck(i, j, puzzle))
        return true; // The current value at puzzle[i][j] is valid
    }

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
    public boolean boxCheck(int i, int j, int [][]puzzle) {
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

    public static void main(String[] args) {

    }
}