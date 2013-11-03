public class ID36 {
    public static boolean isPalindromeDecimal(int n) {
        String s = (new Integer(n)).toString();
        for (int i = 0; i < (s.length() / 2); i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;
        }
        return true;
    }

    public static boolean isPalindromeBinary(int n) {
        // Using Bitshifts would be the easiest?
        // java ints have 32 bits, 31 without sign
        int highestSetBit = 32;
        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                highestSetBit = i;
                break;
            }
        }

        if (highestSetBit == 32)
            return false;

        int checkWidth = (highestSetBit + 1) / 2;
        int mask = filledUpTo(checkWidth - 1);
        int low = n & mask;

        int off = 0;
        if (((highestSetBit + 1) % 2) != 0)
            off = 1;

        int high = (n >> (checkWidth + off)) & mask;

        return low == reverseBits(high, checkWidth);
    }

    public static int reverseBits(int num, int width) {
        int result = 0;
        for (int i = 0; i < width; i++) {
            if ((num & (1 << i)) != 0)
                result = result | (1 << (width - i - 1));
        }
        return result;
    }

    public static int filledUpTo(int x) {
        int res = 0;
        for (int i = 0; i <= x; i++) {
            res = res | (1 << i);
        }
        return res;
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            if (isPalindromeDecimal(i) && isPalindromeBinary(i))
                sum += i;
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
