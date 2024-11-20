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
		int product = 0;
        // we call plus, x2 times to do this operation
		for (int i = 0; i < Math.abs(x2); i++) product = plus(product, x1);

		// If x2 is negative we will just inverse the result
		if (x2 < 0) product = minus(0, product);
		return product;
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
		// as we are talking about integers we will just return 0 if x2 is 0
		if (x2 == 0) return 0;
		// so no overwriting will be done to x1
        int temp = Math.abs(x1);
		int product = 0;
		/*  we will call minus function with x2 until negative (which is the remainder)
			to get the product itself we will count the amount of iterations it took
		*/
		while (temp >= x2){
			temp = minus(temp, Math.abs(x2));
			product++;
		}
		/*  as we want the division of negatives if both are negatives
			they will cancel out otherwise,
			we must return the inverse of the product */

		if (x2 < 0 || x1 < 0) product = minus(0, product);

		return product;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		/*  we do division and then we simply multiply x2 by the product
			then we get the difference of x1 and the result,
			which gives us the remainder 
		*/
	
		// mod(0) is undefined as it's division by 0, thus we return -1
		if (x2 == 0) return -1;
        return minus(x1,times(div(x1, x2), x2));
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		// we will use newton raphson as it's the most elegant

		// no negative roots
		if (x < 0) {
            return -1;
        }

        if (x == 0 || x == 1) {
            return x; // sqrt(0) = 0, sqrt(1) = 1
        }

        // Initial guess
	    // start from the middle as we already checked edge cases
        int guess = div(x,2); 

        // Newton-Raphson iteration
		// Formula: 
		// g - (g^2-x)/(2*g) = g - (g^2/g - x/g)/2
		// = g- (g/2-x/2g) = g/2 + x/2g = (g+x/g)/2
		// the last part is the one I use
        while (true) {
            int next = div(plus(guess, div(x, guess)), 2);
            if (next >= guess) break;
            guess = next; // Update the guess
        }

        return guess;
	}	  	  
}