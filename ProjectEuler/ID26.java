import java.util.*;

public class ID26 {
    public static int unitFractionCycle(int d) {
        ArrayList<Integer> remain = new ArrayList<Integer>();
        int nextRemain = 0;
        int number = 10; // d does not fit in 1, get a 0 --> 10

        while (true) {
            // recurring cycle found!
            if (remain.contains(number))
                return remain.size() - remain.indexOf(number);

            // no recurring cycle!
            if ((number % d) == 0)
                return 0;

            if (number < d) {
                remain.add(nextRemain++, number);
                number *= 10; // add 0 at end
                continue;
            }

            // divide
            int howOften = number / d;
            number -= howOften * d;
        }
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        int max = 0, maxPos = -1;
        for (int d = 2; d < 1000; d++) {
            int r = unitFractionCycle(d);
            if (r > max) {
                max = r;
                maxPos = d;
            }
        }

        System.out.println("Maximal recurring cycle (with " + max + ") at d = " + maxPos);

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
