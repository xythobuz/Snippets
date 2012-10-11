import java.math.*;
public class ID20 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		String s = "10";
		if (args.length > 0) {
			s = args[0];
		}

		// Yeah, that's cheating... I know :(
		BigInteger c = new BigInteger(s, 10);
		BigInteger n = new BigInteger("1", 10);
		BigInteger zero = new BigInteger("0", 10);
		BigInteger one = new BigInteger("1", 10);
		BigInteger r = new BigInteger("0", 10);

		while (c.compareTo(zero) == 1) { // c > 0
			n = n.multiply(c); // n *= c
			c = c.subtract(one); // c--;
		}

		System.out.println(s + "! = " + n.toString());

		System.out.println("Sum of digits: " + quersumme(n.toString()));

		java.util.Date stop = new java.util.Date();
		printTime(start, stop);
	}

	public static int quersumme(String s) {
		char c[] = s.toCharArray();
		int sum = 0;
		for (int i = 0; i < c.length; i++) {
			sum += (c[i] - '0');
		}
		return sum;
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
