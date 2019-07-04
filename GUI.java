import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jeong-Hyun Boo
 *
 */
public class GUI extends JFrame{
	public GUI() 
	{
		super("Calculator");
		
		CalculatorState calcState = new SimpleCalcState();
		
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout_X = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);
		
		setLayout(boxLayout_X);
		
		JPanel leftPanel = new JPanel();
		
		JPanel rightPanel = new JPanel();
		JPanel digitPanel = new JPanel();
		JPanel operationPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		JLabel exceptionLabel = new JLabel("exception");
		JLabel valueLabel = new JLabel("value");
		
		Box box = Box.createVerticalBox();
		
		add(leftPanel);
		add(box.createHorizontalStrut(50));
		add(rightPanel);
		
		BoxLayout boxLayout_left = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(boxLayout_left);
		
		BoxLayout boxLayout_right = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		
		rightPanel.setLayout(boxLayout_right);
	
		Box box1 = Box.createHorizontalBox();
		leftPanel.add(exceptionLabel);
		leftPanel.add(box1.createHorizontalStrut(2));
		leftPanel.add(digitPanel);
		
		rightPanel.add(valueLabel);
		rightPanel.add(box1.createHorizontalStrut(2));
		rightPanel.add(operationPanel);
		rightPanel.add(box1.createHorizontalStrut(1));
		rightPanel.add(controlPanel);
		
		pack();
		setVisible(true);
		
		digitPanel.setLayout(new GridLayout(4, 3, 10, 10));
		operationPanel.setLayout(new GridLayout(3, 5, 10, 10));
		controlPanel.setLayout(new GridLayout(1, 5, 10, 10));
		
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "7", "7"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "8", "8"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "9", "9"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "4", "4"));
		
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "5", "5"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "6", "6"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "1", "1"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "2", "2"));
		
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "3", "3"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, ".", "."));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "0", "0"));
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "+/-", "+/-"));

		
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "*", "*"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "/", "÷"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "sin", "sin"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "cos", "cos"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "tan", "tan"));
		
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "+", "+"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "-", "-"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "^", "^"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "log", "log"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "!", "!"));
		
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "sqrt", "√"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "NA", "NA"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "NA", "NA"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "NA", "NA"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "NA", "NA"));
		
		
		
		
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "=", "="));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "C", "C"));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "AC", "AC"));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "(", "("));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, ")", ")"));
		
	}

	public static void main(String[] args) {
		new GUI();
	}
}
