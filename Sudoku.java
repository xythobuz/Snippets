/*
 * By: Thomas Buck <xythobuz@me.com>
 * Visit: www.xythobuz.org
 */
import java.io.*;

public class Sudoku {

    public static final int base = 3;
    public static int length = base * base;
    public static int[][] data = new int[length][length]; // data[y][x] starts top left
    public static boolean[][][] possibilities = new boolean[length][length][length];

    // Give it cell coordinates and a number. Returns true if number is in corresponding block
    public static boolean numberInBlock(int x, int y, int number) {
        x /= base;
        y /= base;
        for (int i = (base * y); i < ((base * y) + base); i++) {
            for (int j = (base * x); j < ((base * x) + base); j++) {
                if (data[i][j] == number)
                    return true;
            }
        }
        return false;
    }

    public static boolean numberInRow(int y, int number) {
        for (int i = 0; i < length; i++) {
            if (data[y][i] == number)
                return true;
        }
        return false;
    }

    public static boolean numberInColumn(int x, int number) {
        for (int i = 0; i < length; i++) {
            if (data[i][x] == number)
                return true;
        }
        return false;
    }

    public static boolean isPossible(int x, int y, int number) {
        return !(numberInRow(y, number + 1) || numberInColumn(x, number + 1) || numberInBlock(x, y, number + 1));
    }

    public static int possibilitiesInBlock(int x, int y, int number) {
        int count = 0;
        x /= base;
        y /= base;
        for (int i = (base * y); i < ((base * y) + base); i++) {
            for (int j = (base * x); j < ((base * x) + base); j++) {
                if (possibilities[i][j][number])
                    count++;
            }
        }
        return count;
    }

    public static int solve() {
        // Record all possibilities
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                for (int num = 0; num < length; num++) {
                    if (data[y][x] == -1)
                        possibilities[y][x][num] = isPossible(x, y, num);
                }
            }
        }

        boolean isSolved = false;
        int found = 0;
        while (!isSolved) {
            boolean progress = false;

            // Go through all cells,
            // Check for all true possibilities if they are true for other cells in the same block
            // if not, we found a solution. set all possibilities for this field to false,
            // recalculate the possibilities for the same block and the same row and column
            for (int y = 0; y < length; y++) {
                for (int x = 0; x < length; x++) {
                    for (int num = 0; num < length; num++) {
                        if ((data[y][x] == -1) && possibilities[y][x][num] && (possibilitiesInBlock(x, y, num) == 1)) {
                            // We found a solution!
                            progress = true;
                            found++;
                            data[y][x] = num + 1;
                            // Reset in block
                            for (int i = (base * (y / base)); i < ((base * (y / base)) + base); i++) {
                                for (int j = (base * (x / base)); j < ((base * (x / base)) + base); j++) {
                                    possibilities[i][j][num] = false;
                                }
                            }
                            // Reset in Row & Column
                            for (int i = 0; i < length; i++) {
                                possibilities[y][i][num] = false;
                                possibilities[i][x][num] = false;
                            }
                        }
                    }
                }
            }

            if (!progress) {
                isSolved = true;
                printPossibilities();
            } else {
                // Now check if we're finished solving
                boolean finished = true;
                for (int y = 0; y < length; y++) {
                    for (int x = 0; x < length; x++) {
                        if (data[y][x] == -1)
                            finished = false;
                    }
                }
                isSolved = finished;
            }
        }
        return found;
    }

    public static void printPossibilities() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                for (int num = 0; num < length; num++) {
                    if (possibilities[y][x][num])
                        System.out.println("Possible: (" + x + " | " + y + "): " + (num + 1));
                }
            }
        }
    }

    public static void readField() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] lines = new String[length];
        try {
            for (int i = 0; i < length; i++) {
                lines[i] = in.readLine();
            }
        } catch (Exception e) {
            System.out.println("Exception");
            System.exit(1);
        }

        for (int i = 0; i < length; i++) {
            String[] tokens = lines[i].split("[ ]+");
            for (int j = 0; j < length; j++) {
                if (tokens[j].equals("-")) {
                    data[i][j] = -1;
                } else {
                    data[i][j] = Integer.parseInt(tokens[j]);
                }
            }
        }
    }

    public static void printField() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (data[i][j] == -1)
                    System.out.print("?");
                else
                    System.out.print(data[i][j] + "");
                if (j < (length - 1)) {
                    if (((j + 1) % base) == 0) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            if (i < (length - 1)) {
                System.out.println();
                if (((i + 1) % base) == 0) {
                    for (int k = 0; k < ((length * 4) - 3); k++) {
                        System.out.print("-");
                    }
                    System.out.println();
                }
            } else {
                System.out.println();
            }
        }
    }

    public static void main (String[] args) {
        readField();
        int n = solve();
        printField();
        System.out.println("Could solve " + n + " Cells...");
    }
}
