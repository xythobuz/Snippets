public class ID10 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		int lasti = 100001;
		int count = 1;
		long sum = 2;
		boolean tmp = true;
		int j = 0;
		
		for (int i = 3; i < 2000000; i += 2) {
			tmp = true;
			for (j = 2; j < i; j++) {
				if (i % j == 0) {
					tmp = false;
					break;
				}
			}
			if (tmp) {
				sum += i;
				count++;
			}
			if (i == lasti) {
				System.out.println(i + ": " + sum + " :: " + count);
				lasti = i + 100000;
			}
		}
		System.out.println("Sum: " + sum);
		
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