public class ID6 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		/*
		 * Find the difference between the sum of the squares of the
		 * first one hundred natural numbers and the square of the sum.
		 */
		long a = 0;
		long b = 0;
		long n = 100;
		a = (2*n+1)*(n+1)*n/6;
		b = n*(n+1)/2;
		b *= b;
		System.out.println("Diff.: " + (b - a));
		
		java.util.Date stop = new java.util.Date();
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
		System.out.println("\nTime elapsed:");
		System.out.println("Minutes: " + diffmin);
		System.out.println("Seconds: " + diffsec);
		System.out.println("Milliseconds: " + diff);
	}
	
}