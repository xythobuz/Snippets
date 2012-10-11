public class ID17 {
	static int countLetters (int number) {
		if (number == 1000)
			return 11;
		
		int hundreds = 0;
		int tens = 0;
		int ones = 0;
		int count = 0;
		while (number % 10 != 0) {
			ones++;
			number--;
		}
		while (number % 100 != 0) {
			tens++;
			number -= 10;
		}
		while (number % 1000 != 0) {
			hundreds++;
			number -= 100;
		}
		
		if (tens == 1) {
			switch (ones) {
				case 1: case 2:
					count += 6; break;
			
				case 5: case 6:
					count += 7; break;
			
				case 3: case 4: case 9:
					count += 8; break;
					
				case 7:
					count += 9; break;
					
				case 8:
					count += 8; break;
				
				case 0:
					count += 3; break;
			
				default:
					break;
			}
		} else {
			switch (ones) {
				case 1: case 2: case 6:
					count += 3; break;
			
				case 3: case 7: case 8:
					count += 5; break;
			
				case 4: case 5: case 9:
					count += 4; break;
			
				default:
					break;
			}
			switch (tens) {
				case 1:
					count += 4; break;
			
				case 2: case 3: case 8: case 9:
					count += 6; break;
			
				case 4: case 5: case 6:
					count += 5; break;
				
				case 7:
					count += 7; break;
			
				default:
					break;
			}
		}
		
		if (hundreds != 0) {
			count += 7; // hundred
			if (tens != 0 || ones != 0) {
				count += 3; // and
			}
		}
		
		switch (hundreds) {
			case 1: case 2: case 6:
				count += 3; break;
			
			case 3: case 7: case 8:
				count += 5; break;
			
			case 4: case 5: case 9:
				count += 4; break;
			
			default:
				break;
		}
		return count;
		
	}
	
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		int sum = 0;
		for (int i = 1; i <= 1000; i++) {
			sum += countLetters(i);
			System.out.println(i + ": " + countLetters(i));
		}
		System.out.println("Sum: " + sum);
		
		/*int c = 0;
		while (true) {
			c = sc.nextInt();
			if (c == 0)
				break;
			System.out.println(c + ": " + countLetters(c));
		}*/
		
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