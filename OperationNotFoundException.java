/**
 * Exception thrown when an Operator that doesn't exist is referenced.
 * MESSAGE: operator [operator] not found.
 * 
 * @author Samuel Lieberman
 *
 */
public class OperationNotFoundException extends Exception {
	public OperationNotFoundException(String operator) {
		super("operator " + operator + " not found.");
	}
}
