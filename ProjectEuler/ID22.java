import java.io.*;
import java.util.*;

public class ID22 {
    // 0 equal, 1 a > b, -1 a < b
    public static int compareStrings(String a, String b) {
        if (a.equals(b))
            return 0;

        int min;
        if (a.length() < b.length())
            min = a.length();
        else
            min = b.length();

        for (int i = 0; i < min; i++) {
            if (a.charAt(i) > b.charAt(i)) {
                return 1;
            } else if (a.charAt(i) < b.charAt(i)) {
                return -1;
            }
        }

        if (a.length() > b.length())
            return 1;
        else
            return -1;
    }

    public static int nameScore(String name) {
        int score = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            score += c - 'A' + 1; // names are all uppercase
        }
        return score;
    }

	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

        // Read Input into string array
        // we expect name list on one line like "ABC","DEF"
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
		try {
            input = in.readLine();
		} catch (Exception e) {
			System.out.println("Exception");
			return;
		}

        // String array with one name per entry
        input = input.substring(1, input.length() - 1); // remove first and last "
        String[] names = input.split("\",\"");

        // Sort array in place, using comb sort
        final float shrink = 1.3f;
        int gap = names.length;
        boolean swapped = false;
        while ((gap > 1) || swapped) {
            if (gap > 1) {
                gap = (int)((float)gap / shrink);
            }
            swapped = false;
            for (int i = 0; (gap + i) < names.length; i++) {
                if (compareStrings(names[i], names[i + gap]) == 1) {
                    String swap = names[i];
                    names[i] = names[i + gap];
                    names[i + gap] = swap;
                    swapped = true;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            sum += (i + 1) * nameScore(names[i]);
        }

        System.out.println("Total Score: " + sum);

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
