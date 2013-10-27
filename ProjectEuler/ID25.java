public class ID25 {

	public static final int digits = 1000;

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		LargeNum n = new LargeNum(1);
		LargeNum n_1 = new LargeNum(1);
		LargeNum tmp = new LargeNum(0);
		int i = 2; // n is Fib(2), n_1 is Fib(1)

		while (n.digits() < digits) {
			tmp.set(n);
			tmp.add(n_1);
			n_1.set(n);
			n.set(tmp);
			i++;
		}

		System.out.println("Fib(" + i + ") has more than " + digits + " digits!");

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

class LargeNum {
	public static final int size = 1100;
	public int num[] = new int[size];

	// n from 0 to 9
	LargeNum(int n) {
		for (int i = 0; i < size; i++)
			num[i] = 0;
		num[0] = n;
	}

	void set(LargeNum n) {
		for (int i = 0; i < size; i++)
			num[i] = n.num[i];
	}

	void print() {
		for (int i = 9; i >= 0; i--) {
			System.out.print("" + num[i]);
		}
		System.out.println();
	}

	int digits() {
		int digits = size;
		for (int i = (size - 1); i >= 0; i--) {
			if (num[i] == 0) {
				digits--;
			} else {
				return digits;
			}
		}
		return digits;
	}

	void add(LargeNum n) {
		int remainder = 0;
		for (int i = 0; i < size; i++) {
			int sum = num[i] + n.num[i] + remainder;
			remainder = 0;
			while (sum >= 10) {
				sum -= 10;
				remainder++;
			}
			num[i] = sum;
		}
	}
}