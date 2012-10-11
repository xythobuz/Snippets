public class ID5 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		
		int i=20; 
		while ( (i % 20!=0)||(i % 19!=0)||(i % 18!=0)||(i % 17!=0)||(i % 16!=0)||(i % 15!=0)||(i % 14!=0)||(i % 13!=0)||(i % 12!=0)||(i % 11!=0)||(i % 10!=0)||(i % 9!=0)||(i % 8!=0)||(i % 7!=0)||(i % 6!=0)||(i % 5!=0)||(i % 4!=0)||(i % 3!=0)||(i % 2!=0)||(i % 1!=0)) {
			i++;
		} 
		System.out.println("Value: " + i); 
		
		/* Waaaaayyyyy too complicated, takes 96 seconds!!
		long a = 1;
		boolean tmp = true;
		long mina = 0;
		while (mina == 0) {
			for (int i = 20; i > 0; i--) {
				if (a % i == 0) {
				} else {
					tmp = false;
				}
			}
			if (tmp) {
				if (a > mina) {
					mina = a;
				}
			} else {
				a++;
			}
			tmp = true;
		}
		System.out.println("Value: " + mina);
		*/
		
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