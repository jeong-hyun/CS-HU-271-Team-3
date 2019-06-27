/**
 * This class represents a sine feature of the calculator.
 * 
 * @author Jeong-Hyun Boo
 *
 */

public class Sine extends UnaryOperator 
{
	@Override
	public double evaluate(double input1) throws ArithmeticException 
	{
		// TODO Auto-generated method stub
		return Math.sin(input1);
	}
	@Override
	public String beforeSymbol() 
	{
		return "sin";
	}

}


