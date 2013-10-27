public class ID21 {

	public static int d(int n) {
		int sum = 0;
		for (int i = 1; i <= (n / 2); i++) {
			if ((n % i) == 0) {
				sum += i;
			}
		}
		return sum;
	}

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		int sum = 0;
		for (int i = 1; i < 10000; i++) {
			int d = d(i);
			int dd = d(d);
			if ((dd == i) && (dd < 10000) && (d != i)) {
				System.out.println("Pair: " + i + " " + d);
				sum += d;
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