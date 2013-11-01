import java.util.*;

public class ID32 {
    public static boolean panDigital(String s) {
        if (s.length() != 9)
            return false;

        for (char c = '1'; c <= '9'; c++) {
            if (s.indexOf("" + c) == -1)
                return false;
        }

        return true;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        final int aMin = 2, aMax = 99;
        Set<Integer> numbers = new HashSet<Integer>();

        for (int a = aMin; a <= aMax; a++) {
            int bMax = 10000 / a + 1;
            int bMin = (a > 9) ? 123 : 1234;
            for (int b = bMin; b <= bMax; b++) {
                if (panDigital(a + "" + b + "" + (a * b))) {
                    numbers.add(a * b);
                }
            }
        }

        int sum = 0;
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            sum += it.next();
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
