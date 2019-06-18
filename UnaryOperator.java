/**
 * Represents a unary operation, meaning that it takes one input. If you want
 * to create a unary operation, extend this class and implement it's methods.
 * You will most likely want to implement the following methods:
 * String beforeSymbol();
 * double evaluate(double input1);
 * 
 * @author Samuel Lieberman
 *
 */
public abstract class UnaryOperator extends Operator{
	/**
	 * Unary operators can only take 1 input
	 */
	@Override public final int numberOfInputs() {return 1;}
	/**
	 * Unary operators are always evaluated first
	 */
	@Override public OrderOfOperations stage() {return OrderOfOperations.SINGLE_INPUT;}
	/**
	 * between symbols don't make sense for a unary operator
	 */
	@Override public final String betweenSymbol() {return null;}
	
	@Override
	public final double evaluate(double... inputs) throws ArithmeticException {
		return evaluate(inputs[0]);
	}
	
	/**
	 * The actual results of running the operation. This method should be very
	 * simple. Throw an ArithemticException if the input is invalid in some way.
	 * 
	 * @param input1 the input for this operation
	 * @return the result of this operation.
	 */
	public abstract double evaluate(double input1) throws ArithmeticException;
}
