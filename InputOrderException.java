/**
 * For if the methods of some class are called in the wrong order.
 * cannot [inputType] at this point in time, [reason]
 * 
 * @author Samuel Lieberman
 *
 */
public class InputOrderException extends Exception {
	public InputOrderException(String inputType, String reason) {
		super("cannot " + inputType + " at this point in time, " + reason);
	}
}
