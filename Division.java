/*
 * This class computes the division for input1 and input2
 * 
 * @author Kyle Epperson 
 */

public class Division extends BinaryOperator {

	@Override
	public OrderOfOperations stage() {
		return OrderOfOperations.MULTIPLICATION_AND_DIVISION;
	}
	
	@Override
	public double evaluate(double input1, double input2) throws ArithmeticException {
		return input1 / input2;
	}

	@Override
	public String betweenSymbol() {
		return "/";
	}

}
