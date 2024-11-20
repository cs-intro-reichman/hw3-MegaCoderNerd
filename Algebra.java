// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		// If x2 is negative we just call minus instead
		if (x2 < 0) return minus(x1, -x2);
		// to not overwrite x1
		int sum = x1;
		// incrementing sum, x2 times to get the sum
		for (int i = 0; i<x2; i++) sum++;
		return sum;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		// If x2 is negative we just call plus instead
		if (x2 < 0) return plus(x1, -x2);
		// to not overwrite x1
		int diff = x1;
		// decrementing diff, x2 times to get the difference
		for (int i = 0; i < x2; i++) diff--;
		return diff;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		// Replace the following statement with your code
		return 0;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		// if n == 0 then the result will be 1 no matter what
		int result = 1;
		// we will call times function n times
		for (int i = 0; i < n; i++) result = times(result, x);
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		// Replace the following statement with your code
		return 0;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		// Replace the following statement with your code
		return 0;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		// Replace the following statement with your code
		return 0;
	}	  	  
}