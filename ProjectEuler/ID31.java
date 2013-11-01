public class ID31 {
    public static int value(int p100, int p50, int p20, int p10, int p5, int p2, int p1) {
        return (p100 * 100) + (p50 * 50) + (p20 * 20) + (p10 * 10) + (p5 * 5) + (p2 * 2) + p1;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        int sum = 1;
        for (int p100 = 0; p100 <= 2; p100++) {
            for (int p50 = 0; p50 <= 4; p50++) {
                for (int p20 = 0; p20 <= 10; p20++) {
                    for (int p10 = 0; p10 <= 20; p10++) {
                        for (int p5 = 0; p5 <= 40; p5++) {
                            for (int p2 = 0; p2 <= 100; p2++) {
                                for (int p1 = 0; p1 <= 200; p1++) {
                                    if (value(p100, p50, p20, p10, p5, p2, p1) == 200) {
                                        sum++;
                                    }
                                    if (value(p100, p50, p20, p10, p5, p2, p1) >= 200) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
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
