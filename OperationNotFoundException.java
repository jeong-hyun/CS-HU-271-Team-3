/**
 * Exception thrown when an Operator that doesn't exist is referenced.
 * MESSAGE: operator [operator] not found.
 * 
 * @author Samuel Lieberman
 *
 */
public class OperationNotFoundException extends Exception {
	private final String OPERATOR;
	
 	public OperationNotFoundException(String operator) {
		super("operator " + operator + " not found.");
		
		OPERATOR = operator;
	}
 	
 	public String getOperator() {
 		return OPERATOR;
 	}
}
