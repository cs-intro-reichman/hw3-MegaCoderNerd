// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), 
	// and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		// we start with the loan and the balance is how much of the loan is left
		
		while (n > 0){
			// substract payments and increase the loan by the rate 
			balance -= payment;
			balance *= (1+rate/100);
			n--;
		}
		
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		double approximation = loan/n; // Start from the loan divided into parts

		while (endBalance(loan, rate, n, approximation) > 0) {
			approximation = approximation + epsilon;
			iterationCounter++;
		}

		return approximation;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		// we check between 0 and the actual amount of money owed 
		// we calculate by loan over n as it cannot be less then the amount of payments
		// and we stop the search at the loan assuming it can not be larger then it
		iterationCounter = 0;
		double L = loan / n, H = loan;
		// the approximation is the middle value
		double approximation = (L + H) / 2.0;
		// we check if we got to epsilon
		while (H-L > epsilon) {
			// if there's still some of the loan left we need to increase payments
			if (endBalance(loan, rate, n, approximation) * endBalance(loan, rate, n, L) > 0){
				L = approximation;
			}else{
				// the payment must be too big if we got here as if the 
				// end balance is zero we would've exited already
				H = approximation;
			}
			// giving new payment values
			approximation = (L + H) / 2;
			iterationCounter++;
		}
		return approximation;
    }
}