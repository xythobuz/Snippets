public class ID1 {
	public static void main (String[] args) {
		/* Problem 1
		 * Find the sum of all the multiples of 3 or 5 below 1000
		 */
		long result = 0;
		for (int i = 1; i < 1000; i++) {
			if ( (i % 3 == 0) || (i % 5 == 0) ) {
				result += i;
			}
		}
		System.out.println("Sum: " + result);
		
	}
	
}