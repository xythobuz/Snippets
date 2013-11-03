/*
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
 *
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 *
 * It can be verified that the sum of the numbers on the diagonals is 101.
 *
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */

public class ID28 {
    final static int RIGHT = 0;
    final static int DOWN = 1;
    final static int LEFT = 2;
    final static int UP = 3;

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        int width = 1001;
        int field[][] = new int[width][width];

        int x = width / 2;
        int y = x;
        int nextValue = 1;
        int nextDir = RIGHT;
        int fieldsVisited = 0;

        int schritteBisRichtungswechsel = 1;
        int schritteGetan = 0;
        int schritteGetan2 = 0;

        while(fieldsVisited++ < (width * width)) {
            field[x][y] = nextValue++;

            switch (nextDir) {
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case UP:
                    y--;
                    break;
            }

            if (++schritteGetan >= schritteBisRichtungswechsel) {
                schritteGetan = 0;
                switch (nextDir) {
                    case RIGHT:
                        nextDir = DOWN;
                        break;
                    case DOWN:
                        nextDir = LEFT;
                        break;
                    case LEFT:
                        nextDir = UP;
                        break;
                    case UP:
                        nextDir = RIGHT;
                        break;
                }
            }

            if (++schritteGetan2 >= (2 * schritteBisRichtungswechsel)) {
                schritteGetan2 = 0;
                schritteBisRichtungswechsel++;
            }
        }

        int sum = 0;
        x = 0;
        y = 0;
        while (x < width) {
            sum += field[x][y];
            x++;
            y++;
        }
        x = 0;
        y = width - 1;
        while (x < width) {
            sum += field[x][y];
            x++;
            y--;
        }

        System.out.println("Sum of Diagonals: " + (sum - 1));

        // -------------------------------------------
        java.util.Date stop = new java.util.Date();
        printTime(start, stop);
    }

    static void printTime(java.util.Date start, java.util.Date stop) {
        long diff = stop.getTime() - start.getTime();
        int diffsec = 0;
        int diffmin = 0;
        while (diff > 1000) {
            diff -= 1000;
            diffsec++;
        }
        while (diffsec > 60) {
            diffsec -= 60;
            diffmin++;
        }
        System.out.println();
        if (diffmin > 0) {
            System.out.println("Minutes: " + diffmin);
        }
        if (diffsec > 0) {
            System.out.println("Seconds: " + diffsec);
        }
        if (diff > 0) {
            System.out.println("Milliseconds: " + diff);
        }
    }
}
