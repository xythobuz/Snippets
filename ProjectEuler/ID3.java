public class ID3 {
	public static void main (String[] args) {
		/*
		 * Problem 3
		 * What is the largest prime factor of the number:
		 * 600851475143
		 */
		long number = 600851475143L;
		long max_factor = 1;
		while (number > 1) {
			long factor = 2;
			while (number % factor != 0) {
				factor++;
			}
			number /= factor;
			max_factor = factor;
		}
		System.out.println("Largest Prime Factor: " + max_factor);
	}
	
}