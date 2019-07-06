/*
 * Performs the modulo operation for calculator.
 * Given two numbers, the first number will be divided by the second,
 * and the remainder of that division operation is returned.
 * 
 * @author Akira Enderle
 * 
 */

public class Modulo extends BinaryOperator {
	@Override
	public OrderOfOperations stage() {
		return OrderOfOperations.MULTIPLICATION_AND_DIVISION;
	}

	@Override
	public double evaluate(double input1, double input2) {
		return input1 % input2;
	}

	@Override
	public String betweenSymbol() {
		return "%";
	}
}
