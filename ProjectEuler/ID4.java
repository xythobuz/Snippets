import java.util.Scanner;
public class ID4 {
	
   /* This is a overly complicated way of determining if a number 
	* really is a palindrome. But, hey, it work's!
	*/
	public static boolean isPalindrome (int pali) {
		Integer pal = new Integer(pali);
		String pl = pal.toString();
		char[] arr1 = pl.toCharArray();
		char[] arr2 = new char[arr1.length];
		int j = arr2.length - 1;
		for (int i = 0; i < arr1.length;) {
			arr2[j] = arr1[i];
			i++;
			j--;
		}
		boolean res = true;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] == arr2[i]) {
			} else {
				res = false;
			}
		}
		return res;
		
	}
	
	public static void main (String[] args) {
		/*
		 * Find the largest palindrome made from the product of two 3-digit numbers.
		 */
		int max = 0;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				if (isPalindrome(i*j)) {
					if ((i*j) > max) {
						max = i * j;
					}
				}
			}
		}
		System.out.println("Result: " + max);
		
	}
	
}