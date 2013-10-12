import java.io.*;

// Work out the first ten digits of the sum of the following one-hundred 50-digit numbers (stdin)
// Usage: copy numbers into a text file, call program like this:
//        java ID13 < ID13numbers

public class ID13 {

		private static int numberCount = 100;
		private static int digitCount = 50;
		private static int resultCount = 10;

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		String lines[] = new String[numberCount];
		Number numbers[] = new Number[numberCount];

		// Read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			for (int i = 0; i < numberCount; i++) {
				lines[i] = in.readLine();
			}
		} catch (Exception e) {
			System.out.println("Exception");
			return;
		}

		// Check input
		for (int i = 0; i < numberCount; i++) {
			if (lines[i] == null) {
				System.out.println("Null at " + i);
				return;
			}
			if (lines[i].length() != digitCount) {
				System.out.println(lines[i].length() + " chars at line " + i);
				return;
			}
		}

		// Get it into memory
		for (int i = 0; i < numberCount; i++) {
			numbers[i] = new Number();
			Digit current = new Digit();
			numbers[i].last = current;
			for (int n = digitCount - 1; n >= 0; n--) {
				current.digit = Character.getNumericValue(lines[i].charAt(n));
				if (n >= 1) {
					current.previous = new Digit();
					current.previous.next = current;
					current = current.previous;
				}
			}
		}

		// Check conversion
		for (int i = 0; i < numberCount; i++) {
			if (numbers[i].digits() != digitCount) {
				System.out.println(numbers[i].digits() + " digits in number " + i);
				for (int k = numbers[i].digits() - 1; k >= 0; k--)
					System.out.print(numbers[i].digitAt(k) + "");
				System.out.println();
				return;
			}
		}

		// Sum it
		int remainder = 0;
		Number sum = new Number();
		Digit current = new Digit();
		sum.last = current;
		for (int i = 0; i < digitCount; i++) {
			int rowSum = 0;
			for (int k = 0; k < numberCount; k++) {
				rowSum += numbers[k].digitAt(i);
			}
			rowSum += remainder;
			current.digit = rowSum % 10;
			remainder = rowSum / 10;
			if (i < digitCount - 1) {
				current.previous = new Digit();
				current.previous.next = current;
				current = current.previous;
			}
		}

		// Print result
		int skip = 0;
		if (remainder > 0) {
			skip = 1;
		}
		if (remainder >= 10) {
			skip = 2;
		}
		if (remainder >= 100) {
			skip = 3;
		}
		System.out.print("" + remainder);
		for (int i = (digitCount - 1 - skip); i >= digitCount - resultCount; i--) {
			System.out.print(sum.digitAt(i));
		}
		System.out.println();

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

class Number {
	public Digit last = null;

	public int digitAt(int revIndex) {
		return last.revDigit(revIndex);
	}

	public int digits() {
		int c = 0;
		Digit cur = last;
		while (cur != null) {
			c++;
			cur = cur.previous;
		}
		return c;
	}
}

class Digit {
	public int digit = -1;
	public Digit previous = null;
	public Digit next = null;

	public int revDigit(int r) {
		if (r == 0) {
			return digit;
		} else {
			return previous.revDigit(r - 1);
		}
	}
}
