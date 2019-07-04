import java.awt.*;
import javax.swing.*;

/**
 * Represents a Control Button
 * @author Akira Enderle
 */
public class ControlButton extends CalculatorButton {
	/**
	 * Constructor: Creates a ControlButton
	 */
	ControlButton(JLabel valueLabel, JLabel exceptionLabel, CalculatorState calcState, String valueToPush, String labelOnButton) {
		super(valueLabel, exceptionLabel, calcState, valueToPush, labelOnButton);
	}
	/**
	 * Call pushControl() from the calcState
	 * @param valueToPush Push the control value unique to this button to the Calculator State
	 */
	void pushValue(String valueToPush) throws OperationNotFoundException {
		getCalcState().pushControl(valueToPush);
	}
}
