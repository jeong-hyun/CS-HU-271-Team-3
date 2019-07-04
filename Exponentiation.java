/*
 * Performs the exponentiation arithmetic for calculator.
 * 
 * @author Akira Enderle
 * 
 */

public class Exponentiation extends BinaryOperator {
	@Override
	public OrderOfOperations stage() {
		return OrderOfOperations.EXPONENTIATION;
	}

	@Override
	public double evaluate(double input1, double input2) {
		return Math.pow(input1, input2);
	}

	@Override
	public String betweenSymbol() {
		return "^";
	}
}
