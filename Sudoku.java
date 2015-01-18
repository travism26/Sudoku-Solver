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

    public int[][] getopenspot(int[][] puzzle) {

        int totalNumOpen = 0;
        
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


    public int[][] getopenspot() {

        return numOpenSpots;
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

    public int randomWithRange(int min, int max) {
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

