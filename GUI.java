import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		
		//setPreferredSize(new Dimension(700, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		
		JPanel rightPanel = new JPanel();
		JPanel digitPanel = new JPanel();
		JPanel operationPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		JLabel exceptionLabel = new JLabel("");
		JLabel valueLabel = new JLabel("0");
		
		Dimension exceptionDimension = new Dimension(185, 40);
		JScrollPane exceptionContainer = new JScrollPane(exceptionLabel);
		exceptionContainer.setBackground(Color.BLUE);
		exceptionContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		exceptionContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		exceptionContainer.setPreferredSize(exceptionDimension);
		exceptionContainer.setMaximumSize(exceptionDimension);
		exceptionContainer.setMinimumSize(exceptionDimension);
		
		Dimension valueDimension = new Dimension(325, 40);
		JScrollPane valueContainer = new JScrollPane(valueLabel);
		valueContainer.setBackground(Color.green);
		valueContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		valueContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		valueContainer.setPreferredSize(valueDimension);
		valueContainer.setMaximumSize(valueDimension);
		valueContainer.setMinimumSize(valueDimension);
		
		
		getContentPane().add(leftPanel);
		getContentPane().add(Box.createHorizontalStrut(40));
		getContentPane().add(rightPanel);
		
		BoxLayout boxLayout_left = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(boxLayout_left);
		
		BoxLayout boxLayout_right = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		
		rightPanel.setLayout(boxLayout_right);
		
		leftPanel.add(exceptionContainer);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(digitPanel);
		
		rightPanel.add(valueContainer);
		rightPanel.add(Box.createVerticalStrut(20));
		rightPanel.add(operationPanel);
		rightPanel.add(Box.createVerticalStrut(20));
		rightPanel.add(controlPanel);
		
		
		//moves every component to the left side of it's container
		//meant mostly to make the labels look right
		exceptionContainer.setAlignmentX(0);
		digitPanel.setAlignmentX(0);
		valueContainer.setAlignmentX(0);
		operationPanel.setAlignmentX(0);
		controlPanel.setAlignmentX(0);
		
		
		
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
		digitPanel.add(new DigitButton(valueLabel, exceptionLabel, calcState, "-", "\u207A\u2044\u208B"));

		

		operationPanel.add(new NumberButton(valueLabel, exceptionLabel, calcState, "" + Math.PI, "\u03C0"));
		operationPanel.add(new NumberButton(valueLabel, exceptionLabel, calcState, "" + Math.E, "e"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "sqrt", "\u221A"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "%", "%"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "NA", "NA"));
		
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "*", "*"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "/", "\u00F7"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "sin", "sin"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "cos", "cos"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "tan", "tan"));
		
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "+", "+"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "-", "-"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "^", "^"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "log", "log"));
		operationPanel.add(new OperatorButton(valueLabel, exceptionLabel, calcState, "!", "!"));
		
		
		
		
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "=", "="));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "C", "C"));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "AC", "AC"));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, "(", "("));
		controlPanel.add(new ControlButton(valueLabel, exceptionLabel, calcState, ")", ")"));
		

		pack();
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
