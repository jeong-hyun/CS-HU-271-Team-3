/**
 * This class represents the factorial feature of the calculator.
 * 
 * @author Akira Enderle
 *
 */

public class Factorial extends UnaryOperator {
	@Override
	public double evaluate(double input1) throws ArithmeticException {
		if (input1 < 0) {
			throw new NumberFormatException("Error: Factorials cannot be negative");
		}
		if ((input1 % 1) != 0) {
			throw new NumberFormatException("Error: Factorials must be integers");
		}
		int intInput = (int) input1;
		int result = 1;
		for (int i = intInput; i > 1; i--) {
			result *= i;
		}
		return result;
	}
	@Override
	public String beforeSymbol() {
		return "!";
	}
}

