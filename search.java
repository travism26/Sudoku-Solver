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

    public static void main(String[] args) {

    }
}