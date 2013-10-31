import java.io.*;
import java.util.*;

// Usage: copy numbers into a text file, call program like this:
//        java ID18 < ID18numbers

public class ID18 {
	public static void main (String[] args) {
		java.util.Date start = new java.util.Date();
		// -------------------------------------------
		// ----------------- Solution ----------------
		// -------------------------------------------

        // Read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<String>();
		try {
            int i = 0;
            String s;
            while ((s = in.readLine()) != null)
                lines.add(i++, s);
		} catch (Exception e) {
			System.out.println("Exception");
			return;
		}

        // Format it
        int data[][] = new int[lines.size()][];
        for (int i = 0; i < data.length; i++) {
            data[i] = new int[i + 1];
            String[] tokens = lines.get(i).split("\\s");
            if ((i + 1) <= tokens.length) {
                for (int j = 0; j < (i + 1); j++) {
                    data[i][j] = Integer.parseInt(tokens[j]);
                }
            } else {
                System.out.println("Malformed Input?");
                return;
            }
        }

        // Find maximum path sum, bottom to top
        for (int line = (data.length - 1); line > 0; line--) {
            for (int row = 0; row < (data[line].length - 1); row++) {
                if (data[line][row] > data[line][row + 1]) {
                    data[line - 1][row] += data[line][row];
                } else {
                    data[line - 1][row] += data[line][row + 1];
                }
            }
        }

        System.out.println("Maximum Path Sum: " + data[0][0]);

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
