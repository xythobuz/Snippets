public class ID19 {
	public static boolean leapYear(int year) {
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 100) == 0) {
			return false;
		} else if ((year % 4) == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static int daysIn(int month, int year) {
		if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
			return 30;
		} else if (month == 2) {
			if (leapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		} else {
			return 31;
		}
	}

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

		int day = 1;
		int month = 1;
		int year = 1900;
		int weekday = 1; // monday
		int sundayCount = 0;

		while(year < 2001) {
			weekday++;
			if (weekday > 7) {
				weekday = 1;
			}
			day++;
			if (day > daysIn(month, year)) {
				day = 1;
				month++;
				if (month > 12) {
					month = 1;
					year++;
				}
			}
			if (weekday == 7) {
				if ((year >= 1901) && (year <= 2000)) {
					if (day == 1) {
						sundayCount++;
					}
				}
			}
		}

		System.out.println("Sundays: " + sundayCount);

		// -------------------------------------------
		java.util.Date stop = new java.util.Date();
		printTime(start, stop);
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