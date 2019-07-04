import java.awt.*;
import javax.swing.*;

/**
 * Represents an Operator Button
 * @author Akira Enderle
 */
public class OperatorButton extends CalculatorButton {
	/**
	 * Constructor: Creates an OperatorButton
	 */
	OperatorButton(JLabel valueLabel, JLabel exceptionLabel, CalculatorState calcState, String valueToPush, String labelOnButton) {
		super(valueLabel, exceptionLabel, calcState, valueToPush, labelOnButton);
	}
	/**
	 * Call pushOperator() from the calcState
	 * @param valueToPush Push the operator value unique to this button to the Calculator State
	 */
	void pushValue(String valueToPush) throws OperationNotFoundException {
		getCalcState().pushOperator(valueToPush);
	}
}
