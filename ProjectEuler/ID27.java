import java.util.Arrays;

public class ID27 {

	public static SieveOfAtkin sieve = new SieveOfAtkin(10 * 1000);

	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		if (num > sieve.limit) {
			System.out.println("Increasing sieve limits for " + num + "...");
			sieve = new SieveOfAtkin(num * 2);
			sieve.calculate();
			System.out.println("Done!");
		}
		return sieve.sieve[num];
	}

	public static int consecutivePrimes(int a, int b) {
		if (!isPrime(b)) {
			return 0;
		} else {
			int n = 0;
			int num = b;
			do {
				n++;
				num = (n * n) + (a * n) + b;
			} while (isPrime(num));
			return n;
		}
	}

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		System.out.println("Initializing sieve...");
		sieve.calculate();
		System.out.println("Done!");

		int max = 0, maxA = 0, maxB = 0;
		for (int a = -999; a < 1000; a++) {
			for (int b = -999; b < 1000; b++) {
				int c = consecutivePrimes(a, b);
				if (c > max) {
					maxA = a;
					maxB = b;
					max = c;
				}
			}
		}

		System.out.println(max + " primes with " + maxA + " and " + maxB);
		System.out.println("a*b = " + (maxA * maxB));

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

// Adapted from http://stackoverflow.com/a/12066272/551296

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
	    // there may be more efficient data structure
	    // arrangements than this (there are!) but
	    // this is the algorithm in Wikipedia
	    // initialize results array
	    Arrays.fill(sieve, false);
	    // the sieve works only for integers > 3, so
	    // set these trivially to their proper values
	    sieve[0] = false;
	    sieve[1] = false;
	    sieve[2] = true;
	    sieve[3] = true;

    	// loop through all possible integer values for x and y
    	// up to the square root of the max prime for the sieve
    	// we don't need any larger values for x or y since the
    	// max value for x or y will be the square root of n
    	// in the quadratics
    	// the theorem showed that the quadratics will produce all
    	// primes that also satisfy their wheel factorizations, so
    	// we can produce the value of n from the quadratic first
    	// and then filter n through the wheel quadratic
    	// there may be more efficient ways to do this, but this
    	// is the design in the Wikipedia article
    	// loop through all integers for x and y for calculating
    	// the quadratics
    	for (int x = 1; x <= limitSqrt; x++) {
    	    for (int y = 1; y <= limitSqrt; y++) {
    	        // first quadratic using m = 12 and r in R1 = {r : 1, 5}
    	        int n = (4 * x * x) + (y * y);
    	        if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
    	            sieve[n] = !sieve[n];
    	        }
    	        // second quadratic using m = 12 and r in R2 = {r : 7}
    	        n = (3 * x * x) + (y * y);
    	        if (n <= limit && (n % 12 == 7)) {
    	            sieve[n] = !sieve[n];
    	        }
    	        // third quadratic using m = 12 and r in R3 = {r : 11}
    	        n = (3 * x * x) - (y * y);
    	        if (x > y && n <= limit && (n % 12 == 11)) {
    	            sieve[n] = !sieve[n];
    	        } // end if
    	        // note that R1 union R2 union R3 is the set R
    	        // R = {r : 1, 5, 7, 11}
    	        // which is all values 0 < r < 12 where r is
    	        // a relative prime of 12
    	        // Thus all primes become candidates
    	    } // end for
    	} // end for
    	// remove all perfect squares since the quadratic
    	// wheel factorization filter removes only some of them
    	for (int n = 5; n <= limitSqrt; n++) {
    	    if (sieve[n]) {
    	        int x = n * n;
    	        for (int i = x; i <= limit; i += x) {
    	            sieve[i] = false;
    	        } // end for
    	    } // end if
    	} // end for
	}
}