public class ID9 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		/*
		 * There exists exactly one Pythagorean triplet for which
		 * a + b + c = 1000
		 * Find the product abc.
		 */
		 
		int a = 0;
		int b = 0;
		int c = 0;
		int res = 0;
		boolean fin = false;
		for (a = 1; a <= 1000; a++) {
			for (b = 1; b <= 1000; b++) {
				for (c = 1; c <= 1000; c++) {
					if ( ((a*a)+(b*b)) == (c*c) ) {
						if ( a+b+c == 1000 ) {
							System.out.println("a: " + a);
							System.out.println("b: " + b);
							System.out.println("c: " + c);
							res = a*b*c;
							fin = true;
							break;
						}
					}
					if (fin)
						break;
				}
				if (fin)
					break;
			}
			if (fin)
				break;
		}
		System.out.println("Result: " + res);
		
		
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