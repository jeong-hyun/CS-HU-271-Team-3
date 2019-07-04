import java.awt.*;
import javax.swing.*;

/**
 * Represents a Number Button
 * @author Akira Enderle
 */
public class NumberButton extends CalculatorButton {
	/**
	 * Constructor: Creates a NumberButton
	 */
	NumberButton(JLabel valueLabel, JLabel exceptionLabel, CalculatorState calcState, String valueToPush, String labelOnButton) {
		super(valueLabel, exceptionLabel, calcState, valueToPush, labelOnButton);
	}
	/**
	 * Call pushNumber() from the calcState
	 * @param valueToPush Push the number value unique to this button to the Calculator State
	 */
	void pushValue(String valueToPush) throws OperationNotFoundException {
		getCalcState().pushNumber(valueToPush);
	}
}
