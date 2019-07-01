/**
 * For if the methods of some class are called in the wrong order.
 * MESSAGE: cannot [inputType] at this time, [reason]
 * 
 * @author Samuel Lieberman
 *
 */
public class InputOrderException extends RuntimeException {
	public InputOrderException(String inputType, String reason) {
		super("cannot " + inputType + " at this time, " + reason);
	}
}
