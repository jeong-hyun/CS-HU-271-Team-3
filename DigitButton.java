import java.awt.*;
import javax.swing.*;

/**
 * Represents a Digit Button
 * @author Akira Enderle
 */
public class DigitButton extends CalculatorButton {
	/**
	 * Constructor: Creates a DigitButton
	 */
	DigitButton(JLabel valueLabel, JLabel exceptionLabel, CalculatorState calcState, String valueToPush, String labelOnButton) {
		super(valueLabel, exceptionLabel, calcState, valueToPush, labelOnButton);
	}
	/**
	 * Call pushDigit() from the calcState
	 * @param valueToPush Push the digit value unique to this button to the Calculator State
	 */
	void pushValue(String valueToPush) throws OperationNotFoundException {
		getCalcState().pushDigit(valueToPush);
	}
}
