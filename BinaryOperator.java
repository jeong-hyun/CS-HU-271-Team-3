/**
 * Represents a binary operation, meaning that it takes two inputs. If you want
 * to create a binary operation, extend this class and implement it's methods.
 * You will most likely want to implement the following methods:
 * String betweenSymbol();
 * double evaluate(double input1, double input2);
 * optional:
 * OrderOfOperations stage();
 * 
 * @author Samuel Lieberman
 *
 */
public abstract class BinaryOperator extends Operator {
	/**
	 * binary operators can only take 2 input
	 */
	@Override public final int numberOfInputs() {return 2;}
	
	@Override
	public final double evaluate(double... inputs) throws ArithmeticException {
		return evaluate(inputs[0], inputs[1]);
	}
	
	/**
	 * The actual results of running the operation. This method should be very
	 * simple. Throw an ArithemticException if the inputs are invalid in some way.
	 * 
	 * @param input1 the 1st input for this operation
	 * @param input2 the 2nd input for this operation
	 * @return the result of this operation.
	 */
	public abstract double evaluate(double input1, double input2) throws ArithmeticException;
}
