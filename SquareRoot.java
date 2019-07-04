/*
 * This class calculates the square root of the input.
 * 
 * @author Kyle Epperson
 */
public class SquareRoot extends UnaryOperator {

	@Override
	public double evaluate(double input1) throws ArithmeticException {
		return Math.sqrt(input1);
	}
	
	@Override
	public String beforeSymbol() 
	{
		return "sqrt";
	}

}
