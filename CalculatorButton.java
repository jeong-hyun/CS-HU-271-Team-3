import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents a Calculator Button
 * @author Akira Enderle
 */
public abstract class CalculatorButton extends JButton {
	private JLabel valueLabel;
	private JLabel exceptionLabel;
	private CalculatorState calcState;
	private String valueToPush;
	private String labelOnButton;

	/**
	 * Constructor: Creates a CalculatorButton
	 * @param valueLabel This displays the standard output
	 * @param exceptionLabel This displays the standard error
	 * @param calcState It tracks the state of the Calculator
	 * @param valueToPush This is the value that the calculatorButton pushes to stdout, which can be different from valueLabel in the case of PushDigit
	 * @param labelOnButton This is the label to be displayed on the JButton
	 */
	CalculatorButton(JLabel valueLabel, JLabel exceptionLabel, CalculatorState calcState, String valueToPush, String labelOnButton) {
		super(labelOnButton);
		this.valueLabel = valueLabel;
		this.exceptionLabel = exceptionLabel;
		this.calcState = calcState;
		this.valueToPush = valueToPush;
		this.addActionListener(new clickListener() );
	}
	/**
	 * An internal class for the CalculatorButton's ActionListener
	 */
	private class clickListener implements ActionListener {
		/**
		 * The CalculatorButton's ActionListener
		 * @param e This is the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				pushValue(valueToPush);
			} catch (Exception ex) {
				exceptionLabel.setText(ex.getMessage() );
			}
				valueLabel.setText(calcState.getExpression() );
		}
	}
	/**
	 * Getter method for getting the CalculatorState to the different types of Calculator Buttons.
	 * @return Return the current Calculator State
	 */
	protected CalculatorState getCalcState() {
		return calcState;
	}
	abstract void pushValue(String valueToPush) throws OperationNotFoundException;
}
