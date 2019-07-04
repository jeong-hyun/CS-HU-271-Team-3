/**
 * This class represents a logarithm feature of the calculator.
 * 
 * @author Jeong-Hyun Boo
 *
 */

public class Log extends UnaryOperator 
{
	@Override
	public double evaluate(double input1) throws ArithmeticException 
	{
		// TODO Auto-generated method stub
		return Math.log(input1);
	}

	@Override
	public String beforeSymbol() 
	{
		return "log";
	}
}
