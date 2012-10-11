public class ID7 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		/*
		 * What is the 10.001st prime number?
		 */
		
		//Code goes here!
		int i = 0;
		int a = 2;
		boolean tmp = true;
		while (true) {
			for (int j = 2; j < a; j++) {
				if (a % j == 0) {
					tmp = false;
					break; // Removing this needs 25sec execution time!
				}
			}
			if (tmp == true) {
				i++;
			} else {
				tmp = true;
			}
			if (i == 10001) {
				System.out.println(i + ": " + a);
				break;
			}
			a++;
		}
		
				
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