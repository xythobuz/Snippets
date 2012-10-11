public class ID2 {
	public static void main (String[] args) {
		/*
		 * Problem 2
		 * Find the sum of all the even-valued terms in the Fibonacci sequence,
		 * which do not exceed four million.
		 */
		int a = 0;
		int b = 1;
		long sum = 0;
		for (;(a <= 4000000) && (b <= 4000000);) {
			a = a + b;
			if (a % 2 == 0) {
				sum += a;
			}
			b = a + b;
			if (b % 2 == 0) {
				sum += b;
			}
		}
		System.out.println("Sum: " + sum);		
	}
	
}