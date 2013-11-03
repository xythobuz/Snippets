import java.util.*;

public class ID38 {
    public static boolean panDigital(String s) {
        if (s.length() != 9)
            return false;

        for (char c = '1'; c <= '9'; c++) {
            if (s.indexOf("" + c) == -1)
                return false;
        }

        return true;
    }

    public static String product(int num, int p) {
        String res = "";
        for (int i = 1; i <= p; i++) {
            res += (num * i);
        }
        return res;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        int max = 0;
        for (int i = 1; i < 10000; i++) {
            for (int l = 2; l < 6; l++) {
                String num = product(i, l);
                if (panDigital(num)) {
                    System.out.println("Found " + num + " with " + i + " and " + l);
                    int n = Integer.parseInt(num);
                    if (n > max) {
                        max = n;
                    }
                }
            }
        }

        System.out.println("Max: " + max);

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
