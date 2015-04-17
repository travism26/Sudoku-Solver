package travis.sudokusolver;

/*
 * This not not complete!
 */
public class puzzleReader() {

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

}