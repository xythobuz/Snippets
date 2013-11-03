public class ID33 {
    public static boolean isDigitCanceling(int top, int bot) {
        String t = (new Integer(top)).toString();
        String b = (new Integer(bot)).toString();
        double num = (double)top / (double)bot;
        for (int i = 0; i < 2; i++) {
            if (b.contains(t.substring(i, i + 1))) {
                String tmp1 = t.replaceFirst(t.substring(i, i + 1), "");
                String tmp2 = b.replaceFirst(t.substring(i, i + 1), "");
                int to = Integer.parseInt(tmp1);
                int bo = Integer.parseInt(tmp2);
                double nu = (double)to / (double)bo;
                if (num == nu)
                    return true;
            }
        }
        return false;
    }

    public static int getLowestBot(int top, int bot) {
        int min;
        if (top > bot)
            min = bot;
        else
            min = top;

        for (int i = min; i >= 2; i--) {
            if (((top % i) == 0) && ((bot % i) == 0))
                return bot / i;
        }

        return -1;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        int topRes = 1;
        int botRes = 1;
        for (int bot = 10; bot < 100; bot++) {
            for (int top = 10; top < bot; top++) {
                if (((top % 10) == 0) && ((bot % 10) == 0))
                    continue; // no trivial fractions!

                if (isDigitCanceling(top, bot)) {
                    topRes *= top;
                    botRes *= bot;
                }
            }
        }

        // But wait! We need to find its lowest denominator and print it.
        System.out.println("Lowest Denominator: " + getLowestBot(topRes, botRes));

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
