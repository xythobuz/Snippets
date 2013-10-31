import java.util.*;

public class ID30 {
    public static int digitPowers(int num, int pow) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        int i = 0;
        while (num > 0) {
            digits.add(i++, num % 10);
            num /= 10;
        }

        int sum = 0;
        for (i = 0; i < digits.size(); i++) {
            num = 1;
            for (int a = 0; a < pow; a++) {
                num *= digits.get(i);
            }
            sum += num;
        }

        return sum;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        final int power = 5;
        final int max = 10000000;
        int sum = 0;

        for (int i = 2; i < max; i++) {
            if (digitPowers(i, power) == i) {
                sum += i;
            }
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
