/*
 * Performs the multiplication arithmetic for calculator.
 * 
 * @author Kyle Epperson
 * 
 */

public class Multiplication extends BinaryOperator {

	@Override
	public double evaluate(double input1, double input2) throws ArithmeticException {
		return input1 * input2;
	}

	@Override
	public String betweenSymbol() {
		return "*";
	}

}
