import java.util.*;

public class ID34 {

    public static int[] factorial = new int[10];

    public static boolean isCurious(int num) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        int i = 0;
        int tmp = num;
        while (tmp > 0) {
            digits.add(i++, tmp % 10);
            tmp /= 10;
        }

        int sum = 0;
        for (i = 0; i < digits.size(); i++) {
            sum += factorial[digits.get(i)];
        }

        return sum == num;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        // Precompute factorials
        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i < 10; i++) {
            factorial[i] = i * factorial[i - 1];
        }

        int sum = 0;
        for (int n = 10; n < 10000000; n++) {
            if (isCurious(n))
                sum += n;
        }

        System.out.println("Sum: " + sum);

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
