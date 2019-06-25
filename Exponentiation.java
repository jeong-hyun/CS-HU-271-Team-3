import java.util.Math;

/*
 * Performs the exponentiation arithmetic for calculator.
 * 
 * @author Akira Enderle
 * 
 */

public class Exponentiation extends BinaryOperator {

	@Override
	public double evaluate(double input1, double input2) throws ArithmeticException {
	    return Math.pow(input1, input2);
	}

	@Override
	public String betweenSymbol() {
		return "^";
	}

}
