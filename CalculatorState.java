/**
 * Keeps track of the calculator's state. Interact with it by calling one of the
 * void methods. This is meant to accept inputs mimicking (or actually from) a
 * GUI. Depending on whether or not order of operations is implemented, The
 * state can be taken with getValue() or getExpression(). Classes that extend
 * this interface are meant to act as the back-end for a calculator.
 * 
 * @author Samuel Lieberman
 */
public interface CalculatorState {
	/**
	 * Add a part of a number to the current number. Could be any of the 10 digits,
	 * could be a decimal place, or it could be a negative symbol. Planned symbols:
	 * "0","1","2","3","4","5","6","7","8","9","." This won't be used until later in
	 * the project.
	 * 
	 * @param digit the character to add to the number.
	 * @throws InputOrderException           if a digit can't be added to the number
	 *                                       at this point.
	 * @throws UnsupportedOperationException if this method hasn't been implemented
	 *                                       yet.
	 */
	public void pushDigit(String digit) throws InputOrderException, UnsupportedOperationException;

	/**
	 * Add a number to the current expression being evaluated. Must be a valid
	 * double.
	 * 
	 * @param number the number to add to the expression
	 * @throws InputOrderException   if a number can't be added to the expression at
	 *                               this point.
	 * @throws NumberFormatException if the number isn't a valid double.
	 */
	public void pushNumber(String number) throws InputOrderException, NumberFormatException;

	/**
	 * Add a "control" symbol to the expression. The only control symbols are "("
	 * and ")".
	 * 
	 * @param control the "control symbol" to be added to the expression
	 * @throws InputOrderException           if a control symbol can't be added to
	 *                                       the expression at this point
	 * @throws UnsupportedOperationException if this method hasn't been added yet.
	 */
	public void pushControl(String control) throws InputOrderException, UnsupportedOperationException;

	/**
	 * Add an operator to the current expression being evaluated. Must be a valid
	 * operator's beforeSymbol() or betweenSymbol().
	 * 
	 * @param operator the operator to add to the expression
	 * @throws InputOrderException        if an operator can't be added to the
	 *                                    expression at this point.
	 * @throws OperationNotFoundException if the number isn't a valid
	 *                                    operator.beforeSymbol() or
	 *                                    operator.betweenSymbol()
	 */
	public void pushOperator(String operator) throws InputOrderException, OperationNotFoundException;

	/**
	 * Gets the current numerical value that is being evaluated. This should be used
	 * early in the project to get what to display after each operation is executed.
	 * 
	 * @return The value currently evaluated
	 * @throws UnsupportedOperationException if we have moved on to evaluating
	 *                                       entire expressions.
	 */
	public double getValue() throws UnsupportedOperationException;

	/**
	 * Returns the entire expression that has been constructed so far.
	 * 
	 * @return the entire expression that has been constructed so far.
	 * @throws UnsupportedOperationException if we haven't added this functionality
	 *                                       yet.
	 */
	public String getExpression() throws UnsupportedOperationException;
}
