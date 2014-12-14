import java.io.*;
import java.math.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
/**
  * @author Travis Martin
  * UNB Computer Science project "Introduction to Artificial intelligence"
  *
  *   Quick note: you can ignore some of my comments I use them to help solve the problem at hand.
  *
  *
  *
  *                 QUICK NOTE!!!!!!
  * THIS CODE WILL BE REDONE TO MAKE USE OF OOP ALSO MAKE IT EASIER TO READ!!
  */

public class Sudoku {

    public static  int numberOfSearchs = 0;
    int[][] numOpenSpots = null;

    /*
     * This method will be used for inputing the whole puzzle as one
     * String it will read and also ignore spaces Ie: 1 2 3 4
     */
    public int[][] readPuz(String input) {
        int count = 0;
        int[][] puzzle = new int[9][9];
        BufferedReader br = new BufferedReader(new StringReader(input));
        try {
            String line = br.readLine();

            while (line != null) {
                for (int j = 0; j < 9; j++) {
                    count = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (i == 9) {
                            break;
                        }
                        if (line.length() <= count) {
                            break;
                        }
                        if (line.charAt(count) == 32) {
                            count++;
                        }
                        puzzle[j][i] = line.charAt(count) - '0';
                        count++;
                    }

                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return puzzle;
    }

    public int[][] readPuzzle(String location) {
        FileInputStream fis = null;
        int[][] puzzle = new int[9][9];

        try {

            fis = new FileInputStream(location);

            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String input = br.readLine();


            while (input != null) {

                for (int j = 0; j < 9; j++) {
                    for (int i = 0; i < 9; i++) {
                        /*
                         * Since every char number represented as a number in the ASCII table
                         * I took the char '0' which equals 48 Decimal and subtract it to the
                         * input that is given will give me that scale 1-9 which are all valid
                         * inputs in the sudoku game.
                         */
                        puzzle[j][i] = input.charAt(i) - '0';

                    }
                    input = br.readLine();
                    if (input == null) {
                        break;
                    }
                }

            }
            fis.close();
            dis.close();
            br.close();


        } catch (Exception e) {
            System.out.println("File not found");
        }

        return puzzle;
    }

    /*
     * the way I decided to solve the sudoku puzzle was to first
     * search for all the unfilled spots of the puzzle then assign
     * another array with the location of the open spots.
     */
    public int[][] getopenspot(int[][] puzzle) {

        int totalNumOpen = 0;
        /*
         * This will check every cell in the puzzle and check if
         * the cell contains 0, if it does it will increase the counter
         * "totalNumOpen" this will determine how big of array size
         * is needed to hold the number of free cells.
         */
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (puzzle[i][j] == 0)
                    totalNumOpen++;

        /*
         * This will create a 2D array holding the
         * Positions of each open "Cell"(cell meaning the position in the puzzle) in the puzzle
         * and from here it will use them to solve the
         * puzzle using a search with an heuristic.
         */
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

    //this is just for testing purposes
    public void printpuzzle(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
    }

    /*
     * This will build a list of possible numbers that can fit into the
     * open cell.
     *
     * the return value will be an vector that holds the only possible numbers
     * that can fit into the row and column provided.
     */

    public boolean isUsed(int[][] input, int i, int j) {
        if (list.contains(input[i][j])) {
            return true;
        } else {
            return false;
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

    /* Check whether the current number on the
     *  puzzle[i][j] is valid in the rules of Sudoku
     */

    public boolean isValid(int i, int j, int[][] puzzle) {
        // Check whether puzzle[i][j] is valid at the i's row
        for (int c = 0; c < 9; c++) {
            if (c != j && puzzle[i][c] == puzzle[i][j]) {
                numberOfSearchs++;
                return false;
            }
        }
        // Check whether puzzle[i][j] is valid at the j's column
        for (int r = 0; r < 9; r++) {
            if (r != i && puzzle[r][j] == puzzle[i][j]) {
                numberOfSearchs++;
                return false;
            }
        }
        // Check whether puzzle[i][j] is valid in the 3 by 3 box
        for (int r = (i / 3) * 3; r < (i / 3) * 3 + 3; r++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (r != i && col != j && puzzle[r][col] == puzzle[i][j]) {
                    numberOfSearchs++;
                    return false;
                }
            }
        }
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


    public int[][] getopenspot() {

        return numOpenSpots;
    }
    /**
     *
     * @param puzzle
     * @return boolean
     * This method is designed just to see if the puzzle is valid
     * as it stands
     */
    public boolean isValid(int[][] puzzle) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (puzzle[i][j] != 0 && !isValid(i, j, puzzle)) return false;


        return true;
    }

    public void Clear() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
    }
    public void printError() {
        System.out.println(error);
        System.out.println(error2);
        System.out.println(error3);
    }
    public int[][] remove(int [][] b, int rowNum) {
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

        //intialize the vector with value
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

    public int[][] puzzleGenerator(int [][] a) {
        int num = 0;
        int x, y;
        int counter = 0;
        int[][] array = a;
        while (counter <= 25) {
            x = (int)(Math.random() * 9);
            y = (int)(Math.random() * 9);
            num = (int)(Math.random() * 10);
            while (x == 9) {
                x = (int)(Math.random() * 10);
            }
            while (y == 9) {
                y = (int)(Math.random() * 10);
            }
            while (num == 0) {
                num = (int)(Math.random() * 10);
            }
            array[x][y] = num;
            while (!isValid(array)) {
                num = (int)(Math.random() * 10);
                while (num == 0) {
                    num = (int)(Math.random() * 10);
                }
                array[x][y] = num;

            }
            counter++;

        }
        return array;
    }
    public void check(int[][] puzzle) {
        if (!isValid(puzzle)) {
            System.out.println("Invalid input");
            System.out.println("List of possible errors in you input");
            printError();
            System.out.println();
        } else if (search(puzzle)) {
            System.out.println("The solution is found:");
            printpuzzle(puzzle);
            System.out.print("The number of searchs done is: ");
            System.out.print(numberOfSearchs);
            System.out.println();
        } else {
            System.out.println("No solution");
        }
    }
    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    /**
      * Goal is to reduce the running time in this search method
      * With the use of vectors that search for the values that
      * already been used up. however i realized that vectors are
      * @deprecated.
      * so i will try something new and fix the problem like arrayList
      * @param puzzle
      * @return
      */
    int counter = 0;
    public boolean searchAlt(int[][] puzzle) {
        Vector<Integer> list = new Vector<Integer>();
        int[][] numOpenSpots = getopenspot(puzzle);
        int k = 0; // Start from the first free cell
        boolean found = false; // Solution found?

        while (!found) {
            int i = numOpenSpots[k][0];
            int j = numOpenSpots[k][1];
            if (puzzle[i][j] == 0) {
                if (list.size() != 0) {
                    puzzle[i][j] = list.firstElement();
                }

            }
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

