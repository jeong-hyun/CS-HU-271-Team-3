/**
 * This class represents a subtraction feature of the calculator.
 * 
 * @author Jeong-Hyun Boo
 *
 */

public class Subtraction extends BinaryOperator {
	@Override
	public OrderOfOperations stage() {
		return OrderOfOperations.ADDITION_AND_SUBTRACTION;
	}

	@Override
	public double evaluate(double input1, double input2) throws ArithmeticException {
		// TODO Auto-generated method stub
		return input1 - input2;
	}
	
	@Override
	public String betweenSymbol() {
		// TODO Auto-generated method stub
		return "-";
	}
}
