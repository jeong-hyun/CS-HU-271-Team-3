/**
 * Represents an operation. This class probably shouldn't be extended directly.
 * Instead, you should extend one of it's child classes, @see BinaryOperator
 * or @see UnaryOperator.
 * 
 * @author Samuel Lieberman
 */
public abstract class Operator {
	/**
	 * The number of inputs that this operator takes. In general, a new abstract
	 * class should be created to represent an operation taking each number of
	 * inputs.
	 * 
	 * @return the number of inputs for this operation.
	 */
	public abstract int numberOfInputs();

	/**
	 * The execution stage for this operation. This doesn't have to be extended at
	 * this stage in the project.
	 * 
	 * @return the execution stage for this operation.
	 */
	public OrderOfOperations stage() {
		return OrderOfOperations.UNKNOWN;
	}

	/**
	 * The symbol to appear before all the inputs in this operation. for example,
	 * in the expression "log(5)" the beforeSymbol is "log(". In most cases, the
	 * default value of an empty string should work fine.
	 * 
	 * @return The symbol to appear before all the inputs in this operation.
	 */
	public String beforeSymbol() {return "";}
	/**
	 * The symbol to appear between all the inputs in this operation. for example,
	 * in the expression "5^2" the betweenSymbol is "^". In the case of more than 2
	 * inputs (which I have never seen before) this string should appear between
	 * each of the values. For example if betweenSymbol is ", ", then you might have
	 * an expression like: "?(2, 2, 2)".
	 * 
	 * @return The symbol to appear between all the inputs in this operation.
	 */
	public abstract String betweenSymbol();
	/**
	 * The symbol to appear after all the inputs in this operation. for example,
	 * in the expression "log(5)" the afterSymbol is ")". In most cases, the
	 * default value of an empty string should work fine.
	 * 
	 * @return The symbol to appear after all the inputs in this operation.
	 */
	public String afterSymbol() {return "";}
	
	/**
	 * The actual results of running the operation. This method should be very
	 * simple. Throw an ArithemticException if the inputs are invalid in some way.
	 * 
	 * @param inputs all the inputs for this operation
	 * @return the result of this operation.
	 */
	public abstract double evaluate(double... inputs) throws ArithmeticException;
}
