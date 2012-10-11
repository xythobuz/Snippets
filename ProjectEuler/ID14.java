public class ID14 {
	static long countElements (long seed) {
		long count = 1;
		while (seed != 1) {
			if (seed % 2 == 0) {
				seed /= 2;
			} else {
				seed = (3*seed) + 1;
			}
			count++;
		}
		return count;
	}
	
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		long longest = 0;
		int longesti = 0;
		for (int i = 1; i < 1000000; i++) {
			long tmp = countElements(i);
			if (tmp > longest) {
				longest = tmp;
				longesti = i;
			}
		}
		System.out.println(longesti + " has " + longest + " elements.");
		
		
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