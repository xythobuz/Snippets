public class ID16 {

	public static final int power = 1000;

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		LargeNum n = new LargeNum();
		n.set(1);
		for (int i = 1; i <= power; i++) {
			n.multiply(2);
		}
		System.out.println("Sum: " + n.digitSum());

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
	public static final int size = 500; // 2^1000 has about 300 digits
	public int num[] = new int[size];

	LargeNum() {
		for (int i = 0; i < size; i++)
			num[i] = 0;
	}

	// n from 0 to 9
	void set(int n) {
		num[0] = n;
	}

	int digitSum() {
		int sum = 0;
		for (int i = 0; i < size; i++)
			sum += num[i];
		return sum;
	}

	// n from 1 to 9
	void multiply(int n) {
		int remainder = 0;
		for (int i = 0; i < size; i++) {
			int product = (num[i] * n) + remainder;
			remainder = 0;
			while (product >= 10) {
				remainder++;
				product -= 10;
			}
			num[i] = product;
		}
	}
}