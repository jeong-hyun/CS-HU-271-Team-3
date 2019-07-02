/**
 * This class represents a tangent feature of the calculator.
 * 
 * @author Jeong-Hyun Boo
 *
 */

public class Tangent extends UnaryOperator 
{
	@Override
	public double evaluate(double input1) throws ArithmeticException 
	{
		// TODO Auto-generated method stub
		return Math.tan(input1);
	}

	@Override
	public String beforeSymbol() 
	{
		return "tan";
	}
}