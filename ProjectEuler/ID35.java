import java.util.*;

public class ID35 {

    final static int circularLimit = 1000000;
    public static SieveOfAtkin sieve = new SieveOfAtkin(circularLimit);

    public static int circular(int prime, HashSet<Integer> primes) {
        int multiplier = 1;
        int number = prime;
        int count = 0;
        int d;

        while (number > 0) {
            d = number % 10;
            if (d % 2 == 0 || d == 5) {
                primes.remove(prime);
                return 0;
            }
            number /= 10;
            multiplier *= 10;
            count++;
        }
        multiplier /= 10;

        number = prime;
        HashSet<Integer> circularPrimes = new HashSet<Integer>();

        for (int i = 0; i < count; i++) {
            if (primes.contains(number)) {
                circularPrimes.add(number);
                primes.remove(number);
            } else if (!circularPrimes.contains(number)) {
                return 0;
            }
            d = number % 10;
            number = d * multiplier + number / 10;
        }

        return circularPrimes.size();
    }

    public static void main (String[] args) {
        java.util.Date start = new java.util.Date();
        // -------------------------------------------
        // ----------------- Solution ----------------
        // -------------------------------------------

        // Create Set with all primes
        sieve.calculate();
        HashSet<Integer> primes = new HashSet<Integer>();
        for (int i = 0; i < circularLimit; i++) {
            if (sieve.sieve[i]) {
                primes.add(i);
            }
        }

        // Special Cases
        primes.remove(2);
        primes.remove(5);

        int count = 2; // Special Cases
        while (primes.size() > 0) {
            count += circular(primes.iterator().next(), primes);
        }

        System.out.println("Count: " + count);

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

class SieveOfAtkin {
	public int limit;
	public boolean[] sieve;
	private int limitSqrt;

	public SieveOfAtkin(int lim) {
		limit = lim;
		sieve = new boolean[limit + 1];
		limitSqrt = (int)Math.sqrt((double)limit);
	}

	public void calculate() {
	    Arrays.fill(sieve, false);
	    sieve[0] = false;
	    sieve[1] = false;
	    sieve[2] = true;
	    sieve[3] = true;

    	for (int x = 1; x <= limitSqrt; x++) {
    	    for (int y = 1; y <= limitSqrt; y++) {
    	        int n = (4 * x * x) + (y * y);
    	        if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
    	            sieve[n] = !sieve[n];
    	        }
    	        n = (3 * x * x) + (y * y);
    	        if (n <= limit && (n % 12 == 7)) {
    	            sieve[n] = !sieve[n];
    	        }
    	        n = (3 * x * x) - (y * y);
    	        if (x > y && n <= limit && (n % 12 == 11)) {
    	            sieve[n] = !sieve[n];
    	        }
    	    }
    	}

    	for (int n = 5; n <= limitSqrt; n++) {
    	    if (sieve[n]) {
    	        int x = n * n;
    	        for (int i = x; i <= limit; i += x) {
    	            sieve[i] = false;
    	        }
    	    }
    	}
	}
}
